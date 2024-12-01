import java.io.*;
import java.util.*;

/**
 * This class will be a utility for the ALOOF/WOOF projects, making it easy to read the
 * contents of a file.
 *
 * To use this class, instantiante a new <code>FileReaderUtility</code>, call <code>readFile()</code>,
 * then make a call to <code>getNextLine()</code> every time you the next line in the
 * file. For example:
 * <br>
 * <br>
 * <pre>
 * FileReaderUtility fru = new FileReaderUtility();
 * fru.readFile(myfilename);
 * String line = null;
 * while ((line = fru.getNextLine()) != null) {
 *  System.out.println("The next line is: " + line);
 * }
 * </pre>
 *
 */
public class FileReaderUtility {

    /** Each entry in <code>lines</code> will be a line in the file. */
    private ArrayList<String> lines = new ArrayList<String>();

    /** The <code>BufferedReader</code> to read the input. */
    private BufferedReader br;

    /**
     * This will return the next line of the text file.
     *
     * @return String that holds the next line in the text file,
     *         <code>null</code> if there are no more lines in the file.
     */
    public String getNextLine() {
        if(br == null) return null;
        try {
            String line = br.readLine();
            if(line == null)
                br.close();
            return line;
        } catch (Exception e){ return null;}
    }

    /**
     * This function will read the entire contents of a file, and store the
     * different lines so that they can be retrieved by a call to
     * <code>getNextLine()</code>.
     *
     * @param filename The name of the file to be read
     * @return true if the file can be read, false if the file does not exist or
     *         cannot be read for other reasons.
     */
    public boolean readFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("File: " + filename
                    + " does not exist. Exiting.");
            return false;
        }
        br = null;
        try {
            br = new BufferedReader(new FileReader(f));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        return true;
    }
}
