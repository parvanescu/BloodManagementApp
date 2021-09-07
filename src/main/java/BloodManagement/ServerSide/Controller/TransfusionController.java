package BloodManagement.ServerSide.Controller;

import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.PersonConverter;
import BloodManagement.ServerSide.Converters.TransfusionConverter;
import BloodManagement.ServerSide.DataTransferObjects.*;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Domain.Transfusion;
import BloodManagement.ServerSide.Service.TransfusionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransfusionController {
    @Autowired
    private TransfusionService service;

    @Autowired
    private TransfusionConverter transfusionConverter;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private DoctorConverter doctorConverter;


    @GetMapping(value = "/transfusions")
    public TransfusionsDTO getAllTransfusions() {
        Collection<Transfusion> transfusions = service.getAllTransfusions();
        TransfusionsDTO dto = new TransfusionsDTO(new HashSet<>());
        transfusions.forEach(transfusion -> dto.getTransfusions().add(transfusionConverter.convertModelToDto(transfusion)));
        return dto;

    }

    @GetMapping(value = "/transfusions/{id}")
    public TransfusionDTO getTransfusion(@PathVariable Integer id) {
        Transfusion transfusion = service.getTransfusion(id);
        return transfusionConverter.convertModelToDto(transfusion);
    }

    @PostMapping(value = "/transfusions")
    public TransfusionDTO addTransfusion(@RequestBody TransfusionDTO transfusionDTO) {
        Transfusion addedTransfusion = service.addTransfusion(
                transfusionDTO.getId(),
                transfusionDTO.getDonorID(),
                transfusionDTO.getReceiverID(),
                transfusionDTO.getDoctorID(),
                transfusionDTO.getUnits()
        );

        return addedTransfusion==null?null:transfusionConverter.convertModelToDto(addedTransfusion);
    }

    @GetMapping(value = "/transfusions/donors")
    public PersonsDTO getAllDonors() {
        Set<Person> donors = service.getAllDonors();
        return new PersonsDTO(donors.stream().map(personConverter::convertModelToDto).collect(Collectors.toSet()));
    }

    @GetMapping(value = "/transfusions/receivers")
    public PersonsDTO getAllReceivers() {
        Set<Person> receivers = service.getAllReceivers();
        return new PersonsDTO(receivers.stream().map(personConverter::convertModelToDto).collect(Collectors.toSet()));
    }

    @DeleteMapping(value = "/transfusions/{id}")
    public TransfusionDTO deleteTransfusion(@PathVariable Integer id) {
        Transfusion deletedTransfusion = service.deleteTransfusion(id);
        return transfusionConverter.convertModelToDto(deletedTransfusion);
    }

    @PutMapping(value = "/transfusions")
    public TransfusionDTO updateTransfusion(TransfusionDTO transfusionDTO) {
        Transfusion updatedTransfusion = service.addTransfusion(
                transfusionDTO.getId(),
                transfusionDTO.getDonorID(),
                transfusionDTO.getReceiverID(),
                transfusionDTO.getDoctorID(),
                transfusionDTO.getUnits()
        );

        return transfusionConverter.convertModelToDto(updatedTransfusion);
    }

    @GetMapping(value = "transfusions/filtered/{id}")
    public TransfusionsDTO getTransfusionsPerformedByDoctor(@PathVariable Integer id) {
        Collection<Transfusion> transfusions = service.getTransfusionsPerformedByDoctor(id);
        TransfusionsDTO dto = new TransfusionsDTO(new HashSet<>());
        transfusions.forEach(transfusion -> dto.getTransfusions().add(transfusionConverter.convertModelToDto(transfusion)));
        return dto;
    }

    @GetMapping(value = "transfusions/report")
    public TransfusionsReportDTO getDoctorsWithDonorsHandledInTransactions(){
        Map<Doctor,Collection<Person>> map = service.getDoctorsWithListOfDonorsHandled();
        TransfusionsReportDTO dto = new TransfusionsReportDTO();
        map.forEach((doctor,donors)->{
            dto.reportDTO.addDoctor(doctorConverter.convertReportModelToDto(doctor),new PersonsDTO(donors.stream().map(donor -> personConverter.convertModelToDto(donor)).collect(Collectors.toSet())));
        });
        return dto;
    }
}
