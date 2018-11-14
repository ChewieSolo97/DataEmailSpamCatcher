import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


// not 100% how we're going to use this, but for now its a "vector" that holds the word set from the emails
public class MailVector {

  private ArrayList<String> emailWords = new ArrayList<>();
  private boolean isSpam; // if the email is spam or not

  public MailVector(HashMap<String, Integer> words) {
    emailWords.addAll(words.keySet());
  }

  public void setSpam(boolean spam) {
    isSpam = spam;
  }
  public boolean getIsSpam() {
    return isSpam;
  }
}
