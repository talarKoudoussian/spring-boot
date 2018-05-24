package employee.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmployeeControllerTest {

    private MockMvc mockMvc;

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
    }

    @Test
    public void testGetEmployeeSuccess() throws Exception {
        Employee emp = new Employee();
        emp.setEmployeeId(Long.valueOf(1));
        emp.setFirstName("Test");
        emp.setLastName("One");
        emp.setAddedDate("2018-05-24T09:36:00.000");
        emp.setEmploymentStatus(Boolean.TRUE);

        when(employeeRepository.findById(emp.getEmployeeId())).thenReturn(java.util.Optional.ofNullable(emp));

        mockMvc.perform(get("/employees/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.employeeId", is(Integer.valueOf(1))))
                .andExpect(jsonPath("$.firstName", is(emp.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(emp.getLastName())))
                .andExpect(jsonPath("$.addedDate", is(emp.getAddedDate())))
                .andExpect(jsonPath("$.employmentStatus", is(emp.getEmploymentStatus())));

        verify(employeeRepository, times(1)).findById(emp.getEmployeeId());
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
    public void testDeleteEmployeeSuccess() throws Exception {
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
}
