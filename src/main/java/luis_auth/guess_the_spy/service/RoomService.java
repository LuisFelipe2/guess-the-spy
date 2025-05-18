package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.controller.representation.RoomRequest;
import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.domain.RoomStatus;
import luis_auth.guess_the_spy.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RoomService {
	CategoryService categoryService;
	RoomRepository repository;

	public Room createRoom(RoomRequest request) throws Exception {
		Category category = categoryService.findCategory(request.categoryName());
		return new Room(request.name(), category, request.limitTime(), request.minPlayers(), request.maxPlayers(), RoomStatus.CREATED, new ArrayList<>());
	}

	public Room updateRoom(RoomRequest request) throws Exception {
		Room room = repository.findRoomByName(request.name());

		if (room.getStatus().equals(RoomStatus.ON_LOBBY)) {
			room.setMinPlayers(request.minPlayers());
			room.setMaxPlayers(request.maxPlayers());
			room.setLimitTime(request.limitTime());

			if (!room.getCategory().getName().equals(request.categoryName())) {
				room.setCategory(categoryService.findCategory(request.categoryName()));
			}
		}

		return room;
	}
}
