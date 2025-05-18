package luis_auth.guess_the_spy.domain;

import java.util.List;

public class Room {

	private String name;
	private Category category;
	private int limitTime;
	private int minPlayers;
	private int maxPlayers;
	private RoomStatus status;
	private List<Player> players;

	public Room(String name, Category category, int limitTime, int minPlayers, int maxPlayers, RoomStatus status, List<Player> players) {
		this.name = name;
		this.category = category;
		this.limitTime = limitTime;
		this.minPlayers = minPlayers;
		this.maxPlayers = maxPlayers;
		this.status = status;
		this.players = players;
	}

	public boolean enterPlayer(Player player) throws Exception {
		if (status.equals(RoomStatus.ON_GAME)) {
			throw new Exception("Partida em andamento");
		}
		if (player == null || player.getName() == null) {
			throw new Exception("Player não informado");
		}
		if (players.size() == maxPlayers) {
			throw new Exception("Sala cheia");
		}

		if (players.contains(player)) {
			player.setName(player.getName());
		}

		player.setRoom(this);
		return true;
	}

	public boolean exitPlayer(Player player) throws Exception {
		if (player == null || player.getName() == null) {
			throw new Exception("Player não informado");
		}

		players.remove(player);
		player.setRoom(null);
		return true;
	}

	public boolean isReady() throws Exception {
		if (status.equals(RoomStatus.ON_GAME)) {
			//throw new Exception("Partida em andamento");
			return false;
		}
		if (players.size() < minPlayers && players.size() > maxPlayers) {
//			throw new Exception("Partida em andamento");
			return false;
		}
		if (category == null) {
//			throw new Exception("Categoria não preenchida");
			return false;
		}

		if (limitTime < 0) {
//			throw new Exception("limite de tempo não configurado");
			return false;
		}

		return true;
	}

	public String getName() {
		return name;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}
}
