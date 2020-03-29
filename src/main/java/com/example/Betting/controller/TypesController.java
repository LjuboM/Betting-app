package com.example.Betting.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Betting.model.Types;
import com.example.Betting.service.TypesService;

@RestController
@RequestMapping("/api")
public class TypesController {

	@Autowired
	TypesService typesService;
	

	@GetMapping("/types")
	ResponseEntity<?> getTypes(){
		Collection<Types> allTypes = typesService.findAllTypes();
		return ResponseEntity.status(HttpStatus.OK).body(allTypes);
	}
	
}
