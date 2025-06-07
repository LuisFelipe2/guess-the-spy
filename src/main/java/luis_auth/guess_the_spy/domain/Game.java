package luis_auth.guess_the_spy.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game {

	private Room room;
	private String password;
	private Player spy;
	private LocalDateTime startTime;

	private Map<String, Integer> votes = new HashMap<>();

	public Game() {
	}

	public void startGame(Room room) throws Exception {
		if (room.isReady()) {
			Random random = new Random();
			List<String> passwords = room.getCategory().getPassword();
			List<Player> players = room.getPlayers();
			password = passwords.get(random.nextInt(passwords.size()));
			spy = players.get(random.nextInt(players.size()));
			room.setStatus(RoomStatus.ON_GAME);
			startTime = LocalDateTime.now();
			this.room = room;
		} else {
			throw new Exception("Game not ready for start");
		}
	}

	public Room getRoom() {
		return room;
	}

	public Map<String, Integer> getVotes() {
		return votes;
	}

	public void setVotes(Map<String, Integer> votes) {
		this.votes = votes;
	}

	public String getPassword() {
		return password;
	}

	public Player getSpy() {
		return spy;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}
}
