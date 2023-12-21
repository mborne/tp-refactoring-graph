package org.acme.graph.routing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.errors.NotFoundException;
import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.acme.graph.model.Path;
import org.acme.graph.model.Vertex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.acme.graph.model.PathNode;
import org.acme.graph.model.PathTree;
/**
 * 
 * Utilitaire pour le calcul du plus court chemin dans un graphe
 * 
 * @author MBorne
 *
 */
public class DijkstraPathFinder {

	private static final Logger log = LogManager.getLogger(DijkstraPathFinder.class);

	private Graph graph;
	
	Map<Vertex,PathNode> nodes = new HashMap<Vertex,PathNode> ();

	public DijkstraPathFinder(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Calcul du plus court chemin entre une origine et une destination
	 * 
	 * @param origin
	 * @param destination
	 * @return
	 */
	public Path findPath(Vertex origin, Vertex destination) {
		PathTree pt = new PathTree(graph);
		log.info("findPath({},{})...", origin, destination);
		pt.initGraph(graph, origin);
		Vertex current;
		while ((current = findNextVertex(pt)) != null) {
			visit(current, pt);
			if (pt.getNode(destination).getReachingEdge() != null) {
				log.info("findPath({},{}) : path found", origin, destination);
				List<Edge> listEdges = pt.buildPath(destination);
				return new Path(listEdges);
			}
		}
		throw new NotFoundException(String.format("Path not found from '%s' to '%s'", origin, destination));
	}

	/**
	 * Parcourt les arcs sortants pour atteindre les sommets avec le meilleur coût
	 * 
	 * @param vertex
	 */
	private void visit(Vertex vertex, PathTree pt) {
	    log.trace("visit({})", vertex);
	    List<Edge> outEdges = graph.getOutEdges(vertex);

	    for (Edge outEdge : outEdges) {
	        Vertex reachedVertex = outEdge.getTarget();
	        double newCost = pt.getNode(vertex).getCost() + outEdge.getCost();
	        if (newCost < pt.getNode(reachedVertex).getCost()) {
	            pt.getNode(reachedVertex).setCost(newCost);
	            pt.getNode(reachedVertex).setReachingEdge(outEdge);
	        }
	    }

	    pt.getNode(vertex).setVisited(true);
	}


	/**
	 * Recherche le prochain sommet à visiter. Dans l'algorithme de Dijkstra, ce
	 * sommet est le sommet non visité le plus proche de l'origine du calcul de plus
	 * court chemin.
	 * 
	 * @return
	 */
	
	private Vertex findNextVertex(PathTree pt) {
	    double minCost = Double.POSITIVE_INFINITY;
	    Vertex result = null;
	    for (Vertex vertex : graph.getVertices()) {
	        if (pt.getNode(vertex).isVisited()) {
	            continue;
	        }
	        if (pt.getNode(vertex).getCost() == Double.POSITIVE_INFINITY) {
	            continue;
	        }
	        if (pt.getNode(vertex).getCost() < minCost) {
	            minCost = pt.getNode(vertex).getCost();
	            result = vertex;
	        }
	    }
	    return result;
	}

}
