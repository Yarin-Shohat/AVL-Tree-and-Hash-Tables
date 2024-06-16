/**
 * Spell class foe spell object
 * This class represents the Spell Structure
 */
public class Spell {
	/* The name of the spell */
    private String name;
    /* Spell's category */
    private String category;
    /* Spell's power level */
    private int powerLevel;
    /* The words required to cast the spell */
    private String words;

	 /** Default constructor */
    public Spell() {
    	this.name = "";
    	this.category = "";
    	this.powerLevel = Integer.MIN_VALUE;
    	this.words = "";
    }
    
    /**
     * Constructor that receive spell name, cast words, power level and category and create new spell
     * @param name - The spell's name
     * @param category - Spell's category
     * @param powerLevel - Spell's power level
     * @param words - Spell's cast words
     */
    public Spell(String name, String category, int powerLevel, String words) {
    	this.name = name;
    	this.category = category;
    	this.powerLevel = powerLevel;
    	this.words = words;
    }

    /**
     * Function that return the spell's name
     * @return - String spell's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Function that return the spell's category
     * @return String - spell's category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Function that return the spell's power level
     * @return int - spell's power level
     */
    public int getPowerLevel() {
        return this.powerLevel;
    }

    /**
     * overriding toString
     * @return String: { name + " (" + category + ") - Power Level: " + powerLevel + ", to cast say: " + words; }
     */
    @Override
    public String toString() {
        return name + " (" + category + ") - Power Level: " + powerLevel + ", to cast say: " + words;
    }
}
