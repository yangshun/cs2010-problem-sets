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
  ArrayList<String> names;

  public BabyNamesR() {
    // Write necessary code during construction
    //
    // write your answer here
    names = new ArrayList<String>();
  }

  void AddSuggestion(String babyName) {
    // You have to insert the information: babyName
    // into your chosen data structure
    //
    // write your answer here
    names.add(babyName);
  }

  int Query(String SUBSTR) {
    int ans = 0;

    // You have to answer how many baby name contains substring SUBSTR
    //
    // write your answer here
    int length = names.size();
    for (int i = 0; i < length; i++) {
      ans += kmp(SUBSTR, names.get(i)) ? 1 : 0;
    }

    return ans;
  }

  private Boolean kmp(String pat, String txt) {
    int M = pat.length();
    int N = txt.length();

    int[] lps = new int[pat.length()];
    int j = 0;

    // Compute LPS array
    int len = 0;  // length of the previous longest prefix suffix
    int i;

    lps[0] = 0; // lps[0] is always 0
    i = 1;

    // the loop calculates lps[i] for i = 1 to M-1
    while (i < M) {
      if (pat.charAt(i) == pat.charAt(len)) {
        len++;
        lps[i] = len;
        i++;
      } else { // (pat[i] != pat[len])
        if (len != 0) {
          // This is tricky. Consider the example AAACAAAA and i = 7.
          len = lps[len-1];
          // Also, note that we do not increment i here
        } else { // if (len == 0)
          lps[i] = 0;
          i++;
        }
      }
    }

    i = 0;  // index for txt[]
    while (i < N) {
      if (pat.charAt(j) == txt.charAt(i)) {
        j++;
        i++;
      }

      if (j == M) {
        return true;
      } else if (i < N && pat.charAt(j) != txt.charAt(i)) { // mismatch after j matches
        // Do not match lps[0..lps[j-1]] characters,
        // they will match anyway
        if (j != 0) {
         j = lps[j-1];
        } else {
         i = i+1;
        }
      }
    }

    return false;
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
