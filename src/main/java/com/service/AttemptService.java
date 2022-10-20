package com.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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

	public List<AttemptedQuizEntity> currentUserAttempt(String userid) {
		List<AttemptedQuizEntity> list=new ArrayList<>();
		for(AttemptedQuizEntity atte : this.attemptRepo.findAll()) {
			if(atte.getUser().getUserid().equals(userid)) {
				list.add(atte);
			}
		}
		return list;
	}

	public AttemptedQuizEntity currentQuizDetail(String id) {
		
		return this.attemptRepo.findById(id).get();
	}

	public List<AttemptedQuizEntity> getAllUserDetails(String id) {
		List<AttemptedQuizEntity> list=new ArrayList<>();
		for(AttemptedQuizEntity atte : this.attemptRepo.findAll()) {
			if(atte.getQuiz().getQuizid().equals(id)) {
				list.add(atte);
			}
		}
		return list;
	}
	
	public void deleteAttempt(String id) {
		this.attemptRepo.deleteAttempt(id);
		
	}
	
}
