package com.arredondo.samples.structures.binarytree;
/* Created by Stephen on 12/9/2014. */

/**
 * This class represents a binary tree node with value of type {@link T}. The
 * {@link #insert(Comparable)} and {@link #delete(Comparable)} methods rely on {@link T}'s
 * implementation of {@link java.lang.Comparable}. The fields for node value, and left and right
 * child nodes, are all encapsulated with appropriate accessor methods.
 * @param <T>    Base type of the node that implements {@link java.lang.Comparable}
 */
public class BinaryTreeNode<T extends Comparable<T>>
{
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    private T value;

    /**
     * Constructor.
     * @param value    Non-null value to give this node
     */
    protected BinaryTreeNode(T value)
    {
        if (value==null) throw new IllegalArgumentException("Value must be non-null");
        this.value = value;
    }

    /**
     * @return The left child of this node
     */
    public BinaryTreeNode<T> getLeft()
    {
        return this.left;
    }

    /**
     * @return The right child of this node
     */
    public BinaryTreeNode<T> getRight()
    {
        return this.right;
    }

    /**
     * @return The value of this node.
     */
    public T getValue()
    {
        return value;
    }

    /**
     * Adds a node of the given {@code value} to this subtree, with the
     * convention of placing values less than this node's to the
     * left, and values greater than this node's to the right. If the
     * node on the appropriate side is unoccupied, a new node is created
     * there with the given value. If the child node is occupied, this
     * method is called on that node with the same argument. The
     * terminating behavior of this method should be with the creation
     * of a new node with the given value.
     * @param value    Non-null value to add to this node
     * @throws java.lang.RuntimeException if argument is null or if a node with the given value
     * already exists in the tree
     */
    protected void insert(T value)
    {
        //Don't allow attempts to insert null values
        if (value==null) throw nullArg();

        int comp = value.compareTo(this.value);
        if (comp < 0) //input less or equal to than this node's value
        {
            if (left==null)
                left = new BinaryTreeNode<T>(value); //create new node on left. Done
            else
                left.insert(value); //left node is occupied. Call this method there
        }
        else if (comp > 0) //input greater than this node's value
        {
            if (right == null)
                right = new BinaryTreeNode<T>(value); //create new node on right. Done
            else
                right.insert(value); //right node is occupied. Call this method there
        }
        else //input equals this node's value--not allowed
        {
            throw new RuntimeException("Node with input value already exists in tree: "+value.toString());
        }
    }

    /**
     * Attempts to delete the node from the subtree of this node matching the input value (that is,
     * a value for which {@link java.lang.Comparable#compareTo(Object)}=0). If no node has a value
     * matching the argument value, nothing happens.
     * @param value    The value to attempt to delete from this node's subtree
     * @return The node to replace this one.
     * @throws java.lang.RuntimeException if argument is null or the given value could not be
     * found in the subtree
     */
    protected BinaryTreeNode<T> delete(T value)
    {
        if (value==null) throw nullArg();

        /*Compare the input value to this node's value*/
        int comp = value.compareTo(this.value);

        if (comp > 0) //search right
        {
            /*
            * Check that the required node exists. If not, throw an exception indicating
            * that the value could not be found.
            */
            if (right==null) throw noSuchValue(value);
            right = right.delete(value);
            /*Return this node now that the appropriate child node has been modified*/
            return this;
        }
        else if (comp < 0) //search left
        {
            if (left==null) throw noSuchValue(value);
            left = left.delete(value);
            return this;
        }
        else // comp == 0. This is the node to be deleted (eventually get to this point)
        {
            if (left==null && right==null) //No child nodes. Can delete without re-balancing
            {
                return null;
            }
            else if (left==null) //This only has a right child. Replace this node with right child
            {
                return right;
            }
            else if (right==null) //This only has a left child. Replace this node with left child
            {
                return left;
            }
            else //This has both left and right children.
            {
                /*
                * Replace this node's value with that of the right-most child of its left subtree
                * (its "predecessor"). This node is guaranteed to have at most a left child, and
                * therefore can easily be deleted.
                */
                T predecessorValue = left.getMax();
                this.value = predecessorValue;
                /* Delete the predecessor node from the left subtree. If the predecessor has a left
                * child, any re-balancing will be taken care of. Otherwise, deletion will be trivial.*/
                left = left.delete(predecessorValue);
                return this;
            }
        }
    }

    /**
     * Searches for a match to {@code value} in the subtree of the calling node.
     * @param value    The value to search for in the subtree of this node
     * @return True if the argument matches the value of the calling node or any of its child nodes.
     * False otherwise.
     */
    protected boolean contains(T value)
    {
        int comp = value.compareTo(this.value);
        if (comp < 0)
            return left!=null && left.contains(value);
        else if (comp > 0)
            return right!=null && right.contains(value);
        else //comp == 0
            return true;
    }

    /**
     * Computes the depth of the subtree of this node by computing the max of the depths of its
     * left and right child nodes, plus 1.
     * @return The depth of this node's subtree, always at least 1.
     */
    protected int depth()
    {
        int lDepth = 0, rDepth = 0;
        if (left!=null) lDepth = left.depth();
        if (right!=null) rDepth = right.depth();
        return 1 + (lDepth >= rDepth ? lDepth : rDepth);
    }

    /**
     * Prints the subtree of this node horizontally. The nodes are displayed using the
     * {@link Object#toString()} method on the node's {@linkplain #getValue() value parameter}.
     */
    protected void print()
    {
        print("",true);
    }

    private static final String TAIL  = "`-- ";
    private static final String SPLIT = "+-- ";
    private static final String STEM  = "|   ";
    private static final String SPACE = "    ";
    /**
     * Prints the subtree of this tree node horizontally, where left child nodes are displayed
     * on top of right child nodes.
     * @param prefix    Initial spaces or characters needed to correctly format the layout.
     * @param isTail    Whether the piece being printed is a "tail" ({@value #TAIL}), or
     *                  a "split" ({@value #SPLIT}).
     */
    private void print(String prefix, boolean isTail)
    {
        System.out.println(prefix + (isTail ? TAIL : SPLIT) + value.toString());
        if (left==null && right==null) return; //nothing to do if no children.
        if (left!=null && right!=null) //has both children.
        {
            left.print(prefix + (isTail ? SPACE : STEM), false); //Place left child on top at middle of a split.
            right.print(prefix + (isTail ? SPACE : STEM), true); //Place right on bottom at end of a tail.
        }
        else if (left==null) //Right child only. Place at end of a tail.
            right.print(prefix + (isTail ? SPACE : STEM), true);
        else //Left child only. Place at end of a tail.
            left.print(prefix + (isTail ? SPACE : STEM), true);
    }

    /**
     * Starting with the calling node, this method searches for the furthest-right child.
     * @return Either the calling node, or a node with at most a left child and no right child.
     */
    private T getMax()
    {
        BinaryTreeNode<T> resultNode = this;
        while (resultNode.getRight()!=null)
        {
            resultNode = resultNode.getRight();
        }
        return resultNode.getValue();
    }

    /**
     * Helper method for when arguments (e.g. values or nodes) are null.
     */
    private static IllegalArgumentException nullArg()
    {
        return new IllegalArgumentException("Argument must be non-null");
    }

    /**
     * Helper method for when a given value cannot be found in the tree.
     */
    private static <T extends Comparable<T>> RuntimeException noSuchValue(T value)
    {
        return new RuntimeException("Could not find value: "+value.toString());
    }

}
