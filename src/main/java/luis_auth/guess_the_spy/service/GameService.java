package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Message;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

	@Autowired
	private RoomService roomService;

	@Autowired
	private GameRepository gameService;
	@Autowired
	private WebSocketService webSocketService;

	public void startGame(String roomName, String playerName) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (room.getAdminName().equals(playerName)
			&& room.getPlayers().stream().anyMatch(player -> player.getName().equals(playerName))
		) {
			Game game = new Game();
			game.startGame(room);
			gameService.saveGame(game);
			webSocketService.updateGame(game);
		}
	}

	public void vote(String roomName, String playerName, String otherPlayer) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (room.getPlayers().stream().anyMatch(player -> player.getName().equals(playerName))
			&& room.getPlayers().stream().anyMatch(player -> player.getName().equals(otherPlayer))
		) {
			Game game = gameService.findGameByRoom(roomName);
			int votes = game.getVotes().getOrDefault(otherPlayer, 0);
			game.getVotes().put(otherPlayer, ++votes);
			webSocketService.updateGame(game);
		} else {
			throw new RuntimeException("Algum dos jogadores informados não existe");
		}
	}

	public void sendMessage(String roomName, String playerName, String messageText) throws Exception {
		Room room = roomService.getRoom(roomName);

		if (room.getAdminName().equals(playerName)
			&& room.getPlayers().stream().anyMatch(player -> player.getName().equals(playerName))
			&& messageText != null && !messageText.isEmpty()
		) {
			Message message = new Message();
			message.setContent(messageText);
			message.setName(playerName);

			room.getHistoryMessages().add(message);
			webSocketService.sendChatMenssage(roomName, message);
		}
	}

	public Game getGame(String roomName) {
		return gameService.findGameByRoom(roomName);
	}
}
