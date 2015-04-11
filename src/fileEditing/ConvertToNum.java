package fileEditing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConvertToNum {

	public static void main(String[] args) {
		action();
	}

	public static void action(){
		try{
			String currLine;
			BufferedReader br = new  BufferedReader(new FileReader("file.txt"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("out.txt"), "utf-8"));
			while((currLine = br.readLine())!=null){
				bw.write(currLine + ":"+convertToNum(currLine)+"\n");
			}
			br.close();
			bw.close();
			System.out.println("Done");
		}
		catch(IOException e){
			System.out.println("Only alphabets are allowed");
			e.printStackTrace();
		}
	}
	//T9 alphabet to num conversion
	public static int getNumForChar(char ch){
		if(ch>='A' && ch<='C')
			return 2;
		else if(ch>='D' && ch<='F')
			return 3;
		else if(ch>='G' && ch<='I')
			return 4;
		else if(ch>='J' && ch<='L')
			return 5;
		else if(ch>='M' && ch<='O')
			return 6;
		else if(ch>='P' && ch<='S')
			return 7;
		else if(ch>='T' && ch<='V')
			return 8;
		else if(ch>='W' && ch<='Z')
			return 9;
		else
			throw new IllegalArgumentException();
	}
	//String to T9 number conversion
	public static String convertToNum(String s){
		s = s.trim().toUpperCase();
		StringBuffer num = new StringBuffer();
		for(char ch: s.toCharArray()){
			num.append(getNumForChar(ch));
		}
		return num.toString();
	}
}
