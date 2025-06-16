package luis_auth.guess_the_spy.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Room {

	private final String name;
	private final String adminName;
	private final Category category;
	private final int limitTime;
	private final int minPlayers;
	private final int maxPlayers;
	private RoomStatus status;
	private final List<Player> players;
	public boolean enterPlayer(Player player) {
		if (status.equals(RoomStatus.ON_GAME) || players.size() >= maxPlayers) {
			return false;
		}

		players.add(player);
		return true;
	}

	public boolean exitPlayer(Player player) {
		if (player == null || player.name() == null || !players.contains(player)) {
			return false;
		}

		players.remove(player);
		return true;
	}

	public boolean isReady() {
		return !status.equals(RoomStatus.ON_GAME)
			&& players.size() >= minPlayers && players.size() <= maxPlayers
			&& limitTime > 0;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}
}
