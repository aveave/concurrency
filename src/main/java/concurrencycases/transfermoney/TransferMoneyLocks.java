package concurrencycases.transfermoney;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransferMoneyLocks {

    Lock lock = new ReentrantLock();

    public boolean transferMoney(AccountLock from, AccountLock to, BigDecimal sum) {
        while (true) {
            if (from.getLock().tryLock()) {
                try {
                    if (to.getLock().tryLock()) {
                        try {
                            if (from.getBalance().compareTo(sum) < 0) {
                                throw new RuntimeException();
                            }
                            from.setBalance(from.getBalance().subtract(sum));
                            to.setBalance(to.getBalance().add(sum));
                            return true;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        AccountLock from = new AccountLock(1, new BigDecimal(100));
        AccountLock to = new AccountLock(1, new BigDecimal(200));
        BigDecimal sum = new BigDecimal(100);
        TransferMoneyLocks transferMoneyLocks = new TransferMoneyLocks();
        transferMoneyLocks.transferMoney(from, to, sum);
        System.out.println("from " + from.getBalance());
        System.out.println("to " + to.getBalance());
    }
}
