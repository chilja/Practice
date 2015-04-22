/**
 * 
 */
package excercises.implementation;

import java.util.LinkedList;

/**
 * @author chiljagossow
 *
 */
public class Queens {

  private final static int noOfQueens = 8;
  /**
   * @param args
   */
  public static void main(String[] args) {
    LinkedList<Integer> placement = new LinkedList<Integer>();
    findValidPlacement(placement, 0);
    for (Integer pos: placement) {
      System.out.println(pos);
    }
  }
  
  public static boolean checkPlacement(LinkedList<Integer> array, int pos) {
    if ( pos == 0) return true;
    // check if queens share a diagonal
    if (checkPlacement(array, pos - 1)) {
      for ( int i = 0; i < pos; i++) {
        if ( Math.abs(i - pos) == Math.abs(array.get(i) - array.get(pos))) {
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  static boolean findValidPlacement(LinkedList<Integer> placement, int no) {
    if (placement == null) {
      placement = new LinkedList<Integer>();      
    }
    
    for (int j = 0; j <= no; j++) {
      placement.add(j, Integer.valueOf(no));
      if( no == noOfQueens - 1 ) {
        // all 8 queens (0-7) have been placed, return if valid
        if (checkPlacement(placement, placement.size() - 1)) return true;
      } else {
        if (findValidPlacement(placement, no + 1)) return true;       
      } 
      placement.remove(j);
    }   
    return false;
  }
}
