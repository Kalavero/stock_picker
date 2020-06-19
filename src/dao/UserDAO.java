package dao;

import model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private DataSource dataSource;

    public UserDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<User> all(){
        try{
            String query = "SELECT * FROM users";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ArrayList<User> users = new ArrayList<User>();

            while(rs.next()) {
                User user = setUser(rs);

                users.add(user);
            }

            ps.close();

            return users;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public User find(int id){
        try {
            String sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            User user = setUser(rs);

            ps.close();

            return user;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public boolean save(User user){
        try {
            String sql = "INSERT INTO users (name, cpf, email, birthday)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getCpf());
            ps.setString(3, user.getEmail());
            ps.setDate(4, user.getBirthday());

            ps.execute();
            dataSource.getConnection().commit();
            ps.close();

            return true;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return false;
    }

    public boolean update(User user){
        try {
            String sql = "UPDATE users SET name = ?, cpf = ?, email = ?, birthday = ? WHERE id = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getCpf());
            ps.setString(3, user.getEmail());
            ps.setDate(4, user.getBirthday());
            ps.setInt(5, user.getId());

            ps.execute();
            dataSource.getConnection().commit();
            ps.close();

            return true;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return false;
    }

    public boolean delete(User user){
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.execute();
            dataSource.getConnection().commit();
            ps.close();

            return true;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return false;
    }

    private User setUser(ResultSet rs){
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setCpf(rs.getString("cpf"));
            user.setEmail(rs.getString("email"));
            user.setBirthday(rs.getDate("birthday"));

            return user;
        }
        catch (SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }

        return null;
    }
}
