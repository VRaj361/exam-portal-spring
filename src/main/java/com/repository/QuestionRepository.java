package com.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.QuestionEntity;
import com.entity.QuizEntity;


public interface QuestionRepository extends JpaRepository<QuestionEntity, String>{
	Set<QuestionEntity> findByQuiz(QuizEntity quiz);

	QuestionEntity findByContent(String content);
	
	
}
