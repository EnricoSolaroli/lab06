package it.unibo.generics.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {

    private Set<N> nodes = new HashSet<>();
    private Map<N, Set<N>> edges = new HashMap<>();

    @Override
    public void addNode(N node) {
        this.nodes.add(node);
    }

    @Override
    public void addEdge(N source, N target) {
        final Set<N> targets = this.edges.getOrDefault(source, new HashSet<>());
        targets.add(target);
        // Set<N> targets;
        // if (this.edges.containsKey(source)) {
        //    targets = this.edges.get(source); 
        // }
        this.edges.put(source, targets);
    }

    @Override
    public Set<N> nodeSet() {
        return new HashSet<N>(this.nodes);
    }

    @Override
    public Set<N> linkedNodes(N node) {
        return new HashSet<N>(this.edges.getOrDefault(node, new HashSet<>()));
    }

    public List<N> getPathImplementation(N source, N target, Set<N> visitedNodes) {
        List<N> result = new LinkedList<>();
        if (source.equals(target)) {
            result.add(source);
        } else {
            Set<N> nodesToVisit = this.linkedNodes(source);
            nodesToVisit.removeAll(visitedNodes);
            Iterator<N> nodeIterator = nodesToVisit.iterator(); 
            visitedNodes.add(source);
            while (nodeIterator.hasNext() && result.isEmpty()) {
                N selectedNode = nodeIterator.next();
                result = this.getPathImplementation(selectedNode, target, visitedNodes);
            }

            if (result.isEmpty() == false){
                result.addFirst(source);
            }

        }
        return result;
    }

    @Override
    public List<N> getPath(N source, N target) {
        return getPathImplementation(source, target, new HashSet<>());
    }
    
}
