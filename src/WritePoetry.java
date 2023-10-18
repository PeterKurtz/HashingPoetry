import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WritePoetry {

    public String writePoem(String fileName, String startWord, int length, boolean print) {

        File file = new File(fileName);
        HashTable<String, WordFreqInfo> poemWords = new HashTable<>();
        ArrayList<String> words = new ArrayList<String>();

        try (Scanner input = new Scanner(file)) {

            while (input.hasNext()) {

                String word = input.next();
                word = word.toLowerCase();

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

        if (print == true) {
            System.out.println(poemWords.toString(poemWords.size()));
        }

        Random rnd = new Random();

        String poem = startWord;
        String word;

        for (int i = 0; i < length; i++) {
            WordFreqInfo startWordFreq = poemWords.find(startWord);
            int numOfOccur = startWordFreq.getOccurCount();
            int randNumber = rnd.nextInt(numOfOccur);
            word = startWordFreq.getFreqWord(randNumber);
            //System.out.println(word);
            if (!Character.isAlphabetic(word.charAt(word.length()-1))) {
                poem = poem + word + "\n";
            }
            else if (poem.endsWith("\n")){
                poem = poem + word;
            }
            else {
                poem = poem + " " + word;
            }
            startWord = word;

        }

        if (Character.isAlphabetic(poem.charAt(poem.length() - 1))) {
            poem = poem + ".";
        }



        return poem;
    }

    private HashTable<String, WordFreqInfo> update(HashTable<String, WordFreqInfo> hashPoem, ArrayList<String> words) {
        int count1 = 0;
        int count2 = 1;

        while (count2 < words.size()) {

            WordFreqInfo wordInHashTable = hashPoem.find(words.get(count1));

            if (wordInHashTable == null) {
                WordFreqInfo wordF = new WordFreqInfo(words.get(count1), 0);
                wordF.updateFollows(words.get(count2));
                hashPoem.insert(words.get(count1), wordF);
            }

            else {
                wordInHashTable.updateFollows(words.get(count2));
            }


            ++count1;
            ++count2;
        }
        return hashPoem;
    }

}
