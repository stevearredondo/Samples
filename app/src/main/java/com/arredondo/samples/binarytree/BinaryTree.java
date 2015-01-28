package com.arredondo.samples.binarytree;

/* Created by Stephen on 12/9/2014. */
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
     * Calls {@link com.arredondo.samples.binarytree.BinaryTreeNode#insert(Comparable)}
     * on the root of this tree. If the root is null, the root is set to a new
     * instance of {@link com.arredondo.samples.binarytree.BinaryTreeNode} with the
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
     * {@link com.arredondo.samples.binarytree.BinaryTreeNode#delete(Comparable)}
     * on the root node of this tree and setting it to the returned (updated) node.
     * @param value    The value to attempt to delete from this tree
     * @throws java.lang.RuntimeException if the root of this tree is null
     */
    public void delete(T value) throws RuntimeException
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
    public boolean contains(T value) throws RuntimeException
    {
        if (root==null) throw nullRoot();
        return root.contains(value);
    }

    /**
     * Prints this tree horizontally by calling {@link BinaryTreeNode#print()} on the root.
     * @throws java.lang.RuntimeException if the root of this tree is null
     */
    public void print() throws RuntimeException
    {
        if (root==null) throw nullRoot();
        root.print();
    }

    private static RuntimeException nullRoot()
    {
        return new RuntimeException("Need to create a root first");
    }

}
