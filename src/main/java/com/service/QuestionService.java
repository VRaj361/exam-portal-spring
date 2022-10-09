package com.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.repository.QuestionRepository;

@Controller
public class QuestionService {
	
	@Autowired
	private QuestionRepository quesRepo;
	
	public QuestionEntity addQuestion(QuestionEntity question) {
		QuestionEntity ques = this.quesRepo.findByContent(question.getContent());
		if(ques == null) {			
			return this.quesRepo.save(question);
		}else {
			return null;
		}
	}
	
	public QuestionEntity updateQuestion(QuestionEntity question) {
		QuestionEntity ques = this.quesRepo.findByContent(question.getContent());
		if(ques == null) {
			return null;
		}else {
			ques.setAnswer(question.getAnswer());
			ques.setOption1(ques.getOption1());
			ques.setOption2(ques.getOption2());
			ques.setOption3(ques.getOption3());
			ques.setOption4(ques.getOption4());
			
			return this.quesRepo.save(ques);
		}
	}
	
	public QuestionEntity getQuestion(String id) {
		return this.quesRepo.findById(id).get();
	}
	
	public Set<QuestionEntity> getQuestions(){
		return new HashSet<>(this.quesRepo.findAll());
	}
	
	public Set<QuestionEntity> getQuestionsOfQuiz(QuizEntity quiz){
		return this.quesRepo.findByQuiz(quiz);
	}
	
	public void deleteQuestion(String id) {
		QuestionEntity ques = new QuestionEntity();
		ques.setQuestionid(id);
		this.quesRepo.delete(ques);
//		this.quesRepo.delete(ques);
	}
}
