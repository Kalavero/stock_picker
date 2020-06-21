import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class StockEval {
    public static void main (String[] args) {
        ArrayList<User> users = new UserDAO().all();
        for(int i = 0; i < users.size(); i++){
            System.out.println(users.get(i).getId());
            System.out.println(users.get(i).getName());
            System.out.println(users.get(i).getEmail());
            System.out.println(users.get(i).getCpf());
            System.out.println(users.get(i).getBirthday());
        }
    }
}
