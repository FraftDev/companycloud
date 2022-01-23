package DataAccess;

import Models.Company;
import Models.Account;

import java.nio.file.Path;
import java.util.*;
import java.io.*;

public class Database {
    public static final String ICON_PATH = Path.of("").toAbsolutePath() + "\\src\\Images\\icon.png";

    public static final String ACCOUNT_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\DabaseFiles\\Account_DB.csv";
    public static final String COMPANY_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\DabaseFiles\\Company_DB.csv";
    public static final String SERVER_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\Server\\";

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
