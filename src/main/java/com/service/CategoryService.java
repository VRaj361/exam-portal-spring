package com.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.entity.CategoryEntity;
import com.entity.UserEntity;
import com.repository.CategoryRepository;

@Controller
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	public CategoryEntity addCategory(CategoryEntity category) {
		CategoryEntity cate = categoryRepo.findByTitle(category.getTitle());
		if(cate != null) {
			return null;
		}else {			
			return this.categoryRepo.save(category);
		}
	}

	public CategoryEntity updateCategory(CategoryEntity category) {
		CategoryEntity cate = categoryRepo.findByTitle(category.getTitle());
		cate.setDescription(category.getDescription());
		return this.categoryRepo.save(cate);
	}

	public LinkedHashSet<CategoryEntity> getCategories() {
		return new LinkedHashSet(this.categoryRepo.findAll());
	}

	public CategoryEntity getCategory(String id) {
		return this.categoryRepo.findById(id).get();
	}

	public void deleteCategory(String id) {
//		CategoryEntity category = new CategoryEntity();
//		category.setCategoryid(id);
		
//		this.categoryRepo.delete(category);
		this.categoryRepo.deleteById(id);

	}

}
