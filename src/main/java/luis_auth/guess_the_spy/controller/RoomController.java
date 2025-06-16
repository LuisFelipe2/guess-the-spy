package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.CategoryResponse;
import luis_auth.guess_the_spy.controller.representation.PlayerResponse;
import luis_auth.guess_the_spy.controller.representation.RoomRequest;
import luis_auth.guess_the_spy.controller.representation.RoomResponse;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<RoomResponse> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		return rooms.stream().map(room -> new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().name(), room.getCategory().password()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), room.getPlayers().stream()
				.map(player -> new PlayerResponse(player.name())).toList()))
			.collect(Collectors.toList());
	}

	@GetMapping("/{roomName}")
	@ResponseStatus(HttpStatus.OK)
	public RoomResponse getAll(@PathVariable String roomName) throws Exception {
		Room room = roomService.getRoom(roomName);
		List<PlayerResponse> playerResponses = room.getPlayers().stream().map(player -> new PlayerResponse(player.name())).toList();
		return new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().name(), room.getCategory().password()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), playerResponses);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RoomResponse createRoom(@RequestBody RoomRequest request) throws Exception {
		Room room = roomService.createRoom(request);
		List<PlayerResponse> playerResponses = room.getPlayers().stream().map(player -> new PlayerResponse(player.name())).toList();
		return new RoomResponse(room.getName(),
			new CategoryResponse(room.getCategory().name(), room.getCategory().password()),
			room.getLimitTime(), room.getMinPlayers(), room.getMaxPlayers(), playerResponses);
	}

	@PostMapping("/{roomName}/{playerName}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void colocarJogadorNaSala(@PathVariable String roomName, @PathVariable String playerName)  {
		roomService.subscribePlayerOnRoom(roomName, playerName);
	}
}

