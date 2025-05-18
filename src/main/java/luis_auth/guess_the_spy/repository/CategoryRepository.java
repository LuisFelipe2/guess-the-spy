package luis_auth.guess_the_spy.repository;

import luis_auth.guess_the_spy.domain.Category;

import java.util.List;
import java.util.Objects;

public class CategoryRepository {
	List<Category> category;
	public Category findByName(String categoryName) {
		return category.stream()
			.filter(c -> Objects.equals(c.getName(), categoryName))
			.findFirst()
			.orElse(null);
	}
}
