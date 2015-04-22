/**
 * 
 */
package excercises.implementation;

import java.util.HashMap;

/**
 * @author chiljagossow
 *
 */
public class Dictionary {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] array = {1,3,4,3,3,6,5,5,5,5};
    HashMap<Integer,Integer> dictionary = makeDictionary(array);
    for (int i = 0; i < array.length; i++) {
      System.out.println(dictionary.get(array[i]));
    }
  }
  
  public static HashMap<Integer, Integer> makeDictionary(int[] array) {
    HashMap<Integer,Integer> dictionary = new HashMap<Integer, Integer>();
    for (int i = 0; i < array.length; i++) {
      if (dictionary.containsKey(array[i])) {
        Integer count = dictionary.get(array[i]);
        dictionary.put(array[i], ++count);
      } else {
        dictionary.put(array[i], 1);
      }
    }
    return dictionary;
  }

}
