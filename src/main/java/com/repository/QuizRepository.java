package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, String>{
	QuizEntity findByTitle(String title);
}
