package luis_auth.guess_the_spy.controller.representation;

import java.util.List;

public record RoomResponse (String name, CategoryResponse categoryName, int limitTime, int minPlayers, int maxPlayers,
                            List<PlayerResponse> players){
}
