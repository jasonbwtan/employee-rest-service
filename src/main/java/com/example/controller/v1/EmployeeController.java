package com.example.controller.v1;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domain.Employee;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;

@RestController("employeeController")
@RequestMapping("/v1/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@Valid @RequestBody Employee employee) {

		// persist employee data to database
		employee = employeeRepository.save(employee);

		// set location header to communicate location for this resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newEmployeeUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employee.getId()).toUri();
		responseHeaders.setLocation(newEmployeeUri);

		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<?> findByEmployeeId(@PathVariable long employeeId) {
		Employee employee = employeeRepository.findOne(employeeId);
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
	
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable Long employeeId)
			throws ResourceNotFoundException {
		if(employeeRepository.exists(employeeId)){
		employeeRepository.save(employee);
		return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId)
			throws ResourceNotFoundException {
		if(employeeRepository.exists(employeeId)){
			employeeRepository.delete(employeeId);
			return new ResponseEntity<>(HttpStatus.OK);		
		} else {
			throw new ResourceNotFoundException();
		}

	}


}
