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
public class BinarySearchTreeNode {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] nodes = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

    BinarySearchTreeNode root = createBalancedTree(null, nodes);

    System.out.println(findAncestor(root, 6, 11, false));
  }

  static boolean checkBalance(BinarySearchTreeNode root) {
    if (getHeight(root) < 0) {
      return false;
    }
    return true;
  }

  static boolean checkOrder(BinarySearchTreeNode root) {
    if (root == null) {
      return true;
    }

    if (checkOrder(root.leftChild) && checkOrder(root.rightChild)) {

      if (root.leftChild != null) {
        if (root.leftChild.getMaxValue() > root.data) {
          return false;
        }
      }

      if (root.rightChild != null) {
        if (root.rightChild.getMinValue() <= root.data) {
          return false;
        }
      }
    }
    return true;
  }

  static BinarySearchTreeNode createBalancedTree(BinarySearchTreeNode root, int[] items) {
    if ((items == null) || (items.length == 0)) {
      return null;
    }

    if (items.length < 3) {
      for (int i = 0; i < items.length; i++) {
        root = insertNode(root, items[i]);
      }
      return root;
    }

    int pivot = (items.length + 1) / 2;

    int[] left = new int[pivot - 1];
    for (int i = 0; i < left.length; i++) {
      left[i] = items[i];
    }

    int[] right = new int[items.length - pivot];
    int index = pivot;
    for (int i = 0; i < right.length; i++) {
      right[i] = items[index];
      index++;
    }

    root = insertNode(root, items[pivot - 1]);
    createBalancedTree(root, left);
    createBalancedTree(root, right);

    return root;
  }

  static int findAncestor(BinarySearchTreeNode root, int data1, int data2, boolean started) {
    if (root == null) {
      return -1;
    }
    int result = -1;

    if (root.leftChild != null) {
      result = BinarySearchTreeNode.findAncestor(root, data1, data2, started);
      if (result >= 0) {
        return result;
      }
    }

    root.visit();
    if ((started == true) && (root.rightChild != null) && !root.rightChild.visited) {
      return root.data;
    }

    if (root.rightChild != null) {
      result = BinarySearchTreeNode.findAncestor(root, data1, data2, started);
      if (result >= 0) {
        return result;
      }
    }

    return result;

  }

  boolean findNode(int data) {
    if (this.data == data) {
      return true;
    }
    if ((data <= this.data) && (leftChild != null)) {
      return leftChild.findNode(data);
    }
    if ((data > this.data) && (rightChild != null)) {
      return rightChild.findNode(data);
    }
    return false;
  }

  static int findSuccessor(BinarySearchTreeNode root, int data) {
    if (root != null) {
      return root.findSuccessor();
    }
    return -1;
  }

  static int getHeight(BinarySearchTreeNode root) {
    if (root == null) {
      return 0;
    }

    int heightLeft = 0;
    int heightRight = 0;

    heightLeft = getHeight(root.leftChild);
    heightRight = getHeight(root.rightChild);

    int diff = heightLeft - heightRight;

    if ((diff < -1) || (diff > 1)) {
      return -1;
    }
    return Math.max(heightLeft, heightRight) + 1;
  }

  static BinarySearchTreeNode insertNode(BinarySearchTreeNode root, int item) {
    if (root == null) {
      root = new BinarySearchTreeNode(item, null);
    } else {
      root.insert(item);
    }
    return root;
  }

  static void printDepthLists(BinarySearchTreeNode root) {
    int level = 1;
    ArrayList<LinkedList<BinarySearchTreeNode>> nodes = new ArrayList<LinkedList<BinarySearchTreeNode>>();
    root.collectNodes(nodes, level);
    for (LinkedList<BinarySearchTreeNode> levelList : nodes) {
      System.out.println();
      System.out.println("level " + level);
      System.out.println();
      for (BinarySearchTreeNode node : levelList) {
        System.out.println(node.data);
      }
      level++;
      System.out.println();
    }
  }

  private int data;

  private BinarySearchTreeNode leftChild;

  private BinarySearchTreeNode rightChild;

  private BinarySearchTreeNode parent;

  boolean visited = false;

  BinarySearchTreeNode(int data, BinarySearchTreeNode parent) {
    this.data = data;
    this.parent = parent;
  }

  void insert(int data) {

    if (data <= this.data) {
      if (leftChild == null) {
        leftChild = new BinarySearchTreeNode(data, this);
      } else {
        leftChild.insert(data);
      }
    } else {
      if (rightChild == null) {
        rightChild = new BinarySearchTreeNode(data, this);
      } else {
        rightChild.insert(data);
      }
    }
  }

  ArrayList<LinkedList<BinarySearchTreeNode>> collectNodes(ArrayList<LinkedList<BinarySearchTreeNode>> nodes, int level) {
    // add new level list
    if (nodes.size() < level) {
      nodes.add(new LinkedList<BinarySearchTreeNode>());
    }
    nodes.get(level - 1).addLast(this);

    if (leftChild != null) {
      leftChild.collectNodes(nodes, level + 1);
    }
    if (rightChild != null) {
      rightChild.collectNodes(nodes, level + 1);
    }
    return nodes;
  }

  int findLeftmostRightChild() {
    // find the left most right child
    BinarySearchTreeNode n = rightChild;

    while (n.leftChild != null) {
      n = n.leftChild;
    }
    return n.data;
  }

  int findSuccessor() {

    if (rightChild != null) {
      // find the left most right child
      return findLeftmostRightChild();
    }

    BinarySearchTreeNode n = this;
    while (n.parent != null) {
      // this is first left child => parent is successor
      if (n.parent.leftChild == n) {
        return n.parent.data;
      }
      n = n.parent;
    }

    if ((parent != null) && (parent.rightChild == this)) {
      if ((parent.parent != null) && (parent == parent.parent.leftChild)) {
        return parent.parent.data;
      }
    }

    // no successor
    return -1;

  }

  int getMaxValue() {
    int max = data;
    if (leftChild != null) {
      int maxLeft = leftChild.getMaxValue();
      if (maxLeft > max) {
        max = maxLeft;
      }
    }
    if (rightChild != null) {
      int maxRight = rightChild.getMaxValue();
      if (maxRight > max) {
        max = maxRight;
      }
    }
    return max;
  }

  int getMinValue() {
    int min = data;
    if (leftChild != null) {
      int minLeft = leftChild.getMinValue();
      if (minLeft > min) {
        min = minLeft;
      }
    }
    if (rightChild != null) {
      int maxRight = rightChild.getMinValue();
      if (maxRight > min) {
        min = maxRight;
      }
    }
    return min;
  }

  void searchInOrder() {

    if (leftChild != null) {
      leftChild.searchInOrder();
    }
    visit();
    if (rightChild != null) {
      rightChild.searchInOrder();
    }
  }

  void searchPostOrder() {

    if (leftChild != null) {
      leftChild.searchPostOrder();
    }
    if (rightChild != null) {
      rightChild.searchPostOrder();
    }
    visit();
  }

  void searchPreOrder() {
    visit();
    if (leftChild != null) {
      leftChild.searchPreOrder();
    }
    if (rightChild != null) {
      rightChild.searchPreOrder();
    }
  }

  void visit() {
    System.out.println("Visit " + data);
    visited = true;
  }
}
