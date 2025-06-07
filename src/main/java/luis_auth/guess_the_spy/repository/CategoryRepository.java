package luis_auth.guess_the_spy.repository;

import luis_auth.guess_the_spy.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CategoryRepository {
	List<Category> categories = new ArrayList<>();
	public Category findByName(String categoryName) {
		return categories.stream()
			.filter(c -> Objects.equals(c.getName(), categoryName))
			.findFirst()
			.orElse(null);
	}

	public Category create(Category category) {
		categories.add(category);
		return category;
	}
}
