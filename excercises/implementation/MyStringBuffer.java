/**
 * 
 */
package excercises.implementation;

/**
 * @author chiljagossow
 *
 */
public class MyStringBuffer {
  private MyArrayList<String> strings;
  
  public MyStringBuffer() {}
  
  public MyStringBuffer(String...strings ) {
    if (strings != null && strings.length != 0) {
      for (int i = 0; i< strings.length; i++) {
        append(strings[i]);
      }
    }
  }
  
  public void append(String s) {
    if (s != null && s.length() != 0) {
      initialize();
      this.strings.add(s);
    }
  }
  
  private void initialize() {
    if (strings == null) {
      strings = new MyArrayList<String>();
    }
  }
}
