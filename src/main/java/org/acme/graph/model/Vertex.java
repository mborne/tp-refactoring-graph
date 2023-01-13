package org.acme.graph.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Un sommet dans un graphe
 * 
 * @author MBorne
 *
 */
public class Vertex {

	/**
	 * Identifiant du sommet
	 */
	private String id;

	/**
	 * Position du sommet
	 */
	private Coordinate coordinate;

	/**
	 * Liste des Edges qui visent ce vertice
	 */
	private List<Edge> inEdge = new ArrayList<>();

	/**
	 * Liste des Edges qui partent de ce vertice
	 */
	private List<Edge> outEdge = new ArrayList<>();

	Vertex() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public List<Edge> getInEdge() {
		return inEdge;
	}

	public List<Edge> getOutEdge() {
		return outEdge;
	}

	@Override
	public String toString() {
		return id;
	}

}
