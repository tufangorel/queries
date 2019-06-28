package com.polygons.queries.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GeometryDTO {

	@SerializedName("type")
	private String type;
	
	@SerializedName("coordinates")
	private List<Double> coordinates = null;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}

}