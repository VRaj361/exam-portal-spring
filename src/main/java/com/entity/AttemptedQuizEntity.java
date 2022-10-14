package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="attemptions")
public class AttemptedQuizEntity {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "uuid2")
	private String attemptid;
	@Column(length = 4000000)
	private String content;
	
	private int attemptQuestions;
	private int correctAnswers;
	private int totalMarks;
	private float percentage ; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private QuizEntity quiz;
	

	public AttemptedQuizEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AttemptedQuizEntity( String content, int attemptQuestions, int correctAnswers,
			int totalMarks, float percentage, UserEntity user, QuizEntity quiz) {
		super();
		
		this.content = content;
		this.attemptQuestions = attemptQuestions;
		this.correctAnswers = correctAnswers;
		this.totalMarks = totalMarks;
		this.percentage = percentage;
		this.user = user;
		this.quiz = quiz;
	}

	public int getAttemptQuestions() {
		return attemptQuestions;
	}

	public void setAttemptQuestions(int attemptQuestions) {
		this.attemptQuestions = attemptQuestions;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}



	public String getAttemptid() {
		return attemptid;
	}

	public void setAttemptid(String attemptid) {
		this.attemptid = attemptid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public QuizEntity getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizEntity quiz) {
		this.quiz = quiz;
	}
	
	
	
	
}
