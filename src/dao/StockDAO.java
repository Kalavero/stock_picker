package dao;

import model.Stock;

import java.sql.*;
import java.util.ArrayList;

public class StockDAO {
    private Connection conn;

    public StockDAO() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_picker", "root", "");
        }catch(SQLException ex){
            System.err.println("Error getting " + ex.getMessage());
        }
    }

    public ArrayList<Stock> all(){
        try{
            String query = "SELECT * FROM stocks";
            PreparedStatement ps = this.conn.prepareStatement(query);
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
            PreparedStatement ps = this.conn.prepareStatement(sql);
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
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, stock.getCode());
            ps.setDouble(2, stock.getPrice());

            ps.execute();
            this.conn.commit();
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

            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setInt(1, stock.getCode());
            ps.setDouble(2, stock.getPrice());
            ps.setInt(3, stock.getId());

            ps.execute();
            this.conn.commit();
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
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            this.conn.commit();
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
