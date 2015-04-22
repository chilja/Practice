/**
 * 
 */
package excercises.implementation;

import java.util.LinkedList;

/**
 * @author chiljagossow
 *
 */
public class TowerOfHanoi {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub 
    LinkedList<Integer> start = new LinkedList<Integer>();
    start.push(4);
    start.push(3);
    start.push(2);
    start.push(1);
    LinkedList<Integer> helper = new LinkedList<Integer>();
    LinkedList<Integer> dest = new LinkedList<Integer>();
    boolean success = move(start.size(), start, helper, dest);
    if (success) {
      while (!dest.isEmpty()) {
        System.out.println(dest.pop());
      }
    }
  }
  
  public static boolean move(int n, LinkedList<Integer> start, LinkedList<Integer> helper, LinkedList<Integer> dest) {
    if (start == null || helper == null || dest == null) {
      return false;
    }
    // nothing to move
    if (n < 1) return true;
    // no disk
    if (start.isEmpty()) return false;
    if (n == 1) {
      if (!dest.isEmpty() && dest.peek() < start.peek()) return false;
      dest.push(start.pop());
      return true;
    }
    return (move(n-1, start, dest, helper) && move(1, start, helper, dest) && move(n-1, helper, start, dest));
    
  }

}
