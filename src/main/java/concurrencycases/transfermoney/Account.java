package concurrencycases.transfermoney;

import java.math.BigDecimal;

public class Account {

    private int id;

    private BigDecimal balance;

    public Account() {
    }

    public Account(int id, BigDecimal sum) {
        this.id = id;
        this.balance = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
