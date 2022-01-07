package org.acme.graph.routing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Vertex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathTree {

    private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);

    private Map<Vertex, PathNode> nodes;

    /**
	 * Prépare le graphe pour le calcul du plus court chemin
	 * 
	 * @param source
	 */
	PathTree(Graph graph, Vertex origin) {
        this.nodes = new HashMap<>();
		log.trace("initGraph({})", origin);
		for (Vertex vertex : graph.getVertices()) {
			nodes.put(vertex, new PathNode());
			getNode(vertex).setCost(origin == vertex ? 0.0 : Double.POSITIVE_INFINITY);
			getNode(vertex).setReachingEdge(null);
			getNode(vertex).setVisited(false);
		}
	}

    /**
	 * Construit le chemin en remontant les relations incoming edge
	 * 
	 * @param target
	 * @return
	 */
	public List<Edge> getPath(Vertex target) {
		List<Edge> result = new ArrayList<>();

		Edge current = getNode(target).getReachingEdge();
		do {
			result.add(current);
			current = getNode(current.getSource()).getReachingEdge();
		} while (current != null);

		Collections.reverse(result);
		return result;
	}

    public PathNode getNode(Vertex vertex){
		return this.nodes.get(vertex);
	}
    
}
