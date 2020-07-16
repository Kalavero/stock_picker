package dao;

import model.StocksWallet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class StocksWalletDAO {
    private Connection conn;

    public StocksWalletDAO(){
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:stock_picker.db");
        }catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
    }

    public ArrayList<StocksWallet> all(){
        try{
            String query = "SELECT * FROM stocks_wallets";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ArrayList<StocksWallet> stocksWallets = new ArrayList<StocksWallet>();

            while(rs.next()) {
                StocksWallet stocksWallet = setStocksWallet(rs);

                stocksWallets.add(stocksWallet);
            }

            ps.close();

            return stocksWallets;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public StocksWallet find(int id){
        try {
            String sql = "SELECT * FROM stocks_wallets WHERE id = ? LIMIT 1";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            StocksWallet stocksWallet = setStocksWallet(rs);

            ps.close();

            return stocksWallet;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public boolean save(StocksWallet stocksWallet){
        try {
            String sql = "INSERT INTO stocks_wallets (stock_id, wallet_id, quantity)"
                    + "VALUES(?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stocksWallet.getStockId());
            ps.setInt(2, stocksWallet.getWalletId());
            ps.setInt(3, stocksWallet.getQuantity());

            ps.execute();
            conn.commit();
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

    public boolean update(StocksWallet stocksWallet){
        try {
            String sql = "UPDATE stocks_wallets SET stock_id = ?, wallet_id = ?, quantity = ? WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, stocksWallet.getStockId());
            ps.setInt(2, stocksWallet.getWalletId());
            ps.setInt(3, stocksWallet.getQuantity());
            ps.setInt(3, stocksWallet.getId());

            ps.execute();
            conn.commit();
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
            String sql = "DELETE FROM stocks_wallets WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            conn.commit();
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

    private StocksWallet setStocksWallet(ResultSet rs){
        try {
            StocksWallet stocksWallet = new StocksWallet();
            stocksWallet.setId(rs.getInt("id"));
            stocksWallet.setStockId(rs.getInt("stock_id"));
            stocksWallet.setWalletId(rs.getInt("wallet_id"));
            stocksWallet.setQuantity(rs.getInt("quantity"));

            return stocksWallet;
        }
        catch (SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }

        return null;
    }
}
