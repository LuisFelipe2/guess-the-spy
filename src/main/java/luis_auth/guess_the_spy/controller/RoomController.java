package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.CategoryResponse;
import luis_auth.guess_the_spy.controller.representation.PlayerResponse;
import luis_auth.guess_the_spy.controller.representation.RoomRequest;
import luis_auth.guess_the_spy.controller.representation.RoomResponse;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private final RoomService roomService;

	@Autowired
	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@GetMapping("/{roomName}")
	@ResponseStatus(HttpStatus.OK)
	public Room getAll(@PathVariable String roomName) throws Exception {
		return roomService.getRoom(roomName);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RoomResponse createRoom(@RequestBody RoomRequest request) throws Exception {
		Room room = roomService.createRoom(request);
		List<PlayerResponse> playerResponses = room.getPlayers().stream().map(player -> new PlayerResponse(player.getName())).toList();
		return new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().getName(), room.getCategory().getPassword()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), playerResponses);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RoomResponse updateRoom(@RequestBody RoomRequest request) throws Exception {
		Room room = roomService.updateRoom(request);
		List<PlayerResponse> playerResponses = room.getPlayers().stream().map(player -> new PlayerResponse(player.getName())).toList();
		return new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().getName(), room.getCategory().getPassword()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), playerResponses);
	}

	@PostMapping("/{roomName}/{playerName}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void colocarJogadorNaSala(@PathVariable String roomName, @PathVariable String playerName)  {
		roomService.subscribePlayerOnRoom(roomName, playerName);
	}
}

