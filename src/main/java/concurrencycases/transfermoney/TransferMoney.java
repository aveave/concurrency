package concurrencycases.transfermoney;

import java.math.BigDecimal;

public class TransferMoney {


    /**
     * Concurrent transfer using id as a condition to sequence to lock on account
     * Also lock order can be done using System.identityHashCode(account)
     */
    public void transferMoney(Account from, Account to, BigDecimal sum) {
        if (from.getBalance().compareTo(sum) < 0) {
            throw new RuntimeException();
        }
        if (from.getId() < to.getId()) {
            synchronized (from) {
                synchronized (to) {
                    makeTransfer(from, to, sum);
                }
            }
        } else {
            synchronized (to) {
                synchronized (from) {
                    makeTransfer(from, to, sum);
                }
            }
        }
    }

    private void makeTransfer(Account from, Account to, BigDecimal sum) {
        from.setBalance(from.getBalance().subtract(sum));
        to.setBalance(to.getBalance().add(sum));
    }
}
