// Copy paste this Java Template and save it as "Labor.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class Labor {
  private int V; // number of vertices in the graph (number of junctions in Singapore map)
  private int Q; // number of queries
  private Vector < Vector < IntegerTriple > > AdjList; // the weighted graph (the Singapore map), the length of each edge (road) is stored here too, as the weight of edge

  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  // --------------------------------------------

  int[][][] timeFromSource;
  private TreeSet<Integer> processedSources;

  public Labor() {
    // Write necessary code during construction
    //
    // write your answer here
    timeFromSource = new int[1000][1000][V];
    processedSources = new TreeSet<Integer>();
  }

  void PreProcess() {
    int MAXIMUM_SOURCE_VERTEX = Math.min(999, V-1);
    int K = Math.min(V, 20);

    for (int i = 0; i <= MAXIMUM_SOURCE_VERTEX; i++) {
      int [][]temp = new int[K+1][1000];
      timeFromSource[i] = temp;
    }
  }

  int[] dijkstra(int source, int C) {
    int time[] = new int[V];
    int mincost[] = new int[V];

    TreeMap<IntegerTriple, Boolean> Q = new TreeMap<IntegerTriple, Boolean>();
    Vector<IntegerTriple> nodes = new Vector<IntegerTriple>();

    time[source] = 0;
    mincost[source] = 1;

    for (int v = 0; v < V; v++) {
      if (v != source) {
        time[v] = Integer.MAX_VALUE;
        mincost[v] = Integer.MAX_VALUE;
      }
      IntegerTriple p = new IntegerTriple(mincost[v], time[v], v);
      nodes.add(p);
    }

    Q.put(nodes.get(source), true);

    while (Q.size() > 0) {
      Map.Entry<IntegerTriple, Boolean> triple = Q.pollFirstEntry();
      int u = triple.getKey().third();
      int d = triple.getKey().second();
      int c = triple.getKey().first();

      Vector <IntegerTriple> neighboursOfU = AdjList.get(u);
      Iterator itr = neighboursOfU.iterator();

      while (itr.hasNext()) {
        IntegerTriple v = (IntegerTriple)itr.next();
        int neighbour = v.first();
        int weight = v.second();
        int cost = v.third();

        int newTime = time[u] + weight;
        int newCost = mincost[u] + cost;
        if (newTime < time[neighbour] && newCost <= C) {
          time[neighbour] = newTime;
          mincost[neighbour] = newCost;
          IntegerTriple p = (IntegerTriple)nodes.get(neighbour);
          Q.remove(p);
          p._first = newCost;
          p._second = newTime;
          Q.put(p, true);
        }
      }
    }

    return time;
  }

  int Query(int s, int t, int k) {
    if (!processedSources.contains(s)) {
      int K = Math.min(V, 20);
      for (int c = 0; c <= K; c++) {
        timeFromSource[s][c] = dijkstra(s, c);
      }
    }
    return timeFromSource[s][k][t] == Integer.MAX_VALUE ? -1 : timeFromSource[s][k][t];
  }

  void run() throws Exception {
    // you can alter this method if you need to do so
    IntegerScanner sc = new IntegerScanner(System.in);
    PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    int TC = sc.nextInt(); // there will be several test cases
    while (TC-- > 0) {
      V = sc.nextInt();

      // clear the graph and read in a new graph as Adjacency List
      AdjList = new Vector < Vector < IntegerTriple > >();
      for (int i = 0; i < V; i++) {
        AdjList.add(new Vector < IntegerTriple >());

        int k = sc.nextInt();
        while (k-- > 0) {
          int j = sc.nextInt(), w = sc.nextInt();
          AdjList.get(i).add(new IntegerTriple(j, w, 1)); // edge (road) weight (in minutes) is stored here
        }
      }

      PreProcess(); // optional

      Q = sc.nextInt();
      while (Q-- > 0)
        pr.println(Query(sc.nextInt(), sc.nextInt(), sc.nextInt()));

      if (TC > 0)
        pr.println();
    }

    pr.close();
  }

  public static void main(String[] args) throws Exception {
    // do not alter this method
    Labor ps5 = new Labor();
    ps5.run();
  }
}



class IntegerScanner { // coded by Ian Leow, using any other I/O method is not recommended
  BufferedInputStream bis;
  IntegerScanner(InputStream is) {
    bis = new BufferedInputStream(is, 1000000);
  }

  public int nextInt() {
    int result = 0;
    try {
      int cur = bis.read();
      if (cur == -1)
        return -1;

      while ((cur < 48 || cur > 57) && cur != 45) {
        cur = bis.read();
      }

      boolean negate = false;
      if (cur == 45) {
        negate = true;
        cur = bis.read();
      }

      while (cur >= 48 && cur <= 57) {
        result = result * 10 + (cur - 48);
        cur = bis.read();
      }

      if (negate) {
        return -result;
      }
      return result;
    }
    catch (IOException ioe) {
      return -1;
    }
  }
}

class IntegerPair implements Comparable < IntegerPair > {
  Integer _first, _second;

  public IntegerPair(Integer f, Integer s) {
    _first = f;
    _second = s;
  }

  public int compareTo(IntegerPair o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else
      return this.second() - o.second();
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
}

class IntegerTriple implements Comparable < IntegerTriple > {
  Integer _first, _second, _third;

  public IntegerTriple(Integer f, Integer s, Integer t) {
    _first = f;
    _second = s;
    _third = t;
  }

  public int compareTo(IntegerTriple o) {
    if (!this.first().equals(o.first()))
      return this.first() - o.first();
    else {
      if (!this.second().equals(o.second())) {
        return this.second() - o.second();
      } else {
        return this.third() - o.third();
      }
    }
  }

  Integer first() { return _first; }
  Integer second() { return _second; }
  Integer third() { return _third; }
}
