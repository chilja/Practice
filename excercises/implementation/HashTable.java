/**
 * 
 */
package excercises.implementation;

import java.util.LinkedList;

/**
 * @author chiljagossow
 * 
 */
public class HashTable<K, V> {

  static class Entry<K, V> {
    private K key;
    private V item;

    Entry(K key, V item) {
      this.key = key;
      this.item = item;
    }

    public boolean equivalent(K key) {
      return this.key.equals(key);
    }

    public boolean equivalent(Entry<K, V> entry) {
      return equivalent(entry.key);
    }

    public V getItem() {
      return item;
    }

    public K getKey() {
      return key;
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  private LinkedList<Entry<K, V>>[] entries;
  private int tableSize = 97;// alternative: binary search tree

  private int entryCount;
  
  HashTable() {
 // instantiate hash table
    entries = (LinkedList<Entry<K, V>>[]) new LinkedList[tableSize];
  }

  private int hashCodeOfKey(K key) {
    return (key.toString().length() % tableSize);
  }

  void delete(K key) {
    // find bucket
    int index = hashCodeOfKey(key);
    if (entries[index] == null) {
      return;
    }
    LinkedList<Entry<K, V>> bucket = entries[index];
    // replace entry with same key
    for (Entry<K, V> entry : bucket) {
      if (entry.equivalent(key)) {
        bucket.remove(key);
        entryCount--;
        return;
      }
    }
  }

  V get(K key) {
    // find bucket
    int index = hashCodeOfKey(key);
    if (entries[index] == null) {
      return null;
    }

    LinkedList<Entry<K, V>> bucket = entries[index];
    // replace entry with same key
    for (Entry<K, V> entry : bucket) {
      if (entry.equivalent(key)) {
        return entry.getItem();
      }
    }
    return null;
  }

  void put(K key, V item) {
    // find bucket
    int index = hashCodeOfKey(key);
    if (entries[index] == null) {
      entries[index] = new LinkedList<Entry<K, V>>();
    }
    LinkedList<Entry<K, V>> bucket = entries[index];
    // replace entry with same key
    for (Entry<K, V> entry : bucket) {
      if (entry.equivalent(key)) {
        bucket.remove(key);
        break;
      }
    }
    // insert
    entries[index].addLast(new Entry<K, V>(key, item));
    entryCount++;
  }
}
