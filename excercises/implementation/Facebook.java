/**
 * 
 */
package excercises.implementation;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author chiljagossow
 *
 */
public class Facebook {

  /**
   * @param args
   */
  public static void main(String[] args) {
    User chilja = new User("Chilja");
    User eva = new User("Eva");
    User ben = new User("Ben");
    User t = new User("T");
    User brandon = new User("Brandon");
    
    chilja.addFriend(eva);
    chilja.addFriend(ben);
    chilja.addFriend(brandon);
    chilja.addFriend(t);
    t.addFriend(brandon);
    eva.addFriend(ben);
    ben.addFriend(eva);
    eva.addFriend(chilja);
    LinkedList<User> path =  eva.getPathToUser(t, new LinkedList<User>());  
    for (User user: path) {
      System.out.println(user.getName());
    }
  }

}

class User {
  private String name;
  private ArrayList<User> friends;
  boolean explored = false;

  User(String name) {
    this.name = name;
    friends = new ArrayList<User>();
  }
  
  String getName() {
    return name;
  }

  void addFriend( User friend) {
    if (friend != null) {
      friends.add(friend);
    }
  }

  void setExplored() {
    explored = true;
  }

  boolean isExplored() {
    return explored;
  }

  LinkedList<User> getPathToUser( User other, LinkedList<User> path) {
    path.addLast(this);
    setExplored();
    
    if (this == other) {
      return path;
    }

//    LinkedList<User> newPath = new LinkedList<User>();
    
    for (User friend : friends ) {
      if (!friend.isExplored() ) {

        LinkedList<User> returnPath = friend.getPathToUser( other, path);
        if (returnPath != null) {
          return returnPath;
        }
      }
    }
    
    return null;
  }
}
