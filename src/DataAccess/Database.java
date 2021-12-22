package DataAccess;

import java.nio.file.Path;
import java.util.*;
import java.io.*;

public class Database {
    public static final String ICON_PATH = Path.of("").toAbsolutePath() + "\\src\\Images\\icon.png";

    private static final String ACCOUNT_PATH = Path.of("").toAbsolutePath() + "\\src\\DataAccess\\DabaseFiles\\Account_DB.csv";

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
}
