package com.polygons.queries.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.polygons.queries.dto.CityVehicleDTO;
import com.polygons.queries.model.CityVehicle;
import com.polygons.queries.service.CityVehicleService;

@Component
public class CarQueryJob {

	private static final Logger logger = LoggerFactory.getLogger(CarQueryJob.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CityVehicleService cityVehicleService;
	
	@Autowired
	private Gson gson;
	
    @Scheduled(fixedRate = 60000)  // runs at every 60 seconds.
    @HystrixCommand( fallbackMethod = "retrieveCarsFallbackMethod", commandProperties = {
			 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100000") })
    public void retrieveCars() {
    	logger.info("CarQueryJob started at : {}", LocalDateTime.now().format(formatter) );
    	
		ResponseEntity<List> response = restTemplate.getForEntity( "http://localhost:3000/vehicles/Stuttgart", List.class);
		List<Object> cityVehicleDTOs = response.getBody();
    	
		if( cityVehicleDTOs != null && cityVehicleDTOs.size() > 0 ) {
			
			List<CityVehicle> cityVehicles = new ArrayList<>();
	    	for(Object cityVehicleDTOObj : cityVehicleDTOs) {
	    		
	    		String json = gson.toJson(cityVehicleDTOObj);
	    		CityVehicleDTO cityVehicleDTO = gson.fromJson(json, CityVehicleDTO.class);
	    		CityVehicle cityVehicle = CityVehicleDTO.toCityVehicle(cityVehicleDTO);
	    		cityVehicles.add(cityVehicle);
				logger.info(""+cityVehicle.getId());
	    	}
	    	cityVehicleService.saveAll(cityVehicles);
		}
    	
    	logger.info("CarQueryJob finished at : {}", LocalDateTime.now().format(formatter) );
    }
    
    public void retrieveCarsFallbackMethod() {
    	logger.info("Timeout occured for CarQueryJob started at : {}", LocalDateTime.now().format(formatter) );
    }
    
}