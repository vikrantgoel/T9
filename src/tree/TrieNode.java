package tree;

public class TrieNode {

	public char letter;
	public boolean fullWord;
	public TrieNode[] links;
	
	public TrieNode(char letter, boolean fullWord)
    {
        this.letter = letter;
        links = new TrieNode[26];
        this.fullWord = fullWord;
    }
}