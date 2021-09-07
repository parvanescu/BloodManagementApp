package BloodManagement.ServerSide.controller;

import BloodManagement.ServerSide.Controller.DoctorController;
import BloodManagement.ServerSide.Controller.TransfusionController;
import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.TransfusionConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.TransfusionDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Transfusion;
import BloodManagement.ServerSide.Service.DoctorService;
import BloodManagement.ServerSide.Service.TransfusionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;

public class TransfusionControllerTest {
    private MockMvc mockMvc;


    @InjectMocks
    private TransfusionController transfusionController;

    @Mock
    private TransfusionService transfusionService;

    @Mock
    private TransfusionConverter transfusionConverter;


    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transfusionController).build();

    }


    private String toJsonString(DoctorDTO dto){
        try{
            return new ObjectMapper().writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void initData(){

    }

    private TransfusionDTO createTransfusionDTO(Transfusion transfusion){
        TransfusionDTO transfusionDTO = TransfusionDTO.builder()
                .doctorID(transfusion.getDoctor().getId())
                .donorID(transfusion.getDonor().getId())
                .receiverID(transfusion.getReceiver().getId())
                .doctorID(transfusion.getDoctor().getId())
                .units(transfusion.getUnits()).build();
        transfusionDTO.setId(transfusion.getId());
        return transfusionDTO;
    }
}
