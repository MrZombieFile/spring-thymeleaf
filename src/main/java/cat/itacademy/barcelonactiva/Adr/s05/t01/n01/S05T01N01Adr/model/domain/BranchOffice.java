package cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "branchOffice")
public class BranchOffice {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer pk_branchOfficeID;

    @Column(name = "branchOfficeName")
    private String branchOfficeName;

    @Column(name = "branchOfficeCountry")
    private String branchOfficeCountry;

    public BranchOffice(String branchOfficeName, String branchOfficeCountry) {
        this.branchOfficeName = branchOfficeName;
        this.branchOfficeCountry = branchOfficeCountry;
    }

    public BranchOffice() {
    }

    public Integer getPk_branchOfficeID() {
        return pk_branchOfficeID;
    }

    public void setPk_branchOfficeID(Integer pk_branchOfficeID) {
        this.pk_branchOfficeID = pk_branchOfficeID;
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
}
