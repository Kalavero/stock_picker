import dao.UserDAO;
import model.User;

import java.util.ArrayList;

public class StockEval {
    public static void main (String[] args) {
        ArrayList<User> users = new UserDAO().all();
        for (User user : users) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getEmail());
            System.out.println(user.getCpf());
            System.out.println(user.getBirthday());
        }
    }
}
