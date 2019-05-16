package laioffer;
import java.util.*;
public class CrossTraining2 {

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
		return map.get(input);
	}
}