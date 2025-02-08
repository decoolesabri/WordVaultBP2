package com.adsabri.wordvaultbp2.controllers;

public class CreateController {

    public boolean handleCreate (String word, String meaning, String note) {

        // Voorlopig alleen een simpele controle (hier kan later echte validatie komen)
        if (!word.isEmpty() && !meaning.isEmpty() && !note.isEmpty()) { // Note mag empty zijn, dit nog maken
            System.out.println("Spaans woord: " + word);
            System.out.println("Vertaling: " + meaning);
            System.out.println("Zin/notitie: " + note);
            System.out.println("Woord succesvol opgeslagen!");
            return true; // Login is "succesvol"
        } else {
            System.out.println("Opslaan mislukt: velden mogen niet leeg zijn!");
            return false; // Login mislukt
        }

    }

}
