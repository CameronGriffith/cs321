import java.util.ArrayList;

/**
 * This class is made to test the modifyString()
 * method from WOOF. It is only used for testing.
 *
 * To use this, simply implement modifyString and compile this 
 * file (javac ModifyStringTest.java) then run this
 * class (java ModifyStringTest) and look at the status of 
 * each word. Add more words to to the list in <code>loadWordsToTest()</code>
 * if you want.
 */
public class ModifyStringTest {

    private ArrayList<WordExample> wordsToTest  = new ArrayList<WordExample>();

    public static void main(String args[]) {
        new ModifyStringTest().runTests();
    }

    public void runTests() {
        wordsToTest.clear();
        loadWordsToTest();
        testAllWords();
    }

    /**
     * This method makes the word all lowercase, and removes leading and
     * trailing punctuation
     * 
     * @param s The string to 'fix'
     * @return s in all lowercase, with no leading/trailing punctuation.
     */
    public String modifyString(String s) {
        s= s.toLowerCase();
        s = s.replaceFirst("^[^a-zA-Z]+", "");
        s = s.replaceAll("[^a-zA-Z]+$", "");

        return s;
    }

    private void testAllWords() {
        boolean passesAll = true;
        System.out.format("%-20s%-20s%-20s%-10s\n", "Initial", "Correct", "After modifyString", "Status");
        for (WordExample we : wordsToTest) {
            System.out.println(we.getStatus(modifyString(we.getInitialWord())));    
            if (passesAll) {
                passesAll = we.isCorrect(modifyString(we.getInitialWord()));
            }
        }

        System.out.println("");
        if (passesAll) {
            System.out.println("Passes all tests.");
        } else {
            System.out.println("Does NOT pass all tests.");
        }
    }

    private void loadWordsToTest() {
        // TODO Add any other words you want to test here
        wordsToTest.add(new WordExample("Hello", "hello"));
        wordsToTest.add(new WordExample("PhD", "phd"));
        wordsToTest.add(new WordExample("\"Can", "can"));
        wordsToTest.add(new WordExample("\"Hello\"!", "hello"));
        wordsToTest.add(new WordExample("know?\"", "know"));
        wordsToTest.add(new WordExample("Can't", "can't"));
        wordsToTest.add(new WordExample("\"'What?'\"", "what"));
    }

    class WordExample {
        private String initialWord;
        private String correctWord;
        public WordExample (String initial, String correct) {
            initialWord = initial;
            correctWord = correct;
        }

        public String getInitialWord() { return initialWord; }

        public String getStatus(String toTest) {
            String status = isCorrect(toTest)?"Passed":"Failed";
            String result = String.format("%-20s%-20s%-20s%-10s", 
                        initialWord, correctWord, toTest, status);
            return result;
        }

        public boolean isCorrect(String toTest) {
            return toTest.equals(correctWord);
        }
    }
}
