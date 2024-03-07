import java.util.ArrayList;

import Accounts.Transaction;

public abstract class Account {
    private final Bank bank;
    private final String accountNumber;
    private final String ownerFName;
    private final String ownerLName;
    private final String ownerMail;
    private String pin;
    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(Bank bank, String accountNumber, String ownerFName, String ownerLName, String ownerMail, String pin) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerFName = ownerFName;
        this.ownerLName = ownerLName;
        this.ownerMail = ownerMail;
        this.pin = pin;
    }

    public Bank getBank() {
        return bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerFName() {
        return ownerFName;
    }

    public String getOwnerLName() {
        return ownerLName;
    }

    public String getOwnerMail() {
        return ownerMail;
    }

    public String getPin() {
        return pin;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public String getOwnerFullName() {
        return this.getOwnerFName() + " " + this.getOwnerLName();
    }

    /**
     * Adds a new transaction log to this account.
     * 
     * @param accountNum - Account Number of source account that triggered this transaction.
     * @param type - Type of transaction triggered.
     * @param description – Description of the transaction.

     */
    public void addNewTransaction(String accountNum, Transaction.Transactions type, String description) {
        Transaction transaction = new Transaction(accountNum, type, description);
        transactions.add(transaction);
    }

    /**
     * Get all information for every transaction that has been logged into this account.
     *
     * @return A string containing information for every transaction that has been logged into this account.
     */
    public String getTransactionsInfo() {
        String info = "";
        for (Transaction transaction : transactions) {
            info += transaction.toString() + "\n";
        }
        return info;
    }

    /**
     * Prints the the bank associated with the account, the account number,
     * the owner's full name, the owner's mail
     * and the list of transactions logged into hte account.
     */
    @Override
    public String toString() {
        return "===== Account =====" + "\n Bank: " + this.getBank() + 
                                        "\n Account Number: " + this.getAccountNumber() +
                                        "\n Owner Full Name: " + this.getOwnerFullName() +
                                        "\n Owner Email Address: " + this.getOwnerMail() +
                                        "\n Transactions: " + this.getTransactionsInfo() +
                                        "\n";
    }
    
}
