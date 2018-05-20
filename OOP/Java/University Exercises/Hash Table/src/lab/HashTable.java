package lab;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

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
	private int filledSlots;
	private double loadFactor;
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
		this.loadFactor = this.filledSlots / this.initialCapacity;

		//entriesArray is filled with nulls
		for(int i= 0; i < entriesArray.length; i++) {
			entriesArray[i] = null;
		}
	}

	public int hashFunction(String key) {
		int keyIntegerValue = 0;
		char[] charA = key.toCharArray();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < charA.length; i++) {
			sb.append((int)charA[i]);
		}

		if (hashFunc.equals("division")) {
			//do this
			keyIntegerValue = Integer.parseInt(sb.toString().substring(0 , 4));
			int hashValue = Math.abs(keyIntegerValue % initialCapacity);
			return hashValue;
		}
		if (hashFunc.equals("folding")) {
			//do this
			if (initialCapacity % 2 == 0) {
				int addressLength = 2;
				String[] foldedStrings = new String[sb.length() / addressLength];
				for(int i = 0; i < sb.length(); i++) {
					if (i % 2 == 0 && i != 0) {
						foldedStrings[i] = sb.reverse().toString().substring(i * 2, i * 2 + 2);
					} else foldedStrings[i] = sb.toString().substring(i * 2, i * 2 + 2);
				}
				keyIntegerValue = Integer.parseInt(sb.toString());

				//int h =
			} else if (initialCapacity % 3 == 0) {
				//int addressLength = 3;
				String[] foldedStrings = new String[sb.length() / 2];
				for(int i = 0; i < sb.length(); i++) {
					if (i % 2 == 0 && i != 0) {
						foldedStrings[i] = sb.reverse().toString().substring(i * 3, i * 3 + 3);
					} else foldedStrings[i] = sb.toString().substring(i * 3, i * 3 + 3);
				}
				keyIntegerValue = Integer.parseInt("00" + sb.toString());

				//int h =
			}

		}
		if (hashFunc.equals("mid_square")) {
			//do this
		}
		return keyIntegerValue;
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
//		for(int i = 0; i < entries.size(); i++) {
//			System.out.println(Arrays.toString(entries.get(i)));
//		}
		return entries.size() - 1;
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
		// if the load factor reaches 75% we rehash the table with more capacity
		if (loadFactor >= 0.75f) {
			rehash();
		} else {
			// Insertion with linear probing collision resolution
			if (colRes.equals("linear_probing")) {
				// counter for the probe sequence
				int i = 0;
				// stores the hash value + probe counter
				int probeSequence = hashFunction(insertEntry.getKey()) + i;
				do {

					// if the key is empty at this index, store it, increment filled slots variable and return true
					if (entriesArray[probeSequence].getKey() == null) {
						entriesArray[probeSequence] = insertEntry;
						filledSlots++;
						return true;
					} else {
						// increment probe counter
						i++;
						// if probeSequence reaches end of hash table, circle to the beginning
						if (probeSequence == initialCapacity) {
							probeSequence = 0;
							if (probeSequence == hashFunction(insertEntry.getKey())) {
								System.out.print("No Space left to insert entry");
								return false;
							}
						}
					}
				} while (probeSequence < initialCapacity);
			}
			// Insertion with quadratic probing collision resolution
			if (colRes.equals("quadratic_probing")) {
				//
				int probeSequence = 0;
				do {
					// quadratic probing hash function
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
		int j = 0;
		do {
			j = hashFunction(searchKey) + i;
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
