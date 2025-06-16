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

	public List<Room> getAllRooms() {
		return repository.getAll();
	}

	public Room getRoom(String roomName) throws Exception {
		return repository.findRoomByName(roomName);
	}
	public Room createRoom(RoomRequest request) throws Exception {
		Category category = categoryService.findCategory(request.categoryName());
		Player player = new Player(request.playerName());

		Room room = new Room(request.name(), player.name(), category, request.limitTime(), request.minPlayers(),
			request.maxPlayers(), RoomStatus.ON_LOBBY, new ArrayList<>(Collections.singleton(player)));
		repository.create(room);
		webSocketService.updateRoom(room);
		return room;
	}

	public void subscribePlayerOnRoom(String roomName, String playerName) {
		Room room = repository.findRoomByName(roomName);

		if (room.enterPlayer(new Player(playerName)))
			webSocketService.updateRoom(room);
	}
}
