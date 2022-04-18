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

        for (Map.Entry<String, ArrayList<String>> entry : this.postingsArray.entrySet()) {
            System.out.print(entry.getKey() + "/ " );
            for (int i=0 ; i< entry.getValue().size() ; i++)
                System.out.print(entry.getValue().get(i));
            System.out.println();
        }

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
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
