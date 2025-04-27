package org.comit.project.triangle.service;

import java.util.HashMap;
import java.util.Map;

import org.comit.project.triangle.repository.TriangleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class TriDetailsCacheService {

	private final Map<String, String> triangleDetailsMap = new HashMap<>();
	private final TriangleDetailsRepository repository;
	  
	@Autowired
	public TriDetailsCacheService(TriangleDetailsRepository repository) {
	    this.repository = repository;
	}
	  
	@EventListener(ApplicationReadyEvent.class)
    public void initCache() {
        System.out.println(">> [TriDetailsCacheService] Loading triangle explanations...DB count: " + repository.findAll().size());
        
        repository.findAll().forEach(detail -> {
            triangleDetailsMap.put(detail.getTriangleType(), detail.getExplanation());
        });
    }
	    
    public String getExplanationByType(String triangleType) {
        return triangleDetailsMap.getOrDefault(triangleType, "No explanation found.");
    }
    
    public void printCache() {
    	System.out.println(">> Cached triangle types:");
    	triangleDetailsMap.forEach((k, v) -> System.out.println("   " + k + " => " + v));
    }
}
