package com.example.Betting.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Betting.model.Types;
import com.example.Betting.repository.TypesRepository;

/**
 * The Class TypesService.
 */
@Service
public class TypesService implements ITypesService {

	/** The types repository. */
	@Autowired
	private TypesRepository typesRepository;

	/**
	 * Find all types.
	 *
	 * @return the collection of types
	 */
	public Collection<Types> findAllTypes() {
		return typesRepository.findAll();
	}
}
