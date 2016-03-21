import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        SearchEngine searchEngine = new SearchEngine();
        searchEngine.initialize("files/");
        System.out.println("Search Engine has initialized successfully!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input your keywords(separated by space): ");
            String input = scanner.nextLine();
            List<File> res = searchEngine.search(input.split(" "));
            System.out.println("---------------------Here is search result-------------------");
            System.out.println("Find " + res.size() + " files which contains your keyword(s)!");
            for (File f : res) {
                System.out.println("file name: " + f.getName() + "      file path: " + f.getAbsolutePath());
            }
        }
    }
}
