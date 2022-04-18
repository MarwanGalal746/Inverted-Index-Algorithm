import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InvertedIndex {
    private HashMap<String, ArrayList<String>> postingsList;
    private ArrayList<String> allDocs;

    public InvertedIndex() {
        this.postingsList = new HashMap<>();
        this.allDocs = new ArrayList<>();
    }

    public void printPostingsList() {
        for (Map.Entry<String, ArrayList<String>> entry : this.postingsList.entrySet()) {
            System.out.print(entry.getKey() + "/ ");
            for (int i = 0; i < entry.getValue().size(); i++)
                System.out.print(entry.getValue().get(i) + " ");
            System.out.println();
        }
    }

    public void AddDocs(ArrayList<String> docs) {
        for (int i = 0; i < docs.size(); i++){
            tokenizingAndSorting(docs.get(i));
            allDocs.add(docs.get(i));
        }
    }

    public void Query(String query) {
        ArrayList<String> q = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(query);
        while (tokenizer.hasMoreTokens()) {
            q.add(tokenizer.nextToken());
        }
        int counter = 0;
        boolean first = true;
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> operand = new ArrayList<String>();
        while (counter<q.size()) {
            String operator = "";
            if (first) {
                if (q.get(counter).equals("not")) {
                    operand = this.not(postingsList.get(q.get(1)));
                    counter += 2;
                } else {
                    operand = postingsList.get(q.get(0));
                    counter += 1;
                }
                result = this.or(result, operand);
            } else {
                if (q.get(counter+1).equals("not")) {
                    operand = this.not(postingsList.get(q.get(counter+2)));
                    operator = q.get(counter);
                    counter += 3;
                } else {
                    operand = postingsList.get(q.get(counter+1));
                    operator = q.get(counter);
                    counter += 2;
                }
                if (operator.equals("and"))
                    result = this.and(result, operand);
                else
                    result = this.or(result, operand);
            }
        }
        for (int i = 0; i < result.size(); i++)
            System.out.println(result.get(i));
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
            if (list1.get(i).compareTo(list2.get(r)) == 0) {
                System.out.println("gg");
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
            if (!result.contains(list2.get(i))) {
                result.add(list2.get(i));
            }
        }
        return result;
    }

    private ArrayList<String> not(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < this.allDocs.size(); i++) {
            if (!list.contains(allDocs.get(i))) {
                result.add(allDocs.get(i));
            }
        }
        return result;
    }


}
