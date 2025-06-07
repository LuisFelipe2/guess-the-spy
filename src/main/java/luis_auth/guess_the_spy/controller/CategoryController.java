package luis_auth.guess_the_spy.controller;

import luis_auth.guess_the_spy.controller.representation.CategoryRequest;
import luis_auth.guess_the_spy.controller.representation.CategoryResponse;
import luis_auth.guess_the_spy.controller.representation.RoomRequest;
import luis_auth.guess_the_spy.controller.representation.RoomResponse;
import luis_auth.guess_the_spy.domain.Category;
import luis_auth.guess_the_spy.domain.Room;
import luis_auth.guess_the_spy.service.CategoryService;
import luis_auth.guess_the_spy.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping("/{categoryName}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponse get(@PathVariable String categoryName) throws Exception {
		Category category = categoryService.findCategory(categoryName);
		return new CategoryResponse(category.getName(), category.getPassword());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponse create(@RequestBody CategoryRequest request) throws Exception {
		Category category = categoryService.create(request);
		return new CategoryResponse(category.getName(), category.getPassword());
	}
}

