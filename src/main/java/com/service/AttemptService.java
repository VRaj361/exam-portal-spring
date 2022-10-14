package com.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.entity.AttemptedQuizEntity;
import com.entity.QuestionEntity;
import com.repository.AttemptRepository;

@Controller
public class AttemptService {
	
	@Autowired
	private AttemptRepository attemptRepo;

	public AttemptedQuizEntity addAttempt(AttemptedQuizEntity att) {
		for(AttemptedQuizEntity atte:this.attemptRepo.findAll()) {
			if(atte.getQuiz().getQuizid().equals(att.getQuiz().getQuizid()) && atte.getUser().getUserid().equals(att.getUser().getUserid())) {
				return null;
			}
		}
		
//		AttemptedQuizEntity atte = this.attemptRepo.findByQuiz(att.getQuiz().getQuizid());
//		if(atte == null) {			
//			return this.attemptRepo.save(att);
//		}else {
//			return null;
		return this.attemptRepo.save(att);
		
	}

	public Set<AttemptedQuizEntity> getAttempt() {
		
		return new LinkedHashSet<>(this.attemptRepo.findAll());
	}
	
	public boolean checkDuplication(AttemptedQuizEntity att) {
		for(AttemptedQuizEntity atte:this.attemptRepo.findAll()) {
			if(atte.getQuiz().getQuizid().equals(att.getQuiz().getQuizid()) && atte.getUser().getUserid().equals(att.getUser().getUserid())) {
				return true;
			}
		}
		return false;
	}
	
}
