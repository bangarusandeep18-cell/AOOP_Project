import java.time.Duration;
import java.util.Optional;

public class Email {
    public static void main(String[] args) {
        UserRegistry registry = new UserRegistry();

        EmailAddress email = new EmailAddress("Sandeep@gmail.com");
        VerificationToken token = VerificationToken.generate(Duration.ofMinutes(15));

        UserAccount user = new UserAccount(
                new UserIdentifier("U101"),
                email,
                Optional.of(new PhoneNumber("+919876543210")),
                Optional.of(new PostalAddress("Main Road", "Hyderabad", "500001", "India")),
                token);

        registry.register(user);

        System.out.println("CASE STUDY 5 - USER MANAGEMENT");
        System.out.println("Registered email: " + user.getEmail());
        System.out.println("Login before verification: " + user.canLogin());

        // In a real system this token is sent by email. It is printed only for this demo.
        System.out.println("Demo verification token: " + token.getValue());
        System.out.println("Email verified: " + user.verifyEmail(token.getValue()));
        System.out.println("Login after verification: " + user.canLogin());

        user.suspend();
        System.out.println("Login after suspension: " + user.canLogin());

        try {
            UserAccount duplicate = new UserAccount(
                    new UserIdentifier("U102"),
                    new EmailAddress("sandeep@gmail.com"),
                    Optional.empty(),
                    Optional.empty(),
                    VerificationToken.generate(Duration.ofMinutes(15)));
            registry.register(duplicate);
        } catch (IllegalArgumentException ex) {
            System.out.println("Expected error: " + ex.getMessage());
        }
    }
}
