/**
 * 
 */
package excercises.implementation;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author chiljagossow
 *
 */
public class Change {

  /**
   * @param args
   */
  public static void main(String[] args) {
    HashSet<Change> set;
    try {
      set = getChange(100);
      for (Change c: set){
        System.out.println("quarters " + c.quarters + " dimes " + c.dimes + " nickels " + c.nickels + " pennies " + c.pennies);
      } 
    }
    catch (CloneNotSupportedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private static HashMap<Integer, HashSet<Change>> sSets = new HashMap<Integer, HashSet<Change>>();
  
  private int quarters;
  private int dimes;
  private int nickels;
  private int pennies;

  @Override
  public boolean equals(Object o) {
    if (o instanceof Change) {
      Change c = (Change) o;
      if ( quarters == c.quarters && 
            dimes == c.dimes &&
            nickels == c.nickels &&
            pennies == c.pennies ) {
        return true;      
      }
    }
    return false;
  }
  
  public int hashCode() {
    return quarters + dimes + nickels + pennies;  
  }
  
  public Object clone() {
    Change c = new Change();
    c.quarters = quarters; 
    c.dimes = dimes;
    c.nickels = nickels;
    c.pennies = pennies;
    return c;
  }
  
  public static HashSet<Change> getChange(int amount) throws CloneNotSupportedException {
    if (amount < 0) return null;
    if (sSets.containsKey(amount)) {
      return sSets.get(amount);
    }
    HashSet<Change> set = new HashSet<Change>();
    if (amount == 0) {
      Change c = new Change();
      set.add(c);
      sSets.put(amount, set);
      return set;
    }
    
    HashSet<Change> set25 = getChange(amount - 25);
    if (set25 != null) {
      for (Change c: set25){
        Change c2 = (Change) c.clone();        
        c2.quarters++;
        set.add(c2);
      } 
    }
    HashSet<Change> set10 = getChange(amount - 10);
    if (set10 != null) {
      for (Change c: set10){
        Change c2 = (Change) c.clone();
        c2.dimes++;
        set.add(c2);
      } 
    }
    HashSet<Change> set5 = getChange(amount - 5);
    if (set5 != null) {
      for (Change c: set5){
        Change c2 = (Change) c.clone();
        c2.nickels++;
        set.add(c2);
      } 
    }
    HashSet<Change> set1 = getChange(amount - 1);
    if (set1 != null) {
      for (Change c: set1){
        Change c2 = (Change) c.clone();
        c2.pennies++;
        set.add(c2);
      } 
    }
    sSets.put(amount, set);
    return set;
  }
}
