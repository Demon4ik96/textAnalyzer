package com.example.textAnalyzer.persistance;

import com.example.textAnalyzer.model.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {
}
