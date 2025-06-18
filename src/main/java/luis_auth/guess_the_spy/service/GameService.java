package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.domain.Player;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.domain.RoomStatus;
import luis_auth.guess_the_spy.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static luis_auth.guess_the_spy.domain.RoomStatus.END_GAME;

@Service
public class GameService {

	public static final Random RANDOM = new Random();
	@Autowired
	private RoomService roomService;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private WebSocketService webSocketService;

	public void startGame(String roomName, String playerName) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (!room.getAdminName().equals(playerName) || !room.isReady()) {
			throw new Exception("Game not ready for start");
		}

		room.setStatus(RoomStatus.ON_GAME);

		List<String> passwords = room.getCategory().password();
		List<Player> players = room.getPlayers();

		Game game = new Game(roomName, passwords.get(RANDOM.nextInt(passwords.size())),
			players.get(RANDOM.nextInt(players.size())), new ArrayList<>(), new HashMap<>()
		);

		gameRepository.saveGame(game);
		webSocketService.updateRoom(room);
		webSocketService.updateGame(game);
	}

	public void vote(String roomName, String playerName, String otherPlayer) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (room.getPlayers().stream().noneMatch(player -> player.name().equals(playerName))
			|| room.getPlayers().stream().noneMatch(player -> player.name().equals(otherPlayer))
		) {
			throw new RuntimeException("Algum dos jogadores informados não existe");
		}

		Game game = gameRepository.findGameByRoom(roomName);
		int votes = game.votes().getOrDefault(otherPlayer, 0);
		game.votes().put(otherPlayer, ++votes);
		webSocketService.updateGame(game);
	}

	public void guess(String roomName, String playerName, String password) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (room.getPlayers().stream().anyMatch(player -> player.name().equals(playerName))) {
			Game game = gameRepository.findGameByRoom(roomName);
			if (game.spy().name().equals(playerName)) {
				game.passwordGuess().add(password);
				room.setStatus(END_GAME);
				webSocketService.updateRoom(room);
				webSocketService.updateGame(game);
			}
		} else {
			throw new RuntimeException("Algum dos jogadores informados não existe");
		}
	}

	public void sendMessage(String roomName, String playerName, String messageText) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (room.getPlayers().stream().anyMatch(player -> player.name().equals(playerName))
			&& messageText != null && !messageText.isEmpty()
		) {
			Message message = new Message(playerName, messageText);
			webSocketService.sendChatMenssage(roomName, message);
		}
	}

	public void endGame(String roomName) throws Exception {
		Room room = roomService.getRoom(roomName);
		room.setStatus(END_GAME);
		webSocketService.updateRoom(room);
	}

	public Game getGame(String roomName) {
		return gameRepository.findGameByRoom(roomName);
	}
}
