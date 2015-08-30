// Copy paste this Java Template and save it as "BabyNames.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class BabyNames {
  TreeMap<String, TreeMap<String, Boolean> > maleNames;
  TreeMap<String, TreeMap<String, Boolean> > femaleNames;
  TreeMap <String, Integer> names;

  // --------------------------------------------

  public BabyNames() {
    maleNames = new TreeMap<String, TreeMap<String, Boolean> >();
    femaleNames = new TreeMap<String, TreeMap<String, Boolean> >();
    for (int i = 65; i < 65 + 26; i++) {
      char c = (char) i;
      TreeMap<String, Boolean> maleNemberChar = new TreeMap<String, Boolean>();
      maleNames.put(Character.toString(c), maleNemberChar);

      TreeMap<String, Boolean> femaleNemberChar = new TreeMap<String, Boolean>();
      femaleNames.put(Character.toString(c), femaleNemberChar);
    }

    names = new TreeMap <String, Integer>();
  }

  void AddSuggestion(String babyName, int genderSuitability) {
    char c = babyName.charAt(0);
    if (genderSuitability == 1) {
      maleNames.get(Character.toString(c)).put(babyName, true);
    } else if (genderSuitability == 2) {
      femaleNames.get(Character.toString(c)).put(babyName, true);
    }
    names.put(babyName, genderSuitability);
  }

  void RemoveSuggestion(String babyName) {
    int genderSuitability = names.get(babyName);
    String c = babyName.substring(0, 1);
    if (genderSuitability == 1) {
      maleNames.get(c).remove(babyName);
    } else if (genderSuitability == 2) {
      femaleNames.get(c).remove(babyName);
    }
  }

  int Query(String START, String END, int genderPreference) {
    int total = 0;
    String s = START.substring(0, 1);
    String e = END.substring(0, 1);
    int startInt = (int)s.charAt(0);
    int endInt = (int)e.charAt(0);

    if (genderPreference == 1 || genderPreference == 0) {
      if (startInt == endInt) {
        total += maleNames.get(e).headMap(END).size() - maleNames.get(s).headMap(START).size();
      } else {
        total += maleNames.get(s).tailMap(START, true).size();
        total += maleNames.get(e).headMap(END).size();
        for (int i = startInt + 1; i <= endInt - 1; i++) {
          total += maleNames.get(Character.toString((char)i)).size();
        }
      }
    }

    if (genderPreference == 2 || genderPreference == 0) {
      if (startInt == endInt) {
        total += femaleNames.get(e).headMap(END).size() - femaleNames.get(s).headMap(START).size();
      } else {
        total += femaleNames.get(s).tailMap(START, true).size();
        total += femaleNames.get(e).headMap(END).size();
        for (int i = startInt + 1; i <= endInt - 1; i++) {
          total += femaleNames.get(Character.toString((char)i)).size();
        }
      }
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
