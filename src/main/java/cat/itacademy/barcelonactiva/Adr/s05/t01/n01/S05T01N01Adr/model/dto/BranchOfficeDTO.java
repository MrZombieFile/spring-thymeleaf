package cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.dto;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

public class BranchOfficeDTO {
    private Integer pk_branchOfficeEntityID;
    private String branchOfficeName;
    private String branchOfficeCountry;
    private String branchOfficeKind;

    private ArrayList<String> countries = new ArrayList<>(Arrays.asList("Austria", "Belgium", "Bulgaria", "Croatia", "Republic of Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland", "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia", "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal", "Romania", "Slovakia", "Slovenia", "Spain", "Sweden"));

    @Autowired
    public BranchOfficeDTO(Integer id, String branchOfficeName, String branchOfficeCountry) {
        this.pk_branchOfficeEntityID = id;
        this.branchOfficeName = branchOfficeName;
        this.branchOfficeCountry = branchOfficeCountry;
        this.branchOfficeKind = setBranchOfficeKind(branchOfficeCountry);
    }


    public String setBranchOfficeKind(String country){
        int i = 0;
        boolean found = false;
        while (i < countries.size() && found == false){
            if(countries.get(i).equalsIgnoreCase(country)){
                found = true;
            }
            i++;
        }
        String toReturn;
        if (found == true) {
            toReturn = "UE";
        }else {
            toReturn ="Abroad the UE";
        }
        return toReturn;
    }

    public Integer getPk_branchOfficeEntityID() {
        return pk_branchOfficeEntityID;
    }

    public void setPk_branchOfficeEntityID(Integer pk_branchOfficeEntityID) {
        this.pk_branchOfficeEntityID = pk_branchOfficeEntityID;
    }

    public String getBranchOfficeName() {
        return branchOfficeName;
    }

    public void setBranchOfficeName(String branchOfficeName) {
        this.branchOfficeName = branchOfficeName;
    }

    public String getBranchOfficeCountry() {
        return branchOfficeCountry;
    }

    public void setBranchOfficeCountry(String branchOfficeCountry) {
        this.branchOfficeCountry = branchOfficeCountry;
    }

    public String getBranchOfficeKind() {
        return branchOfficeKind;
    }

}
