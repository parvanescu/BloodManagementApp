package BloodManagement.ServerSide.Controller;

import BloodManagement.ServerSide.Converters.PrescriptionConverter;
import BloodManagement.ServerSide.DataTransferObjects.PrescriptionDTO;
import BloodManagement.ServerSide.DataTransferObjects.PrescriptionsDTO;
import BloodManagement.ServerSide.Domain.Prescription;
import BloodManagement.ServerSide.Service.PrescriptionService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PrescriptionController {
    @Autowired
    private PrescriptionService service;

    @Autowired
    private PrescriptionConverter prescriptionConverter;


    @GetMapping(value = "/prescriptions")
    public PrescriptionsDTO handleGetALlPrescriptions() {
        Collection<Prescription> prescriptions = service.getAll();
        PrescriptionsDTO dto = new PrescriptionsDTO(new HashSet<>());
        prescriptions.forEach(prescription -> dto.getPrescriptions().add(prescriptionConverter.convertModelToDto(prescription)));
        return dto;
    }

    @GetMapping(value = "/prescriptions/{id}")
    public PrescriptionDTO handleGetPrescription(@PathVariable Integer id) {
        Prescription prescription = service.getPrescription(id);
        return prescriptionConverter.convertModelToDto(prescription);
    }


    @PostMapping(value = "/prescriptions")
    public PrescriptionDTO handleAddPrescription(@RequestBody PrescriptionDTO prescriptionDTO){
        Prescription addedPrescription = service.addPrescription(
                prescriptionDTO.getId(),
                prescriptionDTO.getDoctorID(),
                prescriptionDTO.getPersonID(),
                prescriptionDTO.getDrugs()
        );
        return addedPrescription==null?null:prescriptionConverter.convertModelToDto(addedPrescription);
    }

    @DeleteMapping(value = "/prescriptions/{id}")
    public PrescriptionDTO handleDeletePrescription(Integer id) {
        Prescription deletedPrescription = service.deletePrescription(id);
        return prescriptionConverter.convertModelToDto(deletedPrescription);
    }

    @PutMapping(value = "/prescriptions")
    public PrescriptionDTO handleUpdatePrescription(PrescriptionDTO prescriptionDTO) {
        Prescription updatedPrescription = service.addPrescription(
                prescriptionDTO.getId(),
                prescriptionDTO.getDoctorID(),
                prescriptionDTO.getPersonID(),
                prescriptionDTO.getDrugs()
        );
        return prescriptionConverter.convertModelToDto(updatedPrescription);
    }

    @GetMapping(value = "/prescriptions/filteredByDay/{date}")
    public PrescriptionsDTO handleFilterPrescriptionsByDay(@PathVariable String date) {
       Collection<Prescription> prescriptions = service.filterPrescriptionsByDay(LocalDate.parse(date));
        PrescriptionsDTO dto = new PrescriptionsDTO(new HashSet<>());
        prescriptions.forEach(prescription -> dto.getPrescriptions().add(prescriptionConverter.convertModelToDto(prescription)));
       return dto;
    }

    @GetMapping(value = "/prescriptions/filteredByDrugs/{nrOfDrugs}")
    public PrescriptionsDTO handleGetPrescriptionsWithLessDrugsThan(@PathVariable Integer nrOfDrugs) {
        Collection<Prescription> prescriptions = service.getPrescriptionsWithLessDrugsThan(nrOfDrugs);
        PrescriptionsDTO dto = new PrescriptionsDTO(new HashSet<>());
        prescriptions.forEach(prescription -> dto.getPrescriptions().add(prescriptionConverter.convertModelToDto(prescription)));
        return dto;
    }

    @GetMapping(value = "/prescriptions/filteredByDate")
    public PrescriptionsDTO handleGetPrescriptionsPrescribedInTheCurrentMonth() {
        Collection<Prescription> prescriptions = service.getPrescriptionsPrescribedInTheCurrentMonth();
        PrescriptionsDTO dto = new PrescriptionsDTO(new HashSet<>());
        prescriptions.forEach(prescription -> dto.getPrescriptions().add(prescriptionConverter.convertModelToDto(prescription)));
        return dto;
    }
}
