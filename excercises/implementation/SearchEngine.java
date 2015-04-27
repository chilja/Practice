/**
 * 
 */
package excercises.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author chiljagossow
 *
 */
public class SearchEngine {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }
  
  public class Cache {
    private HashMap<String,Integer> queryToMachineId;
    private LinkedList<String> recentQueries;
    
    public String getQueryResult(String query) {
      int machineId = getMachineId(query);
      if (machineId < 0) return null;
      Machine machine = getMachine(machineId);
      return machine.getResult( query).result;
    }
    
    public Machine getMachine(int machineId) {
      return new Machine(machineId);
    }
    
    public int getMachineId(String query){
      if (queryToMachineId.containsKey(query))
        return queryToMachineId.get(query);
      return -1;
    }
    
    public void enqueueQuery(String query, int machineId) {
      
    
    }

  }

  public class Machine{

    private int machineId;
    Cache cache;

    HashMap<String, Result> results = new HashMap<String, Result>();
    
    Machine(int id){
      machineId = id;
    }

    public Result getResult(String query){
      return results.get(query);
    }
    
    void refreshResults() {
      //refresh outdated results
    }

    void deleteResult(String query){
      results.remove(query);
    }
    
    Result processSearch(String query) {
      // ...
      Result result = getResult(query);
      if (result == null) {
        cache.getQueryResult(query);
      }
      if (result == null) {   
        result = new Result("...");
        results.put(query, result);
        cache.enqueueQuery(query, machineId);
      }
      return result;
    }

  }



  public class Result {
    private String result;
    private Date createdAt;
    
    public Result(String result) {
      this.result = result;
      createdAt = new Date();
    }
    
    public String getResult() {
      return result;  
    }
    
    public Date getCreatedAt(){
      return createdAt;
    }
  }

}
