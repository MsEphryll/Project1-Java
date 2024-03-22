package Banks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Main.*;
import Accounts.*;

public class BankLauncher {

    private static final ArrayList<Bank> Banks = new ArrayList<>();
    private static Bank loggedBank = null;

    public static ArrayList<Bank> getBanks() {
        return Banks;
    }

    public static boolean isLogged() {
        return loggedBank != null;
    }

    /**
     * Bank interaction initialization.
     * Utilized only when log in
     */
    public static void bankInit() {

        showBanksMenu(); // Show Registered Banks. Pero wala sa test run
        bankLogin();

        if (isLogged()) {
            while (true) {

                Main.showMenuHeader("Bank Menu");
                Main.showMenu(Menu.BankMenu.menuIdx);
                Main.setOption();

                int option = Main.getOption();

                if (option == 1) {
                    showAccounts();
                }

                else if (option == 2) {
                    newAccounts();
                }

                else if (option == 3) {
                    logout();
                    break;
                }

                else {
                    System.out.println("Invalid option! Please choose from the options below.");
                }
            }
        }
    }

    /**
     * Show the accounts registered to this bank.
     * Must prompt the user to select which type of accounts to show:
     * (1) Credit Accounts
     * (2) Savings Accounts
     * (3) All
     */
    private static void showAccounts() {

        Main.showMenuHeader("Show Accounts");
        Main.showMenu(Menu.ShowAccounts.menuIdx);
        Main.setOption();

        int option = Main.getOption();
        if (option == 1) {
            loggedBank.showAccounts(CreditAccount.class);
        } else if (option == 2) {
            loggedBank.showAccounts(SavingsAccount.class);
        } else if (option == 3) {
            loggedBank.showAccounts(Account.class);
        }

        else if (option == 4) {
            return;
        } else {
            System.out.println("Invalid option!");
        }
    }

    /**
     * Bank Interaction when creating new accounts for the currently logged in bank.
     */
    private static void newAccounts() {

        Main.showMenuHeader("New Accounts");
        Main.showMenu(Menu.AccountTypeSelection.menuIdx);
        Main.setOption();

        int option = Main.getOption();
        if (option == 1) {
            loggedBank.createNewCreditAccount();
            System.out.println("New Credit Account created successfully!");
        } else if (option == 2) {
            loggedBank.createNewSavingsAccount();
            System.out.println("New Savings Account created successfully!");
        } else {
            System.out.println("Invalid option!");
        }
    }

    /**
     * Bank interaction when attempting to login to the banking module using a bank
     * user's credentials
     */
    public static void bankLogin() {
        Scanner input = new Scanner(System.in);

        // Prompt user to enter Bank ID
        System.out.print("Enter Bank ID: ");
        String bankIDInput = input.nextLine();

        try {
            // Convert the input to an integer
            int bankID = Integer.parseInt(bankIDInput);

            System.out.print("Enter Bank Name: ");
            String bankName = input.nextLine();

            if (bankName != null) {
                System.out.print("Enter Bank Passcode: ");
                String passcode = input.nextLine();

                // Error Handler siguro if walay input

                Bank dummyBank = new Bank(bankID, bankName, passcode); // For reference pero oi ambot

                Bank bank = getBank(dummyBank.new BankCredentialsComparator(), new Bank(bankID, bankName, passcode));
                if (bank != null) {
                    setLogSession(bank);
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid credentials!");
                }
            } else {
                System.out.println("Bank ID does not exist.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Bank ID must be numeric.");
        }
        // String bankName = Main.prompt("Enter Bank Name: ", true);

        // // Error Handler siguro if walay input

        // String passcode = Main.prompt("Enter Bank Passcode: ", true);

        // // Error Handler siguro if walay input

        // Bank dummyBank = new Bank(0, bankName, passcode); // For reference pero oi
        // ambot

        // Bank bank = getBank(dummyBank. new BankCredentialsComparator(), new Bank(0,
        // bankName, passcode));

        // if (bank != null)
        // {
        // setLogSession(bank);
        // System.out.println("Login successful!");
        // }

        // else
        // {
        // System.out.println("Invalid credentials!");
        // }
    }

    /**
     * Creates a new login session for the logged in bank user. Sets up a new value
     * for the loggedBank field.
     * 
     * @param b - Bank user that successfully logged in
     */
    private static void setLogSession(Bank b) {
        loggedBank = b;
    }

    /**
     * Destroys the login session for the current user.
     */
    private static void logout() {
        loggedBank = null;
        System.out.println("Logged out successfully!");
    }

    /**
     * Creates a new bank record.
     * Utilized separately from the rest of the methods of this class.
     * 
     * @throws NumberFormatException May happen when inputting deposit, withdraw,
     *                               credit limit, and processing fee.
     */
    public static void createNewBank() {

        String bankName = Main.prompt("Enter Bank Name: ", true);

        // Error Handler siguro if walay input

        String passcode = Main.prompt("Enter Bank Passcode: ", true);

        // Error Handler siguro if walay input

        Bank newBank = new Bank(bankSize() + 1, bankName, passcode);
        addBank(newBank);
        System.out.println("New bank successfully added!");
    }

    /**
     * Output a menu of all registered or created banks in this session
     */
    public static void showBanksMenu() {
        Main.showMenuHeader("Registered Banks");
        System.out.println("[0] Back");
        for (Bank bank : Banks) {
            System.out.println("[" + bank.getId() + "] " + bank.getName());
        }
    }

    /**
     * Adds new bank to array list of banks.
     * 
     * @param b - Bank object to be added.
     */
    private static void addBank(Bank b) {
        Banks.add(b);
    }

    /**
     * Checks if a bank exists based on some criteria.
     * 
     * @param bankComparator - Criteria for searching
     * @param bank           - Bank object to be compared
     * 
     * @return Bank object if it passes the criteria. Null if none
     */
    public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
        for (Bank b : Banks) {
            if (comparator.compare(b, bank) == 0) {
                return b;
            }
        }
        return null;
    }

    /**
     * Finds the Account object based on some account number on all registered
     * banks.
     * 
     * @param accountNum - Account number of target Account.
     * 
     * @return Account object if it exists. Null if not found.
     */
    public static Account findAccount(String accountNum) {
        for (Bank bank : Banks) {
            Account account = bank.getBankAccount(bank, accountNum);
            if (account != null) {
                return account;
            }
        }
        return null;
    }

    /**
     * Get number of currently registered banks.
     * 
     * @return Integer number of banks
     */
    public static int bankSize() {
        return Banks.size();
    }
}
