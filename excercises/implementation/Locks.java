/**
 * 
 */
package excercises.implementation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chiljagossow
 *
 */
public class Locks {

  /**
   * @param args
   */
  public static void main(String[] args) {
    LockProvider lp = new LockProvider();
    LinkedList<Integer> locks1 = new LinkedList<Integer>();
    locks1.add(1);
    locks1.add(2);
    locks1.add(3);
    locks1.add(4);
    System.out.println(lp.acquire(1, locks1));
    LinkedList<Integer> locks2 = new LinkedList<Integer>();
    locks2.add(1);
    locks2.add(3);
    locks2.add(5);
    System.out.println(lp.acquire(2, locks2));
    LinkedList<Integer> locks3 = new LinkedList<Integer>();
    locks3.add(7);
    locks3.add(5);
    locks3.add(9);
    locks3.add(2);
    System.out.println(lp.acquire(3, locks3));
  }
  
  static class LockProvider {

    private Map<Integer, LinkedList<Integer>> grantedLocks;
    private HashMap<Integer, MyLock> locks;
    
    LockProvider(){
      grantedLocks = new HashMap<Integer, LinkedList<Integer>>();
      locks = new HashMap<Integer, MyLock>();
    }

    boolean acquire(int threadId, LinkedList<Integer> newLocks){
      // check if thread has already acquired locks that have not been released
      if (grantedLocks.containsKey(threadId)) return false;
        
      if (!detectCircle(newLocks)) {
        // insert locks
        for (Integer id: newLocks) {
          if (!locks.containsKey(id)){
          locks.put(id, new MyLock(id));
          }
        }
        // insert edges
        Iterator<Integer> i = newLocks.iterator();
        if (i.hasNext()) {
          MyLock head = locks.get(i.next());
          while (i.hasNext()) {
            MyLock lock = locks.get(i.next());
            head.insertSuccessor(lock);
            head = lock;
          }     
        }
        
        //
        grantedLocks.put(threadId, newLocks);
        
        return true;    
      }
      
      return false;
    }
    
    private boolean detectCircle(LinkedList<Integer> newLocks) {
      LinkedList<Integer> existent = new LinkedList<Integer>();
      for (Integer id: newLocks) {
        if (locks.containsKey(id)){
          existent.push(id);
        }
      }
      if (existent.size() < 2) return false; // no circle possible
      while (!existent.isEmpty()) {
        MyLock next = locks.get(existent.pop());
        for (Integer id: existent) {
          if (next.findLock(id)) return true;
        }
      } 
      return false;
    }
    
    void release(MyLock lock) {
      // delete edges and nodes
    
    }

    private class MyLock extends ReentrantLock {
      private int id;
      Set<MyLock> adjacentLocks = new HashSet<MyLock>();
      
      MyLock(int id) {
        this.id = id;
      }
      
      public boolean equals(Object o) {
        if (o instanceof MyLock) {
          return (id == (((MyLock) o).id));
        }
        return false;
      }
      
      public int hashCode() {
        return id;
      }
      
      void insertSuccessor(MyLock lock){
        adjacentLocks.add(lock);
      }
      
      boolean findLock(int id) {
        if (adjacentLocks.contains(new MyLock(id))) {
          return true;
        }
        for (MyLock lock: adjacentLocks) {
          if (lock.findLock(id)) return true;
        }
        return false;
      }
    }

  }

}
