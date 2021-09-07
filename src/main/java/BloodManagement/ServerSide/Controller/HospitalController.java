package BloodManagement.ServerSide.Controller;

import BloodManagement.ServerSide.Converters.HospitalConverter;
import BloodManagement.ServerSide.DataTransferObjects.HospitalDTO;
import BloodManagement.ServerSide.DataTransferObjects.HospitalsDTO;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HospitalController {

    @Autowired
    private HospitalService service;

    @Autowired
    private HospitalConverter converter;

    @GetMapping(value = "/hospitals/{id}")
    public HospitalDTO handleGetHospital(@PathVariable Integer id) {
        return converter.convertModelToDto(service.getHospital(id));
    }

    @PostMapping(value = "/hospitals")
    public HospitalDTO handleAddHospital(@RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital = service.addHospital(
                hospitalDTO.getId(),
                hospitalDTO.getCapacity(),
                hospitalDTO.getName(),
                hospitalDTO.getAddress());
        return hospital == null?null:converter.convertModelToDto(hospital);
    }

    @GetMapping(value = "/hospitals")
    public HospitalsDTO handleGetAllHospitals() {
        return new HospitalsDTO(
                service.getAllHospitals().stream()
                        .map(hospital -> converter.convertModelToDto(hospital))
                        .collect(Collectors.toSet()));
    }

    @PutMapping(value = "/hospitals")
    public HospitalDTO handleUpdateHospital(@RequestBody HospitalDTO hospitalDTO) {
        return converter.convertModelToDto(service.updateHospital(
                hospitalDTO.getId(),
                hospitalDTO.getName(),
                hospitalDTO.getAddress(),
                hospitalDTO.getCapacity()
        ));
    }

    @DeleteMapping(value = "/hospitals/{id}")
    public HospitalDTO handleDeleteHospital(@PathVariable Integer id) {
        Hospital hospital = service.deleteHospital(id);
        return converter.convertModelToDto(hospital);

    }

    @GetMapping(value = "/hospitals/filtered/{minimumCapacity}")
    public HospitalsDTO handleGetHospitalsWithMinimumCapacity(@PathVariable Integer minimumCapacity) {
        Collection<Hospital> hospitals = service.getHospitalsWithMinimumCapacity(minimumCapacity);
        return new HospitalsDTO(
                hospitals.stream()
                        .map(hospital -> converter.convertModelToDto(hospital))
                        .collect(Collectors.toSet()));
    }

    @GetMapping(value = "/hospitals/reports")
    public Double handleAverageHospitalCapacity() {
        return service.averageHospitalCapacity();
    }
}
