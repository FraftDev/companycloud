package Models;

import DataAccess.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * To handle all the copmany related matters
 */
public class Company {
    public int id;
    public String name;
    public String adresse;
    public String domain;
    public String abteilung1;
    public String abteilung2;

    /**
     * @param _id Company ID from the Database
     * @param _name Name of the Company
     * @param _adresse Adress of the Company
     * @param _domain domain of the employees email adresses as in @aaa.aa
     * @param _abteilung1 Name of the first department
     * @param _abteilung2 Name of the second department
     */
    public Company(int _id, String _name, String _adresse, String _domain, String _abteilung1, String _abteilung2){
        id = _id;
        name = _name;
        adresse = _adresse;
        domain = _domain;
        abteilung1 = _abteilung1;
        abteilung2 = _abteilung2;
    }

    /**
     * Formats data to be csv with ;, in order to write it into a csv file
     *
     * @return formatted string
     */
    public String toString(){
        return String.format("%s;%s;%s;%s;%s;%s", id, name, adresse, domain, abteilung1, abteilung2);
    }

    /**
     * Searches for a company in the database
     *
     * @param companyName Name of Company
     * @return bool: whether the company is in the database or not
     */
    public static Company GetCompanyByName(String companyName){
        return GetCompanies().stream()
                .filter(x -> x.name.equals(companyName))
                .findAny()
                .orElse(null);
    }

    /**
     * Updates an account, by email address
     * Deletes the old one and replaces it with the provided one
     *
     * @param company the company that should be updated
     */
    public static void UpdateCompany(Company company)
    {
        List<Company> companies = GetCompanies();
        companies.removeIf(x -> x.name.equals(company.name));
        companies.add(company);

        Database.UpdateCompanies(companies);
    }

    /**
     * Gets the current companies
     *
     * @return ArrayList of company objects
     */
    public static List<Company> GetCompanies(){
        List<Company> companyObjects = new ArrayList<>();

        var companies = Database.GetCompanies();

        if(companies.size() == 0)
            return null;

        for(String company : companies){
            String[] companySplit = company.split(";");

            Company newCompany = new Company(
                    Integer.parseInt(companySplit[0]),
                    companySplit[1],
                    companySplit[2],
                    companySplit[3],
                    companySplit[4],
                    companySplit[5]
            );

            companyObjects.add(newCompany);
        }

        return companyObjects;
    }
}
