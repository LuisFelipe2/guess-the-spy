package luis_auth.guess_the_spy.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Category {

	private String name;
	private List<String> password;

	public String getName() {
		return name;
	}

	public List<String> getPassword() {
		return password;
	}
}
