package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.entity.CategoryEntity;
import com.entity.CustomResponse;
import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.repository.CategoryRepository;
import com.resourse.TwilioOTPHandler;
import com.service.CategoryService;
import com.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizSer;

	@Autowired
	private CategoryService cateSer;
	
	@Autowired 
	private CategoryRepository cateRepo;
	
	// add quiz
	@PostMapping("/")
	public CustomResponse<?> addQuiz(@RequestBody QuizEntity quiz) {
		QuizEntity quizRes = this.quizSer.addQuiz(quiz);
		if (quizRes == null) {
			return new CustomResponse<QuizEntity>(400, "Duplicate Quiz Found", quizRes);
		} else {
			return new CustomResponse<QuizEntity>(200, "Quiz Added Successfully", quizRes);
		}
	}

	@GetMapping("/particular")
	public CustomResponse<?> getQuiz(@RequestHeader("quizid") String id) {
		QuizEntity quiz = this.quizSer.getQuiz(id);
		if (quiz == null) {
			return new CustomResponse<QuizEntity>(404, "Quiz not found", quiz);
		} else {
			return new CustomResponse<QuizEntity>(200, "Quiz found", quiz);
		}
	}

	@GetMapping("/")
	public CustomResponse<?> getQuizzes() {
		Set<QuizEntity> quizes = this.quizSer.getQuizzes();
		ArrayList<QuizEntity> arr = new ArrayList<>(quizes);
		Collections.sort(arr, new Comparator<QuizEntity>() {
			public int compare(QuizEntity o1, QuizEntity o2) {
				return o1.getTitle().charAt(0) - o2.getTitle().charAt(0);
			}
		});
		return new CustomResponse<ArrayList<QuizEntity>>(200, "Quizzes found", arr);
	}

	@PutMapping("/")
	public CustomResponse<?> updateCategory(@RequestBody QuizEntity quiz) {
		QuizEntity qu = this.quizSer.updateQuiz(quiz);
		if (qu == null) {
			return new CustomResponse<>(404, "Quiz not found", qu);
		} else {
			return new CustomResponse<>(200, "Quiz Update Successfully", qu);
		}
	}

	@DeleteMapping("/")
	public void deleteQuiz(@RequestHeader("quizid") String id) {
		this.quizSer.deleteQuiz(id);
	}
	
	@GetMapping("/category")
	public CustomResponse<?> getQuizzesOfCategory(@RequestHeader("categorytitle") String title){
		title = title.replace("%20", " ");
		System.out.println(title);
		CategoryEntity cate = this.cateRepo.findByTitle(title);
		Set<QuizEntity> questions = cate.getManyQuizzes();
		return new CustomResponse<Set<QuizEntity>>(200, "List of Quizzes", questions);
	}
	
	
//	@Autowired 
//	private TwilioOTPHandler handler;
//	@PostMapping("/router/sendOTP")
//	
//	public RouterFunction<ServerResponse> handleSms(){
//		return RouterFunctions.route().POST("/router/sendOTP",handler::sendOTP).POST("/router/validateOTP", handler::validateOTP).build();ss
//	}
	
	
}
