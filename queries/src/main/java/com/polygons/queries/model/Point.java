package com.polygons.queries.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Point implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
	private Double latitude;
	
    @Column
	private Double longitude;
	
    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn(name = "polygon_id")
    private Polygon polygon;
    
    public Point() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void setPolygon(Polygon polygon) {
		this.polygon = polygon;
	}
    
}
