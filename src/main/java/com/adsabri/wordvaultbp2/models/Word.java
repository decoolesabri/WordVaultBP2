package com.adsabri.wordvaultbp2.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {

    private final StringProperty word;
    private final StringProperty meaning;
    private final StringProperty note;

    public Word(String word, String meaning, String note) {
        this.word = new SimpleStringProperty(word);
        this.meaning = new SimpleStringProperty(meaning);
        this.note = new SimpleStringProperty(note);
    }

    public String getWord() {
        return word.get();
    }

    public StringProperty wordProperty() {
        return word;
    }

    public String getMeaning() {
        return meaning.get();
    }

    public StringProperty meaningProperty() {
        return meaning;
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }
}
