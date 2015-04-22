/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author chiljagossow
 * 9.10
 */
public class MaxStacks {

  /**
   * @param args
   */
  public static void main(String[] args) {
    MaxStacks ms = new MaxStacks();
    ArrayList<Box> boxes = new ArrayList<Box>();
    boxes.add(new Box(1,1,1));
    boxes.add(new Box(2,2,2));
    boxes.add(new Box(3,2,2));
    boxes.add(new Box(3,3,3));
    boxes.add(new Box(8,1,1));
    Stack s = ms.maxStack(boxes);
    System.out.println(s.getHeight());
  }
  
  static class Box{
    private int height;
    private int width;
    private int depth;
    
    Box(int height, int width, int depth) {
      this.height = height;
      this.width = width;
      this.depth = depth;
    }

    int getHeight() {
      return height;
    }
    
    boolean fitsOnTopOf(Box b) {
      return (width < b.width && height < b.height && depth < b.depth); 
    }
  }

  static class Stack{
    private ArrayList<Box> boxes = new ArrayList<Box>();
    private int height;
    
    Stack stackBox(Box b) {
      if (b != null) {
        boxes.add(b);
        height = height + b.getHeight();
      }
      return this;
    }
    
    int getHeight() {
      return height;
    }
    
    Stack stackBoxes(ArrayList<Box> boxes) {
      if (boxes != null) {
        for(Box b: boxes) {
          stackBox(b);
        }
      }
      return this;
    }
    
    ArrayList<Box> getBoxes() {
      return boxes;
    }
  }

  static class StackComparator implements Comparator<Stack> {
    public int compare(Stack s1, Stack s2) {
      return s2.getHeight() - s1.getHeight();
    }
  }

  private HashMap<Box, Stack> maxStacks = new HashMap<Box, Stack>();
    
  public Stack maxStack(ArrayList<Box> boxes) {
    PriorityQueue<Stack> stacks = new PriorityQueue<Stack>(boxes.size(), new StackComparator());
    for (Box next:boxes) {
      stacks.offer(maxStack(boxes, next));
    }
    return stacks.poll();
  }

  public Stack maxStack(ArrayList<Box> boxes, Box bottom) {
    if (maxStacks.containsKey(bottom)) {
      return maxStacks.get(bottom);
    }
    PriorityQueue<Stack> stacks = new PriorityQueue<Stack>(boxes.size(), new StackComparator());
    stacks.offer(new Stack().stackBox(bottom));
    for (Box next:boxes) {
      if (next.fitsOnTopOf(bottom)) {
        Stack s = new Stack();
        s.stackBox(bottom);
        s.stackBoxes(maxStack(boxes, next).getBoxes());
        stacks.offer(s);
      }
    }
    Stack maxStack = stacks.poll();
    maxStacks.put(bottom, maxStack);
    return maxStack;
  }
}
