package BloodManagement.ServerSide.Converters;

import BloodManagement.ServerSide.DataTransferObjects.TransfusionDTO;
import BloodManagement.ServerSide.Domain.Transfusion;
import org.springframework.stereotype.Component;

@Component
public class TransfusionConverter implements Converter<Transfusion, TransfusionDTO> {

    @Override
    public Transfusion convertDtoToModel(TransfusionDTO dto) {
        return new Transfusion();
    }

    @Override
    public TransfusionDTO convertModelToDto(Transfusion transfusion) {
        var transfusionDto = new TransfusionDTO(transfusion.getDonor().getId(),
                transfusion.getReceiver().getId(), transfusion.getDoctor().getId(), transfusion.getUnits());
        transfusionDto.setId(transfusion.getId());
        return transfusionDto;
    }
}
