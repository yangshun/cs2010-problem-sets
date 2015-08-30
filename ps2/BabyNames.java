// Copy paste this Java Template and save it as "BabyNames.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class BabyNames {
  TreeMap<String, Boolean> maleNames;
  TreeMap<String, Boolean> femaleNames;
  TreeMap <String, Integer> names;

  // --------------------------------------------

  public BabyNames() {
    maleNames = new TreeMap<String, Boolean>();
    femaleNames = new TreeMap<String, Boolean>();
    names = new TreeMap <String, Integer>();
  }

  void AddSuggestion(String babyName, int genderSuitability) {
    if (genderSuitability == 1) {
      maleNames.put(babyName, true);
    } else if (genderSuitability == 2) {
      femaleNames.put(babyName, true);
    }
    names.put(babyName, genderSuitability);
  }

  void RemoveSuggestion(String babyName) {
    int genderSuitability = names.get(babyName);
    if (genderSuitability == 1) {
      maleNames.remove(babyName);
    } else if (genderSuitability == 2) {
      femaleNames.remove(babyName);
    }
  }

  int Query(String START, String END, int genderPreference) {
    int total = 0;
    if (genderPreference == 1 || genderPreference == 0) {
      total += maleNames.headMap(END).size() - maleNames.headMap(START).size();
    }

    if (genderPreference == 2 || genderPreference == 0) {
      total += femaleNames.headMap(END).size() - femaleNames.headMap(START).size();
    }

    return total;
  }

  void run() throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    while (true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      if (command == 0) // end of input
        break;
      else if (command == 1) // AddSuggestion
        AddSuggestion(st.nextToken(), Integer.parseInt(st.nextToken()));
      else if (command == 2) // RemoveSuggestion
        RemoveSuggestion(st.nextToken());
      else // if (command == 3) // Query
        pr.println(Query(st.nextToken(), // START
                         st.nextToken(), // END
                         Integer.parseInt(st.nextToken()))); // GENDER
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method to avoid unnecessary errors with the automated judging
    BabyNames ps2 = new BabyNames();
    ps2.run();
  }
}
