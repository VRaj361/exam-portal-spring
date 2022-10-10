package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CategoryEntity;
import com.entity.CustomResponse;
import com.entity.QuizEntity;
import com.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService cateService;
	
	@PostMapping("/")
	public CustomResponse<?> addCategory(@RequestBody CategoryEntity category){
		CategoryEntity cate=this.cateService.addCategory(category);
		if(cate == null) {
			return new CustomResponse<CategoryEntity>(400,"Duplicate Category Found",cate);
		}else {
			return new CustomResponse<CategoryEntity>(200,"Category Add Successfully",cate);
		}
	}
	
	@GetMapping("/particular")
	public CustomResponse<?> getCategory(@RequestHeader("categoryid") String id){
		CategoryEntity cate = this.cateService.getCategory(id);
		if(cate == null) {
			return new CustomResponse<CategoryEntity>(404,"Category not found",cate);
		}else {
			return new CustomResponse<CategoryEntity>(200,"Category found",cate);
		}
	}
	
	@GetMapping("/")
	public CustomResponse<?> getCategories(){
		LinkedHashSet<CategoryEntity> cate = this.cateService.getCategories();
		ArrayList<CategoryEntity> arr = new ArrayList<>(cate);
		Collections.sort(arr, new Comparator<CategoryEntity>() {
			public int compare(CategoryEntity o1, CategoryEntity o2) {
				return o1.getTitle().charAt(0) - o2.getTitle().charAt(0);
			}
		});
		return new CustomResponse<ArrayList<CategoryEntity>>(200,"Categories found",arr);
	}
	
	@PutMapping("/")
	public CustomResponse<?> updateCategory(@RequestBody CategoryEntity category){
		CategoryEntity cate = this.cateService.updateCategory(category);
		if(cate == null) {
			return new CustomResponse<>(404,"Category not found",cate);
		}else {
			return new CustomResponse<>(200,"Category Update Successfully",cate);
		}
	}
	
	@DeleteMapping("/")
	public void deleteCategory(@RequestHeader("categoryid") String id) {
		this.cateService.deleteCategory(id);
	}
	
	
	
}
