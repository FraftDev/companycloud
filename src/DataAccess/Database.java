package DataAccess;

import Models.Company;
import Models.Account;

import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.util.concurrent.ConcurrentMap;

/**
 * Class to handle the Databases, including addition and deletion of entries
 */
public class Database {
    public static final String ICON_PATH = Path.of("").toAbsolutePath() + "\\src\\Images\\icon.png";

    public static final String ACCOUNT_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\DabaseFiles\\Account_DB.csv";
    public static final String COMPANY_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\DabaseFiles\\Company_DB.csv";
    public static final String SERVER_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\Server\\";

    /**
     * Get the Available Accounts from the accountpath
     *
     * @return List with all Accounts
     */
    public static List<String> GetAccounts(){
        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ACCOUNT_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return records;
    }

    /**
     * Write a new Entry to a Database (companies or accounts)
     *
     * @param line The entry to be added
     * @param path The path to the underlying database
     */
    public static void WriteDatabase(String line, String path){
        try{
            FileWriter writer = new FileWriter(path, true);
            writer.write("\n"
                    + line);
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Update Entries in the Account Database
     *
     * @param accounts the accounts to be written into the database
     */
    public static void UpdateAccounts(List<Account> accounts){
        try{
            FileWriter writer = new FileWriter(ACCOUNT_PATH);
            for(Account account : accounts){
                writer.write(account.toString() + "\n");
            }
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Update Entries in the Company Database
     *
     * @param companies the companies to be written into the company database
     */
    public static void UpdateCompanies(List<Company> companies){
        try{
            FileWriter writer = new FileWriter(COMPANY_PATH);
            for(Company company : companies){
                writer.write(company.toString() + "\n");
            }
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Get all companies and their metadata, that are currently in the Database
     *
     * @return ArrayList with all companies
     */
    public static List<String> GetCompanies(){
        List<String> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(COMPANY_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                records.add(line);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return records;
    }
}
