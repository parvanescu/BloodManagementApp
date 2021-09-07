package BloodManagement.ServerSide.Converters;
import BloodManagement.ServerSide.DataTransferObjects.PrescriptionDTO;
import BloodManagement.ServerSide.Domain.Prescription;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionConverter implements Converter<Prescription, PrescriptionDTO> {

    @Override
    public Prescription convertDtoToModel(PrescriptionDTO dto) { return new Prescription(); }

    @Override
    public PrescriptionDTO convertModelToDto(Prescription prescription) {
        var prescriptionDto = new PrescriptionDTO(prescription.getDoctor().getId(), prescription.getPerson().getId(), prescription.getAllDrugIds(), prescription.getReleaseDate());
        prescriptionDto.setId(prescription.getId());
        return prescriptionDto;
    }
}
