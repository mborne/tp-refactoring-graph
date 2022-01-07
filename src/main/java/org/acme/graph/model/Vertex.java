package org.acme.graph.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.locationtech.jts.geom.Coordinate;

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
	 * Liste de aretes entrantes
	 */
	@JsonIgnore
	private List<Edge> inEdges;
	/**
	 * Liste de aretes sortantes
	 */
	@JsonIgnore
	private List<Edge> outEdges;

	Vertex() {
		this.inEdges = new ArrayList<>();
		this.outEdges = new ArrayList<>();
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


	public List<Edge> getInEdges(){
		return this.inEdges;
	}

	public List<Edge> getOutEdges(){
		return this.outEdges;
	}

	@Override
	public String toString() {
		return id;
	}

}
