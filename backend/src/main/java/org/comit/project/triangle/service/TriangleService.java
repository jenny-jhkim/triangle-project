package org.comit.project.triangle.service;

import java.util.List;
import java.util.Optional;

import org.comit.project.triangle.dto.TriangleClassifyRequestDto;
import org.comit.project.triangle.dto.TriangleClassifyResponseDto;
import org.comit.project.triangle.jpa.Triangle;
import org.comit.project.triangle.repository.TriangleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TriangleService {
	
	@Autowired
	private TriangleRepository triangleRepo;
	
	@Autowired
	private TriDetailsCacheService detailsCache;
	
	public TriangleClassifyResponseDto classify(TriangleClassifyRequestDto input) {
		TriangleClassifier triangle = new TriangleClassifier();
		String triangleType;
		String explanation;
		
		//get type of triangles from TriangleClassifier logic
		if(input.getInputType().equals("Sides")) {
			triangleType = triangle.classifyBySides(input.getA(), input.getB(), input.getC());
		} else {
			triangleType = triangle.classifyByAngles(input.getA(), input.getB(), input.getC());
		}
		
		//get explanation from the cache
		explanation = detailsCache.getExplanationByType( triangleType );
		
		if(triangleType.equals("Not a Triangle")) {
			//add more detail reason
			explanation += detailsCache.getExplanationByType( input.getInputType() );
		} 	
		
		return new TriangleClassifyResponseDto( triangleType, explanation );		
	}
	
    @Transactional(propagation=Propagation.REQUIRED)
	public Triangle saveTriangle(Triangle triangle) {
		return this.triangleRepo.save(triangle);
	}
	
	public List<Triangle> listTriangle() {
		 return this.triangleRepo.findAll();
	}
	
	public Optional<Triangle> getTriangleById(int id){
		return this.triangleRepo.findById(id);
	}
	
	public void deleteTriangle(int id) {
		this.triangleRepo.deleteById(id);
	}

}
