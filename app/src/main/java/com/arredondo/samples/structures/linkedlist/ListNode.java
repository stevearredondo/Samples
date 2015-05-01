package com.arredondo.samples.structures.linkedlist;

/* Created by Stephen on 4/26/2015. */

/**
 * Represents a node in a linked list. The {@link #data} contained in the node
 * can be of some arbitrary type {@link T}. This node also contains pointers to
 * both the {@link #next next node} and {@link #rand another arbitrary node}.
 *
 * @param <T> The type of the node's data
 */
public class ListNode<T>
{
    public T data;
    public ListNode<T> next;
    public ListNode<T> rand;

    /**
     * Constructs a new node with the given data and node pointers
     * @param data    The data held by this node
     * @param next    A pointer to the next node in the list
     * @param rand    A pointer to some arbitrary node in the list
     */
    public ListNode(T data, ListNode<T> next, ListNode<T> rand)
    {
        this.data = data;
        this.next = next;
        this.rand = rand;
    }

    /**
     * Constructs a new node with the given data and null pointers for {@link #next} and {@link #rand}
     * @param data    The data held by this node
     */
    public ListNode(T data)
    {
        this(data, null, null);
    }

    public boolean hasNext()
    {
        return next!=null;
    }
}
