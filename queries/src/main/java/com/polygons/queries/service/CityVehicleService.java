package com.polygons.queries.service;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polygons.queries.dto.CityVehicleDTO;
import com.polygons.queries.model.CityVehicle;
import com.polygons.queries.model.Point;
import com.polygons.queries.model.Polygon;
import com.polygons.queries.repository.CityVehicleRepository;
import com.polygons.queries.repository.PolygonRepository;
import com.polygons.queries.util.PolygonCacheUtil;
import com.polygons.queries.util.PolygonUtil;

@Service
public class CityVehicleService {

	@Autowired
	private CityVehicleRepository cityVehicleRepository;
	
	@Autowired
	private PolygonRepository polygonRepository;
	
	public void saveAll(List<CityVehicle> cityVehicles ) {
		cityVehicleRepository.deleteAll();
		cityVehicleRepository.saveAll(cityVehicles);
	}
	
	public List<CityVehicleDTO> carsWithPolygonId() {
		
		List<CityVehicleDTO> cityVehicleDTOs = new ArrayList<>(); 
		List<CityVehicle> cityVehicles = (List<CityVehicle>) cityVehicleRepository.findAll();
		
		for( CityVehicle cityVehicle : cityVehicles ) {
			
			List<Polygon> polygons = (List<Polygon>) polygonRepository.findAll();
			for( Polygon polygon : polygons ) {
				
				List<Point> points = (List<Point>) PolygonCacheUtil.getInstance().getFromCache(polygon.getId());
				List<Point2D.Double> AWTPoints = PolygonUtil.convertToAWTPoint(points);
				Point2D.Double currentCarPoint = new Point2D.Double();
				currentCarPoint.setLocation( (double)(cityVehicle.getLatitude()), (double)(cityVehicle.getLongitude()) );
				boolean isInsidePolygon = PolygonUtil.isPointInsidePolygon(currentCarPoint, AWTPoints);
				
				CityVehicleDTO cityVehicleDTO = CityVehicleDTO.toCityVehicleDTO(cityVehicle);
				if( isInsidePolygon ) {
					cityVehicleDTO.setPolygonId(polygon.getId());
					cityVehicleDTOs.add(cityVehicleDTO);
					break;
				}
			}
		}
		return cityVehicleDTOs;
	}
}