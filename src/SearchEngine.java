import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SearchEngine {
    private TrieNode root;
    SearchEngine() {
        root = new TrieNode();
    }


    public  void initialize(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles();
        System.out.println("-------------Here are all the files in the directory---------");
        for (File f : files) {
            System.out.println(f.getName());
            String str = Utils.readFileToString(f);
            // split the content by space, \n or others
            StringTokenizer st = new StringTokenizer(str, "[\n\\., ]");
            while (st.hasMoreTokens()) {
                String term = st.nextToken();
//                System.out.println(term);
                TrieNode pointer = this.root;
                for (int i = 0; i < term.length(); i++) {
                    char c = term.charAt(i);
                    if (!pointer.nexts.containsKey(c))
                        pointer.nexts.put(c, new TrieNode());
                    pointer = pointer.nexts.get(c);
                }
                pointer.isLeaf = true;
                if (pointer.occurrenceList == null)
                    pointer.occurrenceList = new LinkedList<>();
                if (!pointer.occurrenceList.contains(f))
                    pointer.occurrenceList.add(f);
            }
        }
    }


    public List<File> search(String[] keywords) {
        List<File> res = new LinkedList<File>();
        if (keywords == null || keywords.length == 0 )
            return res;
        res = searchWord(keywords[0]);
        for (int i = 1; i < keywords.length; i++) {
            List<File> list = searchWord(keywords[i]);
            // Merge the occurrence lists into one list
            res.retainAll(list);
        }
        return res;
    }

    private  List<File> searchWord(String word) {
        TrieNode pointer = this.root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (pointer.nexts.containsKey(c)) {
                pointer = pointer.nexts.get(c);
            } else {
                System.out.println("Cannot find the keyword: " + word);
                return new LinkedList<File>();
            }
        }
        if (pointer.isLeaf == false) {
            System.out.println("Cannot find the keyword: " + word);
            return new LinkedList<File>();
        } else {
            return pointer.occurrenceList;
        }
    }

}
