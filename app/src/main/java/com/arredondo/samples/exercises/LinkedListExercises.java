package com.arredondo.samples.exercises;

import com.arredondo.samples.structures.linkedlist.ListNode;

import java.util.HashMap;
import java.util.Map;

/* Created by Stephen on 4/26/2015. */
public class LinkedListExercises
{
    /**
     * Performs a deep copy of the linked list starting with the node <var>head</var>
     * @param head    The head node of the linked list to copy
     * @param <T>     The base type of the list node
     * @return The head of the copy of the linked list.
     */
    public static <T> ListNode<T> deepCopy(ListNode<T> head)
    {
        /*First, scan list starting at head and copy node data as you go through.
        We need a mapping between originals and copies to keep track. */
        Map<ListNode<T>, ListNode<T>> map = new HashMap<ListNode<T>, ListNode<T>>();

        ListNode<T> current = head;
        while (current!=null)
        {
            map.put(current, new ListNode<T>(current.data, null, null));
            current = current.next; //update current node
        }

        /*Now scan list again and copy pointers from newly made node copies*/
        current=head;
        while (current!=null)
        {
            ListNode<T> copy = map.get(current);
            copy.next = map.get(current.next);
            copy.rand = map.get(current.rand);
            current = current.next;
        }

        return map.get(head);
    }

}
