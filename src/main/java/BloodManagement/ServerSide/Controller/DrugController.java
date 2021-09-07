package BloodManagement.ServerSide.Controller;

import BloodManagement.ServerSide.Converters.DrugConverter;
import BloodManagement.ServerSide.DataTransferObjects.DrugDTO;
import BloodManagement.ServerSide.DataTransferObjects.DrugsDTO;
import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.Service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DrugController {
    @Autowired
    private DrugService service;
    @Autowired
    private DrugConverter drugConverter;

    @PostMapping(value = "/drugs")
    public DrugDTO handleAddDrug(@RequestBody DrugDTO dto) {
        Drug addedDrug = service.addDrug(
                dto.getId(),
                dto.getName(),
                dto.getRecommendedAmount());
        return addedDrug == null? null:drugConverter.convertModelToDto(addedDrug);
    }

    @PutMapping(value = "/drugs")
    public DrugDTO handleUpdateDrug(@RequestBody DrugDTO dto) {
        Drug updated = service.updateDrug(
                dto.getId(),
                dto.getName(),
                dto.getRecommendedAmount());
        return drugConverter.convertModelToDto(updated);
    }

    @DeleteMapping(value = "/drugs/{id}")
    public DrugDTO handleDeleteDrug(@PathVariable Integer id) {
        Drug deletedDrug = service.deleteDrug(id);
        return drugConverter.convertModelToDto(deletedDrug);
    }

    @GetMapping(value = "/drugs/{id}")
    public DrugDTO handleGetDrug(@PathVariable Integer id) {
        Drug drug = service.getDrug(id);
        return drugConverter.convertModelToDto(drug);
    }

    @GetMapping(value = "/drugs")
    public DrugsDTO handleGetAllDrugs() {
        Collection<Drug> drugs = service.getAllDrugs();
        return new DrugsDTO(drugs.stream().map(drugConverter::convertModelToDto).collect(Collectors.toSet()));
    }

    @GetMapping(value = "/drugs/filtered/{amount}")
    public DrugsDTO handleGetDrugsWithDoseBiggerThan(@PathVariable Integer amount) {
        Collection<Drug> drugs = service.getDrugsWithDoseBiggerThan(amount);
        return new DrugsDTO(drugs.stream().map(drugConverter::convertModelToDto).collect(Collectors.toSet()));
    }

    @GetMapping(value = "/drugs/report")
    public DrugsDTO handleGetDrugsWithDosesOf() {
        Collection<Drug> drugs = service.getDrugsWithDosesOf();
        return new DrugsDTO(drugs.stream().map(drugConverter::convertModelToDto).collect(Collectors.toSet()));

    }
}
