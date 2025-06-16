package luis_auth.guess_the_spy.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record Game (String roomName, String password, Player spy, List<String> passwordGuess, LocalDateTime endsAt,
                    Map<String, Integer> votes) {
}
