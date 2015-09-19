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
  private TreeMap<Integer, TreeMap<Integer, Boolean> > AdjMatrix;
  private int[] RatingScore;

  private boolean[] visited;
  private int[] depth;
  private int[] low;
  private int[] parent;
  private TreeMap<Integer, Boolean> articulationPoints;

  void getArticulationPoints(int i, int d) {
    visited[i] = true;
    depth[i] = d;
    low[i] = d;
    int childCount = 0;
    boolean isArticulation = false;

    for (Map.Entry<Integer, Boolean> entry : AdjMatrix.get(i).entrySet()) {
      int ni = (int)entry.getKey();

      if (!visited[ni]) {
        parent[ni] = i;
        getArticulationPoints(ni, d + 1);
        childCount++;

        int low_i = low[i];
        int low_ni = low[ni];
        int depth_i = depth[i];

        if (low_ni != -1 && depth_i != -1 && (low_ni >= depth_i)) {
          isArticulation = true;
        }
        if (low_i != -1 && low_ni != -1) {
          low[i] = Math.min(low_i, low_ni);
        } else if (low_i == -1 && low_ni != -1) {
          low[i] = low_ni;
        } else if (low_i != -1 && low_ni == -1) {
          low[i] = low_i;
        }
      } else if (ni != parent[i]) {
        int depth_ni = depth[ni];
        int low_i = low[i];

        if (low_i != -1 && depth_ni != -1) {
          low[i] = Math.min(low_i, depth_ni);
        } else if (low_i == -1 && depth_ni != -1) {
          low[i] = depth_ni;
        } else if (low_i != -1 && depth_ni == -1) {
          low[i] = low_i;
        }
      }
    }

    int parent_i = parent[i];

    if ((parent_i != -1 && isArticulation) || (parent_i == -1 && childCount > 1)) {
      articulationPoints.put(i, true);
    }
  }

  int Query() {
    int minimum = 100001;

    visited = new boolean[V];
    Arrays.fill(visited, false);

    depth = new int[V];
    Arrays.fill(depth, -1);

    low = new int[V];
    Arrays.fill(low, -1);

    parent = new int[V];
    Arrays.fill(parent, -1);

    articulationPoints = new TreeMap<Integer, Boolean>();

    getArticulationPoints(0, 0);

    for (Map.Entry<Integer, Boolean> entry : articulationPoints.entrySet()) {
      int i = (int)entry.getKey();
      if (RatingScore[i] < minimum) {
        minimum = RatingScore[i];
      }
    }

    return minimum != 100001 ? minimum : -1;
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
      AdjMatrix = new TreeMap<Integer, TreeMap<Integer, Boolean> >();
      for (int i = 0; i < V; i++) {
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        TreeMap<Integer, Boolean> g = AdjMatrix.get(i);
        if (g == null) {
          g = new TreeMap<Integer, Boolean>();
          AdjMatrix.put(i, g);
        }

        while (k-- > 0) {
          int j = Integer.parseInt(st.nextToken());

          g.put(j, true);
          TreeMap<Integer, Boolean> gCon = AdjMatrix.get(j);
          if (gCon == null) {
            gCon = new TreeMap<Integer, Boolean>();
            AdjMatrix.put(j, gCon);
          }

          gCon.put(i, true);
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
