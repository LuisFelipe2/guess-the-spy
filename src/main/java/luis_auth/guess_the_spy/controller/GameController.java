package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.MessageRequest;
import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	private GameService gameService;


	@GetMapping("/{roomName}")
	@ResponseStatus(HttpStatus.OK)
	public Game debugGame(@PathVariable String roomName) throws Exception {
		return gameService.getGame(roomName);
	}

	@PostMapping("/{roomName}/{playerName}")
	@ResponseStatus(HttpStatus.CREATED)
	public void startGame(@PathVariable String roomName, @PathVariable String playerName) throws Exception {
		gameService.startGame(roomName, playerName);
	}

	@PostMapping("/{roomName}/{playerName}/vote/{otherPlayer}")
	@ResponseStatus(HttpStatus.CREATED)
	public void vote(@PathVariable String roomName, @PathVariable String playerName, @PathVariable String otherPlayer) throws Exception {
		gameService.vote(roomName, playerName, otherPlayer);
	}

	@PostMapping("/{roomName}/{playerName}/message")
	@ResponseStatus(HttpStatus.CREATED)
	public void chatMessage(@PathVariable String roomName, @PathVariable String playerName, @RequestBody MessageRequest message) throws Exception {
		gameService.sendMessage(roomName, playerName, message.getValue());
	}
}
