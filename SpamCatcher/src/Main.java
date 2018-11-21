import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

  public static ArrayList<MailVector> emails = new ArrayList<>();
  public static void main(String args[]) {

    boolean invalidInput = true;

    System.out.println("Welcome to the SpamCatcher \n");
    while(invalidInput) {
      try {
        System.out.println("Please enter the full path to your training data file.");
        //Scanner scan = new Scanner(System.in);
        //String trainingData = scan.nextLine();
        //scan = new Scanner(new File(trainingData));

        System.out.println("Please enter the full path to your testing data file.");
        //String testingData = scan.nextLine();
        //scan = new Scanner(new File(testingData));
        setUpNB("/Users/Mitchell/Downloads/train"); // using string for testing
        invalidInput = false;

      } catch (Exception e) {
        e.printStackTrace();
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
    HashMap<String, Double> spam = new HashMap<>();
    HashMap<String, Double> notSpam = new HashMap<>();
    Scanner s = new Scanner(System.in);
    int spamCount = 0;
    int notSpamCount = 0;
    try {
      for (File file : dir.listFiles()) {
        s = new Scanner(file);
        if (file.getName().contains("spm")) {
          spamCount++;
        } else {
          notSpamCount++;
        }
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
      System.out.println("in the set up exception ");
    }

    wordCounts(spamWords, spam);
    wordCounts(notSpamWords, notSpam);

    for (String word : spam.keySet()) {
      spam.put(word, spam.get(word) / spamCount);
    }
    for (String word : notSpam.keySet()) {
      notSpam.put(word, notSpam.get(word) / notSpamCount);
    }

    classifyNB(spam, notSpam);

//    for (String stuff : spam.keySet()) {
//      System.out.println("Word " + stuff + " appears " + spam.get(stuff) + " times");
//    }
  } // end setUpNB

  // lambda way didn't work so I did it the simple way instead and it works
  // Its sorted and if the word is the same as the previous, increment count, otherwise reset to one
  // then add to the map using the fact that it will replace duplicate keys with the most recent
  public static void wordCounts(ArrayList<String> words, HashMap<String, Double> wordCount) {
    double count = 1;
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

    // this should work but for whatever reason takes forever to run, I have yet to have it finish
    //allWords.forEach((w) -> words.put(w, Collections.frequency(allWords, w)));


  public static void classifyNB(HashMap<String, Double> spam, HashMap<String, Double> notSpam) {

    File dir = new File("/Users/Mitchell/Downloads/test");
    ArrayList<String> words = new ArrayList<>();
    HashMap<String, Double> email = new HashMap<>();
    Scanner s = new Scanner(System.in);
    ArrayList<Double> spamProb = new ArrayList<Double>();
    ArrayList<Double> notSpamProb = new ArrayList<Double>();
    ArrayList<MailVector> emailsAccuracy = new ArrayList<>();
    double accuracy = 0;
    int i = 0;
    try {
//      for (File file : dir.listFiles()) {
//        System.out.println("does this loop work");
//      }
      for (File file : dir.listFiles()) {
        //System.out.println("whats going on");
        s = new Scanner(file);
        emailsAccuracy.add(new MailVector(file.getName().contains("spm") ? true : false));
        while (s.hasNext()) {
          words.add(s.next());
        }
        wordCounts(words, email);
        for (String word : email.keySet()) {
          if(spam.keySet().contains(word)) {
            spamProb.add(spam.get(word) * email.get(word));
          } else {
            spamProb.add(0.0); // fix this later, just using 0.0 for convenience
          }
        }
        double probSpam = 1;
        for (Double prob : spamProb) {
          //System.out.println("Spam prob is: " + prob);
          probSpam += (prob);
        }


        for (String word : email.keySet()) {
          if(notSpam.keySet().contains(word)) {
            notSpamProb.add(notSpam.get(word) * email.get(word));
          } else {
            notSpamProb.add(0.0); // fix this later, just using 0.0 for convenience
          }
        }
        double probNotSpam = 1;
        for (Double prob : notSpamProb) {
          probNotSpam += (prob);
        }

        if (probSpam > probNotSpam) {
          emailsAccuracy.get(i).setSpam(true);
        } else {
          emailsAccuracy.get(i).setSpam(false);
        }
        //System.out.println("i equals: " + i);
        i++;

        // empty out collections
        words.clear();
        email.clear();
        spamProb.clear();
        notSpamProb.clear();
         //emails.add(new MailVector()); set up vectors for verification of spam or not spam
        //System.out.println("The prob of being spam is: " + probSpam);
        //System.out.println("The prob of not being spam is: " + probNotSpam);
      }
      s.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    for (MailVector m : emailsAccuracy) {
      if (m.getLabel() == m.getIsSpam()) {
        accuracy++;
      }
    }
    System.out.println("The accuracy is : " + (accuracy * 100) / emailsAccuracy.size() + "%");


  }
  public static void testingPurposes(String trainingData, String testingData) {

//      int i = 1;
//      for (MailVector name : spam) {
//        System.out.println("This is the " + i++ + "st/nd/rd spam message " + name.toString());
//      }
//      System.out.println("\n");
//      i = 1;
//      for (MailVector name : notSpam) {
//        System.out.println("This is the " + i++ + "st/nd/rd non-spam message " + name.toString());
//      }
  }
}