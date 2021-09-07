package BloodManagement.ServerSide.Converters;


import BloodManagement.ServerSide.DataTransferObjects.BaseDto;
import BloodManagement.ServerSide.Domain.BaseEntity;

/**
 * Created by radu.
 */

public interface Converter<Model extends BaseEntity<Integer>,DTO extends BaseDto> {

    Model convertDtoToModel(DTO dto);

    DTO convertModelToDto(Model model);
}

