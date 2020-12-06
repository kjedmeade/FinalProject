package com.skilldistillery.organmatcher.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.organmatcher.entities.Patient;
import com.skilldistillery.organmatcher.services.PatientService;

@RestController
@CrossOrigin({ "*" , "http://localhost:4210" })
@RequestMapping("api")
public class PatientController {
	
	@Autowired
	private PatientService patientSvc;
	
	@GetMapping( "patients/all" )
	public List<Patient> allPatients() {
		return patientSvc.index();
	}
	
	@GetMapping("patients/{id}")
	public Patient patientById( @PathVariable int id, HttpServletResponse res ) {
		Patient patient = patientSvc.patientById( id );
		if ( patient == null ) {
			res.setStatus(404);
		}
		
		return patient;
	}
	
	@GetMapping( "patients/blood-type/{typeId}" )
	public List<Patient> patientsByBloodType( @PathVariable int typeId, HttpServletResponse res ) {
		List<Patient> resultList = patientSvc.patientsByBloodTypeId( typeId );
		if ( resultList.size() == 0 ) {
			res.setStatus(404);
		}
		return resultList;
	}
	
	@GetMapping( "patients/transplant-type/{organ}/blood-type/{bloodId}" )
	public List<Patient> patientsByBloodAndTransplantType( @PathVariable String organ , @PathVariable int bloodId, HttpServletResponse res ) {
		List<Patient> resultList = patientSvc.patientsByBloodAndTransplantType(bloodId, organ);
//		if ( resultList.size() == 0 ) {
//			res.setStatus(404);
//		}
		return resultList;
	}
}
