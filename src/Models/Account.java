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

    public Account(String _firstName, String _lastname, String _email, String _password, LocalDate _registeredAt, String _company, String _department, int _verified){
        firstname = _firstName;
        lastname = _lastname;
        email = _email;
        password = _password;
        registeredAt = _registeredAt;
        company = _company;
        department = _department;
        verified = _verified;
    }

    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s;%s;%s", firstname, lastname, email, password, registeredAt, company, department, verified);
    }

    public static Account Login(String _email, String _password){

        return GetAccounts().stream()
                .filter(x -> x.email.equals(_email) && x.password.equals(_password))
                .findAny()
                .orElse(null);
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
                    LocalDate.parse(accountSplit[4], DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMAN)),
                    accountSplit[5],
                    accountSplit[6],
                    Integer.parseInt(accountSplit[7])
            );

            accountObjects.add(newAccount);
        }

        return accountObjects;
    }
}
