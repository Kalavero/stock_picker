package dao;

import model.Wallet;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WalletDAO {
    private DataSource dataSource;

    public WalletDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Wallet> all(){
        try{
            String query = "SELECT * FROM wallets";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ArrayList<Wallet> wallets = new ArrayList<Wallet>();

            while(rs.next()) {
                Wallet wallet = setWallet(rs);

                wallets.add(wallet);
            }

            ps.close();

            return wallets;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public Wallet find(int id){
        try {
            String sql = "SELECT * FROM wallets WHERE id = ? LIMIT 1";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Wallet wallet = setWallet(rs);

            ps.close();

            return wallet;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public boolean save(Wallet wallet){
        try {
            String sql = "INSERT INTO wallets (user_id, name, description)"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, wallet.getUserId());
            ps.setString(2, wallet.getName());
            ps.setString(3, wallet.getDescription());

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

    public boolean update(Wallet wallet){
        try {
            String sql = "UPDATE wallets SET user_id = ?, name = ?, description = ? WHERE id = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);

            ps.setInt(1, wallet.getUserId());
            ps.setString(2, wallet.getName());
            ps.setString(3, wallet.getDescription());
            ps.setInt(3, wallet.getId());

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

    public boolean delete(int id){
        try {
            String sql = "DELETE FROM wallets WHERE id = ?";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
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

    private Wallet setWallet(ResultSet rs){
        try {
            Wallet wallet = new Wallet();
            wallet.setId(rs.getInt("id"));
            wallet.setUserId(rs.getInt("user_id"));
            wallet.setName(rs.getString("name"));
            wallet.setDescription(rs.getString("description"));

            return wallet;
        }
        catch (SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }

        return null;
    }
}
