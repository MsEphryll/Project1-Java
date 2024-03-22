package Banks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

import Accounts.*;
import Main.*;
import Main.Field.StringFieldLengthValidator;
import Main.Field.StringFieldValidator;

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

    // public int setId(int id) {
    // try {
    // // Try parsing the provided ID as an integer
    // int bId = Integer.parseInt(String.valueOf(id));

    // // Check if the parsed ID is non-negative
    // if (bId >= 0) {
    // // If it's valid, set the ID
    // this.id = bId;
    // } else {
    // System.out.println("Error: ID must be a non-negative integer.");
    // }

    // } catch (NumberFormatException e) {
    // // If parsing fails, it means the provided ID is not an integer
    // System.out.println("Error: ID must be an integer.");
    // }
    // return this.id;
    // }

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
        ArrayList<Field<String, ?>> accountDetails = new ArrayList<>();

        // Field for Account Number
        // try {
        Field<String, String> accountNumberField = new Field<>("Account number", String.class, "",
                new StringFieldValidator());
        // Compile the regex pattern outside the loop for efficiency
        boolean validInput = false;
        while (!validInput) {

            Pattern pattern = Pattern.compile("\\d+");
            String input = (accountNumberField.getFieldName() + ": ");
            // String input = accountNumberField.getFieldValue(); // Directly obtain user
            // input. This method needs to be defined to capture user input.
            accountNumberField.setFieldValue(input, true);

            if (pattern.matcher(accountNumberField.getFieldValue()).matches()) {
                // If input is numeric, set the field value and exit the loop
                accountDetails.add(accountNumberField);
                validInput = true;
            } else {
                // If input is not numeric, inform the user and the loop will continue
                System.out.println("Account number must be numeric.");
            }
        }

        // Field for First Name
        Field<String, String> firstNameField = new Field<>("First Name", String.class, "", new StringFieldValidator());
        boolean fnameInput = false;
        while (!fnameInput) {

            Pattern pattern = Pattern.compile("^[A-Za-z\\s]+$");
            String input = (firstNameField.getFieldName() + ": ");
            firstNameField.setFieldValue(input, true);

            if (pattern.matcher(firstNameField.getFieldValue()).matches()) {
                // If input is valid, set the field value and exit the loop
                accountDetails.add(firstNameField);
                fnameInput = true; // Exit the loop
            } else {
                // If input is not valid, inform the user and the loop will continue
                System.out.println("First name must only contain letters and spaces.");
            }
        }

        // Field for Last Name
        Field<String, String> lastNameField = new Field<>("Last Name", String.class, "", new StringFieldValidator());
        boolean lnameInput = false;
        while (!lnameInput) {

            Pattern pattern = Pattern.compile("^[A-Za-z\\s]+$");
            String input = (lastNameField.getFieldName() + ": ");
            lastNameField.setFieldValue(input, true);

            if (pattern.matcher(lastNameField.getFieldValue()).matches()) {
                accountDetails.add(lastNameField);
                lnameInput = true; // Exit the loop
            } else {
                System.out.println("Last name must only contain letters and spaces.");
            }
        }

        // Field for Email
        Field<String, String> emailField = new Field<>("Email", String.class, "", new StringFieldValidator());
        boolean emailInput = false;
        while (!emailInput) {

            Pattern pattern = Pattern.compile(
                    "^(?:(?!.*?[.]{2})[a-zA-Z0-9](?:[a-zA-Z0-9.+!%-]{1,64}|)|\\\"[a-zA-Z0-9.+!% -]{1,64}\\\")@[a-zA-Z0-9][a-zA-Z0-9.-]+(.[a-z]{2,}|.[0-9]{1,})$");
            String input = (emailField.getFieldName() + ": ");
            emailField.setFieldValue(input, true);

            if (pattern.matcher(emailField.getFieldValue()).matches()) {
                accountDetails.add(emailField);
                emailInput = true; // Exit the loop
            } else {
                System.out.println("Email must only contain letters, @, _, and . symbols. e.g. example@gmail.com");
            }
        }

        // Field for Account Pin
        Field<String, Integer> pinField = new Field<>("PIN", String.class, 4, new StringFieldLengthValidator());
        boolean pinInput = false;
        while (!pinInput) {
            Pattern pattern = Pattern.compile("\\d+");
            String input = (pinField.getFieldName() + ": ");

            pinField.setFieldValue(input, true);
            if (pattern.matcher(pinField.getFieldValue()).matches()) {
                accountDetails.add(pinField);
                pinInput = true;
            } else {
                System.out.println("Pinfield number must be numeric.");
            }
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
