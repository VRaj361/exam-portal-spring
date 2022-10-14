package com.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.AttemptedQuizEntity;
import com.entity.CustomResponse;
import com.entity.QuestionEntity;
import com.service.AttemptService;

@RestController
@CrossOrigin("*")
@RequestMapping("/attempt")
public class AttemptController {
	
	@Autowired
	private AttemptService attSer;
	
	@PostMapping("/")
	public CustomResponse<?> addAttemp(@RequestBody AttemptedQuizEntity att){
		AttemptedQuizEntity atte = this.attSer.addAttempt(att);
		if(atte == null) {
			return new CustomResponse<AttemptedQuizEntity>(400, "Duplicate Question Found", atte);
		}else {
			return new CustomResponse<AttemptedQuizEntity>(200, "Question Added Successfully", atte);
		}
	}
	
	@GetMapping("/")
	public CustomResponse<?> getAttemp(){
		Set<AttemptedQuizEntity> attempts = this.attSer.getAttempt();
		return new CustomResponse<Set<AttemptedQuizEntity>>(200, "Question Added Successfully", attempts);
	}
	
	@PostMapping("/checkDuplicate")
	public CustomResponse<?> checkDuplication(@RequestBody AttemptedQuizEntity att){
		boolean atte = this.attSer.checkDuplication(att);
		if(atte) {
			
			return new CustomResponse<>(400, "Attempted Previously..", atte);
		}else {
			return new CustomResponse<>(200, "Ready for Quiz..", atte);
		}
	}
	
	@GetMapping("/currentUserAttempts")
	public CustomResponse<?> currentUserAttempt(@RequestHeader("userid") String id){
		List<AttemptedQuizEntity> list = this.attSer.currentUserAttempt(id);
		if(list == null || list.size()==0) {
			return new CustomResponse<List<AttemptedQuizEntity>>(404, "You don't attempt any Quiz.", null);
		}else{
			return new CustomResponse<List<AttemptedQuizEntity>>(200, "Current User Attempts", list);
		}
		
	}
	
	@GetMapping("/currentQuizDetails")
	public CustomResponse<?> currentQuizDetail(@RequestHeader("attemptid") String id){
		AttemptedQuizEntity att=this.attSer.currentQuizDetail(id);
		if(att == null) {
			return new CustomResponse<AttemptedQuizEntity>(404, "You don't attempt any Quiz.", null);
		}else{
			return new CustomResponse<AttemptedQuizEntity>(200, "Current User Quiz Found", att);
		}
	}
}
