// Copy paste this Java Template and save it as "BabyNamesR.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

class Node {
  public TreeMap<Character, Node> neighbours;
  public TreeSet<String> words;

  public Node() {
    neighbours = new TreeMap<Character, Node>();
    words = new TreeSet<String>();
  }

  public String neighboursAsString() {
    Set<Character> set = neighbours.keySet();
    if (set == null) {
      return "[]";
    }
    String ns = Arrays.toString(set.toArray());
    return ns == null ? "[]" : ns;
  }

  public String wordsAsString() {
    String ns = Arrays.toString(words.toArray());
    return ns == null ? "[]" : ns;
  }
}

class Trie {
  private Node root;

  public Trie() {
    root = new Node();
  }

  private void insertWord(Node node, String word, String originalWord) {
    node.words.add(originalWord);
    Character c = word.charAt(0);
    Node nextNode = node.neighbours.get(c);
    if (nextNode == null) {
      nextNode = new Node();
      node.neighbours.put(c, nextNode);
    }
    nextNode.words.add(originalWord);
    if (word.length() > 1) {
      insertWord(nextNode, word.substring(1), originalWord);
    }
  }

  public void insertWord(String word) {
    for (int i = 0; i < word.length(); i++) {
      insertWord(root, word.substring(i), word);
    }
  }

  public int findWord(String word) {
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      Character c = word.charAt(i);
      node = node.neighbours.get(c);
      if (node == null && i != word.length() - 1) {
        return 0;
      }
    }
    return node.words.size();
  }
}

class BabyNamesR {
  // if needed, declare a private data structure here that
  // is accessible to all methods in this class
  Trie names;

  public BabyNamesR() {
    names = new Trie();
  }

  void AddSuggestion(String babyName) {
    names.insertWord(babyName);
  }

  int Query(String SUBSTR) {
    return names.findWord(SUBSTR);
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
