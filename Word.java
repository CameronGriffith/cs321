/**
 * This class will hold a single 'word' found in a file, and also the
 * number of times the word has been seen. We define a 'word' as being
 * delimited by whitespace, no leading or trailing punctuation.
 *
 */
public class Word {
    /** int To hold the number of times this word has been seen **/
    private int numSeen;
    
    /** String The word that was seen in the file **/
    private String myWord;
    
    /**
     * Only constructor for the Word class. It's required that you
     * give it the 'word' you encountered before you can create the
     * Word object.
     * 
     * By providing a constructor that takes a parameter and NOT
     * implementing the default constructor, this is the <b>only</b>
     * way to make a <code>Word</code>
     * object.
     * @param theWord the word that this object represents
     */
    public Word(String theWord) {
        myWord = theWord;
        numSeen = 1;
    }
    
    /**
     * Call this method when you encounter another word that matches this one. 
     * It will increment numSeen by one
     */
    public void increment() {
        numSeen++;
    }
    
    /**
     * @return The 'word' that this file represents
     */
    public String getWord() {
        return myWord;
    }
    
    /**
     * @return an integer representing the number of times this word
     * has been seen.
     */
    public int getNumSeen() {
        return numSeen;
    }
    
    /**
     * String representation of this object
     */
    public String toString() {
        return "word: " + myWord + "/t" + "Count: " + numSeen;
    }
}
