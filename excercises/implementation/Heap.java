/**
 * 
 */
package excercises.implementation;

import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * @author chiljagossow
 *
 */
public class Heap {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
  
  private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
  private int minHeapCounter;
  private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10, new MaxSort());
  private int maxHeapCounter;
  
  
  public void add(Integer i) { 
    if (minHeapCounter < maxHeapCounter) {
      if (minHeap.peek() == null || minHeap.peek() < i) {
        minHeap.offer(i);        
      } else {
        maxHeap.offer(i);
        minHeap.offer(maxHeap.poll());
      }
      minHeapCounter++;
    } else {
      if (maxHeap.peek() == null || maxHeap.peek() > i) {
        maxHeap.offer(i);        
      } else {
        minHeap.offer(i);
        maxHeap.offer(minHeap.poll());
      }
      maxHeapCounter++;
    }
  }
  
  public int getMedian() {
    return maxHeap.peek().intValue();
  }
  
  private class MaxSort implements Comparator<Integer> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Integer no1, Integer no2) {
      // TODO Auto-generated method stub
      return no2 - no1;
    }
    
  }
  
  

}
