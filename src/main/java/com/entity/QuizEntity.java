package com.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "quizzes")
public class QuizEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String quizid;
	private String title;
	@Column(length = 1500)
	private String description;
	private int maxMarks;
	private int numberOfQuestions;
	private int timeOfQuiz;
	private boolean isActive = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private CategoryEntity category;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<QuestionEntity> manyQuestions = new HashSet<>();
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "quiz")
	@JsonIgnore
	private Set<AttemptedQuizEntity> attemptions = new HashSet<AttemptedQuizEntity>();
	
	
	
	public QuizEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuizEntity(String quizid, String title, String description, int maxMarks, int numberOfQuestions,
			boolean isActive,CategoryEntity category,int timeOfQuiz) {
		super();
		this.quizid = quizid;
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberOfQuestions = numberOfQuestions;
		this.isActive = isActive;
		this.category = category;
		this.timeOfQuiz = timeOfQuiz;
	}

	
	
	public int getTimeOfQuiz() {
		return timeOfQuiz;
	}

	public void setTimeOfQuiz(int timeOfQuiz) {
		this.timeOfQuiz = timeOfQuiz;
	}

	public String getQuizid() {
		return quizid;
	}

	public void setQuizid(String quizid) {
		this.quizid = quizid;
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

	public int getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(int maxMarks) {
		this.maxMarks = maxMarks;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public void setCategory(CategoryEntity category) {
		this.category = category;
	}
	
	public CategoryEntity getCategory() {
		return this.category;
	}

	public Set<QuestionEntity> getManyQuestions() {
		return manyQuestions;
	}

	public void setManyQuestions(Set<QuestionEntity> manyQuestions) {
		this.manyQuestions = manyQuestions;
	}
	
}
