package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AttemptedQuizEntity;


public interface AttemptRepository extends JpaRepository<AttemptedQuizEntity, String>{

	AttemptedQuizEntity findByQuiz(String quizid);

	
	@Modifying
	@Transactional
	@Query(value = "delete from attemptions where attemptid = :id",nativeQuery = true)
	public void deleteAttempt(@Param("id")String id);
	
	
}
