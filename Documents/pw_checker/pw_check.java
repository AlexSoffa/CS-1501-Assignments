import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Alex Soffa
 */
public class pw_check{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        String answer = args[0];
        Scanner in = new Scanner(System.in);
        String s;
        DLB dictionaryWords;
        DLB goodWords;
        boolean found = false;
        int count = 0;
        if(answer.equalsIgnoreCase("-g"))
        {
            System.out.println("Starting dictionary");
            dictionaryWords = makeDictionary();
            System.out.println("Finished dictionary");
            goodWords = makeGoodWords(dictionaryWords);
        }
        else if(!answer.equalsIgnoreCase("-g"))
        {
            dictionaryWords = makeDictionary();
            makeGoodWords(dictionaryWords);
            while(count == 0)
            {
                System.out.println("Enter a password of 5 character length: ");
                s = in.nextLine();
                if(s.length() > 5)
                    continue;
                found = dictionaryWords.search(s);
                if(found)
                {
                    System.out.println("That is a bad password");
                    System.out.println("Here's some passwords that work: ");
                }
                else
                {
                    System.out.println("Nice password!");
                }
                count = 1;
            }
        }
        
                    
    }
    public static DLB makeDictionary()
    {
        char currChar;
        char [] converted = {'7', '4', '0', '3', '1', '1', '5'};
        char [] conversions = {'t', 'a', 'o', 'e', 'i', 'l', 's'};
        StringBuilder word = new StringBuilder();
        DLB dictionary = new DLB();
        dictionary.iterator = dictionary.root;
        File dictFile = new File("C:\\Users\\Alex Soffa\\Documents\\NetBeansProjects\\pwChecker\\src\\pwchecker\\Dictionary.txt");
        BufferedReader read = null;
        try
        {
            read = new BufferedReader(new FileReader(dictFile));
            String dictWord = null;
            while((dictWord = read.readLine()) != null && dictWord.length() < 6)
            {
                StringBuilder cWord = new StringBuilder(dictWord);
                System.out.println(dictWord);
                dictionary.insert(dictWord);
                char [] convert = dictWord.toCharArray();
                for(int i = 0; i < convert.length; i++)
                    for(int j = 0; j < conversions.length; j++)
                    {
                        if(convert[i] == conversions[j])
                        {
                            convert[i] = converted[j];
                            cWord.setCharAt(i, convert[i]);
                            dictWord = cWord.toString();
                            dictionary.insert(dictWord);
                            System.out.println(dictWord);
                        }
                    }
                
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error in opening File");
        }
        catch(IOException e)
        {
            System.out.println("Error inputting/outputting File");
        }
        finally
        {
            try{
                if(read != null)
                    read.close();
            }
            catch(Exception e)
            {
                System.out.println("Error closing File");
            }
        }
        //dictionary.exhaustiveSearch();
        return dictionary;
    }
    public static DLB makeGoodWords(DLB dictionary)
    {
        int charCount;
        int numCount;
        int symbolCount;
        StringBuilder word = new StringBuilder();
        DLB passwords = new DLB();
        char currChar;
        char c;
        boolean found = false;
        for(int i = 33; i < 123; i++)
        {
            charCount = 0;
            numCount = 0;
            symbolCount = 0;
            found = false;
            if(i == 33 | i == 36 | i == 37 | i == 38 | i == 42 | i == 64 | (i > 47 && i < 49) | (i > 49 && i < 52) | (i > 52 && i < 58) | (i > 97 && i < 105) | (i > 105 && i < 123))
            {
                if(i == 33 | i == 36 | i == 37 | i == 38 | i == 42 | i == 64)
                    symbolCount++;
                else if((i > 47 && i < 49) | (i > 49 && i < 52) | (i > 52 && i < 58))
                    numCount++;
                else if((i > 97 && i < 105) | (i > 105 && i < 123))
                    charCount++;
                word = new StringBuilder();
                currChar = (char) i;
                word.append(currChar);
                for(int j = 33; j < 123; j++)
                {
                    if(j == 33 | j == 36 | j == 37 | j == 38 | j == 42 | j == 64 | (j > 47 && j < 49) | (j > 49 && j < 52) | (j > 52 && j < 58) | (j > 97 && j < 105) | (j > 105 && j < 123))
                    {
                        if(j == 33 | j == 36 | j == 37 | j == 38 | j == 42 | j == 64)
                            symbolCount++;
                        else if((j > 47 && j < 49) | (j > 49 && j < 52) | (j > 52 && j < 58))
                            numCount++;
                        else if((j > 97 && j < 105) | (j > 105 && j < 123))
                            charCount++;
                        currChar = (char) j;
                        word.append(currChar);
                        for(int check = 0; check < word.length(); check++)
                        {
                            if(dictionary.search(word.substring(check, (word.length()-1))) == true)
                            {
                                found = true;
                            }
                            if(dictionary.search(word.substring(0, check)) == true)
                            {
                                found = true;
                            }
                        }
                        for(int k = 33; k < 123; k++)
                        {
                            if(k == 33 | k == 36 | k == 37 | k == 38 | k == 42 | k == 64 | (k > 47 && k < 49) | (k > 49 && k < 52) | (k > 52 && k < 58) | (k > 97 && k < 105) | (k > 105 && k < 123))
                            {
                                if(k == 33 | k == 36 | k == 37 | k == 38 | k == 42 | k == 64)
                                {
                                    if(symbolCount == 2)
                                    {
                                        continue;
                                    }
                                    symbolCount++;
                                }
                                else if((k > 47 && k < 49) | (k > 49 && k < 52) | (k > 52 && k < 58))
                                {
                                    if(numCount == 2)
                                    {
                                        continue;
                                    }
                                    numCount++;
                                }
                                else if((k > 97 && k < 105) | (k > 105 && k < 123))
                                    charCount++;
                                
                                currChar = (char) k;
                                word.append(currChar);
                                for(int check = 0; check < word.length(); check++)
                                {
                                    if(dictionary.search(word.substring(check, (word.length()-1))) == true)
                                    {
                                        found = true;
                                    }
                                    if(dictionary.search(word.substring(0, check)) == true)
                                    {
                                        found = true;
                                    }
                                }
                                for(int l = 33; l < 123; l++)
                                {
                                    if(l == 33 | l == 36 | l == 37 | l == 38 | l == 42 | l == 64 | (l > 47 && l < 49) | (l > 49 && l < 52) | (l > 52 && l < 58) | (l > 97 && l < 105) | (l > 105 && l < 123))
                                    {
                                        if(l == 33 | l == 36 | l == 37 | l == 38 | l == 42 | l == 64)
                                        {
                                            if(symbolCount == 2)
                                            {
                                                continue;
                                            }
                                            symbolCount++;
                                        }
                                        else if((l > 47 && l < 49) | (l > 49 && l < 52) | (l > 52 && l < 58))
                                        {
                                            if(numCount == 2)
                                            {
                                                continue;
                                            }
                                            numCount++;
                                        }
                                        else if((l > 97 && l < 105) | (l > 105 && l < 123))
                                        {
                                            if(charCount == 3)
                                            {
                                                continue;
                                            }
                                            charCount++;
                                        }
                                        currChar = (char) l;
                                        word.append(currChar);
                                        for(int check = 0; check < word.length(); check++)
                                        {
                                            if(dictionary.search(word.substring(check, (word.length()-1))) == true)
                                            {
                                                found = true;
                                            }
                                            if(dictionary.search(word.substring(0, check)) == true)
                                            {
                                                found = true;
                                            }
                                        }
                                        for(int m = 33; m < 123; m++)
                                        {
                                            if(m == 33 | m == 36 | m == 37 | m == 38 | m == 42 | m == 64 | (m > 47 && m < 49) | (m > 49 && m < 52) | (m > 52 && m < 58) | (m > 97 && m < 105) | (m > 105 && m < 123))
                                            {
                                                if(m == 33 | m == 36 | m == 37 | m == 38 | m == 42 | m == 64)
                                                {
                                                    if(symbolCount == 2)
                                                    {
                                                        continue;
                                                    }
                                                    symbolCount++;
                                                }
                                                else if((m > 47 && m < 49) | (m > 49 && m < 52) | (m > 52 && m < 58))
                                                {
                                                    if(numCount == 2)
                                                    {
                                                        continue;
                                                    }
                                                    numCount++;
                                                }
                                                else if((m > 97 && m < 105) | (m > 105 && m < 123))
                                                {
                                                    if(charCount == 3)
                                                    {
                                                        continue;
                                                    }
                                                    charCount++;
                                                }
                                                currChar = (char) m;
                                                word.append(currChar);
                                                for(int check = 0; check < word.length(); check++)
                                                {
                                                    if(dictionary.search(word.substring(check, (word.length()-1))) == true)
                                                    {
                                                        found = true;
                                                    }
                                                    if(dictionary.search(word.substring(0, check)) == true)
                                                    {
                                                        found = true;
                                                    }
                                                }
                                                if(found == false)
                                                {
                                                    System.out.println(word);
                                                    passwords.insert(word.toString());
                                                }
                                                word.deleteCharAt(4);
                                                if(m == 33 | m == 36 | m == 37 | m == 38 | m == 42 | m == 64)
                                                    symbolCount--;
                                                else if((m > 47 && m < 49) | (m > 49 && m < 52) | (m > 52 && m < 58))
                                                    numCount--;
                                                else if((m > 97 && m < 105) | (m > 105 && m < 123))
                                                    charCount--;
                                            }
                                        }
                                        if(l == 33 | l == 36 | l == 37 | l == 38 | l == 42 | l == 64)
                                            symbolCount--;
                                        else if((l > 47 && l < 49) | (l > 49 && l < 52) | (l > 52 && l < 58))
                                            numCount--;
                                        else if((l > 97 && l < 105) | (l > 105 && l < 123))
                                            charCount--;
                                        word.deleteCharAt(3);
                                    }
                                }
                                if(k == 33 | k == 36 | k == 37 | k == 38 | k == 42 | k == 64)
                                    symbolCount--;
                                else if((k > 47 && k < 49) | (k > 49 && k < 52) | (k > 52 && k < 58))
                                    numCount--;
                                else if((k > 97 && k < 105) | (k > 105 && k < 123))
                                    charCount--;
                                word.deleteCharAt(2);
                            }
                        }
                        word.deleteCharAt(1);
                        if(j == 33 | j == 36 | j == 37 | j == 38 | j == 42 | j == 64)
                            symbolCount--;
                        else if((j > 47 && j < 49) | (j > 49 && j < 52) | (j > 52 && j < 58))
                            numCount--;
                        else if((j > 97 && j < 105) | (j > 105 && j < 123))
                            charCount--;
                    }
                }
            }
        }
        return passwords;
    }
}