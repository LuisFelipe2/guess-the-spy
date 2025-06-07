package luis_auth.guess_the_spy.controller.representation;

import java.util.List;

public record CategoryRequest(String name, List<String> passwords) {}
