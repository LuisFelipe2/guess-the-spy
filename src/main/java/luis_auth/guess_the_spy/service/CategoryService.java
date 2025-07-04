package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.controller.representation.CategoryRequest;
import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findCategory(String categoryName) {
		return categoryRepository.findByName(categoryName);
	}

	public Category create(CategoryRequest request) {
		Category category = new Category(request.name(), request.passwords());
		return categoryRepository.create(category);
	}

	public List<Category> getAll() {
		return categoryRepository.getAll();
	}
}
