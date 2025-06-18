package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.GameResponse;
import luis_auth.guess_the_spy.controller.representation.MessageRequest;
import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping("/{roomName}")
	@ResponseStatus(HttpStatus.OK)
	public GameResponse getGame(@PathVariable String roomName) throws Exception {
		Game game = gameService.getGame(roomName);
		return new GameResponse(game.password(), game.spy().name(), game.passwordGuess().stream().findFirst().orElse(""), game.votes());
	}

	@PostMapping("/{roomName}")
	@ResponseStatus(HttpStatus.CREATED)
	public void endGame(@PathVariable String roomName) throws Exception {
		gameService.endGame(roomName);
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

	@PostMapping("/{roomName}/{playerName}/guess/{password}")
	@ResponseStatus(HttpStatus.CREATED)
	public void guess(@PathVariable String roomName, @PathVariable String playerName, @PathVariable String password) throws Exception {
		gameService.guess(roomName, playerName, password);
	}

	@PostMapping("/{roomName}/{playerName}/message")
	@ResponseStatus(HttpStatus.CREATED)
	public void chatMessage(@PathVariable String roomName, @PathVariable String playerName, @RequestBody MessageRequest message) throws Exception {
		gameService.sendMessage(roomName, playerName, message.value());
	}
}
