/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author chiljagossow
 * 
 */
public class StringTasks {

  /**
   * @param args
   */
  public static void main(String[] args) {
    String s = "abccacbddad";
    int x = getLength(s);
    System.out.println(x);
    x = findMaxSequence(s,2);
    System.out.println(x);
  }
  
  public static Boolean checkAnagram(char[] s1, char[] s2) {
    if ((s1 == null) || (s2 == null) || (s1.length != s2.length)) {
      return Boolean.FALSE;
    }

    outer: for (int i = 0; i < s1.length; i++) {
      for (int j = 0; j < s2.length; j++) {
        if (s1[i] == s2[j]) {
          s2[j] = 0;
          continue outer;
        }
      }
      return Boolean.FALSE;
    }
    return Boolean.TRUE;
  }
  
  public ArrayList<StringBuilder> permutations(String s) {
    if (s == null || s.length() == 0) return null;
    
    ArrayList<StringBuilder> permuts = new ArrayList<StringBuilder>();
    
    // base case
    if (s.length() == 1) {
      permuts.add(new StringBuilder(s));
      return permuts;
    } 
    
    ArrayList<StringBuilder> prePermuts = permutations(s.substring(0, s.length()-1));
    
    char c = s.charAt(s.length() - 1);
    for(StringBuilder sb: prePermuts) {
      for (int i = 0; i<= sb.length(); i++){
        permuts.add(new StringBuilder(sb.insert(i, c)));
        sb.deleteCharAt(i);
      }
    }
    return permuts;
  }
  
  static int getLength(String s) {
    if ( s == null || s.length() < 1) {
      return 0;
    }

    char c1 = s.charAt(0);
    char c2 = s.charAt(0);
    char current = s.charAt(0);
    int lastChange = 0;
    int max = 0;
    int length = 0;

    for (int i = 0; i < s.length(); i++) {
      current = s.charAt(i);
      if ( c1 == current) {
        length++;
        c1 = c2;
        c2 = current; 
        lastChange = i;   
      } else if ( c2 == current) {
        length++;
      } else if ( c1 == c2 ) {
        c2 = current;
        length++;
        lastChange = i;
      } else {      
        c1 = c2;
        c2 = current;
        if ( max < length) {
          max = length;
        }
        length = i - lastChange + 1;
        lastChange = i;
      }
    }
    if ( max < length) {
      max = length;
    }
    return max;
  }
  
  static int findMaxSequence(String s, int n) {
    // error checking   

    int max = 0;
    int length = 0;
    Map<Character, Integer> map = new HashMap<Character, Integer>();
    LinkedList<Character> lastSeen = new LinkedList<Character>();
   
    for (int i = 0; i < s.length(); i++) {
      Character c = s.charAt(i);

      // c is in current set
      if ( map.containsKey(c)|| map.size() < n) {

        lastSeen.remove(c);
        lastSeen.addFirst(c);
       
        map.put(c, i);
        
        length++;
      
      } else {
      
      // char is not in current set
      if (max < length) max = length;
      
      char least = lastSeen.pollLast();
      lastSeen.addFirst(c); 
      
      int leastIndex = map.remove(least);      
      map.put(c, i);
        
      length = i - leastIndex;
      }
    }
    
    if (max < length) max = length;
    return max;
  }


  public static void removeDuplicates(char[] s) {
    if (s == null) {
      return;
    }
    int length = s.length;
    if (length < 2) {
      return;
    }

    for (int i = 0; i < length; i++) {
      for (int j = i + 1; j < length; ++j) {
        if (s[i] == s[j]) {
          s[j] = 0;
        }
      }
    }

    for (int i = 0; i < s.length; i++) {
      if (s[i] != 0) {
        System.out.println(s[i]);
      }
    }
  }
  
  // 1.4
  public char[] replaceSpace(char[] string, int length) {
    if (string != null && string.length != 0) {
      int spaceCount = 0;
      for (int i = 0; i < length; i++) {
        if (string[i] == ' ')
          spaceCount++;
      }
      int leftOffset = length-1 + 2*spaceCount;
//      string[leftOffset+1] = '\0';
      for (int i = length -1; i >= 0; i--) {
        if (string[i] == ' ') {
          string[leftOffset] = '0';
          string[leftOffset - 1] = '2';
          string[leftOffset - 2] = '%';
          leftOffset -= 3;
        } else {
          string[leftOffset]  = string[i];
          leftOffset--;
        }
      }      
    }    
    return string;
  }
  
  // 1.5
  public String compressString(String s) {
    if ( s == null || s.length() == 0) return s;
    char current = s.charAt(0);
    int count = 0;
    StringBuilder compressed = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      if ( s.charAt(i) == current) {
        count++;
      } else { 
        compressed.append(current);
        compressed.append(count);
        current = s.charAt(i);
        count = 1;
      }
      if (compressed.length() + 1 + String.valueOf(count).length() >= s.length()) {
        return s;
      }
    }    
    compressed.append(current);
    compressed.append(count);
    return compressed.toString();    
  }
  
  
  
  
  
  

  public static void rotate() {

    int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
    int n = matrix.length - 1;

    System.out.println("before");
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    for (int i = 0; i < n; i++) {
      for (int j = i; j < (n - i); j++) {

        int x = matrix[i][j];
        matrix[i][j] = matrix[n - j][i];
        matrix[n - j][i] = matrix[n - i][n - j];
        matrix[n - i][n - j] = matrix[j][n - i];
        matrix[j][n - i] = x;
      }
    }
    System.out.println("after");

    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= n; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void zeroMatrix() {
    // i,j
    int[][] matrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
    int[] row = new int[matrix.length];
    int[] column = new int[matrix[0].length];

    System.out.println("before");
    for (int i = 0; i < row.length; i++) {
      for (int j = 0; j < column.length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

    // check for 0
    for (int i = 0; i < row.length; i++) {
      for (int j = 0; j < column.length; j++) {
        if (matrix[i][j] == 0) {
          row[i] = 1;
          column[j] = 1;
        }
      }
    }

    for (int i = 0; i < row.length; i++) {
      if (row[i] == 1) {
        for (int j = 0; j < column.length; j++) {
          matrix[i][j] = 0;
        }
      }
    }

    for (int j = 0; j < column.length; j++) {
      if (column[j] == 1) {
        for (int i = 0; i < row.length; i++) {
          matrix[i][j] = 0;
        }
      }
    }

    System.out.println("after");
    for (int i = 0; i < row.length; i++) {
      for (int j = 0; j < column.length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }

  }

}
