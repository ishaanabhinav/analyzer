package com.example.analyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is the log controller that exposes the end points to be consumed
 * End point - http://localhost:8080/log-parser/{mapping}
 * @author: Ishaan Abhinav
 */
@Controller	// This means that this class is a Controller
@RequestMapping(path="/log-parser") // This means URL's start with /log-parser (after Application path)
public class LogController {
	@Autowired
	private LogRepository logRepository;

	@Autowired
	private LogService logService;

	/**
	 * 
	 * @param pagingCriteria
	 * @return Iterable<Log>
	 * @apiNote: This returns log entries based on pagingCriteria and sorting criteria.
	 * 
	 */
	@GetMapping(path="/all")
	public ResponseEntity<Iterable<Log>> getAllLogs(@Validated PagingCriteria pagingCriteria) {
		// This returns a JSON or XML with the log Entry
		return ResponseEntity.ok().body(logRepository.findAll(pagingCriteria.getPageable()));
	}

	/**
	 * 
	 * @param logEntry -> if no log entry, hard coded entry will be taken.
	 * @return Object<Log>
	 * @apiNote This runs a sample parse to store in database.
	 */
	@GetMapping(path="/sampleParse")
	public ResponseEntity<Log> parseSampleLog(String logEntry){
		//This Method is used to run a sample parse,to verify data if no parse is given.
		return ResponseEntity.ok().body(logService.parseLogLine(logEntry));
	}

	/**
	 * This will accept the request and parse the file in an async way.
	 * @param filePath -> Pass the file path to parse
	 * @return accepted response 202
	 */
	@GetMapping(path="/parseLog")
	public ResponseEntity<String> parseLog(@RequestParam(value = "filePath", defaultValue = "") final String filePath){
		logService.runLogParser(filePath.trim());
		return ResponseEntity.accepted().body("Request is accepted");
	}
}
