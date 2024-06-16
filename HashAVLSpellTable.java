import java.util.LinkedList;
import java.util.List;
/**
 * This class represents a hash table where each bucket is a linked list, and each node in the
 * linked list is an AVL tree as implemented in previous class
 */
public class HashAVLSpellTable {
	/* Array of Linked Lists, where each link represented by the AVL tree object */
    private LinkedList<AVLTree> buckets[];
    /* The numbers of buckets in the hash table */
    private int tableSize;
    /* Total number of spells inside the data structure */
    private int numSpells;

    /**
     * Default constructor
     * Default size - 101
     */
    public HashAVLSpellTable() {
    	this.buckets = new LinkedList[101]; // Default Prime number
        this.tableSize = 101;
        this.numSpells = 0;
    }
    
    /**
     * Constructor, Initialize the table where the size is the number of buckets
     * @param size - The numbers of buckets in the hash table
     */
    public HashAVLSpellTable(int size) {
    	this.buckets = new LinkedList[size];
        this.tableSize = size;
        this.numSpells = 0;
    }

    /**
     * Hash function for the Hash Table
     * @param category - The category we want to active on it the hash function
     * @return int - The result of the hash function
     */
    private int hash(String category) {
    	int hash = 0;
    	char[] arr = category.toCharArray();
    	for(char chr : arr) {
    		hash += (int)chr;
    	}
        return hash%this.tableSize;
    }

    /**
     * Add a spell to the hash table
     * @param s - The new spell we want to insert
     */
    public void addSpell(Spell s) {
    	// Get hash code
    	int hashByCategory = hash(s.getCategory());
    	// If the bucket is empty, create new tree and add the spell
    	if(this.buckets[hashByCategory] == null) {
    		this.buckets[hashByCategory] = new LinkedList<AVLTree>();
    		this.buckets[hashByCategory].add(new AVLTree(s));
    		this.numSpells++;
    		return;
    	}
    	// Else not empty, search for the right tree to insert
    	for(AVLTree tree : this.buckets[hashByCategory]) {
    		if(tree.getCategory().equals(s.getCategory())) {
    			tree.insert(s);
    			this.numSpells++;
    			return;
    		}
    	}
    	// If no trees of the new spell category
    	// add a new tree for that spell
    	this.buckets[hashByCategory].add(new AVLTree(s));
    	this.numSpells++;
    }

    public Spell searchSpell(String category, String spellName, int powerLevel) {
    	// Get hash code
    	int hashByCategory = hash(category);
    	// Search in potential trees
    	if(this.buckets[hashByCategory] != null) {
        	for(AVLTree tree : this.buckets[hashByCategory]) {
        		if(tree.getCategory().equals(category)) {
        			Spell spell = tree.search(spellName, powerLevel);
            		if(spell != null) {
            			// Found!!
            			return spell;
            		}
        		}
        	}
    	}
    	// Not found - Return null!
    	return null;
    }

    /**
     * Getter for the number of spells that exist in the entire data structure(Without category)
     * @return - The number of spells - Without category
     */
    public int getNumberSpells(){
        return this.numSpells;
    }

    /**
     * Get the number of spells exist for the input category
     * @param category - The category we want to check the number of spells that has
     * @return - The number of spells by the category
     */
    public int getNumberSpells(String category){
    	// Get hash code, init vars
    	int hash = hash(category);
    	int num_spells = 0;
    	// Search for the category
    	if(this.buckets[hash] != null) {
        	for(AVLTree tree : this.buckets[hash]) {
        		if(tree.getCategory().equals(category)) {
        			// If category exist, add the size number
        			num_spells += tree.getSize();
        		}
        	}

    	}
        return num_spells;
    }

    /**
     * Return the top K spells
     * based on the power level for the input category
     * if K > tree.size - return all the spels category
     * @param category
     * @param k
     * @return
     */
    public List<Spell> getTopK(String category, int k) {
    	// Get hash code by category
    	int hashByCategory = hash(category);
    	// Search for potential tree
    	if(this.buckets[hashByCategory] != null) {
    		for(AVLTree tree : this.buckets[hashByCategory]) {
        		if(tree != null && tree.getCategory().equals(category)) {
        			// Found the tree, return top k spells
        			return tree.getTopK(k);
        		}
        	}
    	}
        return null;
    }
}
