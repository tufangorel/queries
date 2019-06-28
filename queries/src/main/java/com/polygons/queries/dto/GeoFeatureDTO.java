package com.polygons.queries.dto;

import com.google.gson.annotations.SerializedName;

public class GeoFeatureDTO {

	@SerializedName("name")
	private String name;
	
	@SerializedName("geometry")
	private GeometryDTO geometry;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GeometryDTO getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryDTO geometry) {
		this.geometry = geometry;
	}

}