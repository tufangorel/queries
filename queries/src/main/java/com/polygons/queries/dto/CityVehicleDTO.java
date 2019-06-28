package com.polygons.queries.dto;

import com.google.gson.annotations.SerializedName;
import com.polygons.queries.model.CityVehicle;

public class CityVehicleDTO {

	@SerializedName("id")
	private Integer id;
	
	@SerializedName("locationId")
	private Integer locationId;
	
	@SerializedName("vin")
	private String vin;
	
	@SerializedName("numberPlate")
	private String numberPlate;
	
	@SerializedName("position")
	private PositionDTO position;
	
	@SerializedName("fuel")
	private Double fuel;
	
	@SerializedName("model")
	private String model;
	
	private String polygonId;

	public static CityVehicle toCityVehicle( CityVehicleDTO cityVehicleDTO ) {
		
		CityVehicle cityVehicle = new CityVehicle();
		cityVehicle.setFuel(cityVehicleDTO.getFuel());
		cityVehicle.setId(cityVehicleDTO.getId());
		cityVehicle.setLatitude(cityVehicleDTO.getPosition().getLatitude());
		cityVehicle.setLongitude(cityVehicleDTO.getPosition().getLongitude());
		cityVehicle.setModel(cityVehicleDTO.getModel());
		cityVehicle.setNumberPlate(cityVehicleDTO.getNumberPlate());
		cityVehicle.setVin(cityVehicleDTO.getVin());
		
		return cityVehicle;
	}
	
	public static CityVehicleDTO toCityVehicleDTO( CityVehicle cityVehicle ) {
		CityVehicleDTO cityVehicleDTO = new CityVehicleDTO();
		cityVehicleDTO.setFuel(cityVehicle.getFuel());
		cityVehicleDTO.setId(cityVehicle.getId());
		PositionDTO positionDTO = new PositionDTO();
		positionDTO.setLatitude(cityVehicle.getLatitude());
		positionDTO.setLongitude(cityVehicle.getLongitude());
		cityVehicleDTO.setPosition(positionDTO);
		cityVehicleDTO.setModel(cityVehicle.getModel());
		cityVehicleDTO.setNumberPlate(cityVehicle.getNumberPlate());
		cityVehicleDTO.setVin(cityVehicle.getVin());
		return cityVehicleDTO;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public PositionDTO getPosition() {
		return position;
	}

	public void setPosition(PositionDTO position) {
		this.position = position;
	}

	public Double getFuel() {
		return fuel;
	}

	public void setFuel(Double fuel) {
		this.fuel = fuel;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPolygonId() {
		return polygonId;
	}

	public void setPolygonId(String polygonId) {
		this.polygonId = polygonId;
	}

}