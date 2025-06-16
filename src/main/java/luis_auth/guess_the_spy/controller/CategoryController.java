package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.CategoryRequest;
import luis_auth.guess_the_spy.controller.representation.CategoryResponse;
import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<CategoryResponse> getAll() {
		List<Category> categories = categoryService.getAll();
		return categories.stream()
			.map(category -> new CategoryResponse(category.name(), category.password()))
			.collect(Collectors.toList());
	}

	@GetMapping("/{categoryName}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse get(@PathVariable String categoryName) throws Exception {
		Category category = categoryService.findCategory(categoryName);
		return new CategoryResponse(category.name(), category.password());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse create(@RequestBody CategoryRequest request) {
		Category category = categoryService.create(request);
		return new CategoryResponse(category.getName(), category.getPassword());
	}
}

