package cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.controller;

import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.domain.BranchOffice;
import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.dto.BranchOfficeDTO;
import cat.itacademy.barcelonactiva.Adr.s05.t01.n01.S05T01N01Adr.model.services.BranchOfficeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class branchOfficeController {

    @Autowired
    private BranchOfficeServiceImpl branchOfficeService;


    @GetMapping({"/", "/getAll"})
    public String viewHomePage(Model model) {
        return findPaginated(1, "branchOfficeName", "asc", model);
    }

    @GetMapping("/add")
    public String showNewBranchOfficeForm(Model model) {
        // create model attribute to bind form data
        BranchOfficeDTO branchOffice = branchOfficeService.convertDataIntoDTO(new BranchOffice());
        model.addAttribute("branchOffice", branchOffice);
        return "new_branchOffice";
    }

    @PostMapping("/add")
    public String saveBranchOffice(@ModelAttribute("branchOffice") BranchOffice branchOffice) {
        // save employee to database
        branchOfficeService.saveBranchOffice(branchOffice);
        return "redirect:/getAll";
    }

    @GetMapping("/update/{pk_branchOfficeID}")
    public String showFormForUpdate(@PathVariable ( value = "pk_branchOfficeID") Integer pk_branchOfficeID, @ModelAttribute("branchOffice") BranchOffice branchOffice, Model model) {

        // get employee from the service
        BranchOfficeDTO bo = branchOfficeService.getBranchOfficeDTOById(pk_branchOfficeID);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("branchOfficeDTO", bo);
        return "update";
    }


    @GetMapping("/delete/{id}")
    public String deleteBranchOffice(@PathVariable (value = "id") Integer id) {

        // call delete employee method
        this.branchOfficeService.deleteBranchOfficeDTOById(id);
        return "redirect:/getAll";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<BranchOffice> page = branchOfficeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        //I switch results into dto
        Page<BranchOfficeDTO> pageDto = page.map(x -> branchOfficeService.convertDataIntoDTO(x));
        List<BranchOfficeDTO> listBranchOfficeDto = pageDto.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBranchOffice", listBranchOfficeDto);
        return "index";
    }

}
