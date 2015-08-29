// Copy paste this Java Template and save it as "BabyNames.java"
import java.util.*;
import java.io.*;

// write your matric number here:
// write your name here:
// write list of collaborators here:
// year 2015 hash code: JESg5svjYpIsmHmIjabX (do NOT delete this line)

/**
 * A class to represent a binary search tree.
 * @author Koffman and Wolfgang
 */
class BinarySearchTree<E extends Comparable<E>> {
  /** Class to encapsulate a tree node. */
  protected static class Node<E> implements Serializable {
    // Data Fields

    /** The information stored in this node. */
    public E data;
    /** Reference to the left child. */
    public Node<E> left;
    /** Reference to the right child. */
    public Node<E> right;

    public int size;

    // Constructors
    /**
     * Construct a node with given data and no children.
     * @param data The data to store in this node
     */
    public Node(E data) {
        this.data = data;
        left = null;
        right = null;
        height = 0;
        size = 1;
    }

    // Methods
    /**
     * Returns a string representation of the node.
     * @return A string representation of the data fields
     */
    public String toString() {
        return data.toString();
    }
  }
  // Data Field
  /** The root of the binary tree */
  public Node<E> root;

  /** Construct an empty BinarySearchTree */
  public BinarySearchTree() {
      root = null;
  }

  /**
   * Construct a BinarySearchTree with a specified root.
   * Should only be used by subclasses.
   * @param root The node that is the root of the tree.
   */
  protected BinarySearchTree(Node<E> root) {
      this.root = root;
  }

  /**
   * Constructs a new binary tree with data in its root,leftTree
   * as its left subtree and rightTree as its right subtree.
   */
  public BinarySearchTree(E data, BinarySearchTree<E> leftTree,
          BinarySearchTree<E> rightTree) {
      root = new Node<E>(data);
      if (leftTree != null) {
          root.left = leftTree.root;
      } else {
          root.left = null;
      }
      if (rightTree != null) {
          root.right = rightTree.root;
      } else {
          root.right = null;
      }
  }

  /**
   * Return the left subtree.
   * @return The left subtree or null if either the root or
   * the left subtree is null
   */
  public BinarySearchTree<E> getLeftSubtree() {
      if (root != null && root.left != null) {
          return new BinarySearchTree<E>(root.left);
      } else {
          return null;
      }
  }

  /**
   * Return the right sub-tree
   * @return the right sub-tree or
   *         null if either the root or the
   *         right subtree is null.
   */
  public BinarySearchTree<E> getRightSubtree() {
      if (root != null && root.right != null) {
          return new BinarySearchTree<E>(root.right);
      } else {
          return null;
      }
  }

  /**
   * Return the data field of the root
   * @return the data field of the root
   *         or null if the root is null
   */
  public E getData() {
    if (root != null) {
      return root.data;
    } else {
      return null;
    }
  }

  /**
   * Determine whether this tree is a leaf.
   * @return true if the root has no children
   */
  public boolean isLeaf() {
    return (root == null || (root.left == null && root.right == null));
  }

  /** Return value from the public add method. */
  protected boolean addReturn;
  /** Return value from the public delete method. */
  protected E deleteReturn;

  //Methods
  /**
   * Starter method find.
   * @pre The target object must implement
   *      the Comparable interface.
   * @param target The Comparable object being sought
   * @return The object, if found, otherwise null
   */
  public int find(E target, boolean isLeftBound) {
    return find(root, target, isLeftBound, 0, 0);
  }

  /**
   * Recursive find method.
   * @param localRoot The local subtree's root
   * @param target The object being sought
   * @return The object, if found, otherwise null
   */
  private int find(Node<E> localRoot, E target, boolean isLeftBound, int parentIndex, int direction) {
    if (localRoot == null) {
      if (isLeftBound) {
        if (direction == -1) {
          return parentIndex;
        } else {
          return parentIndex + direction;
        }
      } else {
        if (direction == 1) {
          return parentIndex;
        } else {
          return parentIndex - 1;
        }
      }
    }

    // Compare the target with the data field at the root.
    int compResult = target.compareTo(localRoot.data);
    int currentIndex;

    if (direction == -1) {
      // Left 
      int rightSubTreeSize = 0;
      if (localRoot.right != null) {
        rightSubTreeSize = localRoot.right.size;
      }
      currentIndex = parentIndex - rightSubTreeSize - 1; 
    } else if (direction == 1) {
      // Right
      int leftSubTreeSize = 0;
      if (localRoot.left != null) {
        leftSubTreeSize = localRoot.left.size;
      }
      currentIndex = parentIndex + leftSubTreeSize + 1;
    } else {
      // Root
      int leftSubTreeSize = 0;
      if (localRoot.left != null) {
        leftSubTreeSize = localRoot.left.size;
      }
      currentIndex = leftSubTreeSize + 1;
    }
    
    if (compResult == 0) {
      if (!isLeftBound) {
        return currentIndex - 1;
      } else {
        return currentIndex;
      }
    } else if (compResult < 0) {
      return find(localRoot.left, target, isLeftBound, currentIndex, -1);
    } else {
      return find(localRoot.right, target, isLeftBound, currentIndex, 1);
    }
  }

  /**
   * Starter method add.
   * @pre The object to insert must implement the
   *      Comparable interface.
   * @param item The object being inserted
   * @return true if the object is inserted, false
   *         if the object already exists in the tree
   */
  public boolean add(E item) {
      root = add(root, item);
      return addReturn;
  }

  /**
   * Recursive add method.
   * @post The data field addReturn is set true if the item is added to
   *       the tree, false if the item is already in the tree.
   * @param localRoot The local root of the subtree
   * @param item The object to be inserted
   * @return The new local root that now contains the
   *         inserted item
   */
  private Node<E> add(Node<E> localRoot, E item) {
    if (localRoot == null) {
      // item is not in the tree, insert it.
      addReturn = true;
      return new Node<E>(item);
    } else if (item.compareTo(localRoot.data) == 0) {
      // item is equal to localRoot.data
      addReturn = false;
      return localRoot;
    } else if (item.compareTo(localRoot.data) < 0) {
      // item is less than localRoot.data
      localRoot.size++;
      localRoot.left = add(localRoot.left, item);
      return localRoot;
    } else {
      // item is greater than localRoot.data
      localRoot.size++;
      localRoot.right = add(localRoot.right, item);
      return localRoot;
    }
  }
  /**
   * Starter method delete.
   * @post The object is not in the tree.
   * @param target The object to be deleted
   * @return The object deleted from the tree
   *         or null if the object was not in the tree
   * @throws ClassCastException if target does not implement
   *         Comparable
   */
  public E delete(E target) {
      root = delete(root, target);
      return deleteReturn;
  }
  /**
   * Recursive delete method.
   * @post The item is not in the tree;
   *       deleteReturn is equal to the deleted item
   *       as it was stored in the tree or null
   *       if the item was not found.
   * @param localRoot The root of the current subtree
   * @param item The item to be deleted
   * @return The modified local root that does not contain
   *         the item
   */
  private Node<E> delete(Node<E> localRoot, E item) {
    if (localRoot == null) {
      // item is not in the tree.
      deleteReturn = null;
      return localRoot;
    }

    // Search for item to delete.
    int compResult = item.compareTo(localRoot.data);
    if (compResult < 0) {
      // item is smaller than localRoot.data.
      localRoot.size--;
      localRoot.left = delete(localRoot.left, item);
      return localRoot;
    } else if (compResult > 0) {
      // item is larger than localRoot.data.
      localRoot.size--;
      localRoot.right = delete(localRoot.right, item);
      return localRoot;
    } else {
      // item is at local root.
      deleteReturn = localRoot.data;
      if (localRoot.left == null) {
        // If there is no left child, return right child
        // which can also be null.
        return localRoot.right;
      } else if (localRoot.right == null) {
        // If there is no right child, return left child.
        return localRoot.left;
      } else {
        // Node being deleted has 2 children, replace the data
        // with inorder predecessor.
        if (localRoot.left.right == null) {
          // The left child has no right child.
          // Replace the data with the data in the
          // left child.
          localRoot.data = localRoot.left.data;
          // Replace the left child with its left child.
          localRoot.left = localRoot.left.left;
          localRoot.size = (localRoot.left == null ? 0 : localRoot.left.size) + (localRoot.right == null ? 0 : localRoot.right.size) + 1;
          return localRoot;
        } else {
          // Search for the inorder predecessor (ip) and
          // replace deleted node's data with ip.
          localRoot.data = findLargestChild(localRoot.left);
          localRoot.size--;
          return localRoot;
        }
      }
    }
  }
  /**
   * Find the node that is the
   * inorder predecessor and replace it
   * with its left child (if any).
   * @post The inorder predecessor is removed from the tree.
   * @param parent The parent of possible inorder
   *        predecessor (ip)
   * @return The data in the ip
   */
  private E findLargestChild(Node<E> parent) {
      // If the right child has no right child, it is
      // the inorder predecessor.
      parent.size--;
      if (parent.right.right == null) {
          E returnValue = parent.right.data;
          parent.right = parent.right.left;
          return returnValue;
      } else {
          return findLargestChild(parent.right);
      }
  }
  /**
   * Removes target from tree.
   * @param target Item to be removed
   * @return true if the object was in the tree, false otherwise
   * @post target is not in the tree
   * @throws ClassCastException if target is not Comparable
   */
  public boolean remove(E target) {
      return delete(target) != null;
  }
}


class BabyNames {
  BinarySearchTree<String> maleNames;
  BinarySearchTree<String> femaleNames;
  TreeMap <String, Integer> names;

  // --------------------------------------------

  public BabyNames() {
    maleNames = new BinarySearchTree<String>();
    femaleNames = new BinarySearchTree<String>();
    names = new TreeMap <String, Integer>();
  }

  void AddSuggestion(String babyName, int genderSuitability) {
    if (genderSuitability == 1) {
      maleNames.add(babyName);
    } else if (genderSuitability == 2) {
      femaleNames.add(babyName);
    }
    names.put(babyName, genderSuitability);
  }

  void RemoveSuggestion(String babyName) {
    int genderSuitability = names.get(babyName);
    if (genderSuitability == 1) {
      maleNames.delete(babyName);
    } else if (genderSuitability == 2) {
      femaleNames.delete(babyName);
    }
  }

  int Query(String START, String END, int genderPreference) {
    int total = 0;
    if (genderPreference == 1 || genderPreference == 0) {
      total += maleNames.find(END, false) - maleNames.find(START, true) + 1;
    }

    if (genderPreference == 2 || genderPreference == 0) {
      total += femaleNames.find(END, false) - femaleNames.find(START, true) + 1;
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
