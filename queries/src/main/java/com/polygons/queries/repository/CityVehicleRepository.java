package com.polygons.queries.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.polygons.queries.model.CityVehicle;

@Repository
public interface CityVehicleRepository extends PagingAndSortingRepository<CityVehicle, Integer> {

}
