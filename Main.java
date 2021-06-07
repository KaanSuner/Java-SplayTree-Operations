
import java.io.*;
import java.util.*;
import java.util.logging.*;



/**
 *
 * @author Kaan Suner 
 * @version 1.8.0_111
 */
public class Main {

    /**
     * keeps the operation names
     */
    ArrayList<String> words = new ArrayList<>();
    /**
     * keeps the value that will take to operation from current operator
     */
    ArrayList<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Main object = new Main();
        object.readFile(args[0]);
        object.execute(args[1]);

    }

    /**
     * Reading file function
     *
     * @param input input file name
     */
    void readFile(String input) {
        try {

            File file = new File(input);
            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                String next = s.nextLine();
                String[] string = next.split(" ");
                String word = string[0];
                String stringnumber = string[1];
                int number = Integer.parseInt(stringnumber);
                words.add(word);
                numbers.add(number);
            }

            s.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Queue.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Execution function
     *
     * @param args string argument parameter for output file
     * @throws IOException
     */
    public void execute(String args) throws IOException {
        FileWriter output = new FileWriter(args);
        PrintWriter printOut = new PrintWriter(output);
        SplayTree object2 = new SplayTree();
        for (int i = 0; i < words.size(); i++) {

            switch (words.get(i)) {
                case "insert":
                    object2.insert(numbers.get(i));
                    object2.print(printOut);
                    printOut.println();
                    break;
                case "remove":
                    object2.remove(numbers.get(i));
                    object2.print(printOut);
                    printOut.println();
                    break;
                case "find":
                    object2.search(numbers.get(i));
                    object2.print(printOut);
                    printOut.println();
                    break;
            }
        }

        printOut.close();

    }

}
