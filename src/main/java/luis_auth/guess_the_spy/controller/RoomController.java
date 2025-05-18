package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.CategoryResponse;
import luis_auth.guess_the_spy.controller.representation.RoomRequest;
import luis_auth.guess_the_spy.controller.representation.RoomResponse;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private final RoomService roomService;

	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping
	public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest request) throws Exception {
		Room room = roomService.createRoom(request);
		RoomResponse roomResponse = new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().getName(), room.getCategory().getPassword()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), new ArrayList<>());
		return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<RoomResponse> updateRoom(@RequestBody RoomRequest request) throws Exception {
		Room room = roomService.updateRoom(request);
		RoomResponse roomResponse = new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().getName(), room.getCategory().getPassword()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), new ArrayList<>());
		return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);	}
}

