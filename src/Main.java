import store.DataStore;
import store.DataStoreManager;

public class Main {
    public static DataStore store;
   public static void main(String [] args) {
       store= DataStoreManager.loadData();

       Runtime.getRuntime().addShutdownHook(
               new Thread(()->DataStoreManager.saveData(store))
       );

       while (true) {

       }
   }
}
