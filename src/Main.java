import Output.SecurityHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    String userInput = "<script>alert('XSS attack');</script>";
    SecurityHandler securityHandler = new SecurityHandler();

    String sanitizedInput = securityHandler.sanitizeInput(userInput);


    public String getSanitizedInput() {
        return sanitizedInput;
    }
}