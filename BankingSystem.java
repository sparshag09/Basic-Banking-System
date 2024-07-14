import java.util.ArrayList;

public class BankingSystem {
    private ArrayList<Account> accounts = new ArrayList<>();

    public Account createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        Account newAccount = new Account(accountNumber, accountHolderName, initialBalance);
        accounts.add(newAccount);
        return newAccount;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
