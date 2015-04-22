/**
 * 
 */
package excercises.implementation;

import java.util.Arrays;

/**
 * @author chiljagossow
 * 
 */
public class Sorting {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] array = { 2,3,1,4,0 };
    quickSort(array);
    System.out.println(Arrays.toString(array));
  }

  static int binarySearch(int[] array, int item, int start, int end) {
    if ((array == null) || (start > end)) {
      return -1;
    }

    int median = (start + end) / 2;

    if (item == array[median]) {
      return median;
    } else if (item < array[median]) {
      return binarySearch(array, item, start, median - 1);
    } else {
      return binarySearch(array, item, median + 1, end);
    }
  }

  static int find(int[] array, int item, int start, int end) {
    if ((array == null) || (start > end)) {
      return -1;
    }

    int median = (start + end) / 2;

    if (item == array[median]) {
      return median;
    } else if ((array[start] < item) && (item < array[median])) {
      return binarySearch(array, item, start, median - 1);
    } else if ((array[median] < item) && (item < array[end])) {
      return binarySearch(array, item, median + 1, end);

    } else if ((item >= array[start]) && (item < array[median])) {
      return find(array, item, start, median - 1);
    } else {
      return find(array, item, median + 1, end);
    }
  }

  static void merge(int[] arrayA, int[] arrayB) {
    if ((arrayA == null) || (arrayB == null)) {
      return;
    }
    if (arrayA.length <= arrayB.length) {
      return;
    }
    int current = arrayA.length - 1;
    int indexB = arrayB.length - 1;
    int indexA = arrayA.length - arrayB.length - 1;

    while ((current >= 0) && (indexA >= 0) && (indexB >= 0)) {
      if (arrayA[indexA] > arrayB[indexB]) {
        arrayA[current] = arrayA[indexA];
        indexA--;
      } else {
        arrayA[current] = arrayB[indexB];
        indexB--;
      }
      current--;
    }

    while ((current >= 0) && (indexB >= 0)) {
      arrayA[current] = arrayB[indexB];
      indexB--;
      current--;
    }
  }

  static void merge(int[] array, int[] helper, int startIndex, int median, int endIndex) {

    // copy both halves into helper array
    for (int i = startIndex; i <= endIndex; i++) {
      helper[i] = array[i];
    }

    int helperLeft = startIndex;
    int helperRight = median + 1;
    int currentIndex = startIndex;

    while ((helperLeft <= median) && (helperRight <= endIndex)) {

      if (helper[helperLeft] < helper[helperRight]) {
        array[currentIndex] = helper[helperLeft];
        helperLeft++;

      } else {
        array[currentIndex] = helper[helperRight];
        helperRight++;
      }
      currentIndex++;
    }

    // move the rest of helperLeft
    while (helperLeft <= median) {
      array[currentIndex] = helper[helperLeft];
      helperLeft++;
      currentIndex++;
    }
  }

  static void mergeSort(int[] array) {
    int[] helper = new int[array.length];
    mergeSort(array, helper, 0, array.length - 1);
  }

  static void mergeSort(int[] array, int[] helper, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int median = (startIndex + endIndex) / 2;
      mergeSort(array, helper, startIndex, median);
      mergeSort(array, helper, median + 1, endIndex);
      merge(array, helper, startIndex, median, endIndex);
    }
  }

  static int partition(int[] array, int leftIndex, int rightIndex) {
    int pivot = (leftIndex + rightIndex) / 2;

    while (leftIndex < rightIndex) {
      while (array[leftIndex] < array[pivot]) {
        leftIndex++;
      }
      while (array[rightIndex] > array[pivot]) {
        rightIndex--;
      }
      if (leftIndex <= rightIndex) {
        swap(array, leftIndex, rightIndex);
        leftIndex++;
        rightIndex--;
      }
    }
    return leftIndex;
  }

  static void quickSort(int[] array) {
    quickSort(array, 0, array.length - 1);
  }

  static void quickSort(int[] array, int leftIndex, int rightIndex) {
    System.out.println(leftIndex + " to " + rightIndex);
    System.out.println(Arrays.toString(array));
    int partition = partition(array, leftIndex, rightIndex);
    System.out.println(partition);
    System.out.println(Arrays.toString(array));
    if (leftIndex < (partition - 1)) {
      quickSort(array, leftIndex, partition - 1);
    }
    if (rightIndex > partition) {
      quickSort(array, partition, rightIndex);
    }
  }

  static void swap(int[] array, int leftIndex, int rightIndex) {
    int temp = array[leftIndex];
    array[leftIndex] = array[rightIndex];
    array[rightIndex] = temp;
  }
}
