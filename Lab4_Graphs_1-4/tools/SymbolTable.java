//SymbolTable.java

/*
 * @author Jonatha Gustafson
 * 
 * This class is a simple implementation of a symbol table used to store strings.
 * The class allows for adding strings, and retrieving their index.
 * 
 */

package tools;

public class SymbolTable {
	
	private String[] symbols; 	//an array for the symbols
	private int n;				//number of elements
	
	//intitialize empty symbol table
	public SymbolTable(int size) {
		this.n = 0;
		symbols = new String[size];
	}
	
	//resize array to given size
	private void resize(int size) {
		
		if(size < n) return;
		
		String[] tempArray = new String[size];
		
		for(int i = 0; i < symbols.length; i++)
			tempArray[i] = symbols[i];
		
		symbols = tempArray;
	}

	public int size() {
		return n;
	}
	
	//adds string to the symbol table, rezies if full.
	public void add(String str) {
		if(n >= symbols.length)
			resize(n * 2);
		
		symbols[n] = str;
		n++;
	}
	
	//returns the symbol on given index
	public String get(int index) {
		return symbols[index];
	}
	
	//returns the index of given string, returns -1 if string doesn't exist
	public int getIndex(String str) {
		for(int i = 0; i < symbols.length; i++)
			if(symbols[i].equals(str))
				return i;
		
		return -1;
		
	}
	
	//returns true if string exists in the symbol table
	public boolean contains(String str) {
		for(int i = 0; i < n; i++)
			if(symbols[i].equals(str))
				return true;
		
		return false;
	}
	
}
