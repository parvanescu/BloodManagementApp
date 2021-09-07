package BloodManagement.ServerSide.Converters;

import BloodManagement.ServerSide.DataTransferObjects.DrugDTO;
import BloodManagement.ServerSide.Domain.Drug;
import org.springframework.stereotype.Component;

@Component
public class DrugConverter implements Converter<Drug, DrugDTO>{

    @Override
    public Drug convertDtoToModel(DrugDTO dto) {
        Drug drug = new Drug(dto.getName(),dto.getRecommendedAmount());
        drug.setId(dto.getId());

        return drug;
    }

    @Override
    public DrugDTO convertModelToDto(Drug drug) {
        var dto = new DrugDTO(drug.getName(), drug.getRecommendedAmount());
        dto.setId(drug.getId());
        return dto;
    }
}
