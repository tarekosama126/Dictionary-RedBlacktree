package Red_Black;


public class Node {
	public static final int red=1;
	public static final int black=0;
	public static final Node nil = new Node();
	
	
	String data = "555";
	int color;
	Node left;
	Node right;
	Node parent;
	
	public Node(String data) {
		this.data = data ;
		this.color = red;
		this.left=nil;
		this.right=nil;
		this.parent=nil;
	}
	public Node() {
		this.data ="555";
		this.color = black;
		this.left=nil;
		this.right=nil;
		this.parent=nil;
	}

	
}
