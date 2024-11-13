import varsel.login_related.LoginMenu;
import varsel.login_related.UserRepository;
import varsel.login_related.UserService;

import java.io.IOException;
// Krell
public class Main {
    private static final String FILE_PATH = "users.txt";

    public static void main(String[] args) throws IOException {
        UserRepository userRepository = new UserRepository(FILE_PATH);
        UserService userService = new UserService(userRepository);
        LoginMenu loginMenu = new LoginMenu(userService);

        loginMenu.start();  // Starter menyen
    }
}
