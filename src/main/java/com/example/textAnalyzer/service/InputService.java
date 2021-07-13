package com.example.textAnalyzer.service;

import com.example.textAnalyzer.model.AnalyzeResult;
import com.example.textAnalyzer.model.Input;
import com.example.textAnalyzer.persistance.InputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InputService {

    private final InputRepository inputRepository;

    @Autowired
    public InputService(InputRepository inputRepository) {
        this.inputRepository = inputRepository;
    }

    public Input save(Input input) {
        return inputRepository.save(input);
    }


}
