package com.adsabri.wordvaultbp2;

// Beheert de sessie van de ingelogde gebruiker
public class UserSession {

    private static int loggedInUserId = -1;  // -1 betekent geen gebruiker ingelogd

    // Id gebruiker instellen
    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }

    // id gebruiker ophalen
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    // Controleren of er een gebruiker is
    public static boolean isLoggedIn() {
        return loggedInUserId != -1;
    }

}
