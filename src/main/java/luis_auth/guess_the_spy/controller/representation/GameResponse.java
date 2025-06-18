package luis_auth.guess_the_spy.controller.representation;

import java.util.Map;

public record GameResponse(String password, String spy, String passwordGuess, Map<String, Integer> votes) {}