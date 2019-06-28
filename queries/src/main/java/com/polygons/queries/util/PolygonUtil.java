package com.polygons.queries.util;

import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PolygonUtil {

	public static boolean isPointInsidePolygon(Point2D.Double p, List<Point2D.Double> points) {
		Path2D.Double polygon = new Path2D.Double();
        for(int i=0; i<points.size(); i++) {
        	if( i == 0 )
        		polygon.moveTo(points.get(i).getX(), points.get(i).getY());
        	else
        		polygon.lineTo(points.get(i).getX(), points.get(i).getY());
        }
        polygon.closePath();
        return polygon.contains(p);
	}
	
	public static List<Point2D.Double> convertToAWTPoint( List<com.polygons.queries.model.Point> points ){
		
		List<Point2D.Double> AWTpoints = new ArrayList<>();
		for( com.polygons.queries.model.Point point : points ) {
			Point2D.Double pointDouble = new Point2D.Double((point.getLatitude().doubleValue()) , (point.getLongitude().doubleValue()));
			AWTpoints.add(pointDouble);
		}
		
		return AWTpoints;
	}
	
}