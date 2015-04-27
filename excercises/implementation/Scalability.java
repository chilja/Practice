/**
 * 
 */
package excercises.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author chiljagossow
 *
 */
public class Scalability {

  /**
   * @param args
   */
  public static void main(String[] args) {
    
    int i;
    try {
      i = findInteger("/Users/chiljagossow/Desktop/Numbers.txt");
      System.out.print(i);
    }
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
  
  public static int findInteger(String fileName) throws IOException {
    long size = Integer.MAX_VALUE;
    byte[] numbers = new byte[(1<<10)];
    Scanner sc = new Scanner(new File(fileName));
    while (sc.hasNextInt()){
      int i = sc.nextInt();
      numbers[i/8] = (byte)((numbers[i/8]) | (1 << (i%8)));
    } 
    int i = 0;
    while (numbers[i] == -1) {
      i++;
    }
    for (int j = 0; j<8; j++) {
      if ((numbers[i]| 1<<j) == -1) {
        sc.close();
        return i*8 + j;
      } 
    }
    sc.close();
    return -1;  
  }

}
