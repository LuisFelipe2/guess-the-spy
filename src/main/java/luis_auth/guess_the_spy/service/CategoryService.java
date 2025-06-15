package luis_auth.guess_the_spy.service;

import luis_auth.guess_the_spy.controller.representation.CategoryRequest;
import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	public Category findCategory(String categoryName) throws Exception {
		Category category = categoryRepository.findByName(categoryName);
		if (category == null) {
			throw new Exception("Categoria não encontrada");
		}
		return category;
	}

	public Category create(CategoryRequest request) {
		Category category = new Category(request.name(), request.passwords());
		return categoryRepository.create(category);
	}

	public List<Category> getAll() {
		return categoryRepository.getAll();
	}
}
