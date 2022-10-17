package cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.services;

import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.domain.BranchOffice;
import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.dto.BranchOfficeDTO;
import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.repository.BranchOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchOfficeServiceImpl implements BranchOfficeService {

    @Autowired
    private BranchOfficeRepository branchOfficeRepository;
    //no es pot posar aquí el dto perque necessita cada una de les "branchOffice",
    // llavors ho poso en cada mètode de la classe.

    @Override
    public BranchOfficeDTO convertDataIntoDTO(BranchOffice branchOffice){
        BranchOfficeDTO dto = new BranchOfficeDTO(branchOffice.getPk_branchOfficeID(), branchOffice.getBranchOfficeName(), branchOffice.getBranchOfficeCountry());
        return dto;
    }

    @Override
    public List<BranchOfficeDTO> getAllBranchOffice() {
        List<BranchOffice> k = branchOfficeRepository.findAll();
        System.out.println(k);
        List<BranchOfficeDTO> bodto = k.stream().map(x -> convertDataIntoDTO(x)).collect(Collectors.toList());
        return bodto;
    }

    @Override
    public void saveBranchOffice(BranchOffice branchOffice) {
        this.branchOfficeRepository.save(branchOffice);
    }

    @Override
    public BranchOfficeDTO getBranchOfficeDTOById(Integer id) {
        Optional<BranchOffice> optional = branchOfficeRepository.findById(id);
        BranchOfficeDTO dto = convertDataIntoDTO(optional.get());
        BranchOfficeDTO branchOfficeDTO = null;
        if (optional.isPresent()) {
            branchOfficeDTO = dto;
        } else {
            throw new RuntimeException(" Branch Office not found for id :: " + id);
        }
        return branchOfficeDTO;
    }

    @Override
    public void deleteBranchOfficeDTOById(Integer id) {
        this.branchOfficeRepository.deleteById(id);
    }

    @Override
    public Page<BranchOffice> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.branchOfficeRepository.findAll(pageable);
    }


    public BranchOfficeDTO updateByBranchOffice(BranchOffice branchOffice){
        BranchOffice bo = this.branchOfficeRepository.findById(branchOffice.getPk_branchOfficeID()).get();
        bo.setBranchOfficeName(branchOffice.getBranchOfficeName());
        bo.setBranchOfficeCountry(branchOffice.getBranchOfficeCountry());
        BranchOffice boFromSave = branchOfficeRepository.save(bo);
        return convertDataIntoDTO(boFromSave);
    }
}
