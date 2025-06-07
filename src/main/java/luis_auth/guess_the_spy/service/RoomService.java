package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.controller.representation.RoomRequest;
import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.domain.Player;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.domain.RoomStatus;
import luis_auth.guess_the_spy.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoomService {
	@Autowired
	CategoryService categoryService;
	@Autowired
	RoomRepository repository;
	@Autowired
	WebSocketService webSocketService;


	public Room getRoom(String roomName) throws Exception {
		return repository.findRoomByName(roomName);
	}
	public Room createRoom(RoomRequest request) throws Exception {
		Category category = categoryService.findCategory(request.categoryName());
		Player player = new Player(request.playerName());

		Room room = new Room(request.name(), category, request.limitTime(), request.minPlayers(), request.maxPlayers(),
			RoomStatus.ON_LOBBY, new ArrayList<>(Collections.singleton(player)), player.getName());
		repository.create(room);
		webSocketService.updateRoom(room);
		return room;
	}

	public Room updateRoom(RoomRequest request) throws Exception {
		Room room = repository.findRoomByName(request.name());

		if (room.getStatus().equals(RoomStatus.ON_LOBBY)
			&& room.getAdminName().equals(request.playerName())
		) {
			room.setMinPlayers(request.minPlayers());
			room.setMaxPlayers(request.maxPlayers());
			room.setLimitTime(request.limitTime());

			if (!room.getCategory().getName().equals(request.categoryName())) {
				room.setCategory(categoryService.findCategory(request.categoryName()));
			}
			webSocketService.updateRoom(room);
		}

		return room;
	}

	public void subscribePlayerOnRoom(String roomName, String playerName) {
		Room room = repository.findRoomByName(roomName);

		if (!room.getStatus().equals(RoomStatus.ON_LOBBY)
			|| room.getPlayers().size() >= room.getMaxPlayers()
			|| room.getPlayers().stream().anyMatch(player -> player.getName().equals(playerName))
		) {
			throw new RuntimeException("Sala cheia, partida em andamento ou usuário ja adicionado");
		}

		Player player = new Player(playerName);
		player.setName(playerName);
		room.getPlayers().add(player);

		webSocketService.updateRoom(room);
	}
}
