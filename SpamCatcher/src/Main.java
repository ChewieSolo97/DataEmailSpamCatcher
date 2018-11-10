import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

  public static void main(String args[]) {
    try {
      // the directory of the training data
      File dir = new File("/Users/Mitchell/Downloads/train/");
      HashMap<String, Integer> words = new HashMap<>(); // this will hold words and their frequency
      ArrayList<String> allWords = new ArrayList<>(); // use this to hold all the words

      // import all the files and and their words to the arraylist
      for (File file : dir.listFiles()) {
        Scanner s = new Scanner(file);
        String line = "";
        while (s.hasNext()) {
          line = s.next();
          allWords.add(line);
        }
        s.close();
      }

      // this should work but for whatever reason takes forever to run, I have yet to have it finish
      //allWords.forEach((w) -> words.put(w, Collections.frequency(allWords, w)));

      // so I did it the simple way instead and it works
      // Its sorted and if the word is the same as the previous, increment count, otherwise reset to one
      // then add to the map using the fact that it will replace duplicate keys with the most recent
      int count = 1;
      String prev = "";
      Collections.sort(allWords);
      for (String word : allWords) {
        if (word.equals(prev)) {
          words.put(word, count++);
        } else {
          count = 1;
          words.put(word, count);
        }
        prev = word;
      }

      // print each word with its frequency, I'll sort by frequency later
      for (String stuff : words.keySet()) {
        System.out.println("Word " + stuff + " appears " + words.get(stuff) + " times");
      }

    } catch (Exception e) {
      // maybe add an error message
    }
  }
}
