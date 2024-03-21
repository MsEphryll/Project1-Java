package Banks;

import java.util.ArrayList;
import java.util.Comparator;

import Accounts.*;
import Main.*;

public class Bank {
    private final int id;
    private String name;
    private String passcode;
    private final double depositLimit = 50000.00;
    private final double withdrawLimit = 50000.00;
    private final double creditLimit = 10000.00;
    private double processsingFee = 10.00;
    private final ArrayList<Account> bankAccounts;

    public Bank(int id, String name, String passcode) {
        this.id = id;
        this.name = name;
        this.passcode = passcode;
        this.bankAccounts = new ArrayList<>();
    }

    // public Bank(int id, String name, String passcode, double depositLimit, double
    // withdrawLimit, double creditLimit) {
    // this(id, name, passcode);
    // this.depositLimit = depositLimit;
    // this.withdrawLimit = withdrawLimit;
    // this.creditLimit = creditLimit;
    // }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public double getDepositLimit() {
        return depositLimit;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getProcesssingFee() {
        return processsingFee;
    }

    public void setProcesssingFee(double processsingFee) {
        this.processsingFee = processsingFee;
    }

    public ArrayList<Account> getBankAccounts() {
        return bankAccounts;
    }

    /**
     * Show accounts based on option.
     * 
     * @param <T>         Type of the field. Can be Double, String, Integer, and all
     *                    other wrapper classes.
     * @param accountType - Type of account to be shown.
     */
    public <T> void showAccounts(Class<T> accountType) {
        // Complete this method

        for (Account account : bankAccounts) {
            if (accountType.isInstance(account)) {
                System.out.println(account);
            }
        }
    }

    /**
     * Get the Account object (if it exists) from a given bank.
     *
     * @param bank       - Bank to search from.
     * @param accountNum - Account number of target account.
     * @return - The Account object with the given account number if it exists in
     *         the bank, or null if it doesn't exist.
     */
    public Account getBankAccount(Bank bank, String accountNum) {
        // Complete this method
        // <<======================================================================================================================
        if (accountExists(bank, accountNum)) {

            // Recursive Algorithm. May cause StackOverflowError. Ana si autofill
            // return bank.getBankAccount(bank, accountNum);

            for (Account account : bank.getBankAccounts()) {
                if (account.getAccountNumber().equals(accountNum)) {
                    return account;
                }
            }

        }

        return null;
    }

    /**
     * Handles the processing of inputting the basic information of the account.
     *
     * @return - Array list of Field objects, which are the basic account
     *         information of the account user.
     */
    public ArrayList<Field<String, ?>> createNewAccount() {
        // Complete this method
        // <<======================================================================================================================

        ArrayList<Field<String, ?>> accountDetails = new ArrayList<>();

        // Field for Account Number
        Field<String, String> accountNumberField = new Field<String, String>("Account Number", String.class, "",
                new Field.StringFieldValidator());
        accountDetails.add(accountNumberField);

        try {
            String accountNum = accountNumberField.getFieldValue();
            if (accountNum.isEmpty()) {
                throw new IllegalArgumentException("Acoount Number must not be empty! Please enter an Account Number.");
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        // Field for First Name
        Field<String, String> firstNameField = new Field<>("First Name", String.class, "",
                new Field.StringFieldValidator());
        accountDetails.add(firstNameField);

        try {
            String fname = firstNameField.getFieldValue();
            if (fname.isEmpty()) {
                throw new IllegalArgumentException("First Name must be  filled out.");
            }
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        // Field for Last Name
        Field<String, String> lastNameField = new Field<>("Last Name", String.class, "",
                new Field.StringFieldValidator());
        accountDetails.add(lastNameField);

        // Field for Email
        Field<String, String> emailField = new Field<>("Email", String.class, "", new Field.StringFieldValidator());
        accountDetails.add(emailField);

        // Field for Account Pin
        Field<String, Integer> pinField = new Field<>("PIN", String.class, 4, new Field.StringFieldLengthValidator());
        accountDetails.add(pinField);

        // User Input para kada Field
        // Wait wala man diay getters ang Field.java sa Field Name
        for (Field<String, ?> field : accountDetails) {
            // Access unta si FieldName sa Field.java
            System.out.print(field.getFieldName() + ": ");

            field.setFieldValue("");
            // or field.setFieldValue(scanner.next()); Is it more efficient?

        }

        return accountDetails;
    }

    /**
     * Create a new credit account. Utilizes the createNewAccount() method.
     *
     * @return - New credit account.
     */
    public CreditAccount createNewCreditAccount() {
        // Complete this method

        ArrayList<Field<String, ?>> accountDetails = createNewAccount();

        // String accountNumber = ""; // Wait lang kita maghimo sa Account Number?

        String accountNumber = (String) accountDetails.get(0).getFieldValue();
        String ownerFName = (String) accountDetails.get(1).getFieldValue();
        String ownerLName = (String) accountDetails.get(2).getFieldValue();
        String ownerMail = (String) accountDetails.get(3).getFieldValue();
        String pin = (String) accountDetails.get(4).getFieldValue();
        double loan = 0.0;

        CreditAccount newCreditAccount = new CreditAccount(this, accountNumber, ownerFName, ownerLName, ownerMail, pin,
                loan);
        addNewAccount(newCreditAccount);
        return newCreditAccount;
    }

    /**
     * Create a new savings account. Utilizes the createNewAccount() method.
     *
     * @return - New savings account.
     */
    public SavingsAccount createNewSavingsAccount() {
        // Complete this method

        ArrayList<Field<String, ?>> accountDetails = createNewAccount();

        // String accountNumber = ""; // Wait lang kita maghimo sa Account Number?

        String accountNumber = (String) accountDetails.get(0).getFieldValue();

        String ownerFName = (String) accountDetails.get(1).getFieldValue();
        String ownerLName = (String) accountDetails.get(2).getFieldValue();
        String ownerMail = (String) accountDetails.get(3).getFieldValue();
        String pin = (String) accountDetails.get(4).getFieldValue();
        double balance = 0.0;

        SavingsAccount newSavingsAccount = new SavingsAccount(this, accountNumber, ownerFName, ownerLName, ownerMail,
                pin, balance);
        addNewAccount(newSavingsAccount);
        return newSavingsAccount;
    }

    /**
     * Adds a new account to this bank, if the account number of the new account
     * does not exist inside the bank.
     *
     * @param account – Account object to be added into this bank
     */
    public void addNewAccount(Account account) {
        // Complete this method

        if (!accountExists(this, account.getAccountNumber())) {
            bankAccounts.add(account);
        }
    }

    /**
     * Checks if an account object exists into a given bank based on some account
     * number.
     *
     * @param bank       - Bank to check if the account exists.
     * @param accountNum - Account number of target account to check.
     * @return True if an account with the given account number exists in the bank,
     *         false otherwise.
     */
    public static boolean accountExists(Bank bank, String accountNum) {
        // Complete this method
        // <<=========================================================================================================
        for (Account account : bank.getBankAccounts()) {
            if (account.getAccountNumber().equals(accountNum)) {
                return true;
            }
        }
        return false;
    }

    // INNER CLASSES COMPARATORS

    /**
     * Compares if two bank objects are the same.
     * 
     * @param b1 The first Bank object to compare.
     * @param b2 The second Bank object to compare.
     * @return 0 if the banks are the same
     *         1 if b1 comes after b2
     *         -1 if b1 comes before b2.
     */
    public class BankComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            // Complete this method
            // <<=========================================================================================================

            int bankObject = b1.getName().compareTo(b2.getName());
            if (bankObject > 0) {
                return 1;
            } else if (bankObject < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Compares if two bank objects have the same bank id.
     * 
     * @param b1 The first Bank object to compare.
     * @param b2 The second Bank object to compare.
     * @return 0 if the banks have the same id.
     *         1 if b1's id is greater than b2's id.
     *         -1 if b1's id is less than b2's id.
     */
    public class BankIDComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            // Complete this method

            if (b1.getId() > b2.getId()) {
                return 1;
            } else if (b1.getId() < b2.getId()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Compares if two bank objects share the same set of credentials (name and
     * passcode).
     * 
     * @param b1 The first Bank object to compare.
     * @param b2 The second Bank object to compare.
     * @return 0 if the banks have the same credentials.
     *         1 if b1's credentials come after b2's credentials.
     *         -1 if b1's credentials come before b2's credentials.
     */
    public class BankCredentialsComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            // Complete this method
            // <<=========================================================================================================

            int nameCompare = b1.getName().compareTo(b2.getName());
            if (nameCompare != 0) {
                if (nameCompare > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }

            int passcodeCompare = b1.getPasscode().compareTo(b2.getPasscode());
            if (passcodeCompare > 0) {
                return 1;
            } else if (passcodeCompare < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    // @Override
    // public String toString() {
    // return "";
    // }
    @Override
    public String toString() {
        String result = "===== Bank =====\n";
        result += "ID: " + this.getId() + "\n";
        result += "Name: " + this.getName() + "\n";
        result += "Passcode: " + this.getPasscode() + "\n";
        result += "Bank Accounts: \n";

        for (Account account : bankAccounts) {
            result += account.toString() + "\n";
        }
        return result;

    }

}
