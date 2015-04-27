/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;
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
    ArrayList<Integer> primes = sieveOfEratosthenes(1000);
    for (Integer i: primes) {
      System.out.print(i + " ");
      System.out.println(checkPrimality(i));     
    }   
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
  
  static boolean checkPrimality(int n) {
    if (n<2) return false;
    
    int sqrt = (int) Math.sqrt(n);
    
    for(int i = 2; i <= sqrt; i++) {
      if(n%i == 0) {
        return false;
      } 
    }
    return true;
  }
  
  static ArrayList<Integer> sieveOfEratosthenes(int max) {
    ArrayList<Integer> primes = new ArrayList<Integer>();
    if (max < 2) return primes;
    boolean[] numbers = new boolean[max + 1];
    
    int prime = 2;
    numbers[0] = numbers[1] = true;
    
    while (prime <= Math.sqrt(max)) {
      crossOff(numbers, prime);
      prime = getNextPrime(numbers, prime);
      if (prime >= numbers.length) break;
    }
    
    for( int i = 2; i< numbers.length; i++) {
      if (!numbers[i]) {
        primes.add(i);
      }
    }
    return primes;

  }

  static void crossOff(boolean[] numbers, int prime) {
    for (int i = prime * prime; i < numbers.length; i += prime) {
      numbers[i] = true;
    }
  }

  static int getNextPrime(boolean[] numbers, int prime) {
    int next = prime + 1;
    while(next < numbers.length && numbers[next]) {
      next++;
    }
    return next;
  }

}
