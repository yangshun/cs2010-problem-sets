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
  public TreeMap<String, String[]> nameSuffixes;

  public BabyNamesR() {
    nameSuffixes = new TreeMap<String, String[]>();
  }

  void AddSuggestion(String babyName) {
    int N = babyName.length();
    String[] suffixes = new String[N];
    for (int i = 0; i < N; i++) {
      suffixes[i] = babyName.substring(i, N);
    }
    Arrays.sort(suffixes);
    nameSuffixes.put(babyName, suffixes);
  }

  boolean countOccurences(String pattern, String[] suffixes, String key) {
    int l = 0;
    int r = suffixes.length;
    while (l < r) {
      int mid = (l+r) / 2;
      if (pattern.compareTo(suffixes[mid]) > 0) {
        l = mid + 1;
      } else {
        r = mid;
      }
    }
    int s = l;
    r = suffixes.length;
    while (l < r) {
      int mid = (l+r) / 2;
      if (pattern.compareTo(suffixes[mid]) < 0) {
        r = mid;
      } else {
        l = mid + 1;
      }
    }
    return s != r ? true : suffixes[Math.min(suffixes.length - 1, s)].contains(pattern);
  }

  int Query(String SUBSTR) {
    int count = 0;
    for (Map.Entry<String, String[]> entry : nameSuffixes.entrySet()) {
      String key = entry.getKey();
      Object value = entry.getValue();
      if (countOccurences(SUBSTR, (String[])value, key)) {
        count += 1;
      }
    }
    return count;
  }

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int N = Integer.parseInt(br.readLine());
    while (N-- > 0)
      AddSuggestion(br.readLine());
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
