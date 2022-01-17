package Models;

import DataAccess.Database;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Company {
    public int id;
    public String name;
    public String adresse;
    public String domain;
    public String abteilung1;
    public String abteilung2;

    public Company(int _id, String _name, String _adresse, String _domain, String _abteilung1, String _abteilung2){
        id = _id;
        name = _name;
        adresse = _adresse;
        domain = _domain;
        abteilung1 = _abteilung1;
        abteilung2 = _abteilung2;
    }

    public static Company GetCompanyByName(String companyName){
        return GetCompanies().stream()
                .filter(x -> x.name.equals(companyName))
                .findAny()
                .orElse(null);
    }

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
