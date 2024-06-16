/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

    private static boolean testPassed = true;
    private static int testNum = 0;

    /**
     * This entry function will test all classes created in this assignment.
     * @param args command line arguments
     */
    public static void main(String[] args) {
    	
		//######################## Part 1 ########################
		///////// SpellSimple /////////
    	testSpellSimple();
		///////// DoubleHashTable /////////
    	testDoubleHashTable();
    	
		//######################## Part 2 ########################
		///////// Spell /////////
    	testSpell();
		///////// AVLTree /////////
    	testAVLTree();
		///////// HashAVLSpellTable /////////
    	testHashAVLSpellTable();
    	
        if (testPassed) {
            System.out.println("All " + testNum + " tests passed!");
        }

    }
    
    /**
     * Check the SpellSimple Class
     */
    private static void testSpellSimple() {
    	SpellSimple spell = new SpellSimple("Abracadabra", "Avada Kedavra");
    	// Test 1
    	test(spell.getName().equals("Abracadabra"), "The name of the spell should be: 'Abracadabra', got: " + spell.getName());
    	// Test 2
    	test(spell.getWords().equals("Avada Kedavra"), "The words of the spell should be 'Avada Kedavra', got: " + spell.getWords());    
    }
    
    /**
     * Check the DoubleHashTable Class
     */
    private static void testDoubleHashTable() {
    	DoubleHashTable table = new DoubleHashTable(11);
        String[] SpellsNames = {"Shazam", "Expecto Patronum", "Wingardium Leviosa", "Shazam2", "Abracadabra", "Accio", "Accio", "Expelliarmus", "Lumos", "Alohomora", "Riddikulus"};
        String[] SpellWords = {"24K Magic in the air", "I’m gonna stand here like a unicorn", "Get up, stand up", "30K Magic in the air", "Avada Kedavra", "Fetch the TV remote", "Fetch the TV remote2", "Dual fight", "Light up", "Unlock doors", "Transforms nasty Boggarts from something scary into something silly"};
    	// Adding 11 spells to the table
        for(int i = 0; i < 11 ; i++) {
        	table.put(new SpellSimple(SpellsNames[i], SpellWords[i]));
        }
        SpellSimple spell = new SpellSimple("Sectumsempra", "A mouthful of a curse invented by troubled teen Severus Snape");
        // Test 3
        test(!table.put(spell), "Should return False if the table is full!");
    	// Test 4
        test(table.getCastWords("Wingardium Leviosa").equals("Get up, stand up"), "Cast wors should be 'Get up, stand up', got: " + table.getCastWords("Wingardium Leviosa"));
        int last_steps = table.getLastSteps();
        // Test 5
        test(table.getCastWords("Sectumsempra") == null, "Spell don't exist in the Hash Table, shuold return null, got: " + table.getCastWords("Sectumsempra"));
        // Test 6
        test(table.getSize() == 11, "Table's size should be 11, got: " + table.getSize());
        // Test 7
        test(table.getLastSteps() == last_steps, "The last steps should be " + last_steps + ", don't need to change after the instert still the same, got: " + table.getLastSteps());
    }
    
    /**
     * Check the Spell Class
     */
    private static void testSpell() {
        Spell spell = new Spell("fireball", "fire", 10, "fireball!");
        // Test 8
        test(spell.getCategory().equals("fire"), "Spell category should be 'fire', got: " + spell.getCategory());
        // Test 9
        test(spell.getName().equals("fireball"), "Spell's name should be 'fireball', got: " + spell.getName());
        // Test 10
        test(spell.getPowerLevel() == 10, "Power level should be '10', got: " + spell.getPowerLevel());
        // Test 11
        test(spell.toString().equals("fireball (fire) - Power Level: 10, to cast say: fireball!"), "ToString function should return 'fireball (fire) - Power Level: 10, to cast say: fireball!', got: " + spell.toString());
    }
    
    private static void testAVLTree() {
    	// Adding spells
		AVLTree Tree = new AVLTree();
        Tree.insert(new Spell("fireball", "fire", 10, "fireball!"));
        Tree.insert(new Spell("light up", "fire", 7, "light people up"));
        Tree.insert(new Spell("wish", "fire", 9, "littel fire ball"));
        Tree.insert(new Spell("burnodicus", "fire", 5, "make things on fire"));
        Tree.insert(new Spell("wawa wish", "fire", 8, "big fire ball!"));
        
        // Test 12
        test(Tree.getSize() == 5, "Tree size should be '5', got: " + Tree.getSize());
        // Test 13
        test(Tree.getTreeHeight() == 2, "Tree height should be '2', got: " + Tree.getTreeHeight());
        // Test 14
        test(Tree.getCategory().equals("fire"), "Category should be 'fire', got: " + Tree.getCategory());
        // Test 15
        test(Tree.getTopK(2).toString().equals("[fireball (fire) - Power Level: 10, to cast say: fireball!, wish (fire) - Power Level: 9, to cast say: littel fire ball]"), "The getTopK function should return '[fireball (fire) - Power Level: 10, to cast say: fireball!, wish (fire) - Power Level: 9, to cast say: littel fire ball]', got: " + Tree.getTopK(2));
        // Test 16
        test(Tree.getTopK(3).toString().equals("[fireball (fire) - Power Level: 10, to cast say: fireball!, wish (fire) - Power Level: 9, to cast say: littel fire ball, wawa wish (fire) - Power Level: 8, to cast say: big fire ball!]"), "The getTopK function should return '[fireball (fire) - Power Level: 10, to cast say: fireball!, wish (fire) - Power Level: 9, to cast say: littel fire ball, wawa wish (fire) - Power Level: 8, to cast say: big fire ball!]', got: " + Tree.getTopK(3));
        // Test 17
        test(Tree.search("wish", 9).toString().equals("wish (fire) - Power Level: 9, to cast say: littel fire ball"), "The search function should return the spell 'wish (fire) - Power Level: 9, to cast say: littel fire ball', got: " + Tree.search("wish", 9));
        // Test 18
        test(Tree.search("babang", 9) == null, "The spells don't exist in the table, should return 'null', got: " + Tree.search("babang", 9));
        
        // Adding more spells
        Tree.insert(new Spell("Allakazzam", "fire", 15, "Sha-Bang-Bang!!"));
        Tree.insert(new Spell("Delenivous", "fire", 11, "Planet inder the river"));
        Tree.insert(new Spell("spectrom", "fire", 12, "10K fire balls"));
        
        // Test 19
        test(Tree.getSize() == 8, "Tree size should be '8', got: " + Tree.getSize());
        // Test 20
        test(Tree.getTreeHeight() == 3, "Tree height should be '3', got: " + Tree.getTreeHeight());
        // Test 21
        test(Tree.getTopK(5).toString().equals("[Allakazzam (fire) - Power Level: 15, to cast say: Sha-Bang-Bang!!, spectrom (fire) - Power Level: 12, to cast say: 10K fire balls, Delenivous (fire) - Power Level: 11, to cast say: Planet inder the river, fireball (fire) - Power Level: 10, to cast say: fireball!, wish (fire) - Power Level: 9, to cast say: littel fire ball]"), "The getTopK function should return '[Allakazzam (fire) - Power Level: 15, to cast say: Sha-Bang-Bang!!, spectrom (fire) - Power Level: 12, to cast say: 10K fire balls, Delenivous (fire) - Power Level: 11, to cast say: Planet inder the river, fireball (fire) - Power Level: 10, to cast say: fireball!, wish (fire) - Power Level: 9, to cast say: littel fire ball]', got: " + Tree.getTopK(5));
        // Test 22
        test(Tree.getTopK(3).toString().equals("[Allakazzam (fire) - Power Level: 15, to cast say: Sha-Bang-Bang!!, spectrom (fire) - Power Level: 12, to cast say: 10K fire balls, Delenivous (fire) - Power Level: 11, to cast say: Planet inder the river]"), "The getTopK function should return '[Allakazzam (fire) - Power Level: 15, to cast say: Sha-Bang-Bang!!, spectrom (fire) - Power Level: 12, to cast say: 10K fire balls, Delenivous (fire) - Power Level: 11, to cast say: Planet inder the river]', got: " + Tree.getTopK(3));
        
        // Adding more spells
        Tree.insert(new Spell("Allakazzam2", "fire", 20, "Sha-Bang-Bang!!Sha-Bang-Bang!!"));
        Tree.insert(new Spell("Delenivous2", "fire", 24, "Planet all over"));
        Tree.insert(new Spell("spectrom2", "fire", 19, "30K fire balls"));
        Tree.insert(new Spell("burnodicus2", "fire", 50, "make things on fire"));
        Tree.insert(new Spell("wawa wish0", "fire", 1, "Very small fire ball!"));
        Tree.insert(new Spell("fireballXX", "fire", 100, "BIG BIG fireball!"));
        Tree.insert(new Spell("flamethrower min", "fire", 6, "foo"));
        Tree.insert(new Spell("flamethrower", "fire", 8, "foo better"));
        Tree.insert(new Spell("fireball II", "fire", 12, "fireball!!"));
        Tree.insert(new Spell("flamethrower II", "fire", 15, "foooooooo!"));

        // Test 23
        test(Tree.getSize() == 18, "Tree size should be '14', got: " + Tree.getSize());
        // Test 24
        test(Tree.getTreeHeight() == 4, "Tree height should be '4', got: " + Tree.getTreeHeight());
        // Test 25
        test(Tree.getTopK(2).toString().equals("[fireballXX (fire) - Power Level: 100, to cast say: BIG BIG fireball!, burnodicus2 (fire) - Power Level: 50, to cast say: make things on fire]"), "The getTopK function should return: '[fireballXX (fire) - Power Level: 100, to cast say: BIG BIG fireball!, burnodicus2 (fire) - Power Level: 50, to cast say: make things on fire]', got: " + Tree.getTopK(2));
        
		AVLTree Tree2 = new AVLTree();
        Tree2.insert(new Spell("fireballXX", "fire", 100, "BIG BIG fireball!"));
        // Test 26
        test(Tree2.getTopK(50).toString().equals("[fireballXX (fire) - Power Level: 100, to cast say: BIG BIG fireball!]"), "The getTopK function should return '[fireballXX (fire) - Power Level: 100, to cast say: BIG BIG fireball!]', got: " + Tree2.getTopK(50));
    }
    
    /**
     * Check the HashAVLSpellTable Class
     */
    private static void testHashAVLSpellTable() {
        // create a new hash AVL spell table
        HashAVLSpellTable table = new HashAVLSpellTable(10);
        // Add some spells to the hash AVL spell table
        table.addSpell(new Spell("lightning bolt", "lightning", 11, "go lightning bolt"));
        table.addSpell(new Spell("fireball", "fire", 10, "fireball!"));
        table.addSpell(new Spell("frostbolt", "ice", 7, "freeze please"));
        table.addSpell(new Spell("thunderstorm", "lightning", 9, "I`m going to shock you"));
        table.addSpell(new Spell("poison spray", "poison", 5, "sssss"));
        table.addSpell(new Spell("shockwave", "lightning", 8, "go pikachu!"));

        // Test 27
        test(table.getNumberSpells() == 6, "Number of spells should be '6', got: " + table.getNumberSpells());
        // Test 28
        test(table.getNumberSpells("fire") == 1, "Numbers of fire spells should be '1', got: " + table.getNumberSpells("fire"));
        // Test 29
        test(table.searchSpell("lightning", "lightning bolt", 11).toString().equals("lightning bolt (lightning) - Power Level: 11, to cast say: go lightning bolt"), "The search function should return 'lightning bolt (lightning) - Power Level: 11, to cast say: go lightning bolt', got: " + table.searchSpell("lightning", "lightning bolt", 11));
        // Test 30
        test(table.searchSpell("fire", "danger", 11) == null, "Search of spell that dont exist should return 'null', gor: " + table.searchSpell("fire", "danger", 11));
        // Test 31
        test(table.getTopK("poison", 1).toString().equals("[poison spray (poison) - Power Level: 5, to cast say: sssss]"), "The search function should return '[poison spray (poison) - Power Level: 5, to cast say: sssss]', got: " + table.getTopK("poison", 1));
        // Test 32
        test(table.getTopK("poison", 11).toString().equals("[poison spray (poison) - Power Level: 5, to cast say: sssss]"), "The search function should return '[poison spray (poison) - Power Level: 5, to cast say: sssss]', got: " + table.getTopK("poison", 11));

        // Add more spells
        table.addSpell(new Spell("flamethrower min", "fire", 6, "foo"));
        table.addSpell(new Spell("flamethrower", "fire", 8, "foo better"));
        table.addSpell(new Spell("fireball II", "fire", 12, "fireball!!"));
        table.addSpell(new Spell("flamethrower II", "fire", 15, "foooooooo!"));
        table.addSpell(new Spell("shockwave II", "lightning", 10,"be useful pikachu."));
        table.addSpell(new Spell("frost nova", "ice", 4, "chill dude"));

        // Test 33
        test(table.getNumberSpells() == 12, "The number of spells should be '12', got: " + table.getNumberSpells());
        // Test 34
        test(table.getNumberSpells("fire") == 5, "The fire spells should be '5', got: " + table.getNumberSpells("fire"));
        // Test 35
        test(table.getTopK("fire", 2).toString().equals("[flamethrower II (fire) - Power Level: 15, to cast say: foooooooo!, fireball II (fire) - Power Level: 12, to cast say: fireball!!]"), "The get function should return '[flamethrower II (fire) - Power Level: 15, to cast say: foooooooo!, fireball II (fire) - Power Level: 12, to cast say: fireball!!]' , got: " + table.getTopK("fire", 2));
        // Test 36
        test(table.getNumberSpells("lightning") == 4, "The lightning spells should be '4', got: " + table.getNumberSpells("lightning"));
        // Test 37
        test(table.getTopK("ice", 3).toString().equals("[frostbolt (ice) - Power Level: 7, to cast say: freeze please, frost nova (ice) - Power Level: 4, to cast say: chill dude]"), "The function shoild return '[frostbolt (ice) - Power Level: 7, to cast say: freeze please, frost nova (ice) - Power Level: 4, to cast say: chill dude]', got: " + table.getTopK("ice", 3));
        // Test 38
        test(table.searchSpell("fire", "fireball II", 12).toString().equals("fireball II (fire) - Power Level: 12, to cast say: fireball!!"), "The search function should return 'fireball II (fire) - Power Level: 12, to cast say: fireball!!', got: " + table.searchSpell("fire", "fireball II", 12));

        // Adding more spells
        table.addSpell(new Spell("light up", "fire", 7, "light people up"));
        table.addSpell(new Spell("wish", "fire", 9, "littel fire ball"));
        table.addSpell(new Spell("burnodicus", "fire", 5, "make things on fire"));
        table.addSpell(new Spell("wawa wish", "fire", 8, "big fire ball!"));
   
        // Test 39
        test(table.getNumberSpells() == 16, "Number of spells should be '16', got: " + table.getNumberSpells());
        // Test 40
        test(table.getNumberSpells("fire") == 9, "Number of fire spells should be '9', got: " + table.getNumberSpells("fire"));
        // Test 41
        test(table.getNumberSpells("ice") == 2, "Number of ice spells should be '2', got: " + table.getNumberSpells("ice"));
        // Test 42
        test(table.getNumberSpells("lightning") == 4, "Number of lightning spells should be '4', got: " + table.getNumberSpells("lightning"));
        // Test 43
        test(table.searchSpell("fire", "burnodicus", 5).toString().equals("burnodicus (fire) - Power Level: 5, to cast say: make things on fire"), "The function should return the spell: 'burnodicus (fire) - Power Level: 5, to cast say: make things on fire', got: " + table.searchSpell("fire", "burnodicus", 5));
        // Test 44
        test(table.getTopK("ice", 0) == null, "Function should return null, got: " + table.getTopK("ice", 0));
        // Test 45
        test(table.getTopK("ice", 2).toString().equals("[frostbolt (ice) - Power Level: 7, to cast say: freeze please, frost nova (ice) - Power Level: 4, to cast say: chill dude]"), "The function should return '[frostbolt (ice) - Power Level: 7, to cast say: freeze please, frost nova (ice) - Power Level: 4, to cast say: chill dude]', got: " + table.getTopK("ice", 2));
        // Test 46
        test(table.getTopK("ice", 10).toString().equals("[frostbolt (ice) - Power Level: 7, to cast say: freeze please, frost nova (ice) - Power Level: 4, to cast say: chill dude]"), "The function should return '[frostbolt (ice) - Power Level: 7, to cast say: freeze please, frost nova (ice) - Power Level: 4, to cast say: chill dude]', got: " + table.getTopK("ice", 10));
    }
    
    
    /**
     * This utility function will count the number of times it was invoked.
     * In addition, if a test fails the function will print the error message.
     * @param exp The actual test condition
     * @param msg An error message, will be printed to the screen in case the test fails.
     */
    private static void test(boolean exp, String msg) {
        testNum++;

        if (!exp) {
            testPassed = false;
            System.out.println("Test " + testNum + " failed: "  + msg);
        }
    }

}
