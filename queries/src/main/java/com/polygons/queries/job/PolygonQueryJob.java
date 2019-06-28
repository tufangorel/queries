package com.polygons.queries.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.polygons.queries.dto.PolygonDTO;
import com.polygons.queries.model.Polygon;
import com.polygons.queries.service.PolygonService;
import com.polygons.queries.util.PolygonCacheUtil;

@Component
public class PolygonQueryJob implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger logger = LoggerFactory.getLogger(PolygonQueryJob.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Gson gson;
	
	@Autowired
	private PolygonService polygonService;
	
    @HystrixCommand( fallbackMethod = "retrievePolygonsFallbackMethod", commandProperties = {
			 @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100000") })
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		logger.info("PolygonQueryJob started at : {}", LocalDateTime.now().format(formatter));
		String url = "https://gist.githubusercontent.com/codeofsumit/6540cdb245bd14c33b486b7981981b7b/raw/73ebda86c32923e203b2a8e61615da3e5f1695a2/polygons.json";
		List<Object> polygonDTOs = restTemplate.getForObject(url, List.class);

		if (polygonDTOs != null && polygonDTOs.size() > 0) {

			List<Polygon> polygons = new ArrayList<>();
			for (Object object : polygonDTOs) {
				String json = gson.toJson(object);
				PolygonDTO polygonDTO = gson.fromJson(json, PolygonDTO.class);
				Polygon polygon = PolygonDTO.toPolygon(polygonDTO);
				polygons.add(polygon);
				
				PolygonCacheUtil.getInstance().putToCache(polygon.getId(), polygon.getPoints());
				logger.info("" + polygon.getId());
			}
			polygonService.saveAll(polygons);
		}

		logger.info("PolygonQueryJob finished at : {}", LocalDateTime.now().format(formatter));
	}
    
    public void retrievePolygonsFallbackMethod(final ApplicationReadyEvent event) {
    	logger.info("Timeout occured for PolygonQueryJob started at : {}", LocalDateTime.now().format(formatter) );
    }
    
}