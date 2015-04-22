package excercises.implementation;

public class ThreeStacks<T> {
  private T[] stackArray;
  private int[] top;
  private int[] bottom;
  
  private void initialize() {
    if (stackArray == null) {
      stackArray = (T[]) new Object[30];
      top = new int[3];
      bottom = new int[3];
      top[0] = bottom[0] = 0;
      top[1] = bottom[1] = 10; 
      top[2] = bottom[2] = 20;
    }
  }
    
  public void push(T item, int stackNo) {
    initialize();
    if (stackArray[top[stackNo]] == null) {
      stackArray[top[stackNo]] = item;
    } else {
      if (stackArray[top[stackNo] + 1] != null) {
        // stack is full
        resize();
      }
      top[stackNo]++; 
      push(item, stackNo);
    }
  }
  
  public T pop(int stackNo) {
    if (stackArray[top[stackNo]] != null) {
      T item = stackArray[top[stackNo]];
      stackArray[top[stackNo]] = null;
      if ( top[stackNo] > bottom[stackNo]) {
        top[stackNo]--;
      }
      return item;
    } 
    return null;
  }
  
  private void resize() {
    T[] newArray = (T[]) new Object[stackArray.length * 2];
    for (int i = 0; i < top.length; i++) {
      int bottomIndex = bottom[i];      
      int newBottom = bottomIndex * 2;
      int newTop = newBottom + top[i] - bottomIndex;
      for (int j = 0; j <= top[i] - bottom[i]; j++) {
        newArray[newBottom +  j] = stackArray[bottomIndex + j];
      }
      bottom[i] = newBottom;
      top[i] = newTop;
    }
    stackArray = newArray;
  } 
}
