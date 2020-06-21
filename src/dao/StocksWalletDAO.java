package dao;

import model.StocksWallet;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StocksWalletDAO {
    private DataSource dataSource;

    public StocksWalletDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<StocksWallet> all(){
        try{
            String query = "SELECT * FROM stocks_wallets";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
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
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
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
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, stocksWallet.getStockId());
            ps.setInt(2, stocksWallet.getWalletId());
            ps.setInt(3, stocksWallet.getQuantity());

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

    public boolean update(StocksWallet stocksWallet){
        try {
            String sql = "UPDATE stocks_wallets SET stock_id = ?, wallet_id = ?, quantity = ? WHERE id = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);

            ps.setInt(1, stocksWallet.getStockId());
            ps.setInt(2, stocksWallet.getWalletId());
            ps.setInt(3, stocksWallet.getQuantity());
            ps.setInt(3, stocksWallet.getId());

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
            String sql = "DELETE FROM stocks_wallets WHERE id = ?";
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
