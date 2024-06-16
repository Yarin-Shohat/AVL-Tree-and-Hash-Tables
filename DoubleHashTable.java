/**
 * DoubleHashTable Class for storing spells for merlin
 * This class represents the hash table, where the hash function is calculated in hash1 and hash2 (using ASCII value of characters).
 * Each bucket in the table can hold SpellSimple struct.
 */
public class DoubleHashTable {
	/* Table of SpellSimple structure */
    private SpellSimple[] table;
    /* Capacity of the hash table */
    private int capacity;
    /* Number of filled buckets */
    private int size;
    /* Number of steps performed on latest put() */
    private int steps=0;

	 /**
	  * Default constructor
	  * Default size - 101
	  */
    public DoubleHashTable() {
    	this.table = new SpellSimple[101]; // Default Prime number to be the size
    	this.capacity = 101;
    	this.size = 0;
    }
    
    /**
     * Constructor that receive capacity and create HashTable in that size
     * @param capacity - The size of the HashTable
     */
    public DoubleHashTable(int capacity) {
    	this.table = new SpellSimple[capacity];
    	this.capacity = capacity;
    	this.size = 0;
    }
    
    /**
     * Inserting a new spell struct to that Hash Table - Based on hash function
     * The function return boolean that represent if the insert succeed
     * @param spell - Spell we want to insert
     * @return - True if succeeds or False if not
     */
    public boolean put(SpellSimple spell) {
    	// If full, can't add any new spells
    	if(this.size == this.capacity) return false;
    	// Init vars
    	int old_steps = this.steps;
    	this.steps = 0;
    	int index;
    	// Get hash code
    	String name = spell.getName();
    	int h1 = hash1(name);
    	int h2 = hash2(name);
    	// Find place in the table
    	while(this.steps < this.capacity) {
    		index = (h1 + this.steps*h2)%this.capacity;
    		if(this.table[index] == null) {
    			this.table[index] = spell;
    			this.size++;
    			return true;
    		}
    		this.steps++;
    	}
    	// Fail to add, return to the old steps value
    	this.steps = old_steps;
        return false;
    }

    /**
     * Function that return the cast words of the spell name inputed
     * @param name - The spell name
     * @return - The cast words of the spell
     */
    public String getCastWords(String name) {
    	// Init vars
    	int old_steps = this.steps;
    	this.steps = 0;
    	int index;
    	// Get hash code
    	int h1 = hash1(name);
    	int h2 = hash2(name);
    	// Search the spell
    	while(this.steps < this.capacity) {
    		index = (h1 + this.steps*h2)%this.capacity;
    		if(this.table[index] != null) {
    			if(this.table[index].getName().equals(name)) {
        			return this.table[index].getWords();
        		}
    		}
    		this.steps++;
    	}
    	// Fail to find, return to the old steps value
    	this.steps = old_steps;
        return null;
    }

    /**
     * Function that return the size of the hash table
     * @return - The size of the hash table
     */
    public int getSize() { return this.size; }

    /**
     * Function that returns the number of steps performed in the last put or getCastWords action
     * @return the number of steps performed in the last put or getCastWords action 
     */
    public int getLastSteps() {return this.steps; }

    /**
     * Hash function 1 that receive spell name and return the result of the function
     * @param name - The spell name
     * @return - The result of hash function 1
     */
    private int hash1(String name) {
    	int hash = 0;
    	char[] arr = name.toCharArray();
    	for(char ch : arr) {
    		hash += (int)ch*31;
    	}
        return hash%this.capacity;
    }

    /**
     * Hash function 2 that receive spell name and return the result of the function
     * @param name - The spell name
     * @return - The result of hash function 2
     */
    private int hash2(String name) {
    	int hash = 0;
    	char[] arr = name.toCharArray();
    	for(char ch : arr) {
    		hash += (int)ch*13;
    	}
        return (1 + hash%(this.capacity-2));
    }
    
    /**
     * overriding toString
     * Print "Spell {num}: Spell / None(if null)"
     */
    @Override
    public String toString() {
    	String s = "";
    	for(int i = 0; i < this.capacity; i++) {
    		s += "Spell " + (int)(i+1) + ": ";
    		if(this.table[i] != null) {
    			s += this.table[i].toString() + "\n";
    		}
    		else {
    			s += "NONE\n";
    		}
    	}
    	return s;
    }
}