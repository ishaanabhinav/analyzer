package com.example.analyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/log-parser") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired
	private LogRepository logRepository;

	@Autowired
	private LogService logService;

	// @PostMapping(path="/add") // Map ONLY POST Requests
	// public @ResponseBody String addNewUser (@RequestParam String name
	// 		, @RequestParam String email) {
	// 	// @ResponseBody means the returned String is the response, not a view name
	// 	// @RequestParam means it is a parameter from the GET or POST request

	// 	User n = new User();
	// 	n.setName(name);
	// 	n.setEmail(email);
	// 	userRepository.save(n);
	// 	return "Saved";
	// }

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Log> getAllUsers() {
		// This returns a JSON or XML with the users
		return logRepository.findAll();
	}

	@GetMapping(path="/sampleParse")
	public ResponseEntity<Log> parseSampleLog(){
		return ResponseEntity.ok().body(logService.parseLogLine());
	}

	@GetMapping(path="/parseLog")
	public ResponseEntity<String> parseLog(@RequestParam(value = "filePath", defaultValue = "") final String filePath){
		logService.runLogParser(filePath.trim());
		return ResponseEntity.accepted().body("Request is accepted");
	}
}