import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

// Tri Tree Search 
public class TrieST<Value> {
	
	private static int R = 256; 
    private Node root;	
	private static class Node{
		Object val;
		Node[] next = new Node[R];
	}
    
	public Value get(String key){
		Node x =get(root,key,0);
		if (x == null) return null;
		return (Value)x.val;
	}
	
	private  Node get(Node root,String key,int d){
		if( root == null) return null;
		if( d == key.length()) return root;
		return get(root.next[key.charAt(d)],key,d+1);
	}
	
	private void put(String key,Value val){
		root = put(root,key,val,0);
	}
	
	private Node  put(Node x,String key,Value val,int d){
	
		if(x == null) x = new Node();
		if( d == key.length()) { x.val = val; return x;} 
		x.next[key.charAt(d)]=put(x.next[key.charAt(d)],key,val,d+1);
		return x;
		
	}
	
	public Iterable<String> keys(){
		return null;
	}
	
	public Iterable<String> keysWithPrefix(String pre){
		Queue<String> q = new ArrayBlockingQueue<String>(100);
		collect(get(root,pre,0) , pre , q);
		return q;
	}
	
	public void collect(Node x, String pre,Queue<String> q ){
		if( x == null) return ;
		if( x.val != null) q.add(pre);
		
		for(char c = 0;c<R;c++)
			collect(x.next[c] , pre+c ,q);
	}
	
	
}
