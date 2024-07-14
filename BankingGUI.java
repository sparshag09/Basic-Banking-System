import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankingGUI {
    private BankingSystem bankingSystem;

    public BankingGUI() {
        bankingSystem = new BankingSystem();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Banking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        panel.add(createAccountButton);

        JButton depositButton = new JButton("Deposit Money");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositMoney();
            }
        });
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw Money");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdrawMoney();
            }
        });
        panel.add(withdrawButton);

        JButton transferButton = new JButton("Transfer Money");
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferMoney();
            }
        });
        panel.add(transferButton);

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });
        panel.add(balanceButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void createAccount() {
        JTextField accountNumberField = new JTextField();
        JTextField accountHolderNameField = new JTextField();
        JTextField initialBalanceField = new JTextField();

        Object[] message = {
                "Account Number:", accountNumberField,
                "Account Holder Name:", accountHolderNameField,
                "Initial Balance:", initialBalanceField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();
            String accountHolderName = accountHolderNameField.getText();
            double initialBalance = Double.parseDouble(initialBalanceField.getText());

            bankingSystem.createAccount(accountNumber, accountHolderName, initialBalance);
            JOptionPane.showMessageDialog(null, "Account created successfully!");
        }
    }

    private void depositMoney() {
        JTextField accountNumberField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] message = {
                "Account Number:", accountNumberField,
                "Amount to Deposit:", amountField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Deposit Money", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Account account = bankingSystem.findAccount(accountNumber);
            if (account != null) {
                account.deposit(amount);
                JOptionPane.showMessageDialog(null, "Deposit successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Account not found.");
            }
        }
    }

    private void withdrawMoney() {
        JTextField accountNumberField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] message = {
                "Account Number:", accountNumberField,
                "Amount to Withdraw:", amountField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Withdraw Money", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Account account = bankingSystem.findAccount(accountNumber);
            if (account != null) {
                if (account.withdraw(amount)) {
                    JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Account not found.");
            }
        }
    }

    private void transferMoney() {
        JTextField fromAccountNumberField = new JTextField();
        JTextField toAccountNumberField = new JTextField();
        JTextField amountField = new JTextField();

        Object[] message = {
                "From Account Number:", fromAccountNumberField,
                "To Account Number:", toAccountNumberField,
                "Amount to Transfer:", amountField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Transfer Money", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String fromAccountNumber = fromAccountNumberField.getText();
            String toAccountNumber = toAccountNumberField.getText();
            double amount = Double.parseDouble(amountField.getText());

            Account fromAccount = bankingSystem.findAccount(fromAccountNumber);
            Account toAccount = bankingSystem.findAccount(toAccountNumber);

            if (fromAccount != null && toAccount != null) {
                if (fromAccount.transfer(toAccount, amount)) {
                    JOptionPane.showMessageDialog(null, "Transfer successful!");
                } else {
                    JOptionPane.showMessageDialog(null, "Insufficient balance.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Account not found.");
            }
        }
    }

    private void checkBalance() {
        JTextField accountNumberField = new JTextField();

        Object[] message = {
                "Account Number:", accountNumberField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Check Balance", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();

            Account account = bankingSystem.findAccount(accountNumber);
            if (account != null) {
                JOptionPane.showMessageDialog(null, "Balance: $" + account.getBalance());
            } else {
                JOptionPane.showMessageDialog(null, "Account not found.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BankingGUI();
            }
        });
    }
}
