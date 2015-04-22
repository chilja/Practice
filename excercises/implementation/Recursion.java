/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author chiljagossow
 *
 */
public class Recursion {

  /**
   * @param args
   */
  public static void main(String[] args) {
    Recursion r = new Recursion();
    int[][] screen = {{1,1,2,2,2},{2,2,3,1,1},{1,3,3,3,3},{2,2,3,2,3},{1,1,1,2,2}};
    print(screen);    
    r.color(screen, 2, 2, 0);  
    print(screen);
  }
  
  // 9.7
  
  public void color(int[][] screen, int i, int j, int newColor) {
    if(screen  == null || i < 0 || j < 0 || i >= screen.length || j >= screen[0].length) 
      return;
    int original = screen[i][j];
    screen[i][j] = newColor;
    if (((i-1) > 0) && (screen[i-1][j] == original)) {
      color(screen, i-1, j, newColor);
    }
    if (((i+1) < screen.length) && (screen[i+1][j] == original)){
      color(screen, i+1, j, newColor);
    }

    if (((j-1) > 0) && (screen[i][j-1] == original)){
      color(screen, i, j-1, newColor);
    }
    
    if (((j+1) < screen[0].length) && (screen[i][j+1] == original)) {
      color(screen, i, j+1, newColor);
    }
  }
  
  static boolean findCircle(Node head) {
    Node slow = head;
    Node fast = head.next;
    while (slow != null && fast != null && fast.next != null) {
      if (slow.equals(fast)) return true;
      slow = slow.next;
      fast = fast.next.next;
    }
    return false;
  }

  
  static class Node {
    Node next;
    private int data;

    Node ( int data ) {
      this.data = data;
    }

    void append( int data ) {
      if (next == null) {
        next = new Node(data);
      } else { 
        next.append(data);
      }
    }
    
    void append( Node n) {
      if (next == null) {
        next = n;
      } else { 
        next.append(n);
      }
    }
    
    public boolean equals(Object o) {
      if (o instanceof Node) {
        return ((Node) o).data == this.data;
      }
      return false;
    }
  }

  static boolean isPalindrome( Node head ) {
    Node tail = head;
    Node previous = null;
    while (tail.next != null) {
      previous = tail;
      tail = tail.next;
    }
    if ( head.data == tail.data) {
      if (previous == null) return true;
      // chop off head and tail
      head = head.next;
      previous.next = null;
      return isPalindrome(head);
    }
    return false;
  }

  
  static int countZeroes(int n) {
    long result = factorial(n);
    int zeroCounter = 0;
    while ( (result % 10) == 0) {
      result = result / 10;
      zeroCounter++;
    }
    return zeroCounter;
  }

  static int factorial(int n) {
    if ( n == 1) return 1;
    return (n * ( factorial(n -1)));
  }

  
  static boolean evaluate( boolean x1, boolean x2, boolean x3) {
    return ( (x1 & x2 & x3) | ( (!x1) & (!x2) & (!x3)));
  }

  static boolean evaluateBoard( boolean[][] board ) {
    for ( int i = 0; i < 3; i++) {
      if (evaluate( board[i][0], board[i][1], board[i][2])) return true;
    }
    for ( int i = 0; i < 3; i++) {
      if (evaluate( board[0][i], board[1][i], board[2][i])) return true;
    }
    if (evaluate( board[0][0], board[1][1], board[2][2])) return true;
    if (evaluate( board[0][2], board[1][1], board[2][0])) return true;
    return false;
  }

  static void print(boolean[][] chessBoard) {
    for ( int i = 0; i< 8; i++) {
      for ( int j = 0; j< 8; j++) {
        if ( chessBoard[i][j] == false) {
          System.out.print(0);
        } else { 
          System.out.print(1);
        }
      }
      System.out.println();
    }
  }
  
  static void print(int[][] screen) {
    for ( int i = 0; i< screen.length; i++) {
      for ( int j = 0; j< screen[0].length; j++) {
        System.out.print(screen[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  static boolean[][] placeQueen(boolean[][] chessBoard, int i, int j) {
    for ( int x = 0; x < chessBoard.length; x++) {
      chessBoard[i][x] = true;
      chessBoard[x][j] = true;
      
    }

    for ( int x = 0, y = j-i; x < chessBoard.length; x++, y++) {
      if (y >= 0 && y < chessBoard.length) {
        chessBoard[x][y] = true;
      }
    }
    
    for ( int x = 0, y = j-i; x < chessBoard.length; x++, y--) {
      if (y >= 0 && y < chessBoard.length) {
        chessBoard[x][y] = true;
      }
    }
    return chessBoard;
  }

  
  static void colorMatrix(int[][] matrix, int color, int x, int y) {
    Screen screen = new Screen(matrix);
    screen.print();
    screen.colorComponent( screen.getPixel(x, y), color);
    screen.print();

  }


  static class Screen {
    int[][] pixels;

    Screen(int[][] pixels) {
      this.pixels = pixels;
    }

    void print() {
      for (int i = 0; i < pixels.length; i++) {
        for (int j = 0; j < pixels[0].length; j++) {
          System.out.print(pixels[i][j] + " ");
        }
        System.out.println();
      }
    }
    
    Pixel getPixel(int x, int y) {
      return new Pixel( x, y, pixels[x][y] );
    }

    Pixel getTop(Pixel pixel) {
      // out of bound
      if (pixel.x - 1< 0) return null;
      return getPixel( pixel.x - 1, pixel.y);   
    }

    Pixel getBottom(Pixel pixel) {
      // out of bound
      if (pixel.x + 1 >= pixels.length) return null;
      return getPixel( pixel.x + 1, pixel.y);
    }

    Pixel getRight(Pixel pixel) {
      // out of bound
      if (pixel.y + 1 >= pixels[0].length) return null;
      return getPixel( pixel.x , pixel.y + 1);
    }

    Pixel getLeft(Pixel pixel) {
      // out of bound
      if (pixel.y - 1 < 0 ) return null;
      return getPixel( pixel.x , pixel.y - 1);
    }
    
    ArrayList<Pixel> getAdjacentPixels(Pixel pixel) {
      ArrayList<Pixel> adjacentPixels = new ArrayList<Pixel>();
      Pixel p = getLeft(pixel);
      if (p != null) adjacentPixels.add(p);
      p = getRight(pixel);
      if (p != null) adjacentPixels.add(p);
      p = getTop(pixel);
      if (p != null) adjacentPixels.add(p);

      p = getBottom(pixel);
      if (p != null) adjacentPixels.add(p);
      return adjacentPixels;
    }

    ArrayList<Pixel> getSameColorComponent(Pixel pixel) {
      int color = pixel.color;
      LinkedList<Pixel> pixels = new LinkedList<Pixel>();
      ArrayList<Pixel> component = new ArrayList<Pixel>();
      pixels.push(pixel);
      component.add(pixel);
      pixel.visit();
      while (!pixels.isEmpty()) {
        Pixel current = pixels.pop(); 
        ArrayList<Pixel> adjacentPixels = getAdjacentPixels(current); 
        for (Pixel adjacentPixel: adjacentPixels) {
          if ( adjacentPixel != null && !adjacentPixel.isVisited() && adjacentPixel.color ==                                            color) {
            adjacentPixel.visit();
            component.add(adjacentPixel);
            pixels.push(adjacentPixel);
          }
        }
      }
      return component;

    }

    void colorComponent( Pixel pixel, int color) {
      ArrayList<Pixel> component = getSameColorComponent(pixel);
      for (Pixel p: component) {
        colorPixel(p, color);
      }
    }

    void colorPixel(Pixel pixel, int color) {
      pixels[pixel.x][pixel.y] = color;
    }
    
  }

  static class Pixel {
    private int x;
    private int y;
    private int color;
    private boolean visited;
    private 

    Pixel(int x, int y, int color) {
      this.x = x;
      this.y = y;
      setColor(color);
    }

    int getColor() {
      return color;
    }

    void setColor(int color) {
      this.color = color;
    }

      
    void visit() {
      visited = true;
    }

    boolean isVisited() {
      return visited;
    }

    
  }

  
  static ArrayList<String> getParentheses(int n) {
    ArrayList<String> permuts = new ArrayList<String>();
    // empty
    if (n < 1) return permuts;
    // base case
    String s = "()";
    if (n == 1) {
      permuts.add(s);
      return permuts;
    }
    
    // n > 1: recursive approach 
    permuts = getParentheses( n -1 );

    ArrayList<String> newPermuts = new ArrayList<String>();

    for (String s2 : permuts) {
      String s3 = s + s2;
      newPermuts.add(s3);
      inner: for ( int i = 0; i < s2.length() - 1; i++) {
        char c = s2.charAt(i);
        if (c == '(' ) {
          s3 = s2.substring(0, i+1) + s + s2.substring(i + 1, s2.length());
          newPermuts.add(s3);
        } else {
          break inner;
        }
      }
    }
    return newPermuts;
  }

  
  static ArrayList<String> getAllSubsets(char[] elements) {
    return getAllSubsets(elements, 0);
  }

  static ArrayList<String> getAllSubsets(char[] elements, int index) {
    ArrayList<String> subsets = new ArrayList<String>();
    ArrayList<String> newSubsets = new ArrayList<String>();

    if ( index < elements.length ) {
      subsets = getAllSubsets(elements, index + 1);
    
      for( String s: subsets) {
        String newString = s + elements[index];
        newSubsets.add(newString);
      }
      subsets.add(String.valueOf(elements[index]));
      subsets.addAll(newSubsets);   
    }
    return subsets; 
  }

  
  
  static int getMagicIndex(int[] numbers) {
    // error
    if (numbers == null) {
      return -1;
    }

    //find magic number
    return getMagicIndex(numbers, 0, numbers.length -1);
      
  }


  static int getMagicIndex(int[] numbers, int lower, int upper) {

    // empty
    if (upper < lower) {
      return -1;
    }

    // if lower bound its too big a magic number cannot exist
    if (numbers[lower]  > lower) return -1;

    int median = ((upper - lower) / 2) + lower;

    if ( numbers[median] == median ) {
      // found a magic index
      return median;
    } else if (numbers[median] > median) {
      // search on the left side
      return getMagicIndex(numbers, lower, median - 1);
    } else {
      // search on the right side
      return getMagicIndex(numbers, median + 1, upper);
    }
  }

  
  
  static int getNoOfPaths(int x, int y) {
    if (x < 0 || y < 0) return 0;
    if (x == 0 && y == 0) return 1;
    if (x == 1 && y == 0) return 1;
    if (x == 0 && y == 1) return 1;
    
    return getNoOfPaths( x-1, y) + getNoOfPaths(x, y -1);


  }

  
  static HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();


  static Integer getNoOfWays(int n) {
    
  if ( n < 0) return 0;
  if (n == 0) return 1;
//  if (n== 2) return 2;
//  if (n== 3) return 4;
  Integer result = null;

    result = cache.get(n);
  if (result != null) return result;
  
  result = Integer.valueOf(0);
//  for ( int i = 1; i < n; i++) {
    result = getNoOfWays(n -1) + getNoOfWays(n -2) + getNoOfWays(n -3);
//  }

  cache.put(n, result);
  return result;

  }


}
