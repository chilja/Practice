/**
 * 
 */
package excercises.implementation;

/**
 * @author chiljagossow
 * 
 */
public class BitManipulation {

  /**
   * @param args
   */
  public static void main(String[] args) {
    double d = (9.0/16.0);
    printBinary(d);

  }
  
  // 5.2
  public static void printBinary(double no) {
    if (no <= 0 || no >= 1) return;
    StringBuilder binary = new StringBuilder(".");
    while (no > 0) {
      no = no * 2;
      int x = (no >= 1)? 1 : 0;
      binary.append(x);
      no = no - x;
      if(binary.length() > 32) {
        System.out.println("ERROR");        
        return;
      }
    }
    System.out.println(binary);
  }
  
  // 5.2
  public static int getBigger(int x) {

    for (int i = 0; i< 31; i++) {
      // find first location of 01
      if ( ((x & (1<<i)) != 0) && ( (~(~(1<<(i+1))| x) != 0)) ) {
        int y = x^(3<<i);
        int z = y;
        while(y > x) {
          z = y;
          y = getSmaller(y);
        }
        return z;
      }
    }
    return 0;
  }
  
  public static int getSmaller(int x) {
    for (int i = 0; i< 31; i++) {
      // find first location of 10
      if ( ((x & (1<<i+1)) != 0) && ( (~(~(1<<(i))| x) != 0)) ) {
        return x^(3<<i);
      }
    }
    return 0;
  }

  static int flip(int n, int m) {
    int x = n ^ m;
    int count1 = 0;
    while (x != 0) {
      if ((x & 1) == 1) {
        count1++;
      }
      x = x >> 1;
    }
    return count1;
  }
  
  public static int countBitsToFlip( int a, int b) {
    int mask = a^b;
    int count = 0;
    while (mask != 0) {
      if ((mask & 1) == 1) {
        count++;
      }
      mask = mask>>1;
    }
    return count;
  }

  static int insert(int n, int m, int i, int j) {
    int mask = 0;
    // j-i +1 1s
    mask = ~((1 << ((j - i) + 2)) - 1);
    n = n & mask;
    m <<= i;
    return m | n;
  }

  static int swap(int n) {
    int uneven = (n >> 1) & Integer.parseInt("01010101010101010101010101010101", 2);
    int even = (n << 1) & Integer.parseInt("00101010101010101010101010101010", 2);
    return uneven | even;
  }
  
  static int getMin(int a, int b) {
    int mask =  ((a-b) & (1<<31)) >> 31;    
    return (mask & a) + (~mask & b);    
  }
  
  static int getMax(int a, int b) {
    int mask =  ((a-b) & (1<<31)) >> 31;    
    return (mask & b) + (~mask & a);    
  }
  
  static boolean isPowerOfTwo(int x) {
    while (x>0) {
      if ( ((x&1) == 1) && ( x > 1)) {
        return false;
      } else if (x== 1) {
        return true;
      }
      x = x >> 1;
    }
    return false;
  }
  
  // 5.7
  static int fetchBit(int no, int j) {
    return ((no & (1<<j)) != 0)? 1 : 0;
  }

  static int powerOfTwo(int j) {
    return (1<<j);
  }

  static int findMissingNumber(int[] array, int n) {
    int no = 0;
    for ( int j = 30; j >= 0; j--) {
      if (countOnes(array, j) < countOnes(n, j)) {
        no = no + powerOfTwo(j);
      }
    }
    return no;
  }

  static int countOnes(int no, int j) {
    int count = 0;
    for( int i = 0; i<= no; i++) {
      count = count + fetchBit(i, j);
    }
    return count;
  }

  static int countOnes(int[] array, int j) {
    int count = 0;
    for( int i = 0; i < array.length; i++) {
      count = count + fetchBit(array[i], j);
    }
    return count;
  }
  
  //
  public void drawHorizontalLine(byte[] screen, int width, int x1, int x2, int y) {
    
    // error checking
    if (screen== null) return; 
    if ( x1 > x2) {
      int temp = x1;
      x1 = x2;
      x2 = temp; 
    }
    
    int bytesPerWidth = width/8;
    if (screen.length < (bytesPerWidth * y + (x2 / 8))) {
      return;
    }
    
    int startIndex = (bytesPerWidth * y) + x1/8;
    if ( (x1 % 8) != 0 ) {
      
      startIndex++;
    }
    int endIndex = (bytesPerWidth * y) + x2/8;
    if (x2 % 8 != 7) endIndex--;
    
    for (int i = startIndex; i<= endIndex; i++) {
      screen[i] = -1;
    }
    
    // draw line
    for ( int x = x1; x <= x2; x++ ) {
      int index = (bytesPerWidth * y) + x/8;
      if (index < startIndex || index > endIndex) {
        int offset = 7 - (x%8);
        screen[index] = setBit(screen[index], offset);
      }
    }
  }
    
  byte setBit(byte x, int offset) {
    return (byte)(x|(1 <<offset));
  } 
  
  void printScreen(byte[] screen, int width){
    for ( int i = 0; i < screen.length; i++) {
      if (i%(width/8)==0) {
        System.out.println();
      }
      for (int j = 0; j<8; j++ ){
        if ((screen[i] & 1<<(7-j)) != 0) {
          System.out.print(1);
        } else {
          System.out.print(0);
        }
      }
    }
    System.out.println();
  }
}
