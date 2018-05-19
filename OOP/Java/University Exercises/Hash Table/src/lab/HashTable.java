package lab;

import java.math.BigInteger;
import java.util.ArrayList;
import frame.Entry;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

/*
 * Implements a Hash-Table structure as introduced in the 
 * lecture to store the information read by the RFID 
 * readers in the library.
 *	
 * Make sure that you have tested all the given test cases
 * given on the homepage before you submit your solution.
 *
 */

public class HashTable {

	public Entry[] entriesArray;
	private int initialCapacity;
	private String hashFunc;
	private String colRes;
	private int addressLength;
	private int probeSequence;
	private int filledSlots;
	private double  loadFactor = filledSlots / initialCapacity;
	/**
	 * The constructor
	 * 
	 * @param initialCapacity
	 *            represents the initial size of the Hash Table.
	 * @param hashFunction
	 *            can have the following values: division folding mid_square
	 * @param collisionResolution
	 *            can have the following values: linear_probing quadratic_probing
	 * 
	 *            The Hash-Table itself should be implemented as an array of entries
	 *            (Entry[] in Java) and no other implementation will be accepted.
	 *            When the load factor exceeds 75%, the capacity of the Hash-Table
	 *            should be increased as described in the method rehash below. We
	 *            assume a bucket factor of 1.
	 */
	public HashTable(int k, String hashFunction, String collisionResolution) {
		this.initialCapacity = k;
		this.hashFunc = hashFunction;
		this.colRes = collisionResolution;
		this.filledSlots = 0;
		this.entriesArray = new Entry[k];

		for(int i= 0; i < entriesArray.length; i++) {
			entriesArray[i] = null;
		}
	}

	public int hashFunction(String key) {
		char[] charA = key.toCharArray();
		int keyIntegerValue;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < charA.length; i++) {
			sb.append((int)charA[i]);
		}


		if (hashFunc.equals("division")) {
			//do this
			keyIntegerValue = Integer.parseInt(sb.toString().substring(0 , 4));
			int h = Math.abs(keyIntegerValue % initialCapacity);
			return h;
		}
		if (hashFunc.equals("folding")) {
			//do this
			if (initialCapacity % 2 == 0) {
				addressLength = 2;
				String[] foldedStrings = new String[sb.length() / 2];
				for(int i = 0; i < sb.length(); i++) {
					if (i % 2 == 0 && i != 0) {
						foldedStrings[i] = sb.reverse().toString().substring(i * 2, i * 2 + 2);
					} else foldedStrings[i] = sb.toString().substring(i * 2, i * 2 + 2);
				}
				keyIntegerValue = Integer.parseInt(sb.toString());

				int h =
			} else if (initialCapacity % 3 == 0) {
				addressLength = 3;
				String[] foldedStrings = new String[sb.length() / 2];
				for(int i = 0; i < sb.length(); i++) {
					if (i % 2 == 0 && i != 0) {
						foldedStrings[i] = sb.reverse().toString().substring(i * 3, i * 3 + 3);
					} else foldedStrings[i] = sb.toString().substring(i * 3, i * 3 + 3);
				}
				keyIntegerValue = Integer.parseInt("00" + sb.toString());

				int h =
			}

		}
		if (hashFunc.equals("mid_square")) {
			//do this
		}
	}

	/**
	 * This method takes as input the name of a file containing a sequence of
	 * entries that should be inserted into the Hash-Table in the order they appear
	 * in the file. You cannot make any assumptions on the order of the entries nor
	 * is it allowed to change the order given in the file. You can assume that the
	 * file is located in the same directory as the executable program. The input
	 * file is similar to the input file for lab 1. The return value is the number
	 * of entries successfully inserted into the Hash-Table.
	 * 
	 * @param filename
	 *            name of the file containing the entries
	 * @return returns the number of entries successfully inserted in the
	 *         Hash-Table.
	 */
	public int loadFromFile(String filename) {
		/**
		 * Uses the class LibraryFileReader from lab1 to
		 * read the input file
		 */
		LibraryFileReader theFile = new LibraryFileReader(filename);
		ArrayList<String[]> entries = theFile.readFile();
		return entries.size();
	}

	/**
	 * This method inserts the entry insertEntry into the Hash-Table. Note that you
	 * have to deal with collisions if you want to insert an entry into a slot which
	 * is not empty. This method returns true if the insertion of the entry
	 * insertEntry is successful and false if the key of this entry already exists
	 * in the Hash-Table (the existing key/value pair is left unchanged).
	 * 
	 * @param insertEntry
	 *            entry to insert into the Hash-table
	 * @return returns true if the entry insertEntry is successfully inserted false
	 *         if the entry already exists in the Hash-Table
	 */
	public boolean insert(Entry insertEntry) {
		
		if (loadFactor >= 0.75) {
			rehash();
		} else {
			if (colRes.equals("linear_probing")) {
				// DO this type of insertion
				int probeSequence = 0;
				do {
					int j = hashFunction(insertEntry.getKey()) ;
					if (entriesArray[j + probeSequence].getKey() == null) {
						entriesArray[j + probeSequence] = insertEntry;
						filledSlots++;
						return true;
					} else probeSequence++;
				} while (probeSequence < initialCapacity);
			}
			if (colRes.equals("quadratic_probing")) {
				// DO this type of insertion
				int probeSequence = 0;
				do {
					int j = (int) (hashFunction(insertEntry.getKey()) - ceil(pow((probeSequence / 2), 2)) * pow((-1), probeSequence)) % initialCapacity;
					if (j < 0) j = j + initialCapacity;
					if (entriesArray[j].getKey() == null) {
						entriesArray[j] = insertEntry;
						filledSlots++;
						return true;
					} else probeSequence++;
				} while (probeSequence < initialCapacity);
			}
		}
		return false;
	}

	/**
	 * This method deletes the entry from the Hash-Table, having deleteKey as key
	 * This method returns the entry, having deleteKey as key if the deletion is
	 * successful and null if the key deleteKey is not found in the Hash-Table.
	 * 
	 * @param deleteKey
	 *            key of the entry to delete from the Hash-Table
	 * @return returns the deleted entry if the deletion ends successfully null if
	 *         the entry is not found in the Hash-Table
	 */
	public Entry delete(String deleteKey) {
		if(!entriesArray[hashFunction(deleteKey)].isDeleted()) {
			entriesArray[hashFunction(deleteKey)].markDeleted();
		}
		return null;
	}

	/**
	 * This method searches in the Hash-Table for the entry with key searchKey. It
	 * returns the entry, having searchKey as key if such an entry is found, null
	 * otherwise.
	 * 
	 * @param searchKey
	 *            key of the entry to find in the Hash-table
	 * @return returns the entry having searchKey as key if such an entry exists
	 *         null if the entry is not found in the Hash-Table
	 */
	public Entry find(String searchKey) {
		int i = 0;
		do {
			int j = hashFunction(searchKey) + probeSequence;
			if(entriesArray[j].getKey().compareTo(searchKey) == 0) {
				return entriesArray[j];
			}
			i++;

		}
		while(entriesArray[j] != null || i < initialCapacity);
		return null;
	}

	/**
	 * This method returns a ArrayList<String> containing the output Hash-Table. The
	 * output should be directly interpretable dot code. Each item in the ArrayList
	 * corresponds to one line of the output Hash-Table. The nodes of the output
	 * Hash-Table should contain the keys of the entries and also the data.
	 * 
	 * @return returns the output Hash-Table in directly interpretable dot code
	 */
	public ArrayList<String> getHashTable() {
		/**
		 * Add your code here
		 */
		return null;
	}

	/**
	 * This method increases the capacity of the Hash-Table and reorganizes it, in
	 * order to accommodate and access its entries more efficiently. This method is
	 * called automatically when the load factor exceeds 75%. To increase the size
	 * of the Hash-Table, you multiply the actual capacity by 10 and search for the
	 * closest primary number less than the result of this multiplication. For
	 * example if the actual capacity of the Hash-Table is 101, the capacity will be
	 * increased to 1009, which is the closest primary number less than (101*10).
	 */
	private void rehash() {
		/**
		 * Add your code here
		 */
		// delete entries marked with deleted
		// multiply capacity by 10 and search for the closest prime under the number
		// reinsert entries

	}
}
