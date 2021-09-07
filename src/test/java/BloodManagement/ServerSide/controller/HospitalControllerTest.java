package BloodManagement.ServerSide.controller;

import BloodManagement.ServerSide.Controller.DoctorController;
import BloodManagement.ServerSide.Controller.HospitalController;
import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.HospitalConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.DoctorsDTO;
import BloodManagement.ServerSide.DataTransferObjects.HospitalDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Hospital;
import BloodManagement.ServerSide.Service.DoctorService;
import BloodManagement.ServerSide.Service.HospitalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.stream.Collectors;

import static org.mockito.MockitoAnnotations.initMocks;

public class HospitalControllerTest {
    private MockMvc mockMvc;


    @InjectMocks
    private HospitalController hospitalController;

    @Mock
    private HospitalService hospitalService;

    @Mock
    private HospitalConverter hospitalConverter;

    @Mock
    private DoctorConverter doctorConverter;


    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(hospitalController).build();
        initData();
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

    private HospitalDTO createHospitalDTO(Hospital hospital){
        HospitalDTO hospitalDTO = HospitalDTO.builder()
                .address(hospital.getAddress())
                .capacity(hospital.getCapacity())
                .doctors(new DoctorsDTO(hospital.getDoctors().stream().map(doctor -> doctorConverter.convertModelToDto(doctor)).collect(Collectors.toSet())))
                .build();
        hospitalDTO.setId(hospital.getId());
        return hospitalDTO;
    }
}
