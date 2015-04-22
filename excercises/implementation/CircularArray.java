/**
 * 
 */
package excercises.implementation;

import java.util.Iterator;

/**
 * @author chiljagossow
 *
 */
public class CircularArray<T> implements Iterable<T> {
  private T[] items;
  private int startIndex;
  
  @SuppressWarnings("unchecked")
  public CircularArray(int size) {
    items = (T[]) new Object[size];
    startIndex = 0;
  }

  public void set(int index, T item) {
    items[convertIndex(index)] = item;
  }
  
  public T get(int index) {
    return items[convertIndex(index)];
  }
  
  public void rotate(int i) {
    i = i%items.length;
    if (i < 0) {
      i = i + items.length;
    }
    startIndex = convertIndex(i);
  }
  
  public Iterator<T> iterator() {
    return new CircularIterator<T>(items); 
  }
  
  private int convertIndex(int index) {
    if (index < 0 || index >= items.length) {
      throw new IndexOutOfBoundsException();
    }
    return (index + startIndex)%items.length; 
  }
  
  private class CircularIterator<IT> implements Iterator<IT>{
    private int offset = -1;
    private IT[] items;
    
    CircularIterator(IT[] items){
      this.items = items;
    }
    
    public boolean hasNext() {
      return (offset < (items.length - 1));
    }
    
    public IT next(){
      offset++;
      return items[convertIndex(offset)];
    }
    
    public void remove(){
      throw new UnsupportedOperationException("remove() not supported");
    }
  } 
}

