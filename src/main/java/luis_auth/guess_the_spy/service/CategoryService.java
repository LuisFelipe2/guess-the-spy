package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.repository.CategoryRepository;

public class CategoryService {

	private CategoryRepository categoryRepository;
	public Category findCategory(String categoryName) throws Exception {
		Category category = categoryRepository.findByName(categoryName);
		if (category == null) {
			throw new Exception("Categoria não encontrada");
		}
		return category;
	}
}
