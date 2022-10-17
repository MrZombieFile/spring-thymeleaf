package cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.services;

import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.domain.BranchOffice;
import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.dto.BranchOfficeDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BranchOfficeService {

    BranchOfficeDTO convertDataIntoDTO(BranchOffice branchOffice);
    List<BranchOfficeDTO> getAllBranchOffice();
    void saveBranchOffice(BranchOffice branchOffice);
    BranchOfficeDTO getBranchOfficeDTOById(Integer id);
    void deleteBranchOfficeDTOById(Integer id);
    Page<BranchOffice> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
