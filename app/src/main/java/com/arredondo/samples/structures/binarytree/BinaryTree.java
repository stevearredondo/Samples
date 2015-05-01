package com.arredondo.samples.structures.binarytree;

/* Created by Stephen on 12/9/2014. */

/**
 * Top-level abstraction of {@link com.arredondo.samples.structures.binarytree.BinaryTreeNode}, where the client
 * has access to only the root node of this tree. All node insertion and deletion functions are
 * performed on the
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>>
{
    private BinaryTreeNode<T> root;

    public BinaryTree(T rootValue)
    {
        this.root = new BinaryTreeNode<T>(rootValue);
    }

    /**
     * @return The root node of this tree
     */
    public BinaryTreeNode<T> getRoot()
    {
        return root;
    }

    /**
     * Calls {@link com.arredondo.samples.structures.binarytree.BinaryTreeNode#insert(Comparable)}
     * on the root of this tree. If the root is null, the root is set to a new
     * instance of {@link com.arredondo.samples.structures.binarytree.BinaryTreeNode} with the
     * given value.
     * @param value    The value to insert into this tree.
     */
    public void insert(T value)
    {
        if (root!=null)
        {
            root.insert(value);
        }
        else
        {
            root = new BinaryTreeNode<T>(value);
        }
    }

    /**
     * Attempts to delete a node of value {@code value} from this tree by calling
     * {@link com.arredondo.samples.structures.binarytree.BinaryTreeNode#delete(Comparable)}
     * on the root node of this tree and setting it to the returned (updated) node.
     * @param value    The value to attempt to delete from this tree
     * @throws java.lang.RuntimeException if the root of this tree is null
     */
    public void delete(T value)
    {
        if (root==null) throw nullRoot();
        root = root.delete(value);
    }

    /**
     * Searches for {@code value} in this tree.
     * @param value    The value to search for in this tree
     * @return Whether if the argument exists in this tree
     * @throws java.lang.RuntimeException if the root of this tree is null
     */
    public boolean contains(T value)
    {
        if (root==null) throw nullRoot();
        return root.contains(value);
    }

    /**
     * Computes the depth of this tree by calling {@link BinaryTreeNode#depth()} on the root.
     * @return The depth of this tree (0 if this tree's root is null).
     */
    public int depth()
    {
        return root == null ? 0 : root.depth();
    }

    /**
     * Computes the diameter of this tree, i.e. the number of nodes on the longest path between
     * two leaves in the tree. This is given by the sum of the {@linkplain BinaryTreeNode#depth() depths}
     * of the left and right children of the root, minus 1.
     * @return The diameter of this tree (0 if this tree's root is null).
     */
    public int diameter()
    {
        if (root==null) return 0;
        int l = 0, r = 0;
        if (root.getLeft()!=null) l = root.getLeft().depth();
        if (root.getRight()!=null) r = root.getRight().depth();
        return l + r + 1;
    }

    /**
     * Prints this tree horizontally by calling {@link BinaryTreeNode#print()} on the root.
     * @throws java.lang.RuntimeException if the root of this tree is null
     */
    public void print()
    {
        if (root==null) throw nullRoot();
        root.print();
    }

    private static RuntimeException nullRoot()
    {
        return new RuntimeException("Null root node.");
    }

}
