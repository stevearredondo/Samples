package com.arredondo.samples.binarytree;

import junit.framework.TestCase;

public class BinaryTreeNodeTest extends TestCase
{
    private BinaryTreeNode<Integer> mNode;

    public void testInsert() throws Exception
    {
        mNode = new BinaryTreeNode<Integer>(9);
        mNode.insert(4);
        mNode.insert(3);
        mNode.insert(11);
        assertNotNull(mNode.getLeft());
        assertNotNull(mNode.getLeft().getLeft());
        assertNotNull(mNode.getRight());
        assertEquals(mNode.getRight().getValue(), (Integer) 11);
    }

    public void testDelete() throws Exception
    {
        mNode = new BinaryTreeNode<Integer>(8);
        mNode.insert(5);
        mNode.insert(10);
        mNode.insert(11);
        mNode.insert(9);
        mNode.insert(2);
        mNode.insert(1);
        mNode.insert(7);
        mNode = mNode.delete(10);
        assertFalse(mNode.contains(10));
    }

    public void testContains() throws Exception
    {
        mNode = new BinaryTreeNode<Integer>(8);
        mNode.insert(5);
        mNode.insert(10);
        mNode.insert(11);
        mNode.insert(9);
        mNode.insert(2);
        mNode.insert(1);
        mNode.insert(7);
        assertTrue(mNode.contains(9));
    }

    /**
     * The output of this set of commands will look like the following:
     * <pre>
     * `-- 8
     *     +-- 5
     *     |   +-- 2
     *     |   |   `--1
     *     |   `-- 7
     *     `-- 10
     *         +-- 9
     *         `-- 11
     * </pre>
     */
    public void testPrint() throws Exception
    {
        mNode = new BinaryTreeNode<Integer>(8);
        mNode.insert(5);
        mNode.insert(10);
        mNode.insert(11);
        mNode.insert(9);
        mNode.insert(2);
        mNode.insert(1);
        mNode.insert(7);
        mNode.print();
    }
}