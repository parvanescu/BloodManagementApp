package BloodManagement.ServerSide.controller;

import BloodManagement.ServerSide.Controller.DoctorController;
import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.DrugDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.Service.DoctorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;

public class DoctorControllerTest {

    private MockMvc mockMvc;


    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorService doctorService;

    @Mock
    private DoctorConverter doctorConverter;

    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();

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

    private DoctorDTO createDoctorDTO(Doctor doctor){
        DoctorDTO doctorDTO = DoctorDTO.builder()
                .fullName(doctor.getFullName())
                .practicingSince(doctor.getPracticingSince().toString())
                .hospital(doctor.getHospital())
                .build();
        doctorDTO.setId(doctor.getId());
        return doctorDTO;
    }
}
