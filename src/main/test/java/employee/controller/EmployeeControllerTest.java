package employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import employee.controller.EmployeeController;
import employee.data.Employee;
import employee.repository.EmployeeRepository;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;
    private Employee employee;
    private String jsonEmployee;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .addFilters(new CorsFilter())
                .build();

        employee = new Employee();
        employee.setFirstName("Test");
        employee.setLastName("One");
        employee.setAddedDate("2018-05-24T09:36:00.000");
        employee.setEmploymentStatus(Boolean.TRUE);

        jsonEmployee = asJsonString(employee);
    }

    @Test
    public void testGetEmployee200() throws Exception {
        employee.setEmployeeId(Long.valueOf(1));
        when(employeeRepository.findById(employee.getEmployeeId())).thenReturn(java.util.Optional.ofNullable(employee));

        mockMvc.perform(get("/employees/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.employeeId", is(Integer.valueOf(1))))
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.addedDate", is(employee.getAddedDate())))
                .andExpect(jsonPath("$.employmentStatus", is(employee.getEmploymentStatus())))
                .andDo(print());

        verify(employeeRepository, times(1)).findById(employee.getEmployeeId());
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testGetEmployee404() throws Exception {
        when(employeeRepository.findById(Long.valueOf(1))).thenReturn(Optional.empty());

        mockMvc.perform(get("/employees/{id}", "1"))
                .andExpect(status().isNotFound());

        verify(employeeRepository, times(1)).findById(Long.valueOf(1));
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testDeleteEmployee200() throws Exception {
        when(Boolean.valueOf(employeeRepository.existsById(Long.valueOf(1)))).thenReturn(Boolean.TRUE);
        doNothing().when(employeeRepository).deleteById(Long.valueOf(1));

        mockMvc.perform(delete("/employees/{id}", "1"))
                .andExpect(status().isOk());

        verify(employeeRepository, times(1)).existsById(Long.valueOf(1));
        verify(employeeRepository, times(1)).deleteById(Long.valueOf(1));
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testDeleteEmployee404() throws Exception {
        when(Boolean.valueOf(employeeRepository.existsById(Long.valueOf(1)))).thenReturn(Boolean.FALSE);

        mockMvc.perform(delete("/employees/{id}", "1"))
                .andExpect(status().isNotFound());

        verify(employeeRepository, times(1)).existsById(Long.valueOf(1));
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void testAddEmployee200() throws Exception {
        when(employeeRepository.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employees")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(jsonEmployee))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(employeeRepository).save(refEq(employee));
        verifyNoMoreInteractions(employeeRepository);
    }

    public static String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
