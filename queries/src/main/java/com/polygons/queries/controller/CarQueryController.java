package com.polygons.queries.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.polygons.queries.dto.CityVehicleDTO;
import com.polygons.queries.service.CityVehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cars")
@CrossOrigin(origins = "*")
@Api(value="Car Query Rest End-Point.")
public class CarQueryController {

	private static final Logger logger = LoggerFactory.getLogger(CarQueryController.class); 
	
	@Autowired
	private CityVehicleService cityVehicleService;

//	Swagger URL : http://localhost:8080/swagger-ui.html
	@ApiOperation(value = "Retrieve cars with poygon ids they are in.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success" ),
							@ApiResponse(code = 500, message = "Failure" ) })
	@RequestMapping( value = "/retrieveCars", method = RequestMethod.GET)
	public ResponseEntity<List<CityVehicleDTO>> retrieveCars() {
		logger.info("Start -- retrieve vehicles of Stutgart.");
		List<CityVehicleDTO> cityVehicleDTOs = cityVehicleService.carsWithPolygonId();
		logger.info("End -- retrieve vehicles of Stutgart.");
		return new ResponseEntity<List<CityVehicleDTO>>(cityVehicleDTOs, HttpStatus.OK);
	}
	
}