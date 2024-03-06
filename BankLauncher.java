import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class BankLauncher {
  private final static ArrayList<Bank> Banks = new ArrayList<>();
  private static Bank loggedBank = null;

  public static ArrayList<Bank> getBanks() {
    return Banks;
  }

  public static Bank getLoggedBank() {
    return loggedBank;
  }

  public static void setLoggedBank(Bank loggedBank) {
    BankLauncher.loggedBank = loggedBank;
  }

  public static boolean isLogged() {
    // Complete this method
    return loggedBank != null;
  }

  /**
   * Bank interaction initialization.
   * Utilized only when log in
   */
  public static void bankInit() {
    // Complete this method

    // Wait dili siya mo loop. While? or Do-While?
    // Wait if iutilize rani when logging in, aha lugar ta magbutang
    // og initialization for bank login og Create new Bank?

    if (isLogged()) {
      System.out.println("\n[1] Show Accounts");
      System.out.println("[2] Create New Account");
      System.out.println("[3] Logout");
      System.out.print("Enter your choice: ");
      Scanner prompt = new Scanner(System.in);
      int choice = prompt.nextInt();

      switch (choice) {
        case 1:
          showAccounts();
          break;
        case 2:
          newAccounts();
          break;
        case 3:
          logout();
          break;
        default:
          System.out.println("Invalid choice. Please choose from the given options and try again.");
      }
    } else {
      System.out.println("Error. Please log in first.");
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
    // Complete this method
    System.out.println("\n[1] Credit Accounts");
    System.out.println("[2] Savings Accounts");
    System.out.println("[3] All");
    System.out.print("Enter your choice: ");
    Scanner prompt = new Scanner(System.in);
    int choice = prompt.nextInt();

    // Mag case 0 pata para mo exit sa menu?

    switch (choice) {
      case 1:
        // Loop dani guro basta tawagon natong CreditAccount
        break;
      case 2:
        // Loop japon pero target si SavingsAccount
        break;
      case 3:
        // Loop pero si Account mismo ang tawagon
        break;
      default:
        System.out.println("Invalid choice. Please choose from the given options and try again.");
    }
  }

  /**
   * 
   * Bank Interaction when creating new accounts for the currently logged in bank.
   * 
   */
  private static void newAccounts() {
    // Complete this method
    System.out.println("\n[1] Create New Credit Account");
    System.out.println("[2] Create New Savings Account");
    System.out.print("Enter your choice: ");
    Scanner prompt = new Scanner(System.in);
    int choice = prompt.nextInt();

    // Same dani sa mag case 0 pata?

    switch (choice) {
      case 1:
        // Tawagon si createNewCreditAccount() sa Bank.java
        break;
      case 2:
        // Tawagon si createNewSavingsAccount() sa Bank.java
        break;
      default:
        System.out.println("Invalid choice. Please choose from the given options and try again.");
    }

  }

  /**
   * Bank interaction when attempting to login to the banking module using a bank
   * user's credentials
   */
  public static void bankLogin() {
    // Complete this method
  }

  /**
   * Creates a new login session for the logged in bank user. Sets up a new value
   * for the loggedBank field.
   * 
   * @param b - Bank user that successfully logged in
   */
  private static void setLogSession(Bank b) {
    // Complete this method
  }

  /**
   * Destroys the login session for the current user.
   */
  private static void logout() {
    // Complete this method

  }

  /**
   * 
   * Creates a new bank record.
   * Utilized separately from the rest of the methods of this class.
   * 
   * @throws NumberFormatException May happen when inputting deposit, withdraw,
   *                               credit limit, and processing fee.
   * 
   */
  public static void createNewBank() {
    // Complete this method
  }

  /**
   * Output a menu of all registered or created banks in this session
   */
  public static void showBanksMenu() {
    // Complete this method
  }

  /**
   * Adds new bank to array list of banks.
   * 
   * @param b - Bank object to be added.
   */
  private static void addBank(Bank b) {
    // Complete this method
  }

  /**
   * Checks if a bank exists based on some criteria.
   * 
   * @param bankComparator - Criteria for searching
   * @param bank           - Bank object to be compared
   * @return Bank object if it passes the criteria. Null if none
   */
  public static Bank getBank(Comparator<Bank> comparator, Bank bank) {
    // Complete this method
  }

  /**
   * Finds the Account object based on some account number on all registered
   * banks.
   * 
   * @param accountNum - Account number of target Account.
   * @return Account object if it exists. Null if not found.
   */
  public static Account findAccount(String accountNum) {
    // Complete this method
  }

  /**
   * Get number of currently registered banks.
   * 
   * @return Intger number of banks
   */
  public static int bankSize() {
    // Complete this method
  }
}
