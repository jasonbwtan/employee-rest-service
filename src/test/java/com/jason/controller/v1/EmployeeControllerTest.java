package com.jason.controller.v1;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Application;
import com.example.controller.v1.EmployeeController;
import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ContextConfiguration(classes = MockServletContext.class)
public class EmployeeControllerTest {
	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeRepository employeeRepository;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(employeeController).build();
	}

	@Test
	public void testGetAllPolls() throws Exception {
		when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>());
		mockMvc.perform(get("/v1/employees")).andExpect(status().isOk()).andExpect(content().string("[]"));
	}
}
