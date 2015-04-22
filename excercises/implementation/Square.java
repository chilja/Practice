/**
 * 
 */
package excercises.implementation;

import java.util.HashMap;

/**
 * @author chiljagossow
 *
 */
public class Square {

  /**
   * @param args
   */
  public static void main(String[] args) {

//    boolean[][] matrix = {{false, true, true}, {false, true, true}, {false, true, false}};
//    System.out.println(findSquare(matrix, 0, 0, matrix.length));
    
    int[][] matrix2 = {{2, -33, 4}, {5, -1, 3}, {2, 8, 1}};
    System.out.println(new Square().getMaxSub(matrix2));
  }
  
  static int findSquare(boolean[][] matrix, int x, int y, int length) {
    if (length < 1 || x > matrix.length -1 || y > matrix.length -1) return 0;
    boolean foundSquare = true;
    for (int i = 0; i < length; i++) {
      if (!matrix[x + i][y] || !matrix[x][y + i] || !matrix[x + length -1][y + i] || !matrix[x + i][y + length - 1]) {
        foundSquare = false;
        break;
      }
    }
    if (foundSquare) return length;
    int a = findSquare(matrix, x, y, length - 1);
    int b = findSquare(matrix, x + 1, y, length - 1);
    int c = findSquare(matrix, x, y + 1, length - 1);
    int d = findSquare(matrix, x + 1, y +1, length - 1);
    return  Math.max(Math.max(a, b), Math.max(c, d));
    
  }
  
  class Matrix {
    int x;
    int y;
    int size;
  Matrix(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
  }

  public boolean equals(Object o) {
    if ( o instanceof Matrix ) {
      Matrix m = (Matrix) o;
      if ( x == m.x && x == m.y && size == m.size ) return true;
    }
    return false;
  }

  }

  HashMap<Matrix, Integer> submatrices = new HashMap<Matrix, Integer>();
  int[][] matrix;

  private int getSum( int x, int y, int size) {
    Matrix m = new Matrix(x, y, size);
    if ( submatrices.containsKey(m) ) {  
      return submatrices.get(m);
    }
    if (m.size == 1) {
      submatrices.put(m, matrix[x][y]);
      return matrix[x][y];
    } else {
      int sum = getSum( m.x, m.y, m.size - 1);
      for ( int i = 0; i< m.size ; i++ ) {
        sum += matrix[x + i][y + m.size - 1] + matrix[x + m.size - 1][y + i];
        if (x + i == x + m.size - 1 && y + m.size - 1 == y + i) sum -= matrix[x + m.size - 1][y + i];
      }
      submatrices.put(m, sum);
      return sum;
    }
  }

  public int getMaxSub(int[][] matrix) {
    int max = 0;
    this.matrix = matrix;
    for (int x = 0; x < matrix.length; x++) {
      for (int y= 0; y < matrix.length; y++) {
        for (int size = 1; (size <= matrix.length - x) && (size <= matrix.length - y) ; size++) {
          int sum = getSum( x, y, size);
          if (sum > max) max = sum;
        }
      }
    }
  return max;
  }

}
