/**
 * 
 */
package excercises.implementation;

import java.util.PriorityQueue;

/**
 * @author chiljagossow
 *
 */
public class Primes {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println(getKthNumber(11));
  }
  
  static Integer getKthNumber(int k) {
    Integer kNumber = 1;
    PriorityQueue<Integer> numbers = new PriorityQueue<Integer>();
    numbers.offer(3);
    numbers.offer(5);
    numbers.offer(7);
      
    for (int i = 0; i< k; i++) {  
      if (numbers.isEmpty()) break;
      kNumber = numbers.poll();
      while(kNumber.equals(numbers.peek())) {
        numbers.poll();
      }       
      numbers.add(kNumber * 3);
      numbers.add(kNumber * 5);
      numbers.add(kNumber * 7);
    }
    return kNumber;
  }

}
