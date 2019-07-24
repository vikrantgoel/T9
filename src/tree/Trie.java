package tree;

import java.io.BufferedReader;
import java.io.FileReader;

public class Trie {

	public TrieNode root;
	public Trie() {
		root = new TrieNode('\0', false);
	}
	public Trie(String path){
		root = new TrieNode('\0', false);
		populate(path);
	} 
	public boolean populate(String path){
		try{
			String currLine;
			BufferedReader br = new  BufferedReader(new FileReader(path));
			while((currLine = br.readLine())!=null){
				add(currLine);
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public void add(String w){
		w = w.toUpperCase().trim();
		int l = w.length();
		char[] word = w.toCharArray();
		TrieNode currNode = root;
		for(int i=0;i<l;i++){
			if(currNode.links[word[i]-65] == null){
				currNode.links[word[i]-65] = new TrieNode(word[i], i==l-1 ? true : false);
			}
			currNode = currNode.links[word[i]-65];
		}
		if (currNode != null && !currNode.fullWord)
			currNode.fullWord = true;
	}
	public boolean find(String w){
		return find(w,root);
	}
	public boolean find(char c){
		return find(Character.toString(c));
	}
	public boolean find(char c, TrieNode startNode){
		return find(Character.toString(c), startNode);
	}
	public TrieNode getNode(char w){
		return getNode(w,root);
	}
	public TrieNode getNode(char ch, TrieNode startNode){
		String w = Character.toString(ch);
		char[] word = w.toUpperCase().toCharArray();
		int l = w.length();
		TrieNode currNode = startNode;
		for(int i=0;i<l;i++){
			if(currNode == null)
				return null;
			currNode = currNode.links[word[i]-65];
		}
		if(currNode==null)
			return null;
		return currNode;
	}
	public boolean find(String w, TrieNode startNode){
		char[] word = w.toUpperCase().toCharArray();
		int l = w.length();
		TrieNode currNode = startNode;
		for(int i=0;i<l;i++){
			if(currNode == null)
				return false;
			currNode = currNode.links[word[i]-65];
		}
//		if(currNode==null || (currNode!=null && !currNode.fullWord))
		if(currNode==null)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Trie t = new Trie();
		t.add("AA");
		t.add("BB");
		System.out.println(t.find("vi"));
		System.out.println(t.find("vik"));
	}
}