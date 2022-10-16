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

@Controller
public class branchOfficeController {

    @Autowired
    private BranchOfficeServiceImpl branchOfficeService;

    // display list of employees
    //@GetMapping("/")
    @GetMapping({"/", "/getAll"})
    public String viewHomePage(Model model) {
        return findPaginated(1, "branchOfficeName", "asc", model);
    }

    //@GetMapping("/showNewBranchOfficeForm")
    @GetMapping("/add")
    public String showNewBranchOfficeForm(Model model) {
        // create model attribute to bind form data
        BranchOffice branchOffice = new BranchOffice();
        model.addAttribute("branchOffice", branchOffice);
        return "new_branchOffice";
    }

    //@PostMapping("/saveBranchOffice")
    @PostMapping("/add")
    public String saveBranchOffice(@ModelAttribute("branchOffice") BranchOffice branchOffice) {
        // save employee to database
        branchOfficeService.saveBranchOffice(branchOffice);
        return "redirect:/getAll";
    }

/*    //@GetMapping("/showFormForUpdate/{id}")
    @GetMapping("/update{pk_branchOfficeID}")
    public String getUpdate(@PathVariable( value = "pk_branchOfficeID") String pk_branchOfficeID, Model model) {
        Integer integerID = 0;
        try {
            integerID = Integer.parseInt(pk_branchOfficeID);
        }catch(Exception exc){
            exc.getMessage();
        }
        // get employee from the service
        BranchOfficeDTO branchOffice = branchOfficeService.getBranchOfficeDTOById(integerID);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("branchOffice", new BranchOffice(branchOffice.getBranchOfficeName(), branchOffice.getBranchOfficeCountry()));
        return "update_branchOffice";
    }

    //@GetMapping("/showFormForUpdate/{id}")
    @PostMapping("/update")
    public String showFormForUpdate(@ModelAttribute("branchOffice") BranchOffice branchOffice, Model model) {

        //Integer integerID = Integer.parseInt();
        //branchOffice.setPk_branchOfficeID(integerID);
        // get employee from the service
        //BranchOfficeDTO branchOffice = branchOfficeService.getBranchOfficeDTOById(integerID);
        branchOfficeService.updateByBranchOffice(branchOffice);
        // set employee as a model attribute to pre-populate the form
        //model.addAttribute("branchOffice", new BranchOffice(branchOffice.getBranchOfficeName(), branchOffice.getBranchOfficeCountry()));
        return "update_branchOffice";
    }
*/

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
        List<BranchOffice> listBranchOffice = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listBranchOffice", listBranchOffice);
        return "index";
    }

}
