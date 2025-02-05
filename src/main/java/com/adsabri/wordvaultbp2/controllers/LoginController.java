package com.adsabri.wordvaultbp2.controllers;

public class LoginController {

    public boolean handleLogin(String username, String password) {

        // Voorlopig alleen een simpele controle (hier kan later echte validatie komen)
        if (!username.isEmpty() && !password.isEmpty()) {
            System.out.println("Gebruikersnaam: " + username);
            System.out.println("Wachtwoord: " + password);
            System.out.println("Login succesvol verwerkt!");
            return true; // Login is "succesvol"
        } else {
            System.out.println("Login mislukt: velden mogen niet leeg zijn!");
            return false; // Login mislukt
        }

    }

}
