package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.AttemptedQuizEntity;

public interface AttemptRepository extends JpaRepository<AttemptedQuizEntity, String>{

	AttemptedQuizEntity findByQuiz(String quizid);

}
