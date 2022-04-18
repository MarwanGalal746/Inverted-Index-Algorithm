import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InvertedIndex {
    private HashMap<String, ArrayList<String>> postingsArray;

    public InvertedIndex() {
        this.postingsArray = new HashMap<>();
    }

    public void AddDocs(ArrayList<String> docs) {
        tokenizingAndSorting(docs.get(0));
    }

    private void tokenizingAndSorting(String file) {
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(data);
                while(tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    if (this.postingsArray.get(word) == null)
                        this.postingsArray.put(word, new ArrayList<String>(Arrays.asList(file)));
                    else {
                        if (!this.postingsArray.get(word).contains(file)) {
                            this.postingsArray.get(word).add(file);
                        }
                    }
                    this.postingsArray.put(tokenizer.nextToken(), new ArrayList<String>());
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
