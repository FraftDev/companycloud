package Models;
import DataAccess.Database;

import java.util.*;
import java.time.*;
import java.time.format.*;

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

    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s", firstname, lastname, email, password, registeredAt, company, department, verified, isAdmin ? 1 : 0);
    }

    public static Account Login(String _email, String _password){

        return GetAccounts().stream()
                .filter(x -> x.email.equals(_email) && x.password.equals(_password))
                .findAny()
                .orElse(null);
    }



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

    public static void UpdateAccount(Account account)
    {
        List<Account> accounts = GetAccounts();
        accounts.removeIf(x -> x.email.equals(account.email));
        accounts.add(account);

        Database.UpdateAccounts(accounts);
    }

    public static void DeleteAccount(Account account)
    {
        List<Account> accounts = GetAccounts();
        accounts.removeIf(x -> x.email.equals(account.email));

        Database.UpdateAccounts(accounts);
    }

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