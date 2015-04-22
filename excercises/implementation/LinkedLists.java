package excercises.implementation;

import java.util.HashSet;

public class LinkedLists {

  public static Node findCircle(Node head) {
    HashSet<Node> set = new HashSet<Node>();
    Node n = head;
    while (n != null) {
      if (set.contains(n)) {
        return n;
      } else {
        set.add(n);
      }
      n = n.next;
    }
    return null;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Node n = new Node(7);
    n.appendToTail(1);
    n.appendToTail(5);
    n.appendToTail(4);
    n.appendToTail(8);
    n.appendToTail(2);
    n.appendToTail(3);
//    partition(n, 4);
    partitionList(n, 4);
  }
  
  static Node findCircle2(Node head) {
    if (head != null) {
      Node slow = head;
      Node fast = head.next;
      while (fast != null && fast.next != null) {
        if (slow == fast) return slow;
        slow = slow.next;
        fast = fast.next.next;
      }
    }
    return null;
  }

  static Node append(Node head, Node n) {
    if (head == null) {
      head = n;
    } else {
      head.appendToTail(n);
    }
    return head;
  }

  static boolean checkPalindrome(Node head) {
    if (head == null) {
      return false;
    }

    Node end = head;
    Node previous = null;
    while (end.next != null) {
      previous = end;
      end = end.next;
    }

    if ((previous == null) || (previous == head)) {
      return true;
    }

    if (end.data == head.data) {
      previous.next = null;
      if (checkPalindrome(head.next)) {
        return true;
      }
    }

    return false;
  }

  static void deleteNode(Node n) {
    if ((n != null) && (n.next != null)) {
      n.data = n.next.data;
      n.next = n.next.next;
    }
  }

  static Node nodeAddition() {

    Node n1 = new Node(7);
    n1.appendToTail(1);
    n1.appendToTail(6);
    n1.appendToTail(1);
    Node n2 = new Node(5);
    n2.appendToTail(9);
    n2.appendToTail(2);
    print(n1);
    print(n2);

    int multiplier = 1;
    int sum = 0;

    while ((n1 != null) || (n2 != null)) {
      if ((n1 != null) && (n2 != null)) {
        sum += (n1.data + n2.data) * multiplier;
      } else {
        if (n1 != null) {
          sum += n1.data * multiplier;
        } else if (n2 != null) {
          sum += n2.data * multiplier;
        }
      }
      multiplier *= 10;
      if (n1 != null) {
        n1 = n1.next;
      }
      if (n2 != null) {
        n2 = n2.next;
      }
    }

    int remainder = 0;
    int divider = 10;
    Node head = null;

    while (sum != 0) {
      remainder = sum % divider;
      sum -= remainder;
      sum = sum / divider;
      if (head == null) {
        head = new Node(remainder);
      } else {
        head.appendToTail(remainder);
      }
    }
    print(head);
    return head;
  }

  static void partition(Node head, int x) {
    if (head == null) {
      return;
    }
    Node smallHead = null;
    Node bigHead = null;
    Node current = head;
    Node next = null;
    print(head);
    while (current != null) {
      if (current.data < x) {
        smallHead = append(smallHead, current);
      } else { // >= x
        bigHead = append(bigHead, current);
      }
      next = current.next;
      current.next = null;
      current = next;
    }
    smallHead = append(smallHead, bigHead);
    System.out.println();
    print(smallHead);
  }
  
  public static void partitionList(Node head, int pivot) {
    if (head == null) return;
    print(head);
    Node n = head;
    while (n.next != null) {
      if (n.next.data < pivot) {
        Node x = n.next;
        n.next = n.next.next;
        x.next = head;
        head = x;
      } else {
        n = n.next;
      } 
    }
    print(head);
  }

  static void print(Node head) {
    if (head == null) {
      return;
    }
    Node n = head;
    while (n != null) {
      System.out.println(n.data);
      n = n.next;
    }
    System.out.println();
  }

  static Node removeDuplicates(Node head) {
    if (head == null) {
      return null;
    }
    Node n = head;
    while (n.next != null) {
      if (n.next.data == head.data) {
        n.next = n.next.next;
      } else {
        n = n.next;
      }
    }
    removeDuplicates(head.next);
    return head;
  }
  
  public void removeDuplicates2(Node head) {
    if (head == null) {
      return;
    }
    Node slow = head;
    while (slow.next != null) {
      Node fast = slow;
      while (fast.next != null) {
        if (fast.next.data == slow.data) {
          fast.next = fast.next.next;
        } else {
          fast = fast.next;
        }
      }
    }
  }

  static Node returnKLast(Node head, int k) {
    if ((head == null) || (k < 1)) {
      return null;
    }
    Node n = head;
    int count = 1;
    while (n.next != null) {
      count++;
      n = n.next;
    }

    if (count >= k) {
      count -= k;
    } else {
      // not enough nodes exist
      return null;
    }

    n = head;
    for (int i = 0; i < count; i++) {
      n = n.next;
    }
    return n;
  }

}

class Node {
  Node next = null;
  int data;

  public Node(int data) {
    this.data = data;
  }

  void appendToTail(int data) {
    Node tail = new Node(data);
    Node n = this;
    while (n.next != null) {
      n = n.next;
    }
    n.next = tail;
  }

  void appendToTail(Node tail) {
    Node n = this;
    while (n.next != null) {
      n = n.next;
    }
    n.next = tail;
  }
}
