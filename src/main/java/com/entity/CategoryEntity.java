package com.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class CategoryEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String categoryid;
	private String title;
	@Column(length = 1500)
	private String description;
	
	@OneToMany(mappedBy = "category",  fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<QuizEntity> manyQuizzes = new LinkedHashSet<>();//for ordering 
	
	public CategoryEntity() {
		super();
		
	}
	public CategoryEntity(String categoryid, String title, String description) {
		super();
		this.categoryid = categoryid;
		this.title = title;
		this.description = description;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setManyQuizzes(Set<QuizEntity> manyQuizzes) {
		this.manyQuizzes = manyQuizzes;
	}
	public Set<QuizEntity> getManyQuizzes(){
		return this.manyQuizzes;
	}
	
}
