/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;

/**
 * @author chiljagossow
 *
 */
public class BinaryTreeNode {

  BinaryTreeNode left;
  BinaryTreeNode right;
  int data;
  
  BinaryTreeNode(int data) {
    this.data = data;
  }
  
  void insert(int data) {
    if (data <= this.data) {
      if (left == null) {
        left = new BinaryTreeNode(data);
      } else {
        left.insert(data);
      }
    } else {
      if (right == null) {
        right = new BinaryTreeNode(data);
      } else {
        right.insert(data);
      }
    }
  }
  
  static boolean findTwo(BinaryTreeNode root, int one, int two) {
    if (root == null) {
      return false;
    }
    if (root.data == one || root.data == two) {
      return true;
    }
    return ( findTwo(root.left, one, two) || findTwo(root.right, one, two) ); 
  }
  
  static BinaryTreeNode findAncestor(BinaryTreeNode root, int one, int two) {
    if (root == null) return null;
    
    BinaryTreeNode ancestor = findAncestor(root.left, one, two);
    if (ancestor != null) return ancestor;
    ancestor = findAncestor(root.right, one, two);
    if (ancestor != null) return ancestor;
    
    boolean left = findTwo(root.left, one, two);
    boolean right = findTwo(root.right, one, two);
    
    if ((left && right)|| ((root.data == one || root.data == two) && (left || right)))
      return root;
    return null;
  }
  
  //4.8
  public static boolean areIdentical(BinaryTreeNode root1, BinaryTreeNode root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    if (root1.data != root2.data) return false;
    return (areIdentical(root1.left, root2.left) && areIdentical(root1.right, root2.right));
  }

  public static boolean isSubtree(BinaryTreeNode root1, BinaryTreeNode root2) {
    if (root1 == null && root2 == null) return true;
    if (root1 == null || root2 == null) return false;
    BinaryTreeNode subTreeRoot = find(root1, root2.data);
    return areIdentical(subTreeRoot, root2);
  }

  public static BinaryTreeNode find(BinaryTreeNode root, int data) {
    if (root == null) return null;
    if (root.data == data) return root;
    BinaryTreeNode node = find(root.left, data) ;
    if (node == null) {
      node = find(root.right, data);
    }
    return node;
  }
  
  // 4.9
  public static void printPath(ArrayList<Integer> path) {
    if (path == null || path.size() == 0) return;
    for (Integer i: path) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  public static void printPath(BinaryTreeNode root, ArrayList<Integer> path, int currentSum, int originalSum) {
    
    if (root == null) return;
    
    path.add(root.data);
    int newCurrentSum = currentSum - root.data;
    if (newCurrentSum == 0) printPath(path);
    
    ArrayList<Integer> newPath = new ArrayList<Integer>();
    
    printPath(root.left, path, newCurrentSum, originalSum);   
    printPath(root.right, path, newCurrentSum, originalSum);
    path.remove(path.size()-1);
    
    if (path.size() == 0) {
      printPath(root.left, newPath, originalSum, originalSum);
      printPath(root.right, newPath, originalSum, originalSum);
    }
    

  }
    
}