package com.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.entity.AttemptedQuizEntity;
import com.entity.CustomResponse;
import com.entity.QuestionEntity;
import com.entity.QuizEntity;
import com.service.QuestionService;
import com.service.QuizService;


@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService quesSer;
	
	@Autowired
	private QuizService quizSer;
	

	
	//add quiz
	@PostMapping("/")
	public CustomResponse<?> addQuestion(@RequestBody QuestionEntity qu){
		QuestionEntity quRes = this.quesSer.addQuestion(qu);
		if(quRes == null) {
			return new CustomResponse<QuestionEntity>(400, "Duplicate Question Found", quRes);
		}else {
			return new CustomResponse<QuestionEntity>(200, "Question Added Successfully", quRes);
		}
	}
	
	@GetMapping("/particular")
	public CustomResponse<?> getQuestion(@RequestHeader("Questionid") String id){
		QuestionEntity qu = this.quesSer.getQuestion(id);
		if(qu == null) {
			return new CustomResponse<QuestionEntity>(404,"Question not found",qu);
		}else {
			return new CustomResponse<QuestionEntity>(200,"Question found",qu);
		}
	}
	
	@GetMapping("/")
	public CustomResponse<?> getQuestions(){
		Set<QuestionEntity> questions = this.quesSer.getQuestions();
		return new CustomResponse<Set<QuestionEntity>>(200,"Questions found",questions);
	}
	
	@PutMapping("/")
	public CustomResponse<?> updateQuestion(@RequestBody QuestionEntity ques){
		QuestionEntity qu = this.quesSer.updateQuestion(ques);
		if(qu  == null) {
			return new CustomResponse<>(404,"Question not found",qu );
		}else {
			return new CustomResponse<>(200,"Question Update Successfully",qu);
		}
	}
	
	@DeleteMapping("/")
	public void deleteQuestion(@RequestHeader("questionid") String id) {
		this.quesSer.deleteQuestion(id);
	}

	@GetMapping("/quiz")
	public CustomResponse<?> getQuestionsOfQuiz(@RequestHeader("quizid") String id){
//		QuizEntity quiz=new QuizEntity();
//		quiz.setQuizid(id);
//		Set<QuestionEntity> questions = this.quesSer.getQuestionsOfQuiz(quiz);
//		return new CustomRespg s onse<Set<QuestionEntity>>(200, "", questions);
		
		QuizEntity quiz = this.quizSer.getQuiz(id);
		Set<QuestionEntity> questions = quiz.getManyQuestions();
		List<QuestionEntity> l = new ArrayList<>(questions);
//		Collections.shuffle(l);
		return new CustomResponse<List<QuestionEntity>>(200, "List of Questions", l);
	}
	
	@GetMapping("/quizselect")//for users
	public CustomResponse<?> getSomeQuestionsOfQuiz(@RequestHeader("quizid") String id){

		
		QuizEntity quiz = this.quizSer.getQuiz(id);
		Set<QuestionEntity> questions = quiz.getManyQuestions();
		List<QuestionEntity> l = new ArrayList<>(questions);
		if(l.size() > quiz.getNumberOfQuestions()) {
			Collections.shuffle(l);
			l = l.subList(0, quiz.getNumberOfQuestions());
		}
//		for(QuestionEntity q:l) {
//			q.setAnswer("");
//		}
		Collections.shuffle(l);
		return new CustomResponse<List<QuestionEntity>>(200, "List of Questions", l);
	}
	
	
	@PostMapping("/evaluate")
	public CustomResponse<?> evuluateResult(@RequestBody List<QuestionEntity> questions){
		int attemptQuestions = 0;
		int correctAnswers = 0;
		int totalMarks = 0;
		float percentage = 0;
		for(QuestionEntity q:questions) {
			QuestionEntity ques = this.quesSer.getQuestion(q.getQuestionid());
			System.out.println(ques.getAnswer()+" : "+q.getGiveAnswer());
			if(ques.getAnswer().equals(q.getGiveAnswer())) {
				correctAnswers++;
		        attemptQuestions++;
			}
		    else if (!ques.getAnswer().equals(q.getGiveAnswer()) && !ques.getAnswer().equals("")) {
		        attemptQuestions++;
		    }
			
		}
		totalMarks = correctAnswers * questions.get(0).getQuiz().getMaxMarks() / questions.get(0).getQuiz().getNumberOfQuestions();
		percentage = totalMarks * 100 / questions.get(0).getQuiz().getMaxMarks();
		Map<String,Object> ans = new HashMap<>();
		ans.put("attemptQuestions", attemptQuestions);
		ans.put("correctAnswers", correctAnswers);
		ans.put("totalMarks", totalMarks);
		ans.put("percentage",percentage);

		return new CustomResponse<Map<String,Object>>(200, "Result Generated", ans);
		
	}
}
