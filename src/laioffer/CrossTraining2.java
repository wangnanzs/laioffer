package laioffer;
import java.util.*;
public class CrossTraining2 {
	public static void main(String[] args) {
		GraphNode node1 = new GraphNode(1);
		GraphNode node2 = new GraphNode(2);
		GraphNode node3 = new GraphNode(3);
		GraphNode node4 = new GraphNode(4);
		node1.neighbors = new ArrayList<>();
//		node1.neighbors.add(node2);
		node1.neighbors.add(node3);
		node2.neighbors = new ArrayList<>();
//		node2.neighbors.add(node1);
//		node2.neighbors.add(node3);
//		node2.neighbors.add(node4);
		node3.neighbors = new ArrayList<>();
		node3.neighbors.add(node1);
//		node3.neighbors.add(node2);
		node3.neighbors.add(node4);
		node4.neighbors = new ArrayList<>();
//		node4.neighbors.add(node2);
		node4.neighbors.add(node3);
		List<GraphNode> graph = new ArrayList<>();
		graph.add(node1);
		graph.add(node2);
		graph.add(node3);
		graph.add(node4);
		CT2Solution sol = new CT2Solution();
		List<GraphNode> newGraph = sol.copy1(graph);
		for(GraphNode node: newGraph) {
			System.out.println(node.key);
		}
		
		
	}
}

//class GraphNode{
//	public int key;
//	public List<GraphNode> neighbors;
//	public GraphNode(int key) {
//		this.key = key;
//		neighbors = new ArrayList<>();
//	}
//}
class CT2Solution{
	private void bfs(GraphNode node, Queue<GraphNode> queue, 
			Map<GraphNode,GraphNode> map, List<GraphNode> result) {
		GraphNode copied = new GraphNode(node.key);
		map.put(node,copied);
		queue.offer(node);
		while(!queue.isEmpty()) {
			GraphNode element = queue.poll();
			GraphNode elementCopy = map.get(element);
			result.add(elementCopy);
			for(GraphNode child :element.neighbors) {
				GraphNode childCopy = map.get(child);
				if(childCopy == null) {
					childCopy = new GraphNode(child.key);
					map.put(child,childCopy);
					queue.offer(child);
				}
				elementCopy.neighbors.add(childCopy);
			}
		}
	}
	public List<GraphNode> copy1(List<GraphNode> graph){
		Map<GraphNode,GraphNode> map = new HashMap<>();
		List<GraphNode> res = new ArrayList<>();
		for(GraphNode node : graph) {
			if(!map.containsKey(node)) {
				cloneNodes(node,map,res);
			}
		}
		return res;
	}
	// Using BFS
	private void cloneNodes(GraphNode input, Map<GraphNode, GraphNode> map, List<GraphNode> ls) {
		if(input == null) {
			return;
		}
		Queue<GraphNode> queue = new ArrayDeque<>();
		GraphNode copied = new GraphNode(input.key);
		map.put(input, copied);
		ls.add(copied);
		queue.offer(input);
		while(!queue.isEmpty()) {
			GraphNode curr = queue.poll();
			GraphNode copiedCurr = map.get(curr);
			for(GraphNode node: curr.neighbors) {
				if(!map.containsKey(node)) {
					GraphNode copiedNode = new GraphNode(node.key);
					ls.add(copiedNode);
					map.put(node,copiedNode);
					queue.offer(node);
				}
				copiedCurr.neighbors.add(map.get(node));
			}
		}
		return;
	}
	
	public List<GraphNode> copy(List<GraphNode> graph){
		Map<GraphNode,GraphNode> map = new HashMap<>();
		List<GraphNode> res = new ArrayList<>();
		for(GraphNode node : graph) {
			if(!map.containsKey(node)) {
				cloneNode(node,map,res);
			}
		}
		return res;
	}
	// Using DFS
	private GraphNode cloneNode(GraphNode input, Map<GraphNode, GraphNode> map, List<GraphNode> ls) {
		if(input == null) {
			return input;
		}
		if(map.containsKey(input)) {
			return map.get(input);
		}
		GraphNode newNode = new GraphNode(input.key);
		map.put(input,newNode);
		ls.add(newNode);
		for(GraphNode node: input.neighbors) {
			newNode.neighbors.add(cloneNode(node,map,ls));
		}
		return newNode;
	}
}