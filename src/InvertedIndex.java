import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InvertedIndex {
    private HashMap<String, ArrayList<String>> postingsList;
    private ArrayList<String> allDocs;

    public InvertedIndex(ArrayList<String> allDocs) {
        this.postingsList = new HashMap<>();
        this.allDocs = allDocs;
    }

    public void printPostingsList() {
        for (Map.Entry<String, ArrayList<String>> entry : this.postingsList.entrySet()) {
            System.out.print(entry.getKey() + "/ ");
            for (int i = 0; i < entry.getValue().size(); i++)
                System.out.print(entry.getValue().get(i));
            System.out.println();
        }

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
                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    if (this.postingsList.get(word) == null)
                        this.postingsList.put(word, new ArrayList<String>(Arrays.asList(file)));
                    else {
                        if (!this.postingsList.get(word).contains(file)) {
                            this.postingsList.get(word).add(file);
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

    private ArrayList<String> and(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<String>();
        int i = 0, r = 0;
        for (; i < list1.size() && r < list2.size(); ) {
            if (list1.get(i) == list2.get(r)) {
                result.add(list2.get(r));
                i++;
                r++;
            } else if (list1.get(i).compareTo(list2.get(r)) < 0)
                i++;
            else
                r++;
        }
        return result;
    }

    private ArrayList<String> or(ArrayList<String> list1, ArrayList<String> list2) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < list1.size(); i++) {
            result.add(list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            if(!result.contains(list2.get(i))) {
                result.add(list2.get(i));
            }
        }
        return result;
    }

    private ArrayList<String> not(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i =0 ; i<this.allDocs.size() ; i++) {
            if (!list.contains(allDocs.get(i))) {
                result.add(allDocs.get(i));
            }
        }
        return result;
    }


}
