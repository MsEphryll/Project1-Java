import java.util.ArrayList;
import java.util.Comparator;
import Main.Field;

// kani?
// Oo
// Ang BankLauncher nakita nimo?
// ga red lagiii
// Lah
// ayy gets, haw
// yes yess
// Go basaha nana HAHAHAHAHAHHAHA
// Wait lang gaicheck pa nako
// ayyy sareh HAHAHA
// SIMBAKO AHAHHAAHHAHAHAHAHAHHAHAHAH
// Ako gicut, balik na nako
// DIli nako marename
// kini na file?
// Oo
//Himo nalang og bag o?
// maka rename ko
// Irename kaw HAHHAHAHAHAHA
// Joke irename nalang behh Bank.java
// Tenchu

public class Bank {
    private final int id;
    private String name;
    private String passcode;
    private final double depositLimit;
    private final double withdrawLimit;
    private final double creditLimit;
    private double processsingFee = 10.00;
    private final ArrayList<Account> bankAccounts = new ArrayList<>();

    public Bank(int id, String name, String passcode, double depositLimit, double withdrawLimit, double creditLimit, double processsingFee) {
        this.id = id;
        this.name = name;
        this.passcode = passcode;
        this.depositLimit = depositLimit = 50000.00;
        this.withdrawLimit = 50000.00;
        this.creditLimit = creditLimit = 10000.00;
        this.processsingFee = processsingFee;
    }

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

    
    public <T extends Account> void showAccounts(Class<T> accountType) {
        for (Account account : this.getBankAccounts()) {
            if (accountType.isInstance(account)) {
                System.out.println(account);
            }
        }
    }


    /**
     * Get the Account object (if it exists) from a given bank.
     *
     * @param bank - Bank to search from.
     * @param accountNum - Account number of target account.
     * @return - The Account object with the given account number if it exists in the bank, or null if it doesn't exist.
     */
    public static Account getBankAccount(Bank bank, String accountNum) {

        // Checks first if it exists from a given bank
        if (accountExists(bank, accountNum)) {


            // Redundant Algorithm. This algorithm was already used in the accountExists method.
            for (Account account : bank.getBankAccounts()) {
                if (account.getAccountNumber().equals(accountNum)) {
                    return account;
                }
            }
            // Please find some alternatives

            
        }
        return null;
    }


    /**
     * Handles the processing of inputting the basic information of the account. 
     *
     * @return - Array list of Field objects, which are the basic account information of the account user.
     */
    public ArrayList<Field<String, ?>> createNewAccount() {
        //WALA KO KAGETS ANIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
    }


    /**
    * Create a new credit account. Utilizes the createNewAccount() method.
    *
    * @return - New credit account.
    */
    public CreditAccount createNewCreditAccount() {
        // WAIT LANG POOOOOOOOOO
    }


    /**
    * Create a new savings account. Utilizes the createNewAccount() method.
    *
    * @return - New savings account.
    */
    public SavingsAccount createNewSavingsAccount() {
        // WAIT LANG POOOOOOOOOO
    }


    /**
    * Adds a new account to this bank, if the account number of the new account does not exist inside the bank.
    *
    * @param account – Account object to be added into this bank
    */
    public void addNewAccount(Account account) {
        //Hmmm...
    }

    /**
     * Checks if an account object exists into a given bank based on some account number.
     *
     * @param bank - Bank to check if the account exists.
     * @param accountNum - Account number of target account to check.
     * @return True if an account with the given account number exists in the bank, false otherwise.
     */
    public static boolean accountExists(Bank bank, String accountNum) {
        for (Account account : bank.getBankAccounts()) {
            if (account.getAccountNumber().equals(accountNum)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Compares if two bank objects are the same.
     * 
     * @param b1 The first Bank object to compare.
     * @param b2 The second Bank object to compare.
     * @return - 0 if the names of both banks are equal, indicating equality;
     *          -1 if the names are different, indicating inequality.
     */
    public class BankComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {

            if (b1.getName().equals(b2.getName())) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * Compares if two bank objects have the same bank id.
     * 
     * @param b1 The first Bank object to compare.
     * @param b2 The second Bank object to compare.
     * @return 1 if the ID of b1 is greater than the ID of b2;
     *        -1 if the ID of b1 is less than the ID of b2;
     *         0 if the IDs of both banks are equal.
     */
    public class BankIDComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            if (b1.id > b2.id) {
                return 1;
            } else if (b1.id < b2.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    /**
     * Compares two Bank objects based on their credentials (name and passcode).
     * 
     * @param b1 The first Bank object to compare.
     * @param b2 The second Bank object to compare.
     * @return 0 if both banks have the same credentials;
     *        -1 if b1's credentials are considered less than b2's credentials;
     *         1 if b1's credentials are considered greater than b2's credentials.
     */
    public class BankCredentialsComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            //Joke lang dili diay ko kabalo
            // Unsay icompare oi sa Credentials, passcode og name??
            // Official. Name and Passcode and credentials
        }
    }

    @Override
    public String toString() {
        return "===== Bank =====" + "\n ID: " + this.getId() + 
                                    "\n Name: " + this.getName() +
                                    "\n Passcode: " + this.getPasscode() +
                                    "\n Bank Account: " + this.getBankAccount() +
                                    "\n";
    }

}   