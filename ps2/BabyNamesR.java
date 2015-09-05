// Copy paste this Java Template and save it as "BabyNamesR.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class BabyNamesR {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  private ArrayList<String> nameSuffixes;
  int number = 0;

  public BabyNamesR() {
    nameSuffixes = new ArrayList<String>();
  }

  void AddSuggestion(String babyName) {
    int N = babyName.length();
    for (int i = 0; i < N; i++) {
      nameSuffixes.add(babyName.substring(i, N) + ":" + String.valueOf(number));
    }
    number++;
  }

  int countOccurences(String pattern, ArrayList<String> suffixes) {
    int l = 0;
    int r = suffixes.size() - 1;
    while (l < r) {
      int mid = (l+r) / 2;
      if (pattern.compareTo(suffixes.get(mid)) > 0) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    int s = l;
    int n = suffixes.size() - 1;

    TreeSet<String> numbers = new TreeSet<String>();
    while (s <= n && suffixes.get(s).contains(pattern)) {
      numbers.add(suffixes.get(s).split(":")[1]);
      s++;
    }

    return numbers.size();
  }

  int Query(String SUBSTR) {
    return countOccurences(SUBSTR, nameSuffixes);
  }

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int N = Integer.parseInt(br.readLine());
    while (N-- > 0)
      AddSuggestion(br.readLine());
    Collections.sort(nameSuffixes);
    int Q = Integer.parseInt(br.readLine());
    while (Q-- > 0)
      pr.println(Query(br.readLine())); // SUBSTR
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    BabyNamesR ps2R = new BabyNamesR();
    ps2R.run();
  }
}
