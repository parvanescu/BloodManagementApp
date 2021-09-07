package BloodManagement.ServerSide.Controller;

import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.HospitalConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.DoctorsDTO;
import BloodManagement.ServerSide.DataTransferObjects.HospitalDTO;
import BloodManagement.ServerSide.DataTransferObjects.HospitalsDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// Commit for presence

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DoctorController {
    @Autowired
    private DoctorService service;

    @Autowired
    private DoctorConverter doctorConverter;

    @Autowired
    private HospitalConverter hospitalConverter;
    
    public DoctorController(){
    }

    @PostMapping(value = "/doctors")
    public DoctorDTO handleAddDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doc = service.addDoctor(
                doctorDTO.getId(),
                doctorDTO.getFullName(),
                LocalDate.parse(doctorDTO.getPracticingSince()),
                doctorDTO.getHospitalId()
        );
        System.out.println("Presence commit");

        return doc == null ? null:doctorConverter.convertModelToDto(doc);
    }
    @GetMapping(value = "/doctors/{id}")
    public DoctorDTO handleGetDoctor(@PathVariable Integer id) {
        return doctorConverter.convertModelToDto(service.getDoctor(id));
    }

    @GetMapping(value = "/doctors")
    public DoctorsDTO handleGetAllDoctors() {
        Collection<Doctor> doctors = service.getAllDoctors();
        return new DoctorsDTO(doctors.stream().map(doctor -> doctorConverter.convertModelToDto(doctor)).collect(Collectors.toSet()));
    }

    @GetMapping(value = "/doctors/filter/{minYearsOfExperience}")
    public DoctorsDTO handleGetDoctorsWithMinimumExperience(@PathVariable Integer minYearsOfExperience) {
            List<Doctor> doctors = service.getDoctorsWithMinimumExperience(minYearsOfExperience);
            return new DoctorsDTO(doctors.stream().map(doctor -> doctorConverter.convertModelToDto(doctor)).collect(Collectors.toSet()));
    }

    @PutMapping(value = "/doctors")
    public DoctorDTO handleUpdateDoctor(@RequestBody DoctorDTO doctorDTO) {
            return doctorConverter.convertModelToDto(service.updateDoctor(
                    doctorDTO.getId(),
                    doctorDTO.getFullName(),
                    doctorDTO.getHospitalId(),
                    LocalDate.parse(doctorDTO.getPracticingSince())
            ));
    }

    @DeleteMapping(value = "/doctors/{id}")
    public DoctorDTO handleDeleteDoctor(@PathVariable Integer id) {
        return doctorConverter.convertModelToDto(service.deleteDoctor(id));
    }

    @GetMapping(value = "/doctors/reports")
    public HospitalsDTO handleGroupDoctorsByHospital() {
        Collection<Hospital> hospitalsReport = service.groupDoctorsByHospital();
        HospitalsDTO dto = new HospitalsDTO(new HashSet<>());
        hospitalsReport.forEach(hospital ->
        dto.getHospitals().add(hospitalConverter.convertModelToDto(hospital)));
        return dto;
    }
}
