package com.example.analyzer;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called LogRepository
 * JpaRepository extends PagingAndSortingRepository and CRUD Repositroy.
 */
public interface LogRepository extends JpaRepository<Log, Long> {

}
