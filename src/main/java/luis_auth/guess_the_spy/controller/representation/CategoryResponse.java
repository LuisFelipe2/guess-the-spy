package luis_auth.guess_the_spy.controller.representation;

import java.util.List;

public record CategoryResponse(String name, List<String> passwords){}
