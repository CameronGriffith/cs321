import java.util.Comparator;

/**
 * Sorts the <code>Word</code> objects by the word they represent,
 * first by number of occurences, and then lexicographically if they
 * occurred the same number of times.
*/
public class WordComparator implements Comparator<Word> {
    /**
     * Implement a Comparator for <code>Word</code>. Will sort them lexicographically
     * @param w1 The first <code>Word</code> for comparison
     * @param w2 The second <code>Word</code> for comparison
     * @return returns -1, 0, or 1 if w1 is less than, equal to, or greater than w2
     */
    @Override
    public int compare(Word w1, Word w2) {
        if (w1.getNumSeen() < w2.getNumSeen()) return 1;
        if (w1.getNumSeen() > w2.getNumSeen()) return -1;
        return 0;

    }
}
