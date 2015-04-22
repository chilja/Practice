/**
 * 
 */
package excercises.implementation;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


/**
 * @author chiljagossow
 *
 */
public class Trie {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
  
  public interface Vocabulary {
    boolean add(String word);
    boolean isPrefix(String prefix);
    boolean contains(String word);
  }
  
  public static class Node {
    Node[] children = new Node[26];
    boolean isWord = false;
    
    public Node getNode(String s) {
      Node node = this;
      for (int i = 0; i < s.length(); i++) {
        int index = LOWERCASE.indexOf(s.charAt(i));
        Node child = node.children[index];
        if (child == null) {
          // There is no such word
          return null;
        }
        node = child;
      }
      return node;
    }
    
    public int getNoOfSubwords(String s) {
      int noOfSubwords = 0;
      Node node = this;
      for (int i = 0; i < s.length(); i++) {
        int index = LOWERCASE.indexOf(s.charAt(i));
        Node child = node.children[index];
        if (child == null) {
          // There is no such word
          return noOfSubwords;
        }
        node = child;
        if (node.isWord) noOfSubwords++;
      }
      return noOfSubwords;
    }
    
    public void insert(String s) {
      Node node = this;
      for (int i = 0; i < s.length(); i++) {
        int index = LOWERCASE.indexOf(s.charAt(i));
        Node child = node.children[index];
        if (child == null) {
          // There is no such word
          child = new Node();
          node.children[index] = child;
        }
        node = child;
        if (i == s.length() - 1) node.isWord = true;
      }

    }
    
  }
  
  static ArrayList<Character> LOWERCASE = new ArrayList<Character>();

  static {
    LOWERCASE.add('a'); LOWERCASE.add('b'); LOWERCASE.add('c'); LOWERCASE.add('d'); LOWERCASE.add('e');
    LOWERCASE.add('f'); LOWERCASE.add('g'); LOWERCASE.add('h'); LOWERCASE.add('i'); LOWERCASE.add('j');
    LOWERCASE.add('k'); LOWERCASE.add('l'); LOWERCASE.add('m'); LOWERCASE.add('n'); LOWERCASE.add('o');
    LOWERCASE.add('p'); LOWERCASE.add('q'); LOWERCASE.add('r'); LOWERCASE.add('s'); LOWERCASE.add('t');
    LOWERCASE.add('u'); LOWERCASE.add('v'); LOWERCASE.add('w'); LOWERCASE.add('x'); LOWERCASE.add('y');
    LOWERCASE.add('z');
//  , , 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
  }
  
  public static String findLongestComposedWord(String[] words) {
    String longestWord = null;
    Node root = new Node();
    for (int i = 0; i < words.length; i++) {
      root.insert(words[i]);
    }
    
    int max = 0;
    for (int i = 0; i < words.length; i++) {
      int noOfSubwords = root.getNoOfSubwords(words[i]);
      if (noOfSubwords > max) {
        max = noOfSubwords;
        longestWord = words[i];
      }
    }
    
    return longestWord;   
  }
  
  // 18.8
  public static String findSubstrings(String word, String[] subs) {
    StringBuffer foundSubs = new StringBuffer();
    Node root = new Node();
    for (int i = 0; i < word.length(); i++) {
      root.insert(word.substring(i));
    }
    for (int i = 0; i < subs.length; i++) {
      if (root.getNode(subs[i]) != null) foundSubs.append(" " + subs[i] );
    }
    
    return foundSubs.toString();   
  }
  
  class ListVocabulary implements Vocabulary {
    private List<String> words = new ArrayList<String>();

    /**
     * Constructor that adds all the words and then sorts the underlying list
     */
    public ListVocabulary(Collection<String> words) {
        this.words.addAll(words);
        Collections.sort(this.words);
    }

    public boolean add(String word) {
        int pos = Collections.binarySearch(words, word);
        // pos > 0 means the word is already in the list. Insert only
        // if it's not there yet
        if (pos < 0) {
            words.add(-(pos+1), word);
            return true;
        }
        return false;
    }

    public boolean isPrefix(String prefix) {
        int pos = Collections.binarySearch(words, prefix) ;
        if (pos >= 0) {
            // The prefix is a word. Check the following word, because we are looking 
            // for words that are longer than the prefix
            if (pos +1 < words.size()) {
                String nextWord = words.get(pos+1);
                return nextWord.startsWith(prefix);
            }
            return false;
        }
        pos = -(pos+1);
        // The prefix is not a word. Check where it would be inserted and get the next word.
        // If it starts with prefix, return true.
        if (pos == words.size()) {
            return false;
        }
        String nextWord = words.get(pos);
        return nextWord.startsWith(prefix);
    }

    public boolean contains(String word) {
        int pos = Collections.binarySearch(words, word);
        return pos >= 0;
    }
  } 

}
