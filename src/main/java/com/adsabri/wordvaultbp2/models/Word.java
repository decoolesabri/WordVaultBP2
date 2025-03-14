package com.adsabri.wordvaultbp2.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {

    private final IntegerProperty id;
    private final StringProperty word;
    private final StringProperty meaning;
    private final StringProperty note;
    private final StringProperty category;

    public Word(int id, String word, String meaning, String note, String category) {

        // Onderdelen van het word object
        this.id = new SimpleIntegerProperty(id);
        this.word = new SimpleStringProperty(word);
        this.meaning = new SimpleStringProperty(meaning);
        this.note = new SimpleStringProperty(note);
        this.category = new SimpleStringProperty(category);

    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getWord() {
        return word.get();
    }

    public void setWord(String word) {
        this.word.set(word);
    }

    public StringProperty wordProperty() {
        return word;
    }

    public String getMeaning() {
        return meaning.get();
    }

    public void setMeaning(String meaning) {
        this.meaning.set(meaning);
    }

    public StringProperty meaningProperty() {
        return meaning;
    }

    public String getNote() {
        return note.get();
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public StringProperty noteProperty() {
        return note;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

}
