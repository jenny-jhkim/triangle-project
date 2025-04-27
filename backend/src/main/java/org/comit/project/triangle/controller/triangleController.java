package org.comit.project.triangle.controller;

import java.text.ParseException;
import java.util.List;

import org.comit.project.triangle.dto.TriangleClassifyRequestDto;
import org.comit.project.triangle.dto.TriangleClassifyResponseDto;
import org.comit.project.triangle.jpa.Triangle;
import org.comit.project.triangle.service.TriangleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/triangle")
public class triangleController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TriangleService triClassifyService;
	
	@PostMapping("/classify")
	public ResponseEntity<TriangleClassifyResponseDto> checkTriangleType(@RequestBody TriangleClassifyRequestDto input) {
		
		try {
			TriangleClassifyResponseDto result = triClassifyService.classify(input);
			return ResponseEntity.ok(result);
			
		} catch (IllegalArgumentException e) {
			TriangleClassifyResponseDto error = new TriangleClassifyResponseDto();
	        error.setResult("Error");
	        error.setExplanation(e.getMessage());
	        return ResponseEntity.badRequest().body( error );
		}
	}
	@PostMapping("/save")
	public ResponseEntity<Void> saveTriangle(@RequestBody Triangle triangle) throws ParseException {
		this.logger.debug("Save/Create Triangle!");
		
		this.triClassifyService.saveTriangle(triangle);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	@GetMapping("/record")
	public List<Triangle> listTriangle() {
		return this.triClassifyService.listTriangle();
	}
	
	
}
