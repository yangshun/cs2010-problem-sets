// Copy paste this Java Template and save it as "SchedulingDeliveries.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class SchedulingDeliveries {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  int MAX_DILATION = 100;

  int index;
  TreeMap <String, Integer> womenDilation;
  TreeMap <String, Integer> womenIndex;

  List<TreeMap<Integer, String>> dilations = new ArrayList<TreeMap<Integer, String>>();

  public SchedulingDeliveries() {
    // Write necessary code during construction
    //
    // write your answer here
    index = 0;
    womenDilation = new TreeMap <String, Integer>();
    womenIndex = new TreeMap <String, Integer>();

    for (int i = 0; i <= MAX_DILATION; i++) {
      dilations.add(new TreeMap<Integer, String>());
    }
  }

  void ArriveAtHospital(String womanName, int dilation) {
    // You have to insert the information (womanName, dilation)
    // into your chosen data structure
    //
    // write your answer here
    womenDilation.put(womanName, dilation);
    womenIndex.put(womanName, index);

    TreeMap<Integer, String> pq = dilations.get(dilation);
    pq.put(index, womanName);

    index++;
  }

  void UpdateDilation(String womanName, int increaseDilation) {
    // You have to update the dilation of womanName to
    // dilation += increaseDilation
    // and modify your chosen data structure (if needed)
    //
    // write your answer here
    int origDilation = womenDilation.get(womanName);
    Integer idx = womenIndex.get(womanName);

    int newDilation = origDilation + increaseDilation;
    dilations.get(origDilation).remove(idx);

    womenDilation.put(womanName, newDilation);

    TreeMap<Integer, String> pq = dilations.get(newDilation);
    pq.put(idx, womanName);
  }

  void GiveBirth(String womanName) {
    // This womanName gives birth 'instantly'
    // remove her from your chosen data structure
    //
    // write your answer here
    Integer idx = womenIndex.get(womanName);

    int dilation = womenDilation.get(womanName);

    dilations.get(dilation).remove(idx);
  }

  String Query() {
    String ans = "The delivery suite is empty";

    // You have to report the name of the woman that the doctor
    // has to give the most attention to. If there is no more woman to
    // be taken care of, return a String "The delivery suite is empty"
    //
    // write your answer here

    for (int i = MAX_DILATION; i >= 0; i--) {
      if (dilations.get(i).size() > 0) {
        int key = dilations.get(i).firstKey();
        return dilations.get(i).get(key);
      }
    }

    return ans;
  }

  void run() throws Exception {
    // do not alter this method

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
    while (numCMD-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
      switch (command) {
        case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 1: UpdateDilation(st.nextToken(), Integer.parseInt(st.nextToken())); break;
        case 2: GiveBirth(st.nextToken()); break;
        case 3: pr.println(Query()); break;
      }
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    SchedulingDeliveries ps1 = new SchedulingDeliveries();
    ps1.run();
  }
}
