package com.example.finalmobileappproject;

import android.widget.CheckBox;

public class ModelTODOList {
    String userInput;
    CheckBox userCheckBox;

    public ModelTODOList(String userInput, CheckBox userCheckBox) {
        this.userInput = userInput;
        this.userCheckBox = userCheckBox;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public CheckBox getUserCheckBox() {
        return userCheckBox;
    }

    public void setUserCheckBox(CheckBox userCheckBox) {
        this.userCheckBox = userCheckBox;
    }
}
