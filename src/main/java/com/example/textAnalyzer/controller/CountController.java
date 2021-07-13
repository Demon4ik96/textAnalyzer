package com.example.textAnalyzer.controller;

import com.example.textAnalyzer.IncorrectStringException;
import com.example.textAnalyzer.model.AnalyzeResult;
import com.example.textAnalyzer.model.Input;
import com.example.textAnalyzer.model.InputDTO;
import com.example.textAnalyzer.service.AnalyzeResultService;
import com.example.textAnalyzer.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CountController {

    private final InputService inputService;
    private final AnalyzeResultService analyzeResultService;

    @Autowired
    public CountController(InputService inputService, AnalyzeResultService analyzeResultService) {
        this.inputService = inputService;
        this.analyzeResultService = analyzeResultService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "startPage";
    }

    @PostMapping("/analyze")
    public String analyzeResult(@ModelAttribute("inputDTO") InputDTO inputDTO, Model model) throws IncorrectStringException {
        if (inputDTO.getText().isEmpty())
            throw new IncorrectStringException("You entered incorrect text, please try again");
        Input input = new Input(inputDTO.getText());
        inputService.save(input);
        List<AnalyzeResult> analyzeResultList = analyzeResultService.getStats(input);
        for (AnalyzeResult analyzeResult : analyzeResultList) {
            analyzeResult.assignInput(input);
            analyzeResultService.saveAnalyzeResult(analyzeResult);
        }
        model.addAttribute("inputText", inputDTO.getText());
        model.addAttribute("showList", analyzeResultService.getStatsForShow(analyzeResultList));
        return "analyze";
    }

    @ExceptionHandler(IncorrectStringException.class)
    public String handleException(IncorrectStringException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorPage";
    }


}
