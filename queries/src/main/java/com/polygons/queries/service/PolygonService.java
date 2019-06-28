package com.polygons.queries.service;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polygons.queries.dto.PolygonDTO;
import com.polygons.queries.model.CityVehicle;
import com.polygons.queries.model.Point;
import com.polygons.queries.model.Polygon;
import com.polygons.queries.repository.CityVehicleRepository;
import com.polygons.queries.repository.PolygonRepository;
import com.polygons.queries.util.PolygonCacheUtil;
import com.polygons.queries.util.PolygonUtil;


@Service
public class PolygonService {

	@Autowired
	private PolygonRepository polygonRepository;
	
	@Autowired
	private CityVehicleRepository cityVehicleRepository;
	
	public void saveAll(List<Polygon> polygons ) {
		polygonRepository.deleteAll();
		polygonRepository.saveAll(polygons);
	}
	
	@SuppressWarnings("unchecked")
	public List<PolygonDTO> polygonsWithCarsAndVINId() {
		
		List<PolygonDTO> polygonDTOs = new ArrayList<>();
		List<Polygon> polygons = (List<Polygon>) polygonRepository.findAll();
		List<CityVehicle> cityVehicles = (List<CityVehicle>) cityVehicleRepository.findAll();
		
		for( Polygon polygon : polygons ) {
			
			PolygonDTO polygonDTO = PolygonDTO.toPolygonDTO(polygon);
			List<String> vinsOfCars = new ArrayList<>();
			for( CityVehicle cityVehicle : cityVehicles ) {
				
				List<Point> points = (List<Point>) PolygonCacheUtil.getInstance().getFromCache(polygon.getId());
				List<Point2D.Double> AWTPoints = PolygonUtil.convertToAWTPoint(points);
				Point2D.Double currentCarPoint = new Point2D.Double();
				currentCarPoint.setLocation( (double)(cityVehicle.getLatitude()), (double)(cityVehicle.getLongitude()) );
				boolean isInsidePolygon = PolygonUtil.isPointInsidePolygon(currentCarPoint, AWTPoints);
				
				if( isInsidePolygon ) {  // if this car is inside this polygon, then add it to the current polygon vin list.
					vinsOfCars.add(cityVehicle.getVin());
				}
			}
			polygonDTO.setVinsOfCars(vinsOfCars);  // set car VINs inside this polygon
			polygonDTOs.add(polygonDTO);
		}
		
		return polygonDTOs;
	}
	
}