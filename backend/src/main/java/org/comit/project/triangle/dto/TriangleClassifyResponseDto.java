package org.comit.project.triangle.dto;

public class TriangleClassifyResponseDto {
    private String result;
    private String explanation;
    
    public TriangleClassifyResponseDto() {
    	
    }

    public TriangleClassifyResponseDto(String result, String explanation) {
        this.result = result;
        this.explanation = explanation;
    }

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
    
}
