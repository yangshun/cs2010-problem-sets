// Copy paste this Java Template and save it as "HospitalTour.java"
import java.util.*;
import java.io.*;
import java.lang.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class HospitalTour {
  private int V;
  private TreeMap<Integer, TreeSet<Integer> > AdjMatrix;
  private int[] RatingScore;

  private TreeSet<Integer> visited;
  private TreeMap<Integer, Integer> depth;
  private TreeMap<Integer, Integer> low;
  private TreeMap<Integer, Integer> parent;
  private TreeSet<Integer> articulationPoints;

  public HospitalTour() {
    // Write necessary code during construction
    //
    // write your answer here
  }

  void getArticulationPoints(int i, int d) {
    visited.add(i);
    depth.put(i, d);
    low.put(i, d);
    int childCount = 0;
    boolean isArticulation = false;

    Iterator itr = AdjMatrix.get(i).iterator();

    while (itr.hasNext()) {
      int ni = (int)itr.next();
      if (!visited.contains(ni)) {
        parent.put(ni, i);
        getArticulationPoints(ni, d + 1);
        childCount++;
        if (low.get(ni) >= depth.get(i)) {
          isArticulation = true;
        }
        low.put(i, Math.min(low.get(i), low.get(ni)));
      } else if (ni != parent.get(i)) {
        low.put(i, Math.min(low.get(i), depth.get(ni)));
      }
    }

    if ((parent.get(i) != null && isArticulation) || (parent.get(i) == null && childCount > 1)) {
      articulationPoints.add(i);
    }
  }

  int Query() {
    int minimum = 100001;

    visited = new TreeSet<Integer>();
    depth = new TreeMap<Integer, Integer>();
    low = new TreeMap<Integer, Integer>();
    parent = new TreeMap<Integer, Integer>();
    articulationPoints = new TreeSet<Integer>();

    getArticulationPoints(0, 0);

    Iterator itr = articulationPoints.iterator();

    while (itr.hasNext()) {
      int i = (int)itr.next();
      if (RatingScore[i] < minimum) {
        minimum = RatingScore[i];
      }
    }

    return minimum;
  }

  void run() throws Exception {
    // for this PS3, you can alter this method as you see fit

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    int TC = Integer.parseInt(br.readLine()); // there will be several test cases
    while (TC-- > 0) {
      br.readLine(); // ignore dummy blank line
      V = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      // read rating scores, A (index 0), B (index 1), C (index 2), ..., until the V-th index
      RatingScore = new int[V];
      for (int i = 0; i < V; i++) {
        RatingScore[i] = Integer.parseInt(st.nextToken());
      }

      // clear the graph and read in a new graph as Adjacency Matrix
      AdjMatrix = new TreeMap<Integer, TreeSet<Integer> >();
      for (int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        TreeSet<Integer> g = AdjMatrix.get(i);
        if (g == null) {
          g = new TreeSet<Integer>();
          AdjMatrix.put(i, g);
        }

        while (k-- > 0) {
          int j = Integer.parseInt(st.nextToken());

          g.add(j);
          TreeSet<Integer> gCon = AdjMatrix.get(j);
          if (gCon == null) {
            gCon = new TreeSet<Integer>();
            AdjMatrix.put(j, gCon);
          }

          gCon.add(i);
        }

      }

      pr.println(Query());
    }
    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    HospitalTour ps3 = new HospitalTour();
    ps3.run();
  }
}
