package com.babel.employeeservice.infrastructure;

import com.babel.employeeservice.application.port.EmployeeUseCase;
import com.babel.employeeservice.factory.EmployeeFactory;
import com.babel.employeeservice.infrastructure.adapter.in.dto.EmployeeRequest;
import com.babel.employeeservice.infrastructure.adapter.in.web.EmployeeController;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeUseCase employeeUseCase;

    @Test
    public void addAllEmployeeTes() throws Exception {
        Mockito.when(employeeUseCase.addAllEmployee(ArgumentMatchers.anyList())).thenReturn(EmployeeFactory.getAllEmployee());

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                            {
                              "employees": [
                                {
                                  "firstName": "Miguel",
                                  "lastName": "Perez",
                                  "age": 35,
                                  "gender": "M",
                                  "birthDate": "04-05-1991",
                                  "position": "Senior Java Developer"
                                }
                              ]
                            }
                        """)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Miguel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].position").value("Java Developer Sr"));

        Mockito.verify(employeeUseCase).addAllEmployee(ArgumentMatchers.anyList());
    }

    @Test
    public void getAllEmployeesTes() throws Exception {
        Mockito.when(employeeUseCase.getAllEmployee()).thenReturn(EmployeeFactory.getAllEmployee());

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/employee")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("Miguel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].position").value("Java Developer Sr"));

        Mockito.verify(employeeUseCase).getAllEmployee();
    }

    @Test
    public void deleteEmployeeTes() throws Exception {
        Mockito.when(employeeUseCase.deleteEmployee(Mockito.any())).thenReturn(EmployeeFactory.getEmployeeResponse());

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/employee/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(employeeUseCase).deleteEmployee(1L);
    }

    @Test
    public void putEmployeeTes() throws Exception {
        Mockito.when(employeeUseCase.putEmployee(ArgumentMatchers.eq(1L), ArgumentMatchers.any(EmployeeRequest.class))).thenReturn(EmployeeFactory.getEmployeeDataResponseResponse());

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/employee/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                {
                                  "firstName": "Miguel",
                                  "lastName": "Perez",
                                  "age": 35,
                                  "gender": "M",
                                  "birthDate": "04-05-1991",
                                  "position": "Senior Java Developer"
                                }
                        """)
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(employeeUseCase).putEmployee(ArgumentMatchers.eq(1L), ArgumentMatchers.any());
    }
}
