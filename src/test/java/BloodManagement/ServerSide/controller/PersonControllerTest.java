package BloodManagement.ServerSide.controller;

import BloodManagement.ServerSide.Controller.DoctorController;
import BloodManagement.ServerSide.Controller.PersonController;
import BloodManagement.ServerSide.Converters.DoctorConverter;
import BloodManagement.ServerSide.Converters.PersonConverter;
import BloodManagement.ServerSide.DataTransferObjects.DoctorDTO;
import BloodManagement.ServerSide.DataTransferObjects.PersonDTO;
import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.Domain.Person;
import BloodManagement.ServerSide.Service.DoctorService;
import BloodManagement.ServerSide.Service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.MockitoAnnotations.initMocks;

public class PersonControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @Mock
    private PersonConverter personConverter;


    @Before
    public void setup() throws Exception{
        initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

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

    private PersonDTO createPersonDTO(Person person){
        PersonDTO personDTO = PersonDTO.builder()
                .fullName(person.getFullName())
                .bloodType(person.getBloodType().toString())
                .dateOfBirth(person.getDateOfBirth())
                .weight(person.getWeight())
                .build();
        personDTO.setId(person.getId());
        return personDTO;
    }
}
