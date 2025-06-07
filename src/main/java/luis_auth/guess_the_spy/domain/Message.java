package luis_auth.guess_the_spy.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	String name;
	String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}