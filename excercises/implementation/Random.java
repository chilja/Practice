/**
 * 
 */
package excercises.implementation;

/**
 * @author chiljagossow
 *
 */
public class Random {

  /**
   * @param args
   */
  public static void main(String[] args) {
    double sum = 0;
    for (int i = 0; i < 700; i++) {
      sum += rand7();
    }
    double average = sum/700.0;
    System.out.println(average);
  }
  
  static int rand7() {
    while (true){
      int result = (rand5() * 5 + rand5());
      if (result < 21){
        return result%7;
      }
    }
  }
  
  static int rand5() {
    return ((int)(Math.random() * 5)) %5;
  }
}
