import java.util.Random;

public class SkipList {
	private SkipListNode head;
	private SkipListNode tail;
	private int height;
	private int nodeCount;
	private int maxLevel;
	private Random rand;

	SkipList(int maxLevel) {
		nodeCount = 0;
		height = 0;
		this.maxLevel = maxLevel;
		rand = new Random();

		SkipListNode head = new SkipListNode(-1);
		SkipListNode tail = new SkipListNode(-1);

		head.setNext(tail);
		tail.setPrev(head);

		this.head = head;
		this.tail = tail;
	}

	private SkipListNode find(int data) {
		SkipListNode curPos = head;
		while (true) {
			while (!(curPos.getNext().toString().equals("null")) && curPos.getNext().getData() <= data)
				curPos = curPos.getNext();
			if (curPos.getDown() != null)
				curPos = curPos.getDown();
			else break;
		}
		return curPos;
	}

	public boolean insertNode(int data) {
		if (data < 0) {
			System.out.println("Data cannot be negative");
			return false;
		}
			
		SkipListNode currentPosition = find(data);
		if (!currentPosition.toString().equals("null") && currentPosition.getData() == data) {
			System.out.println("Duplicate value will not be inserted");
			return false;
		}

		SkipListNode node = new SkipListNode(data);
		node.setNext(currentPosition.getNext());
		node.setPrev(currentPosition);
		currentPosition.getNext().setPrev(node);
		currentPosition.setNext(node);

		int levelForCurrentNode = getLevel();
		if (levelForCurrentNode > 0)
			buildSkipList(node, currentPosition, levelForCurrentNode);
		nodeCount++;
		System.out.println("Data successfully inserted into skiplist");
		return true;

	}

	private void buildSkipList(SkipListNode currentPosition, SkipListNode previousPosition, int levelForCurrentNode) {
		int initHeight = height;
		int offset;
		if(levelForCurrentNode>=height) {
			buildEmptyLevel();
		}
		
		if (levelForCurrentNode < height)
			initHeight = 0;
		if (levelForCurrentNode > initHeight)
			offset = levelForCurrentNode - initHeight;
		else
			offset = 1;
		
		for (int i = 0; i < offset; i++) {
			while (previousPosition.getUp() == null)
				previousPosition = previousPosition.getPrev();
			previousPosition = previousPosition.getUp();

			
			SkipListNode towerNode = new SkipListNode(currentPosition.getData());
			towerNode.setPrev(previousPosition);
			towerNode.setNext(previousPosition.getNext());
			previousPosition.getNext().setPrev(towerNode);
			previousPosition.setNext(towerNode);
			towerNode.setDown(currentPosition);
			currentPosition.setUp(towerNode);
			currentPosition = towerNode;
		}

	}
	
	private void buildEmptyLevel() {
		SkipListNode tempHead = new SkipListNode(-1);
		SkipListNode tempTail = new SkipListNode(-1);
		tempHead.setNext(tempTail);
		tempTail.setPrev(tempHead);
		tempHead.setDown(head);
		tempTail.setDown(tail);
		head.setUp(tempHead);
		tail.setUp(tempTail);
		head = tempHead;
		tail = tempTail;
		height++;
	}

	public int getLevel() {
		int row = 0;
		// Return a certain random level
		while (rand.nextBoolean())
			row++;
		if (row >= maxLevel)
			row--;
		return row;
	}

	public boolean contains(int data) {
		if(data == find(data).getData()) {
			return true;
		}
		else return false;
	}
	
	public boolean deleteNode(int data) {
		if (!contains(data)) {
			System.out.println("Skiplist does not contain this data.");
			return false;
		}
		SkipListNode currentPosition = find(data);
		SkipListNode previousPosition = currentPosition.getPrev();
		
		while (true) {
			previousPosition.setNext(currentPosition.getNext());
			currentPosition.getNext().setPrev(previousPosition);
			currentPosition.setNext(null);
			currentPosition.setPrev(null);
			currentPosition.setUp(null);
			currentPosition.setDown(null);
			while (previousPosition.getUp() == null)
				previousPosition = previousPosition.getPrev();
			previousPosition = previousPosition.getUp();
			if (previousPosition == head && previousPosition.getNext() == tail)
				break;
			currentPosition = previousPosition.getNext();

			
		}
		currentPosition = null;
		nodeCount--;
		if ((head.getDown().getNext() == tail.getDown()))
			removeEmptyLevel();
		System.out.println("Data has been successfully removed from skiplist");
		return true;
	}

	private void removeEmptyLevel() {
		SkipListNode tempHead = head;
		SkipListNode tempTail = tail;
		head = head.getDown();
		tail = tail.getDown();
		head.setUp(null);
		tail.setUp(null);
		tempHead.setDown(null);
		tempTail.setDown(null);
		tempHead.setNext(null);
		tempTail.setPrev(null);
		tempHead = null;
		tempTail = null;
		
	}

	public void printList() {
		SkipListNode currentPosition = head;
		while (currentPosition.getDown() != null)
			currentPosition = currentPosition.getDown();
		int i = 0;
		while (currentPosition != null) {
			currentPosition.setPrintPosition(i++);
			currentPosition = currentPosition.getNext();
		}
		currentPosition = head;
		while (currentPosition != null) {
			printOneRow(currentPosition);
			currentPosition = currentPosition.getDown();
		}
		
	}

	private void printOneRow(SkipListNode currentPosition) {
		StringBuilder sb = new StringBuilder();
		SkipListNode walk = currentPosition;
		sb.append(walk.toString());
		walk = walk.getNext();
		int n = 0, counter;

		while (walk != null) {
			SkipListNode n_walk = walk;
			while (n_walk.getDown() != null)
				n_walk = n_walk.getDown();
			int max_pos = n_walk.getPrintPosition();

			sb.append(" ");
			for (counter = n + 1; counter < max_pos; counter++)
				sb.append(" ");
			sb.append(" " + walk.toString());
			counter = max_pos;
			walk = walk.getNext();
		}
		System.out.println(sb.toString());
		
	}

	public void findNode(int data) {
		SkipListNode node = find(data);
		if(data == node.getData()) {
			System.out.println("Key found in the skiplist");
			System.out.println("Previous Key = "+node.getPrev().getData());
			System.out.println("Next Key = "+node.getNext().getData());
		}
		else {
			System.out.println("Key not present");
		}
		
	}

}
