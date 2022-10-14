package com.service;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.entity.QuizEntity;
import com.repository.QuizRepository;

@Controller
public class QuizService {

	@Autowired
	private QuizRepository quizRepo;
	
	public QuizEntity addQuiz(QuizEntity quiz) {
		QuizEntity quizRes = quizRepo.findByTitle(quiz.getTitle());
		if(quizRes == null) {			
			return this.quizRepo.save(quiz);
		}else {
			return null;
		}
	}
	
	public QuizEntity updateQuiz(QuizEntity quiz) {
		QuizEntity quizRes = quizRepo.findById(quiz.getQuizid()).get();
		quizRes.setTitle(quiz.getTitle());
		quizRes.setActive(quiz.isActive());
		quizRes.setDescription(quiz.getDescription());
		quizRes.setMaxMarks(quiz.getMaxMarks());
		quizRes.setNumberOfQuestions(quiz.getNumberOfQuestions());
		quizRes.setCategory(quiz.getCategory());
		quizRes.setTimeOfQuiz(quiz.getTimeOfQuiz());
		return this.quizRepo.save(quizRes);
	}
	
	public LinkedHashSet<QuizEntity> getQuizzes(){
		return new LinkedHashSet<>(this.quizRepo.findAll());
	}
	
	public QuizEntity getQuiz(String id) {
		return this.quizRepo.findById(id).get();
	}
	
	public void deleteQuiz(String id) {
//		QuizEntity quiz = new QuizEntity();
//		quiz.setQuizid(id);
//		this.quizRepo.delete(quiz);
		this.quizRepo.deleteById(id);
		
	}
}
