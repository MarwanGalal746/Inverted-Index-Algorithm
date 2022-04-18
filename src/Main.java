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
            index.AddDocs(new ArrayList<String>(List.of("100.txt")));

        }
    }
}