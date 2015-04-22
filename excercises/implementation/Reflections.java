/**
 * 
 */
package excercises.implementation;

import com.sun.tools.internal.xjc.model.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * @author chiljagossow
 *
 */
public class Reflections {

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      Class c = Class.forName("excercises.implementation.FizzBuzz");

      java.lang.reflect.Constructor con = c.getDeclaredConstructor();
      int type = con.getModifiers();
      switch (type){
        case Modifier.PUBLIC: 
          System.out.println("public");
          break;
        default:System.out.println("other");
        
      }
      FizzBuzz fb = (FizzBuzz) con.newInstance(null);     
    }
    catch (SecurityException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (NoSuchMethodException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalArgumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InstantiationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (IllegalAccessException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    catch (InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }    
    catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
