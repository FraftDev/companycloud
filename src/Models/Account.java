package Models;
import DataAccess.Database;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.time.*;
import java.time.format.*;

/**
 * To handle all Account related matters
 */
public class Account {
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public LocalDate registeredAt;
    public String company;
    public String department;
    public int verified;
    public boolean isAdmin;

    /**
     * @param _firstName first name
     * @param _lastname last name
     * @param _email email adress of the form aaa@aaa.aa
     * @param _password password for logging into the app
     * @param _registeredAt Date of registration
     * @param _company Company, the User belongs to (must be in Copmany Database)
     * @param _department Department, the User belongs to
     * @param _verified whether he is verifies
     * @param _isAdmin whether he is the admin
     */
    public Account(String _firstName, String _lastname, String _email, String _password, LocalDate _registeredAt, String _company, String _department, int _verified, int _isAdmin){
        firstname = _firstName;
        lastname = _lastname;
        email = _email;
        password = _password;
        registeredAt = _registeredAt;
        company = _company;
        department = _department;
        verified = _verified;
        isAdmin = _isAdmin == 1 ? true : false;
    }

    /**
     * Formats data to be csv with ;, in order to write it into a csv file
     *
     * @return formatted string
     */
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s", firstname, lastname, email, password, registeredAt, company, department, verified, isAdmin ? 1 : 0);
    }

    /**
     * Verifies a Login attempt
     *
     * @param _email email adress of user
     * @param _password password of user
     * @return boolean of mail/password pair was found
     */
    public static Account Login(String _email, String _password){

        return GetAccounts().stream()
                .filter(x -> x.email.equals(_email) && x.password.equals(_password))
                .findAny()
                .orElse(null);
    }

    /**
     * Adds a line to the los
     *
     * @param pathToLog path to the log.txt file
     * @param messageToLog message to be added
     */
    public void Log(String pathToLog, String messageToLog){
        try{
            FileWriter writer = new FileWriter(pathToLog, true);
            writer.write("\nNutzer: " + this.email + " | Message: " + messageToLog);
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Registers a new User, by adding it to the Account Database
     *
     * @param firstname first name
     * @param lastname last name
     * @param email email adress of the form aaa@aaa.aa
     * @param password desired password
     * @param companyName Name of the Company
     * @return the new account
     */
    public static Account Register(String firstname, String lastname, String email, String password, String companyName)
    {
        var accounts = GetAccounts();

        if(accounts.stream().filter(x -> x.email.equals(email)).findAny().isPresent())
            return null;

        var companyCount = accounts.stream().filter(x -> x.company.equals(companyName)).count();

        if(companyCount >= 10)
            return null;

        if(Company.GetCompanyByName(companyName) == null)
            return null;

        Account registeredAccount = new Account(firstname, lastname, email, password, LocalDate.now(), companyName, Company.GetCompanyByName(companyName).abteilung1, 0, 0);
        Database.WriteDatabase(registeredAccount.toString(), Database.ACCOUNT_PATH);

        return registeredAccount;
    }

    /**
     * Updates an account, by email address
     * Deletes the old one and replaces it with the provided one
     *
     * @param account the account that should be updated
     */
    public static void UpdateAccount(Account account)
    {
        List<Account> accounts = GetAccounts();
        accounts.removeIf(x -> x.email.equals(account.email));
        accounts.add(account);

        Database.UpdateAccounts(accounts);
    }

    /**
     * Deletes an account, by looking for the email address
     *
     * @param account
     */
    public static void DeleteAccount(Account account)
    {
        List<Account> accounts = GetAccounts();
        accounts.removeIf(x -> x.email.equals(account.email));

        Database.UpdateAccounts(accounts);
    }

    /**
     * Gets the current accounts
     *
     * @return ArrayList of account objects
     */
    public static List<Account> GetAccounts(){
        List<Account> accountObjects = new ArrayList<>();

        var accounts = Database.GetAccounts();

        if(accounts.size() == 0)
            return null;

        for(String account : accounts){
            String[] accountSplit = account.split(";");

            Account newAccount = new Account(
                    accountSplit[0],
                    accountSplit[1],
                    accountSplit[2],
                    accountSplit[3],
                    LocalDate.parse(accountSplit[4], DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.GERMAN)),
                    accountSplit[5],
                    accountSplit[6],
                    Integer.parseInt(accountSplit[7]),
                    Integer.parseInt(accountSplit[8])
            );

            accountObjects.add(newAccount);
        }

        return accountObjects;
    }
}