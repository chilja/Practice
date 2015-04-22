/**
 * 
 */
package excercises.implementation;

/**
 * @author chiljagossow
 * 
 */
public class Stack{

  /**
   * @param args
   */
  public static void main(String[] args) {
    Stack stack = new Stack();
    stack.push(6);
    stack.push(1);
    stack.push(4);
    stack.push(3);
    stack.push(5);
    stack.push(2);
    sortStack(stack);
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }

  }

  ObjectNode top;

  Integer peek() {
    return top.data;
  }

  Integer pop() {
    if (top != null) {
      Integer item = top.data;
      top = top.next;
      return item;
    }
    return null;
  }

  void push(Integer item) {
    ObjectNode n = new ObjectNode(item);
    n.next = top;
    top = n;
  }
  
  boolean isEmpty() {
    if (top == null) {
      return true;
    } else {
      return false;
    }
  }
  
  public static void sortStack(Stack stack) {
    if (stack == null) return;
    
    Stack helper = new Stack();
    boolean sorted = false;
    Integer temp = null;

    while (!sorted) {
      sorted = true;
      while (!stack.isEmpty()) {
        if (temp == null) {
          temp = stack.pop();
        }
        if (!stack.isEmpty() && temp.compareTo( stack.peek()) < 0 ) {
          helper.push(stack.pop());
          sorted = false;
        } else {
          helper.push(temp); 
          temp = null;
        }
      }
      while (!helper.isEmpty()) {
        if (temp == null) {
          temp = helper.pop();
        }
        if (!helper.isEmpty() && temp.compareTo( helper.peek())>= 0) {
          stack.push(helper.pop());
          sorted = false;
        } else {
          stack.push(temp); 
          temp = null;
        }
        
      }
    }
  }

}

class MyQueue {

  private Stack stack1 = new Stack();
  private Stack stack2 = new Stack();

  Object dequeue() {
    while (stack1.peek() != null) {
      stack2.push(stack1.pop());
    }
    return stack2.pop();
  }

  void enqueue(Integer item) {
    while (stack2.peek() != null) {
      stack1.push(stack1.pop());
    }
    stack1.push(item);
  }
}

class ObjectNode  implements Comparable<ObjectNode>{
  ObjectNode next = null;
  Integer data;

  public ObjectNode(Integer data) {
    this.data = data;
  }

  void appendToTail(int data) {
    ObjectNode tail = new ObjectNode(data);
    ObjectNode n = this;
    while (n.next != null) {
      n = n.next;
    }
    n.next = tail;
  }

  void appendToTail(ObjectNode tail) {
    ObjectNode n = this;
    while (n.next != null) {
      n = n.next;
    }
    n.next = tail;
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(ObjectNode o) {
    return data.compareTo(o.data);
  }
}

class SetOfStacks {

  private int counter;
  private final int maxHeight;
  private Stack[] stacks;

  public SetOfStacks(int maxHeight) {
    if (maxHeight < 1) {
      throw new IllegalArgumentException("The maximum height must be greater than 0");
    }

    this.maxHeight = maxHeight;
  }

  public synchronized Object peek() {
    if (stacks == null) {
      return null;
    }

    Object item = stacks[stacks.length - 1].peek();
    if ((item == null) && (stacks.length > 1)) {
      return stacks[stacks.length - 2].peek();
    } else {
      return item;
    }
  }

  public synchronized Object pop() {
    if (stacks == null) {
      return null;
    }
    Object item = stacks[stacks.length - 1].pop();
    if ((item == null) && (stacks.length > 1)) {
      shrink();
    } else {
      return item;
    }
    return pop();
  }

  public synchronized void push(Integer item) {

    if (stacks == null) {
      stacks = new Stack[1];
    }

    if (stacks[stacks.length - 1] == null) {
      stacks[stacks.length - 1] = new Stack();
    }

    if (counter < maxHeight) {
      stacks[stacks.length - 1].push(item);
      counter++;
      return;
    } else {
      Stack[] newStacks = new Stack[stacks.length + 1];
      for (int i = 0; i < stacks.length; i++) {
        newStacks[i] = stacks[i];
      }
      stacks = newStacks;
      counter = 0;
      push(item);
    }
  }

  private void shrink() {
    if (stacks == null) {
      return;
    }
    if (stacks.length > 1) {
      Stack[] newStacks = new Stack[stacks.length - 1];
      for (int i = 0; i < newStacks.length; i++) {
        newStacks[i] = stacks[i];
      }
      stacks = newStacks;
    } else {
      stacks = null;
    }
  }
}
