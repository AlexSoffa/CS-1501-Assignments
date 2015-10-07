/*************************************************************************
 *  Compilation: 
javac LZW.java
 *  Execution:    java LZW - < input.txt   (compress)
 *  
Execution:    java LZW + < input.txt   (expand)
 *  
Dependencies: BinaryIn.java BinaryOut.java
 *
 *  
Compress or expand binary input from standard input using LZW.
 *
 *  
WARNING: STARTING WITH ORACLE JAVA 6, UPDATE 7 the SUBSTRING
 *  
METHOD TAKES TIME AND SPACE LINEAR IN THE SIZE OF THE EXTRACTED
 *  
SUBSTRING (INSTEAD OF CONSTANT SPACE AND TIME AS IN EARLIER
 *  IMPLEMENTATIONS).
 *
 *  
See <a href = "http://java-performance.info/changes-to-string-java-1-7-0_06/">this article</a>
 *  
for more details.
 
*
 *************************************************************************/


import java.util.Arrays;

public class myLZW 
{
    
	private static final int R = 256;        // number of input chars   
	private static final int L = 512;        // number of codewords = 2^9
	private static final int mL = 65536;     // max codewords = 2^16   
	private static final int W = 9;          // codeword width
	private static final int mW = 16;         // max codeword width
 
	public static void compress(String mode) 
	{   	
		int l = L;
		int w = W;
		String input = BinaryStdIn.readString();
		TST<Integer> st = new TST<Integer>();
		
		for (int i = 0; i < R; i++)  
			st.put("" + (char) i, i);
        
		int code = R+1;  // R is codeword for EOF
		
		while (input.length() > 0) 
		{	
			String s = st.longestPrefixOf(input);  // Find max prefix match s.
			BinaryStdOut.write(st.get(s), w);      // Print s's encoding.
			int t = s.length();
			if (t < input.length() && code < l)
			{						     
				st.put(input.substring(0, t + 1), code++);
			}
			if (code == l && w < 16)
			{
				w = w + 1;
				l = (int) Math.pow(2, w);
				st.put(input.substring(0, t + 1), code++);
			}
			if (code == l && w == 16 && mode.equals("r"))
			{
				st = new TST<Integer>();
				for (int i = 0; i < R; i++)
					st.put("" + (char) i, i);
				code = R+1;
				l = L;
				w = W;
			}
			input = input.substring(t);            // Scan past s in input.
		}
		BinaryStdOut.write(R, w);
		BinaryStdOut.close(); 
	} 
	
	public static void expand(String mode) 
	{
		int l = L;
		int w = W; 
		String s;
		String val;
		int codeword;
		String[] st = new String[mL];
		int i; // next available codeword value

	  	// initialize symbol table with all 1-character strings
		for (i = 0; i < R; i++)
			st[i] = "" + (char) i;
		st[i++] = "";                        // (unused) lookahead for EOF
		codeword = BinaryStdIn.readInt(w);
		if (codeword == R) return;           // expanded message is empty string
		val = st[codeword];			 // gets first ascii character
		while (true) 
		{
			BinaryStdOut.write(val);				// Writes first and subsequent chars
			codeword = BinaryStdIn.readInt(w); // reads next encoded int of bit-width w;
			if (codeword == R) 
				break;
			s = st[codeword];		   //gets ascii char
			if (i == codeword) 
				s = val + val.charAt(0);		// special case hack									
			if (i < l - 1)
			{
				st[i++] = val + s.charAt(0);
			}
			if (i == l - 1 && w < 16)
			{
				w = w + 1;
				l = (int) Math.pow(2, w);
				st[i++] = val + s.charAt(0);
			}
			if (i == l - 1 && w == 16 && mode.equals("r"))
			{
				st = new String[mL];
				l = L;
				w = W;
				for(i = 0; i < R; i++)
					st[i] = "" + (char) i;
				st[i++] = "";
				codeword = BinaryStdIn.readInt(w);
				if (codeword == R)
					return;
				val = st[codeword];
				continue;
			}
			val = s;
		}
		BinaryStdOut.close();
	}

   	public static void main(String[] args) 
   	{
		if(args[0].equals("-")) compress(args[1]);
		else if (args[0].equals("+")) expand(args[1]);
		else throw new IllegalArgumentException("Illegal command line argument");
    
   	}


}
