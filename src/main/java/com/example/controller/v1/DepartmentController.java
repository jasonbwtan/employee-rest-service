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

import com.example.domain.Department;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.DepartmentRepository;

@RestController("departmentController")
@RequestMapping("/v1/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createDepartment(@Valid @RequestBody Department department) {
		department = departmentRepository.save(department);
		// set location header to communicate location for this resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newEmployeeUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(department.getId()).toUri();
		responseHeaders.setLocation(newEmployeeUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<?> findByEmployeeId(@PathVariable long departmentId) {
		Department department = departmentRepository.findOne(departmentId);
		return new ResponseEntity<>(department, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(departmentRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{departmentId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDepartment(@Valid @RequestBody Department department, @PathVariable Long departmentId)
			throws ResourceNotFoundException {
		if(departmentRepository.exists(departmentId)){
		departmentRepository.save(department);
		return new ResponseEntity<>(HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException();
		}
	}
	
	@RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@PathVariable Long departmentId)
			throws ResourceNotFoundException {
		if(departmentRepository.exists(departmentId)){
			departmentRepository.delete(departmentId);
			return new ResponseEntity<>(HttpStatus.OK);		
		} else {
			throw new ResourceNotFoundException();
		}

	}


}
