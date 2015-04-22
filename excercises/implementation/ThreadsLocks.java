/**
 * 
 */
package excercises.implementation;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chiljagossow
 *
 */
public class ThreadsLocks {

  /**
   * @param args
   */
  public static void main(String[] args) {
    final Foo foo = new Foo();
    final Thread threadA = new Thread( new Runnable() {
      public void run() {
        try {
          Thread.sleep(100);
        }
        catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        foo.first();
      }
    });

    final Thread threadB = new Thread( new Runnable() {
      public void run() {
        try {
          threadA.join();
          Thread.sleep(100);
          foo.second();
        }
        catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
      }
    });

    Thread threadC = new Thread( new Runnable() {
      public void run() {
        try {
          threadB.join();
          foo.third();
        }
        catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        
      }
    });
    
    threadA.start();
    threadB.start();
    threadC.start();
   


  }

}

class Foo {
  Semaphore sem1;
  Semaphore sem2;
  ReentrantLock lock;

public void first() { 
  sem1 = new Semaphore(1);
  sem2 = new Semaphore(1);
  System.out.println("first");  
}
public void second() {
  System.out.println("second"); 
}
public void third() {
  System.out.println("third");  
}

}

