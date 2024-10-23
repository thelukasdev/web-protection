package Output;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityHandler {

    public static String sanitizeInput(String input) {
        if (input != null && !input.isEmpty()) {
            // Nur Buchstaben, Zahlen und bestimmte Sonderzeichen zulassen
            String regex = "^[a-zA-Z0-9.,!?\\-\\s]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            if (matcher.matches()) {
                return input;
            } else {
                return "Ungültige Eingabe";
            }
        } else {
            return "Leere Eingabe";
        }
    }
    public static void main(String[] args) {
        String userInput = "<script>alert('XSS attack');</script>";

        String sanitizedInput = sanitizeInput(userInput);

        System.out.println("Sicherer Input: " + sanitizedInput);
    }
}