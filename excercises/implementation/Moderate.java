/**
 * 
 */
package excercises.implementation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author chiljagossow
 *
 */
public class Moderate {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
  
  public Integer swapNumbers(Integer a, Integer b) {
    
    a = a - b;
    b = b + a;
    a = b - a; 
    return a;

  }
  
  enum BallColor { R, B, G, Y, D}

  static class HitResult {
    int hits;
    int pseudoHits;
  }

  static class SlotAssignment {
    BallColor[] slots;
    SlotAssignment(BallColor slot1, BallColor slot2, BallColor slot3,BallColor slot4) {
      slots = new BallColor[4];
      slots[0] = slot1;
      slots[1] = slot2;
      slots[2] = slot3;
      slots[3] = slot4;
    }
  }

  public HitResult evaluateColorGuess( SlotAssignment guess, SlotAssignment solution) {
    HitResult result = new HitResult();
    
    for ( int i = 0; i < solution.slots.length; i++ ) {
      if ( solution.slots[i] == guess.slots[i] ) {
        result.hits++;
      } else {
        inner: for ( int j = 0; j < solution.slots.length; j++ ) {
          if ( solution.slots[j] == guess.slots[i] && solution.slots[j] != guess.slots[j]) {
            result.pseudoHits++;
            solution.slots[j] = BallColor.D;
            break inner;
          }
        }
      }

    }
    return result;
  }
  
  class Sequence {
    int low; 
    int high;
  }

  public Sequence findUnsortedSequence( int[] array) {

    Sequence sequence = new Sequence();
    
    // find first unsorted
    outer: for ( int i = 0; i < array.length - 1; i++) {
      for ( int j = i + 1; j < array.length ; j++ ) {
        if (array[i] > array[j] ) {
          sequence.low = i;
          break outer;
        }
      }
    }
    
    // find last unsorted
    outer:for ( int i = array.length - 1; i > 0; i--) {
      for ( int j = i - 1; j >= 0; j-- ) {
        if (array[i] < array[j] ) {
          sequence.high = i ;
          break outer;
        }
      }
    }

    return sequence;
  }
  
  // 17.8
  public int findLargestSum( int[] array ) {
    int sum = 0;
    int maxSum = 0;
    int start = 0;
    int currentStart = 0;
    int end = 0;
    
    for ( int i = 0; i < array.length; i++ ) {
      sum += array[i];
      if (maxSum < sum) {
        maxSum = sum;
        end = i;
        start = currentStart;
      }
      if (sum < 0) {
        sum = 0;
        currentStart = i +1;
      }
      
    }
    return maxSum;
  }
  
  class Element {
    LinkedList<Attribute> attributes = new LinkedList<Attribute> ();
    LinkedList<Element> children = new LinkedList<Element> ();
    String tagName;
    int tag;
    String value;

  Element(String tagName, int tag) {
    this.tagName = tagName;
    this.tag = tag;
  }

  void addElement(Element e) {
    children.add(e);
  }

  void addAttribute(Attribute a) {
    attributes.add(a);
  }

  void setValue(String value) {
    this.value = value;
  }



  String getEncoding() {
      StringBuilder result = new StringBuilder();

      result.append(tag);
      result.append(" ");
      
      for (Attribute a: attributes) {
        result.append(a.getEncoding());
      }
      result.append(0);
      result.append(" ");

      for (Element e: children) {
        result.append(e.getEncoding());
      }
      if (value != null){
        result.append(value);
        result.append(" ");
      }
      result.append(0);
      result.append(" ");
      
      return result.toString();
    }
  }

  class Attribute {
    String tagName;
    int tag;
    String value; 

    Attribute(String tagName, int tag) {
      this.tagName = tagName;
      this.tag = tag;
    }

    String getEncoding() {
      StringBuilder result = new StringBuilder();

      result.append(tag);
      result.append(" ");
      result.append(value);
      result.append(" ");
      
      return result.toString();
    }

    void setValue(String value) {
      this.value = value;
    }

  }


  public String encodeXml( Attribute root ) {
    return root.getEncoding();
  }
  
  // 17.12
  class Pair {
    private int summand1;
    private int summand2;
    Pair(int summand1, int summand2) {
      this.summand1 = summand1;
      this.summand2 = summand2;
    }
    
    int getsummand1() {
      return summand1;
    }
    int getsummand2() {
      return summand2;
    }

  }


  public  LinkedList<Pair> findPairs( int[] array, int sum) {
    if (array == null || array.length == 0) return null;
    LinkedList<Pair> pairs = new LinkedList<Pair>();
    for ( int i = 0; i < array.length - 1; i++) {
      for (int j = i+1; j < array.length; j++) {
        if (array[i] + array[j] == sum) {
          pairs.add(new Pair(array[i], array[j]));
        }
      }
    }
    return pairs;
  }
  
  // 17.13
  class BiNode {
    BiNode node1; 
    BiNode node2;
    int data; 
    void print(){
      int data1 = -1;
      int data2 = -1;
      if (node1 != null) data1 = node1.data;
      if (node2 != null) data2 = node2.data;
      System.out.println("node " + data + " node1 " + data1 + " node2 " + data2 );
    }
  }

  BiNode convertTreeToList(BiNode root) {

    if (root == null) {
      return null;
    }
    
    System.out.println("Tree");
    printTree(root);

    // singly linked list
    root = rotate(root);
    
    addPredecessor(root);
    System.out.println("List");
    printList(root);
    return root;
  }
  
  void printTree(BiNode root) {
    
    if (root == null) return;
    printTree(root.node1);
    root.print();
    printTree(root.node2);
  }
  
  void printList(BiNode root) {
    
    if (root == null) return;
    root.print();
    printList(root.node2);
  }
  
  BiNode rotate(BiNode root) {
    // no rotation needed at this node
    if (root == null) return root;
    if (root.node2 != null) {
      root.node2 = rotate(root.node2);
    }
    if (root.node1 != null) {
      BiNode tail = getRigthTail(root.node1);
      tail.node2 = root;
      root = root.node1;
      tail.node1 = null;
    }

    return root;
  }
  
  BiNode getRigthTail(BiNode root) {
    if (root == null ) return root;
    BiNode node = root;
    while (true) {
      node = rotate(node);
      if (node.node2 == null) break;
      node = node.node2;
    }
    return node;
  }

  void addPredecessor(BiNode root) {
    BiNode node = root;
    while (node.node2 != null) {
      node.node2.node1 = node;
      node = node.node2;
    }
  }
  
  // 18.1
  public int add(int s1, int s2) {
    if (s2 == 0) return s1;
    int sum = s1 ^ s2;
    int carryOn = (s1 & s2) << 1; 
    return add(sum, carryOn);
  }
  
  // 18.2 
  
  public int[] shuffleCards(int[] cards, int i) {
    if ( i == 0) return cards;
    shuffleCards(cards, i - 1);
    int rand = rand(0, i);
    int card = cards[rand];
    cards[rand] = cards[i];
    cards[i] = card;
    return cards;
    
  }

  
  public HashSet<Integer> getRandomSet( int[] array, int m) {

    if ( array == null || m > array.length || m < 1 ) return null; 
    
    HashSet<Integer> set = new HashSet<Integer>();
    while (set.size() < m) { 
      set.add (rand(0, array.length));
    }

    return set;

  }

  int rand(int high, int low) {
    return low + ((int) ((high - low) * Math.random()));
  }
  
  public int countTwos(int number) {
    if ( number < 2 ) return 0;
    int remainder = number % 10;
    int result = 0;
    if (remainder == 2) result++;
    return result += countTwos( (number - remainder) / 10);  
  }

  public int countAllTwos(int number) {
    if ( number < 2 ) return 0;
    return countTwos(number) + countAllTwos(number - 1);
  }
  
  // 18.4
  public int distance( String textString, String word1, String word2) {
    Text text = new Text(textString);
    return text.findShortestPath(word1, word2);
  }

  class Text {

    HashMap<String, LinkedList<Integer>> occurence;
    String[] words;

    Text (String text) {
      words = text.split(" ");
    }

    void findOccurences() {
      if (occurence == null ) {
        occurence = new HashMap<String, LinkedList<Integer>>();
        for (int i = 0; i < words.length; i++) {
          String word = words[i];
          LinkedList<Integer> positions = occurence.get(word);
          if ( positions == null ) {
            positions = new LinkedList<Integer>();
          }
          positions.add(Integer.valueOf(i));  
          occurence.put(word, positions);
        }
      }
    }

    int findShortestPath(String word1, String word2) {
      findOccurences();
      LinkedList<Integer> positions1 = occurence.get( word1);
      LinkedList<Integer> positions2 = occurence.get( word2);
      if (positions1 == null || positions2 == null) return -1;
      int min = words.length + 1;
      for (Integer pos1: positions1) {
        for (Integer pos2: positions2) {
          int distance = Math.abs(pos1 - pos2) ;
           if (distance < min) {
            min = distance;
          }
        }
      }
      return min;
    }
  }

}
