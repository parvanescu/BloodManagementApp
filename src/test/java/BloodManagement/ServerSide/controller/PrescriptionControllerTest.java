package BloodManagement.ServerSide.controller;

import BloodManagement.ServerSide.Controller.DoctorController;
import BloodManagement.ServerSide.Controller.PrescriptionController;
import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.PrescriptionConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.PrescriptionDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Prescription;
import BloodManagement.ServerSide.Service.DoctorService;
import BloodManagement.ServerSide.Service.PrescriptionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;

public class PrescriptionControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private PrescriptionController prescriptionController;

    @Mock
    private PrescriptionService prescriptionService;

    @Mock
    private PrescriptionConverter prescriptionConverter;

    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(prescriptionController).build();

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

    private PrescriptionDTO createPrescriptionDTO(Prescription prescription){
        PrescriptionDTO prescriptionDTO = PrescriptionDTO.builder()
                .doctorID(prescription.getDoctor().getId())
                .drugs(prescription.getAllDrugIds())
                .personID(prescription.getPerson().getId())
                .releaseDate(prescription.getReleaseDate())
                .build();
        prescriptionDTO.setId(prescription.getId());
        return prescriptionDTO;
    }
}
