package com.polygons.queries.dto;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.polygons.queries.model.Point;
import com.polygons.queries.model.Polygon;

public class PolygonDTO {

	@SerializedName("_id")
	private String id;
	
	@SerializedName("updatedAt")
	private String updatedAt;
	
	@SerializedName("createdAt")
	private String createdAt;
	
	@SerializedName("__v")
	private Integer v;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("cityId")
	private String cityId;
	
	@SerializedName("legacyId")
	private String legacyId;
	
	@SerializedName("type")
	private String type;
	
	@SerializedName("geoFeatures")
	private List<GeoFeatureDTO> geoFeatures = null;
	
	@SerializedName("timedOptions")
	private List<TimedOptionDTO> timedOptions = null;
	
	@SerializedName("geometry")
	private GeometryTypeCoordinateDTO geometry;
	
	@SerializedName("version")
	private Integer version;

	private List<String> vinsOfCars;
	
	public PolygonDTO() {
	}
	
	public static Polygon toPolygon( PolygonDTO polygonDTO ) {
		
		Polygon polygon = new Polygon();
		polygon.setId(polygonDTO.getId());
		polygon.setName(polygonDTO.getName());
		
		List<Point> points = new ArrayList<>();
		if( polygonDTO.getGeometry() != null && polygonDTO.getGeometry().getCoordinates() != null ) {
			List<List<List<Double>>> lists = polygonDTO.getGeometry().getCoordinates();
			List<List<Double>> innerList = lists.get(0);
			if( innerList != null && innerList.size() > 0 ) {
				for (List<Double> list : innerList) {
					Double longitude = list.get(0);
					Double latitude = list.get(1);
					Point point = new Point();
					point.setLatitude(latitude);
					point.setLongitude(longitude);
					point.setPolygon(polygon);
					points.add(point);
				}
			}
		}
		polygon.setPoints(points);
		
		return polygon;
	}
	
	public static PolygonDTO toPolygonDTO( Polygon polygon ) {
		
		PolygonDTO polygonDTO = new PolygonDTO();
		polygonDTO.setId(polygon.getId());
		polygonDTO.setName(polygon.getName());
		
		GeometryTypeCoordinateDTO geometryTypeCoordinateDTO = new GeometryTypeCoordinateDTO();
		
		List<List<List<Double>>> coordinates = new ArrayList<>();
		List<List<Double>> innerList = new ArrayList<>();
		
		List<Point> points = polygon.getPoints();
		for (Point point : points) {
			List<Double> doubles = new ArrayList<>();
			doubles.add(point.getLatitude());
			doubles.add(point.getLongitude());
			innerList.add(doubles);
		}
		coordinates.add(innerList);
		geometryTypeCoordinateDTO.setCoordinates(coordinates);
		polygonDTO.setGeometry(geometryTypeCoordinateDTO);
		
		return polygonDTO;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getV() {
		return v;
	}

	public void setV(Integer v) {
		this.v = v;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getLegacyId() {
		return legacyId;
	}

	public void setLegacyId(String legacyId) {
		this.legacyId = legacyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<GeoFeatureDTO> getGeoFeatures() {
		return geoFeatures;
	}

	public void setGeoFeatures(List<GeoFeatureDTO> geoFeatures) {
		this.geoFeatures = geoFeatures;
	}

	public List<TimedOptionDTO> getTimedOptions() {
		return timedOptions;
	}

	public void setTimedOptions(List<TimedOptionDTO> timedOptions) {
		this.timedOptions = timedOptions;
	}

	public GeometryTypeCoordinateDTO getGeometry() {
		return geometry;
	}

	public void setGeometry(GeometryTypeCoordinateDTO geometry) {
		this.geometry = geometry;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<String> getVinsOfCars() {
		return vinsOfCars;
	}

	public void setVinsOfCars(List<String> vinsOfCars) {
		this.vinsOfCars = vinsOfCars;
	}

}