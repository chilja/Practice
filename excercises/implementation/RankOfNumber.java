/**
 * 
 */
package excercises.implementation;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author chiljagossow
 *
 */
public class RankOfNumber {

  /**
   * @param args
   */
  public static void main(String[] args) {
    RankOfNumber r = new RankOfNumber();
    r.track(5);
    r.track(1);
    r.track(4);
    r.track(4);
    r.track(5);
    r.track(9);
    r.track(7);
    r.track(13);
    r.track(3);
    System.out.println(r.getRankOfNumber(1));
    System.out.println(r.getRankOfNumber(3));
    System.out.println(r.getRankOfNumber(4));

  }

  TreeMap<Integer, Integer> frequency = new TreeMap<Integer, Integer>();

  void track(int x) {
    if (!frequency.containsKey(x)) {
      frequency.put(x, 1);
    } else {
      frequency.put(x, frequency.get(x) + 1);
    }
  }

  int getRankOfNumber(int x) {
    int rank = 0;
    for (Map.Entry<Integer, Integer> f: frequency.entrySet()) {
      if (f.getKey() < x) {
        rank += f.getValue();
      } else if (f.getKey().equals(x)) {
        return rank += f.getValue() - 1;
      } else {
        return rank;
      } 
    }
    return rank;
  }
}
