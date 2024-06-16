import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an AVL tree as learned in class
 * Nodes are ordered by the power level of the spell
 */
public class AVLTree {
	/* Pointer to the root of the AVL tree */
	private Node root;
    /* The number of spell's stored in the AVL tree */
    private int size;
    /* A category that the AVL tree represents */
    private String category;
	
    
	// private Node class for the AVL Tree nodes
    private class Node { 
    	/* The value of the node */
    	private Spell spell;
        /* Pointer to left son */
    	private Node left;
        /* Pointer to right son */
    	private Node right;
        /* Height of the current node */
        private int height;

        /**
         * Constructor to create node
         * @param spell - Receive spell to be the node's value
         */
        private Node(Spell spell) {
        	this.spell = spell;
        	this.height = 0;
        }
    }

    /**
     * Default constructor
     */
    public AVLTree() {
    	this.root = null;
    	this.category = "";
    	this.size = 0;
    }

    /**
     * Constructor that receive a spell
     * @param spell - The first spell to insert the tree and create it
     */
    public AVLTree(Spell spell) {
    	this.root = new Node(spell);
    	this.category = spell.getCategory();
    	this.size = 1;
    }

    /**
     * Function that return the tree height
     * @return int - The tree height
     */
    public int getTreeHeight(){
        if(this.root != null) return this.root.height;
        return -1;
    }

    /**
     * Function that get the tree size
     * @return int - Tree size
     */
    public int getSize(){
        if(this.root != null) return this.size;
        return 0;
    }

    /**
     * Function that return the tree category
     * @return String - Tree category
     */
    public String getCategory() {
        if(this.root != null) return this.category;
        return null;
    }

    /**
     * Function that search for a spell based on the name and the power level
     * @param spellName - Spell's name we want to search
     * @param powerLevel - Spell's power level we wanr to search
     * @return - The spell we wanted to find
     */
    public Spell search(String spellName, int powerLevel) {
    	Node node = search_Helper(this.root, spellName, powerLevel);
    	if(node == null) return null;
    	else return node.spell;
    }
    
    /**
     * Function help search spells recursively 
     * @param node - The node we work on it - Start from root
     * @param spellName - Spell's name we want to search
     * @param powerLevel - Spell's power level we wanr to search
     * @return - The node the spell is on it
     */
    private Node search_Helper(Node node, String spellName, int powerLevel) {
    	if(node == null || is_spell(node, spellName, powerLevel)) {
    		return node;
    	}
    	else if(node.spell.getPowerLevel() < powerLevel) {
    		return search_Helper(node.right, spellName, powerLevel);
    	}
    	else if(node.spell.getPowerLevel() > powerLevel) {
    		return search_Helper(node.left, spellName, powerLevel);
    	}
    	return null;
    }
    
    /**
     * Internal function that help check if a specific node has the spell
     * @param node - Node we check if has the spell
     * @param spellName - The name of the spell we want to check
     * @param powerLevel - The powerLevel of the spell
     * @return True if equals, Else False
     */
    private boolean is_spell(Node node, String spellName, int powerLevel) {
    	boolean powerLevelCheck = node.spell.getPowerLevel() == powerLevel;;
    	boolean nameCheck = spellName.equals(node.spell.getName());
    	return powerLevelCheck && nameCheck;
    }

    /**
     * Insert a new spelll to the AVL Tree and balance it if it need
     * @param spell - New spell we want to insert
     */
    public void insert(Spell spell) {
    	// If the tree is empty
    	if(this.root == null) {
        	this.root = new Node(spell);
        	this.category = spell.getCategory();
        	this.size = 1;
        	return;
    	}
    	// Check for the same category spells - If not the same don't insert the spell
    	if(!spell.getCategory().equals(this.category)) return;
    	// Else not empty - add another spell
    	this.size++;
    
    	// Insert the node
    	Node y = null;
    	Node x = this.root;
    	// Find the place to insert the spell
    	while(x != null) {
    		y = x;
    		if(spell.getPowerLevel() < x.spell.getPowerLevel()) {
    			x = x.left;
    		}
    		else {
    			x = x.right;
    		}
    	}
    	// Put the spell in the correct place
		if(spell.getPowerLevel() < y.spell.getPowerLevel()) {
			y.left = new Node(spell);
		}
		else {
			y.right = new Node(spell);
		}
    	
    	// Update nodes height
    	update_nodes_height(this.root, spell);
    	
    	// Rotation if need
    	this.root = check_for_rotation(this.root, spell);
    }
    
    /**
     * Returns list of top-k spells in the tree 
     * If k > Tree Size: return all nodes
     * @param k - Number of top spells we want to return
     * @return - Linked list of top k spells(top by powerLevel)
     */
    public List<Spell> getTopK(int k) {
    	List<Spell> top_k_List = new LinkedList<>();
    	top_k_List = getTopK_Helper(this.root, top_k_List, k);
    	if(top_k_List.size() == 0) return null;
        return top_k_List;
    }
    
    /**
     * Internal function that help the getTopK function to be recursive
     * Get the top k spells from the tree
     * @param node - The node we working on it, start from root
     * @param list - The Linked List we will return
     * @param k - The number of spells we want to add the list
     * @return - Linked list that contain top k spells from the tree
     */
    private List<Spell> getTopK_Helper(Node node, List<Spell> list, int k) {
    	// If the list size smaller then k or Tree size, keep going
    	if(list.size() < k && list.size() < this.size) {
        	if(node == null) return list;
        	// Go to the big number first
        	if(node.right != null) getTopK_Helper(node.right, list, k);
        	// If the list size smaller then k or Tree size, keep going
        	if(list.size() < k && list.size() < this.size) list.add(node.spell);
        	// Before continue, check if left son has spells to add
        	if(node.left != null) getTopK_Helper(node.left, list, k);
    	}
    	return list;
    }
   
    /**
     * Internal function that check if we need to make rotation and rotate if need
     * @param node - The node we work on it - start from root
     * @param spell - The Spell we check hiss balance
     */
    private Node check_for_rotation(Node node, Spell spell) {
    	// If current node power_level is greater then the spell power_level - Go left
    	if(spell.getPowerLevel() < node.spell.getPowerLevel()) {
    		node.left = check_for_rotation(node.left, spell);
    	}
    	// If current node power_level is smaller then the spell power_level - Go right
    	else if(spell.getPowerLevel() > node.spell.getPowerLevel()) {
    		node.right = check_for_rotation(node.right, spell);
    	}
    	// If current node power_level the spell power_level - we arrived the spell node
    	if(spell.getPowerLevel() == node.spell.getPowerLevel()) {
    		return node;
    	}
    	// After returning from the recursion we need to check unbalanced nodes
    	else {
        	update_nodes_height(node, node.spell);
    		int balance = get_balance(node);
    		if(balance > 1) {
    			// Single: Right rotation
    			if(get_balance(node.left) < 0) {
    				// Double: Left-Right rotation
    				node.left = left_rotate(node.left);
    			}
    			node =  right_rotate(node);
    		}
    		if(balance < -1) {
    			// Single: Left rotation
    			if(get_balance(node.right) > 0) {
    				// Double: Right-Left rotation
    				node.right = right_rotate(node.right);
    			}
    			node =  left_rotate(node);
    		}
    	}
    	return node;
    }
    
    /**
     * Function that help rotation the AVL tree - Left rotation
     * @param node - The root node that has 2 son un-balanced
     * @return - The new root for the fixed sub-tree
     */
    private Node left_rotate(Node node) {
    	Node new_root = node.right;
    	Node center = new_root.left;
    	new_root.left = node;
    	node.right = center;
    	update_nodes_height(node, node.spell);
    	update_nodes_height(new_root, new_root.spell);
    	return new_root; // The new root
    }
    
    /**
     * Function that help rotation the AVL tree - Right rotation
     * @param node - The root node that has 2 son un-balanced
     * @return - The new root for the fixed sub-tree
     */
    private Node right_rotate(Node node) {
    	Node newRoot = node.left;
    	Node center = newRoot.right;
    	newRoot.right = node;
    	node.left = center;
    	update_nodes_height(node, node.spell);
    	update_nodes_height(newRoot, newRoot.spell);
    	return newRoot; // The new root
    }
    
    /**
     * Function that return the balance between 2 son of a node
     * return left-son_height - right-son_height
     * null son height = -1
     * leaf height = 0
     * @param node - node we want to check his son's balance
     * @return - The balance between the 2 sons
     */
    private int get_balance(Node node) {
    	// Leaf of null
    	if(node == null || (node.left == null && node.right == null)) {
    		return 0;
    	}
    	else if(node.left == null) {
    		return (-1) - node.right.height;
    	}
    	else if(node.right == null) {
    		return node.left.height - (-1);
    	}
    	else if(node.right != null && node.left != null){
    		return node.left.height - node.right.height;
    	}
    	return 0;
    }
    
    /**
     * Function that update the tree height recursively recursively
     * @param node - Node we will work on it
     * @param spell - The spell we inserted, find the path by that spell
     */
    private void update_nodes_height(Node node, Spell spell) {
    	// If current node power_level is greater then the spell power_level - Go left
    	if(spell.getPowerLevel() < node.spell.getPowerLevel()) {
    		update_nodes_height(node.left, spell);
    	}
    	// If current node power_level is smaller then the spell power_level - Go right
    	else if(spell.getPowerLevel() > node.spell.getPowerLevel()) {
    		update_nodes_height(node.right, spell);
    	}
    	// If current node power_level equals the spell power_level - we arrived the spell node
    	// If we are leaf
    	if(node.right == null && node.left == null) {
    		node.height = 0;
    	}
    	// Else, correct the heights
    	else {
    		// Left is null
    		if(node.left == null && node.right != null) node.height = node.right.height+1;
    		// Right is null
    		else if(node.right == null && node.left != null) node.height = node.left.height+1;
    		// Has 2 sons
    		else if(node.right != null && node.right != null) {
    			node.height = Math.max(node.left.height, node.right.height) + 1;
    		}
    	}
    }

}


