
public class SkipListNode {
	
	//Node Attributes
	private SkipListNode up = null;
	private SkipListNode down = null;
	private SkipListNode next = null;
	private SkipListNode prev = null;
	private int printPosition = 0;
	private Integer data;
	
	//Constructor
	SkipListNode(Integer data){
		setData(data);
	}

	//Getters and Setters
	public SkipListNode getUp() {
		return up;
	}

	public void setUp(SkipListNode up) {
		this.up = up;
	}

	public SkipListNode getDown() {
		return down;
	}

	public void setDown(SkipListNode down) {
		this.down = down;
	}

	public SkipListNode getNext() {
		return next;
	}

	public void setNext(SkipListNode next) {
		this.next = next;
	}

	public SkipListNode getPrev() {
		return prev;
	}

	public void setPrev(SkipListNode prev) {
		this.prev = prev;
	}

	public int getPrintPosition() {
		return printPosition;
	}

	public void setPrintPosition(int printPosition) {
		this.printPosition = printPosition;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}
	
	//Modifying toString method, we print null if the object is empty i.e. data is -1
	public String toString() {
		return (data==null ? "null" : Integer.toString(data));
	}
}
