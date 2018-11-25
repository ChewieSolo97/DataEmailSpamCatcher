import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


// An object from this class will represent an email with its words and their counts, or an average email
public class MailVector {
  private boolean label;
  //private HashMap<String, Double> emailWords = new HashMap<>();
  private boolean isSpam; // if the email is spam or not

  public MailVector() { // HashMap<String, Double> words) {
    //emailWords.putAll(words);

    // add the other two to the constructor
  }

  public MailVector(boolean label) {
    this.label = label;
  }

  public void setLabel(boolean emailName) {
    label = emailName;
  }

  public boolean getLabel() {
    return label;
  }

  public void setSpam(boolean spam) {
    isSpam = spam;
  }

  public boolean getIsSpam() {
    return isSpam;
  }
}
