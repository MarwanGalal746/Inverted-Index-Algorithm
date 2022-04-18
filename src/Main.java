import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        App.app();
    }

    static class App {
        public static void app() {
            InvertedIndex index = new InvertedIndex();
            index.AddDocs(new ArrayList<String>(List.of("100.txt", "526.txt", "527.txt")));
            //index.printPostingsList();
            System.out.println("Query1: ");
            index.Query("Ehab and Labib");
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Query2: ");
//            index.Query("software or not computing or first");
//            System.out.println("-----------------------------------------------------");
//            System.out.println("Query3: ");
//            index.Query("development or not computing ");
//            System.out.println("-----------------------------------------------------");

        }
    }
}