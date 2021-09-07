package BloodManagement.ServerSide.service;

import BloodManagement.ServerSide.Domain.Doctor;
import BloodManagement.ServerSide.ITConfig;
import BloodManagement.ServerSide.ServerSideApplication;
import BloodManagement.ServerSide.Service.DoctorService;
import BloodManagement.ServerSide.Service.HospitalService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.time.LocalDate;
import java.util.Collection;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:db-h2.properties"
)
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @Test
    public void findAll() throws Exception{
        doctorService.addDoctor(1,"aaa", LocalDate.now(),1);
        Collection<Doctor> doctorList = doctorService.getAllDoctors();
        Assert.assertEquals("there should be 2 doctors",doctorList.size(),2);
    }

}
