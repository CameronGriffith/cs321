import java.util.*;

/**
 * WOOF -- Words Occurring Often in Files Tracks the number of times
 * each word occurs in a text file.<br>
 * <br> 
 * For example, if the file has the following line:<br>
 * "This is the the way this works."<br>
 * <br>
 * The resulting output would be:
 * <br>
 * <pre>
 * The top 10 words found in ./example.txt are:
 * 1. the      2
 * 2. this     2
 * 3. is       1
 * 4. way      1
 * 5. works    1
 * </pre>
 */
public class WOOF {
    /** ArrayList to hold all of the words we encounter */
    //private ArrayList<Word> wordList = new ArrayList<Word>();
    private HashMap<String, Word> wordList = new HashMap<String, Word>();
    int uniqueWords; 

    /** String to hold the filename being processed */
    private String filename;

    /**
     * The main function of the WOOF class. Makes a new WOOF object
     * and calls its <code>start</code> method.
     * 
     * @param args Command line arguments. args[0] should be the file
     * to process.
     */
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Incorrect # of arguments");
            System.out.println("usage: java WOOF <filename>");
            System.exit(1);
        }

        // To time the program
        long start = System.currentTimeMillis();

        // Make a new Woof, and start it.
     	WOOF w = new WOOF(args[0]);
        w.start();

        long stop = System.currentTimeMillis();
        
        // Print out the results. No need to time this.
        System.out.print(w);
        System.out.println("Processing time:\t" + (stop - start)/1000.0 + "s");
    }

    /**
     * Constructor. Given the filename to be processed.
     * @param file to be processed
     */
    public WOOF(String file) {
        filename = file;

    }

    /**
     * @return the number of unique words encountered by WOOF
     */
    public int getUniqueWords() {
        return uniqueWords;
    }


    /**
     * The <code>start()</code> method does the following things:
     * <ul>
     * <li>Makes a new <code>FileReaderUtility</code> object and calls <code>readFile()</code></li>
     * <li>Passes each line from <code>getNextLine()</code> to <code>handleLine()</code> for
     * processing.
     * <li>Repeat this process until <code>readFile()</code> returns <code>null</code>, 
     * meaning there are no more lines.</li>
     * </ul>
     */
    public void start() {
        FileReaderUtility reader = new FileReaderUtility();
        reader.readFile(filename);
	String line = null;
            while((line = reader.getNextLine()) != null){
		    handleLine(line);
        }

        }

    /**
     * This will handle a line of text from the file.
     * <ul><li>Use <code>String</code>'s <code>split()</code> method to break the
     * line returned from <code>getNextLine()</code> into different words, based
     * on whitespace.</li>
     * <li>Send that word to <code>addOrIncrementWord()</code>, and repeat for
     * each word in the line</li></ul>
     * @param line is a line of text from the file.
     */
    private void handleLine(String line) {
        String[] split = line.split("\\s+");
        for (String element : split) {
	//System.out.println(element);
	element  = modifyString(element);
	element = element.trim();
	if(element.equals("")) {
	continue;
	}
       //     System.out.println(element);
	    addOrIncrementWord(element);

        }

    }

    /**
     * Once we get a single word from a line, we need to either create
     * a new <code>Word</code> object, or increment the number of
     * occurrences of an existing <code>Word</code> object, if we have
     * already encountered this word.
     * <p>
     * Basically, we prep the word we encountered using
     * <code>modifyString</code>, then iterate through all of the
     * words we've already seen. If we see the word in our ArrayList,
     * then simply call <code>increment()</code> on that
     * <code>Word</code> object and return. If we don't find that word
     * already in our ArrayList, then we need to add a new
     * <code>Word</code> object to the ArrayList.
     * 
     * @param newWord The word encountered from the file.
     */
    private void addOrIncrementWord(String newWord) {
        for( Word check : wordList) {
            if(check.getWord().equals(newWord)) {
	       check.increment();
          	return;
            }
        }
        Word toAdd = new Word(newWord);
        wordList.add(toAdd);
        uniqueWords++;

    }

    /**
     * This method makes the word all lowercase, and removes leading and
     * trailing punctuation
     * 
     * @param s The string to 'fix'
     * @return s in all lowercase, with no leading/trailing punctuation.
     */
    private String modifyString(String s) {
    // old bad solution
    //s= s.toLowerCase();
      //  s = s.replaceFirst("^[^a-zA-Z]+", "");
       // s = s.replaceAll("[^a-zA-Z]+$", "");
    	// new good solution
        s = s.toLowerCase();


        // Is it already good?
        // not necessarily needed
        if( s.matches("[a-z0-9]+")) return s;

        // Trim punctuation from the front.
        while (s.length() > 0 && !Character.isLetterorDigit(s.charAt(0))) {
         s = s.substring(1);
        }

        // what I know now is that s starts with a letter or number.
        //
         while (s.length() > 0 &&
                 !Character.isLetterorDigit(s.charAt((s.length() -1))) {
                s = s.substring(0,s.length() -1);
         }
        return s;
    }

    /**
     * This prints out the top 10 words found and the number of occurrences of
     * each word, sorted by the number of occurrences. If you write
     * the <code>toString()</code> method in <code>Word</code>
     * correctly, this is a piece of metaphorical cake. Yum.
     * 
     * @return String formatted for printing.
     */
    public String toString() {
        Collections.sort(wordList, new WordComparator());
	String combined = "The top 10 words found in " + filename + " are: \n";
    for(int i = 0; i < 10 ; i++) {
            combined += i + 1 + ". " + wordList.get(i).getWord() + "\t" + wordList.get(i).getNumSeen() + "\n";

    }
    combined +=  "Total unique words: " + uniqueWords + "\t";
    return combined;
    }

}
