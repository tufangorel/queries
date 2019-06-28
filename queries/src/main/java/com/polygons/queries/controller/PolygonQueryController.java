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

import com.polygons.queries.dto.PolygonDTO;
import com.polygons.queries.service.PolygonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/polygons")
@CrossOrigin(origins = "*")
@Api(value="Polygon Query Rest End-Point.")
public class PolygonQueryController {

	private static final Logger logger = LoggerFactory.getLogger(PolygonQueryController.class);
	
	@Autowired
	private PolygonService polygonService;
	
//	Swagger URL : http://localhost:8080/swagger-ui.html
	@ApiOperation(value = "Retrieve Polygons with cars in them.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success" ),
							@ApiResponse(code = 500, message = "Failure" ) })
	@RequestMapping( value = "/retrievePolygons", method = RequestMethod.GET)
	public ResponseEntity<List<PolygonDTO>> retrievePolygons() {
		logger.info("Start -- retrieve polygons.");
		List<PolygonDTO> polygonDTOs = polygonService.polygonsWithCarsAndVINId();
		logger.info("End -- retrieve polygons.");
		return new ResponseEntity<List<PolygonDTO>>(polygonDTOs, HttpStatus.OK);
	}
	
}