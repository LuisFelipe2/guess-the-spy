package luis_auth.guess_the_spy.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Game {

	private String password;
	private Player spy;
	private LocalDateTime startTime;

	public Game startGame(Room room) throws Exception {
		if (room.isReady()) {
			Random random = new Random();
			List<String> passwords = room.getCategory().getPassword();
			List<Player> players = room.getPlayers();
			password = passwords.get(random.nextInt(passwords.size()));
			spy = players.get(random.nextInt(players.size()));
			room.setStatus(RoomStatus.ON_GAME);
			startTime = LocalDateTime.now();
			return this;
		} else {
			throw new Exception("Game not ready for start");
		}
	}
}
