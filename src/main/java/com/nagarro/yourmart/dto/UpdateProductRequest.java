package com.nagarro.yourmart.dto;

public class UpdateProductRequest {
	
	private String code;
	private String name;
	private String shortDescription;
	private String longDescription;
	private String dimensions;
	private int mrp;
	private int ssp;
	private int ymp;
	private String usageInstructions;
	private String attributes;
	private int categoryId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public int getMrp() {
		return mrp;
	}

	public void setMrp(int mrp) {
		this.mrp = mrp;
	}

	public int getSsp() {
		return ssp;
	}

	public void setSsp(int ssp) {
		this.ssp = ssp;
	}

	public int getYmp() {
		return ymp;
	}

	public void setYmp(int ymp) {
		this.ymp = ymp;
	}

	public String getUsageInstructions() {
		return usageInstructions;
	}

	public void setUsageInstructions(String usageInstructions) {
		this.usageInstructions = usageInstructions;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
