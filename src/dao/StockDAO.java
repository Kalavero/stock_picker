package dao;

import model.Stock;
import model.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockDAO {
    private DataSource dataSource;

    public StockDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Stock> all(){
        try{
            String query = "SELECT * FROM stocks";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            ArrayList<Stock> stocks = new ArrayList<Stock>();

            while(rs.next()) {
                Stock stock = setStock(rs);

                stocks.add(stock);
            }

            ps.close();

            return stocks;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public Stock find(int id){
        try {
            String sql = "SELECT * FROM stocks WHERE id = ? LIMIT 1";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Stock stock = setStock(rs);

            ps.close();

            return stock;
        }
        catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
        catch (Exception ex){
            System.err.println(("Runtime error" + ex.getMessage()));
        }
        return null;
    }

    public boolean save(Stock stock){
        try {
            String sql = "INSERT INTO stocks (code, price)"
                    + "VALUES(?, ?)";
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setInt(1, stock.getCode());
            ps.setDouble(2, stock.getPrice());

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

    public boolean update(Stock stock){
        try {
            String sql = "UPDATE stocks SET code = ?, price = ? WHERE id = ?";

            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);

            ps.setInt(1, stock.getCode());
            ps.setDouble(2, stock.getPrice());
            ps.setInt(3, stock.getId());

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
            String sql = "DELETE FROM stocks WHERE id = ?";
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

    private Stock setStock(ResultSet rs){
        try {
            Stock stock = new Stock();
            stock.setId(rs.getInt("id"));
            stock.setCode(rs.getInt("code"));
            stock.setPrice(rs.getDouble("price"));

            return stock;
        }
        catch (SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }

        return null;
    }
}
