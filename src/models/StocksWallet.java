package models;

import java.io.Serializable;

public class StocksWallet implements Serializable {
    private int id;
    private int stock_id;
    private int wallet_id;

    public int getId() {
        return id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(int wallet_id) {
        this.wallet_id = wallet_id;
    }

    public StocksWallet() {}
}
