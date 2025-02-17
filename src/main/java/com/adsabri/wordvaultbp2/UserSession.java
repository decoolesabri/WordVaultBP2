package com.adsabri.wordvaultbp2;

public class UserSession {
    private static int loggedInUserId = -1;  // -1 betekent geen gebruiker ingelogd

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static boolean isLoggedIn() {
        return loggedInUserId != -1;
    }
}
