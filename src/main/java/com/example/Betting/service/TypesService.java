package com.example.Betting.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Types;
import com.example.Betting.repository.TypesRepository;

@Service
public class TypesService {
	
	@Autowired
	private TypesRepository typesRepository;
	
	public Collection<Types> findAllTypes(){
		return typesRepository.findAll();
	}
}
