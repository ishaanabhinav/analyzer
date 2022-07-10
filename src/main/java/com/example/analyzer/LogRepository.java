package com.example.analyzer;

import org.springframework.data.repository.CrudRepository;

import com.example.analyzer.Log;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LogRepository extends CrudRepository<Log, Long> {

}
