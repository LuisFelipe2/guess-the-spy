package luis_auth.guess_the_spy.domain;

import java.util.List;

public record Category(String name, List<String> password) {
}