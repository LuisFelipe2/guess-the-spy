package luis_auth.guess_the_spy.controller.representation;

public record RoomRequest (String name, String categoryName, int limitTime, int minPlayers, int maxPlayers,
                           String playerName){
}
