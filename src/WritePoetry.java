import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WritePoetry {

    public String writePoem(String fileName, String title, int length, boolean print) {

        File file = new File(fileName);
        HashTable<String, WordFreqInfo> poemWords = new HashTable<>();
        ArrayList<String> words = new ArrayList<String>();

        try (Scanner input = new Scanner(file)) {

            while (input.hasNext()) {

                String word = input.next();

                //Word has punctuation at the end
                if (!Character.isAlphabetic(word.charAt(word.length() - 1))) {
                    words.add(word.substring(0, word.length() - 1));
                    words.add(word.substring(word.length() - 1));
                }

                //Word does not have punctuation
                else {
                    words.add(word);
                }
            }

        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the dictionary: " + ex);
        }


        poemWords = update(poemWords, words);

        String wordT = "This is a test";
        return wordT;
    }

    private HashTable<String, WordFreqInfo> update(HashTable<String, WordFreqInfo> hashTable, ArrayList<String> words) {
        int count1 = 0;
        int count2 = 1;

        while (count2 < words.size()) {
            //System.out.println(words.get(count2));




            ++count1;
            ++count2;
        }
        return hashTable;
    }

}
