package com.example.textAnalyzer.persistance;

import com.example.textAnalyzer.model.AnalyzeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeResultRepository extends JpaRepository<AnalyzeResult, Integer> {
}
