package edu.chalmers.fillin.bookmanagerlab2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {
    public static void saveBooks(String value) {
        SharedPreferences.Editor editor= MainActivity.preferences.edit();
        editor.putString("books", value);
        editor.apply();
    }
    public static String getBooks() {
        return MainActivity.preferences.getString("books","No saved books.");
    }

    public static void clearBooks() {
        SharedPreferences.Editor editor= MainActivity.preferences.edit();
        editor.remove("books");
        editor.apply();
    }
}
