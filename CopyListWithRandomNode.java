package amazonQuestionFromLC;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomNode {
	class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	}

	/*
	 * Edition 1: recursion
	 * Algorithm: 
	 * 	The nature of this problem is to deep copy a graph, so we can use solve it in a 
	 * depth first manner. But we need to record the current node that we have seen so that
	 * we can avoid duplication when doing copy. and we can use recursive rule such that:
	 * 		cloned_node.next = copyListNode(cur.next);
	 * 		cloned_node.random = copyListNode(cur.random);
	 * 
	 * T: O(N) N is the number of nodes
	 * S: O(N) N is the data structure that we use to record visited nodes.
	 * */
	// use a map to record the the node that we have already seen to avoid
	// duplication
	Map<RandomListNode, RandomListNode> map = new HashMap<>();

	public RandomListNode copyRandomList(RandomListNode head) {
		// base case
		if (head == null) {
			return null;
		}
		if (this.map.containsKey(head)) {
			return this.map.get(head);
		}

		// else : we make a new copy of the current node
		RandomListNode node = new RandomListNode(head.label);
		this.map.put(head, node);
		node.next = copyRandomList(head.next);
		node.random = copyRandomList(head.random);
		return node;
	}
	
	/*
	 * Edition 2: Iterative
	 * Algorithm:
	 *   Just go as a normal linkedlist, but still we need to record the nodes that we
	 *   have already visited.
	 *   
	 * T: O(N)
	 * S: O(N)
	 * */
	public RandomListNode copyRandomListII(RandomListNode head) {
		if (head == null) {
			return null;
		}
		RandomListNode old = head;
		RandomListNode newOne = new RandomListNode(old.label);
		map.put(old, newOne);
		while (old != null) {
			newOne.random = getClone(old.random);
			newOne.next = getClone(old.next);
			old = old.next;
			newOne = newOne.next;
		}
		return map.get(head);
	}

	private RandomListNode getClone(RandomListNode node) {
		if (node != null) {
			if (map.containsKey(node)) {
				return map.get(node);
			} else {
				map.put(node, new RandomListNode(node.label));
				return map.get(node);
			}
		}
		return null;
	}
}
