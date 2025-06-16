package luis_auth.guess_the_spy.repository;

import luis_auth.guess_the_spy.domain.Game;
import luis_auth.guess_the_spy.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GameRepository {

	private final List<Game> games = new ArrayList<>();

	public Game findGameByRoom(String name)  {
		return games.stream()
			.filter(game -> game.roomName().equals(name))
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Sala não existe"));
	}

	public void saveGame(Game game) {
		if(games.contains(game)) {
			throw new RuntimeException("Game já existe");
		}
		games.add(game);
	}
}
