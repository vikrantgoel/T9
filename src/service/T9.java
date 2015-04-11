package service;

import java.util.ArrayList;
import java.util.Scanner;

import tree.Trie;
import tree.TrieNode;

public class T9 { 

	public Trie trie;

	public T9(Trie t){
		trie=t;
	}
	public T9(String path){
		trie = new Trie(path);
	}
	public int reverseNum(int n){
		int num=0;
		while(n!= 0){
			num = num * 10;
			num = num + n%10;
			n = n/10;
		}
		return num;
	}
	public ArrayList<String> getWords(int n){
		n = reverseNum(n);
		int num=0;

		ArrayList<tempNode> currNodes=new ArrayList<tempNode>();
		while(n!=0){
			num=n%10;
			n=n/10;

			if(currNodes.size()==0){
				for(char ch: getCharsForNumber(num)){
					if(trie.find(ch)){
						currNodes.add(new tempNode(Character.toString(ch), trie.getNode(ch)));
					}
				}
			}
			else{
				currNodes = getNodes(currNodes,num);
				if(currNodes.size()==0)
					break;
			}
		}
		currNodes=checkForFullWords(currNodes);
		ArrayList<String> finalStrings = getFinalStrings(currNodes);
		return finalStrings;
	}
	public ArrayList<tempNode> checkForFullWords(ArrayList<tempNode> currNodes){
		ArrayList<tempNode> t = new ArrayList<tempNode>();
		for(tempNode tn : currNodes){
			if(tn.node.fullWord)
				t.add(tn);
		}
		if(t.size()==0)
			return currNodes;
		return t;
	}
	public ArrayList<String> getFinalStrings(ArrayList<tempNode> currNodes){
		ArrayList<String> strings = new ArrayList<String>();
		for(tempNode t:currNodes){
			strings.add(t.word);
		}
		return strings;
	}
	public char[] getCharsForNumber(int n){
		switch(n){
		case 2: return new char[]{'a','b','c'};
		case 3: return new char[]{'d','e','f'};
		case 4: return new char[]{'g','h','i'};
		case 5: return new char[]{'j','k','l'};
		case 6: return new char[]{'m','n','o'};
		case 7: return new char[]{'p','q','r','s'};
		case 8: return new char[]{'t','u','v'};
		case 9: return new char[]{'w','x','y','z'};
		default: 
			throw new IllegalArgumentException("Zero not permitted");
		}
	}
	public ArrayList<tempNode> getNodes(ArrayList<tempNode> currNodes, int num){
		ArrayList<tempNode> newNodes = new ArrayList<tempNode>();
		for(tempNode t: currNodes){
			for(char ch:getCharsForNumber(num)){
				if(trie.find(ch, t.node)){
					newNodes.add(new tempNode(t.word+Character.toString(ch), trie.getNode(ch, t.node)));
				}
			}
		}
		return newNodes;
	}

	public static void main(String [] args) {
		T9 t9 = new T9("file.txt");
		
		Scanner sc = new Scanner(System.in);
		int num=0;
		try{
		while(true){
			System.out.println("Enter the number for T9 prediction, type EXIT to quit");
			num = sc.nextInt();
			System.out.println(t9.getWords(num));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

class tempNode {
	public String word;
	public TrieNode node;
	public tempNode(String w, TrieNode n){
		word=w;
		node=n;
	}
}