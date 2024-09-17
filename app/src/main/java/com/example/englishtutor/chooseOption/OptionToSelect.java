package com.example.englishtutor.chooseOption;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class OptionToSelect implements Serializable {

    private Integer pathImage;
    private String correctAns;
    private List<String> options;

    public OptionToSelect(Integer pathImage, String correctAns, List<String> options) {
        this.pathImage = pathImage;
        this.correctAns = correctAns;
        this.options = options;
        Collections.shuffle(this.options);
    }

    public Integer getPathImage() {
        return pathImage;
    }

    public void setPathImage(Integer pathImage) {
        this.pathImage = pathImage;
    }

    public String getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(String correctAns) {
        this.correctAns = correctAns;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
