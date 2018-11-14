import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

  public static void main(String args[]) {

    boolean invalidInput = true;

    System.out.println("Welcome to the SpamCatcher \n");
    while(invalidInput) {
      try {
        System.out.println("Please enter the full path to your training data file.");
        Scanner scan = new Scanner(System.in);
        String trainingData = scan.nextLine();
        //scan = new Scanner(new File(trainingData));

        System.out.println("Please enter the full path to your testing data file.");
        String testingData = scan.nextLine();
        //scan = new Scanner(new File(testingData));
        setUpNB(trainingData);
        invalidInput = false;
      } catch (Exception e) {
        System.out.println("Invalid path, please try again.");
      }
    }
  }



  // should probably modularize everything, will start moving stuff into these when I have a clearer
  // idea of how they can and should be split
  public void runKNN() {

  }

  public void runNB() {

  }

  public static void setUpNB(String trainingData) {
    File dir = new File(trainingData);
    ArrayList<String> spamWords = new ArrayList<>();
    ArrayList<String> notSpamWords = new ArrayList<>();
    HashMap<String, Integer> spam = new HashMap<>();
    HashMap<String, Integer> notSpam = new HashMap<>();
    Scanner s = new Scanner(System.in);
    try {
      for (File file : dir.listFiles()) {
        s = new Scanner(file);
        while (s.hasNext()) {
          if (file.getName().contains("spm")) {
            spamWords.add(s.next());
          } else {
            notSpamWords.add(s.next());
          }
        }
      }
      s.close();
    } catch (Exception e) {

    }

    wordCounts(spamWords, spam);
    for (String stuff : spam.keySet()) {
      System.out.println("Word " + stuff + " appears " + spam.get(stuff) + " times");
    }
  }


  public static void wordCounts(ArrayList<String> words, HashMap<String, Integer> wordCount) {
    int count = 1;
    String prev = "";
    Collections.sort(words);
    for (String word : words) {
      if (word.equals(prev)) {
        wordCount.put(word, count++);
      } else {
        count = 1;
        wordCount.put(word, count);
      }
      prev = word;
    }
  }


  public static void testingPurposes(String trainingData, String testingData) {
//    try {
//      // the directory of the training data
//      // we should either make this user entry or keep it in the project folder
//      File dir = new File(trainingData);
//      HashMap<String, Integer> words = new HashMap<>(); // this will hold words and their frequency
//      ArrayList<String> allWords = new ArrayList<>(); // use this to hold all the words
//      ArrayList<MailVector> spam = new ArrayList<>();
//      ArrayList<MailVector> notSpam = new ArrayList<>();
//      HashMap<String, Integer> spamWords = new HashMap<>();
//      HashMap<String, Integer> nonSpamWords = new HashMap<>();
//
//
//      // import all the files and and their words to the arraylist
//      for (File file : dir.listFiles()) {
//        Scanner s = new Scanner(file);
//        String line = "";
//        while (s.hasNext()) {
//          line = s.next();
//          allWords.add(line);
//        }
//        s.close();
//      }
//
//      for (File file : dir.listFiles()) {
//        if(file.getName().contains("spm")) {
//          spam.add(new MailVector(words));
//        } else {
//          notSpam.add(new MailVector(words));
//        }
//      }
//
//      // adding the spam and not spam emails to arrays of MailVectors just to see if it
//      // would successfully split the emails by naming format
//      int i = 1;
//      for (MailVector name : spam) {
//        System.out.println("This is the " + i++ + "st/nd/rd spam message " + name.toString());
//      }
//      System.out.println("\n");
//      i = 1;
//      for (MailVector name : notSpam) {
//        System.out.println("This is the " + i++ + "st/nd/rd non-spam message " + name.toString());
//      }
//
//      // this should work but for whatever reason takes forever to run, I have yet to have it finish
//      //allWords.forEach((w) -> words.put(w, Collections.frequency(allWords, w)));
//
//      // so I did it the simple way instead and it works
//      // Its sorted and if the word is the same as the previous, increment count, otherwise reset to one
//      // then add to the map using the fact that it will replace duplicate keys with the most recent
////      int count = 1;
////      String prev = "";
////      Collections.sort(allWords);
////      for (String word : allWords) {
////        if (word.equals(prev)) {
////          words.put(word, count++);
////        } else {
////          count = 1;
////          words.put(word, count);
////        }
////        prev = word;
////      }
//      //Map.sortByKey(words);
//      // print each word with its frequency, I'll sort by frequency later
//      for (String stuff : words.keySet()) {
//        //System.out.println("Word " + stuff + " appears " + words.get(stuff) + " times");
//      }
//
//
//    } catch (Exception e) {
//      // maybe add an error message
//    }
  }
}
