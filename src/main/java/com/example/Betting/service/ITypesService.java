package com.example.Betting.service;

import java.util.Collection;

import com.example.Betting.model.Types;

/**
 * The Interface ITypesService.
 */
public interface ITypesService {

	/**
	 * Find all types.
	 *
	 * @return the collection of odd types
	 */
	Collection<Types> findAllTypes();
}
