package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.routing.DijkstraPathFinder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {

	
	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);

	Map<Vertex,PathNode> nodes = new HashMap<Vertex,PathNode> ();

	public PathTree(Graph graph) {
	}
	/**
	 * Construit le chemin en remontant les relations incoming edge
	 * 
	 * @param target
	 * @return
	 */
	public List<Edge> buildPath(Vertex destination) {
		List<Edge> result = new ArrayList<>();

		Edge current = getNode(destination).getReachingEdge();
		do {
			result.add(current);
			current = getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result);
		return result;
	}

	/**
	 * Prépare le graphe pour le calcul du plus court chemin
	 * 
	 * @param source
	 */
	public void initGraph(Graph graph, Vertex origin) {
		log.trace("initGraph({})", origin);
		for (Vertex vertex : graph.getVertices()) {
			PathNode pathNode = new PathNode();
			nodes.put(vertex, pathNode);
			pathNode.setCost(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			pathNode.setReachingEdge(null);
			pathNode.setVisited(false);
		}
	}
	
	public PathNode getNode(Vertex vertex) {
		return nodes.get(vertex);
	}
}
