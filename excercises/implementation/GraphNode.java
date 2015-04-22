/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author chiljagossow
 * 
 */
public class GraphNode {

  /**
   * @param args
   */
  public static void main(String[] args) {
    GraphNode n1 = new GraphNode(1);
    GraphNode n2 = new GraphNode(2);
    GraphNode n3 = new GraphNode(3);
    GraphNode n4 = new GraphNode(4);
    GraphNode n5 = new GraphNode(5);

    n1.addAdjacentNode(n2);
    n1.addAdjacentNode(n4);

    n2.addAdjacentNode(n3);

    n3.addAdjacentNode(n1);

    n4.addAdjacentNode(n5);

    System.out.println(n1.searchDF(n4));
    
    LinkedList<GraphNode>[] adjacencyList = (LinkedList<GraphNode>[]) new LinkedList[1];

  }

  private int data;
  private ArrayList<GraphNode> adjacentNodes;
  private boolean visited;

  GraphNode(int data) {
    this.data = data;
    adjacentNodes = new ArrayList<GraphNode>();
  }

  void addAdjacentNode(GraphNode node) {
    adjacentNodes.add(node);
  }

  boolean isVisited() {
    return visited;
  }

  boolean searchBF(GraphNode node) {
    visit();
    
    if (this == node) {
      return true;
    }

    LinkedList<GraphNode> nodes = new LinkedList<GraphNode>();
    nodes.addLast(this);
    while (!nodes.isEmpty()) {
      GraphNode next = nodes.pollFirst();
      for (GraphNode neighbor : next.adjacentNodes) {
        if (!neighbor.isVisited()) {
          neighbor.visit();
          if (neighbor == node) {
            return true;
          }
          nodes.addLast(neighbor);
        }
      }
    }
    return false;
  }

  boolean searchDF(GraphNode node) {
    visit();
    if (this == node) {
      return true;
    }

    for (GraphNode neighbor : adjacentNodes) {
      if (!neighbor.isVisited()) {
        if (neighbor.searchDF(node)) {
          return true;
        }
      }
    }
    return false;
  }

  void visit() {
    System.out.println("Visit " + data);
    visited = true;
  }

}
