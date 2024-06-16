/**
 * SpellSimple Class use for creating spells object
 * This class represents the SpellSimple struct
 */
public class SpellSimple {
	/* The spell name */
    private String name;
    /* The spell words required to cast the spell */
    private String words;

	 /** Default constructor */
    public SpellSimple(){
    	this.name = "";
    	this.words = "";
    }
    
    /**
     * Constructor that receive spell name and spell words
     * @param name - The spell name in String
     * @param words - The spell words in String
     */
    public SpellSimple(String name, String words) {
    	this.name = name;
    	this.words = words;
    }

    /**
     * Function that return the Spell name
     * @return - The spell name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Function that return spell words
     * @return -  The words to cast the spell
     */
    public String getWords(){
        return this.words;
    }
    
    /**
     * overriding toString
     * "Spell: {spellName}, to cast say {SpellWords}"
     */
    @Override
    public String toString() {
    	return "Spell: " + this.name + ", to cast say: " + this.words;
    }
}