package store;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataStoreManager {
	public static final String file = "datastore.dat";

	public static DataStore loadData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			return (DataStore) ois.readObject();
		} catch (Exception e) {
			return new DataStore();
		}
	}

	public static void saveData(DataStore store) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
			oos.writeObject(store);
		} catch (Exception e) {
			// Nothing
		}
	}
}
