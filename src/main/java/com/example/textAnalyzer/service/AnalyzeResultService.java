package com.example.textAnalyzer.service;

import com.example.textAnalyzer.model.AnalyzeResult;
import com.example.textAnalyzer.model.Input;
import com.example.textAnalyzer.persistance.AnalyzeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AnalyzeResultService {

    private final AnalyzeResultRepository analyzeResultRepository;

    Map<String, Integer> words;

    @Autowired
    public AnalyzeResultService(AnalyzeResultRepository analyzeResultRepository) {
        this.analyzeResultRepository = analyzeResultRepository;
    }

    public AnalyzeResult saveAnalyzeResult(AnalyzeResult analyzeResult) {
        return analyzeResultRepository.save(analyzeResult);
    }

    public List<AnalyzeResult> getStats(Input input) {
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(input.getPayload().split("[\\p{Punct}\\s]+")));
        ArrayList<AnalyzeResult> resultList = new ArrayList<>();
        fillMap(wordList);
        for (Map.Entry<String, Integer> set : words.entrySet()) {
            resultList.add(new AnalyzeResult(set.getKey(), set.getValue()));
        }
        return resultList;
    }

    public List<AnalyzeResult> getStatsForShow(List<AnalyzeResult> analyzeResultList) {
        Iterator<AnalyzeResult> itr = analyzeResultList.iterator();
        int uniqueCounter = 0;
        while (itr.hasNext()) {
            AnalyzeResult tempResult = itr.next();
            if (tempResult.getWordCount() == 1) {
                itr.remove();
                uniqueCounter++;
            }
        }
        Collections.sort(analyzeResultList);
        analyzeResultList.add(new AnalyzeResult("unique: ", uniqueCounter));
        return analyzeResultList;
    }

    private void fillMap(ArrayList<String> wordList) {
        words = new HashMap<>();
        for (String word : wordList) {
            String lowerWord = word.toLowerCase();
            if (words.containsKey(lowerWord)) {
                words.put(lowerWord, words.get(lowerWord) + 1);
            } else {
                words.put(lowerWord, 1);
            }
        }
    }


}
