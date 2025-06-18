package luis_auth.guess_the_spy.domain;

import java.util.List;

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

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getAdminName() {
		return adminName;
	}

	public Category getCategory() {
		return category;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Room(String name, String adminName, Category category, int limitTime, int minPlayers, int maxPlayers, RoomStatus status, List<Player> players) {
		this.name = name;
		this.adminName = adminName;
		this.category = category;
		this.limitTime = limitTime;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.status = status;
		this.players = players;
	}
}
