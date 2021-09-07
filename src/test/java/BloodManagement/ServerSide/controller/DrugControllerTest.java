package BloodManagement.ServerSide.controller;

import BloodManagement.ServerSide.Controller.DoctorController;
import BloodManagement.ServerSide.Controller.DrugController;
import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.DrugConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.DrugDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Drug;
import BloodManagement.ServerSide.ServerSideApplication;
import BloodManagement.ServerSide.Service.DoctorService;
import BloodManagement.ServerSide.Service.DrugService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;

import static org.hamcrest.Matchers.is;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.Mockito.*;

@SpringBootTest
public class DrugControllerTest {
    private MockMvc mockMvc;

    private Drug drug1;
    private Drug drug2;
    private DrugDTO drugDTO1;
    private DrugDTO drugDTO2;

    @InjectMocks
    private DrugController drugController;

    @Mock
    private DrugService drugService;

    @Mock
    private DrugConverter drugConverter;

    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(drugController).build();

    }

    @Test
    public void getAllDrugs() throws Exception{
        org.mockito.Mockito.when(drugService.getAllDrugs()).thenReturn(new HashSet<>(Arrays.asList(drug1,drug2)));
        org.mockito.Mockito.when(drugConverter.convertModelToDto(drug1)).thenReturn(drugDTO1);
        org.mockito.Mockito.when(drugConverter.convertModelToDto(drug2)).thenReturn(drugDTO2);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/drugs/{id}",drug1.getId(),drugDTO1).contentType(MediaType.APPLICATION_JSON_UTF8).content(toJsonString(drugDTO1)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name",is("Paracetamol")));
    }


    private String toJsonString(DrugDTO dto){
        try{
            return new ObjectMapper().writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void initData(){
        drug1 = Drug.builder().name("Paracetamol").recommendedAmount(1).build();
        drug1.setId(1);
        drug2 = Drug.builder().name("Agocalmin").recommendedAmount(2).build();
        drug2.setId(2);

        drugDTO1 = createDrugDTO(drug1);
        drugDTO2 = createDrugDTO(drug2);
    }

    private DrugDTO createDrugDTO(Drug drug){
        DrugDTO drugDTO = DrugDTO.builder()
                .name(drug.getName())
                .recommendedAmount(drug.getRecommendedAmount())
                .build();
        drugDTO.setId(drug.getId());
        return drugDTO;
    }
}
