import data.IDB;
import data.PostgresDB;
import models.User;
import repository.User.IUserRepository;
import repository.User.UserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "0000", "Endterm_Project");
        IUserRepository userRepository = new UserRepository(db);

        User user = new User(1, "ali", "email", "ppp");

        userRepository.createUser(user);
    }
}