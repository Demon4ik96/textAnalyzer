package com.example.textAnalyzer.model;

import javax.persistence.*;

@Entity
@Table(name = "analyze_result")
public class AnalyzeResult implements Comparable<AnalyzeResult> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String word;

    @Column(name = "word_count")
    private int wordCount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "input_id", referencedColumnName = "input_id")
    private Input input;

    public AnalyzeResult(String word, int wordCount) {
        this.word = word;
        this.wordCount = wordCount;
    }

    public AnalyzeResult() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return word + "  -  " + wordCount;
    }

    @Override
    public int compareTo(AnalyzeResult otherAnalyzeResult) {
        return Integer.compare(otherAnalyzeResult.wordCount, wordCount);
    }

    public void assignInput(Input input) {
        this.input = input;
    }

}
