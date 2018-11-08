import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

  public static void main(String args[]) {
    try {
      //Files.list(Paths.get("/Users/Mitchell/Downloads/train/")).forEach(System.out::println);
      File dir = new File("/Users/Mitchell/Downloads/train/");
      HashMap<String, Integer> words = new HashMap<>();
      StringTokenizer token = new StringTokenizer(" ");
      for (File file : dir.listFiles()) {
        Scanner s = new Scanner(file);
        for(int i = 0; i < 10; i++) {
          words.put(token.nextToken(), 0);
        }
        s.close();

      }
    } catch(Exception e) {

    }
  }
}
