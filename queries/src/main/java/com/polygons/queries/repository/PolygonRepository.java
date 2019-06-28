package com.polygons.queries.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.polygons.queries.model.Polygon;

@Repository
public interface PolygonRepository extends PagingAndSortingRepository<Polygon, String> {

}
