import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


// not 100% how we're going to us this, but for now its a "vector" that holds the word set from the emails
public class MailVector {

  private ArrayList<String> emailWords = new ArrayList<>();

  public MailVector(HashMap<String, Integer> words) {
    emailWords.addAll(words.keySet());
  }
}
