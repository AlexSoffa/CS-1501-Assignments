import java.util.Queue;

/**
 *
 * @author Alex Soffa
 */
public class DLB {
    public Node root;
    public Node iterator;
    int childCount;
    int siblingCount;
    public char [] m = new char[5];
    int c = 0;
    public DLB(){
        root = new Node(' ');
    }
    public void insert(String s){
        char [] string = s.toCharArray();
        iterator = root;
        for(int i = 0; i < string.length; i++)
        {
            if(iterator.value == string[i])
            {
                if(i == (string.length - 1))
                {
                    iterator.isWord = true;
                    i = string.length;
                }
                iterator = iterator.childNode;
            }
            else if(iterator.value != string[i])
            {
                if(iterator.value == ' ')
                {
                    iterator.value = string[i];
                    if(i == (string.length - 1))
                    {
                        iterator.isWord = true;
                        i = string.length;
                    }
                    iterator.childNode = new Node(' ');
                    iterator.siblingNode = new Node(' ');
                    iterator = iterator.childNode;
                }
                else if(iterator.value != ' ')
                {
                    iterator = iterator.siblingNode;
                    i--;
                }
            }
        }
    }
    public boolean search(String s)
    {
        char [] string = s.toCharArray();
        iterator = root;
        boolean found = false;
        for(int i = 0; i < string.length; i++)
        {
            if(iterator.value == string[i])
            {
                if(i == (string.length - 1))
                    found = true;
                else 
                    iterator = iterator.childNode;
            }
            else if(iterator.value != string[i])
            {
                if(iterator.value == ' ')
                    found = false;
                else
                    iterator = iterator.siblingNode;
                i--;
            }     
        }
        return found;
    }
    public void exhaustiveSearch()
    {
        Node n = root;
        int i = 0;
        exhaustiveSearch(n, i);
    }
    private void exhaustiveSearch(Node curr, int index)
    {
        m[index] = curr.value;
        index = index + 1;
        if(curr.hasChild(curr))
        {
            exhaustiveSearch(curr.childNode, index);
        }
        if(curr.hasSibling(curr))
        {
            exhaustiveSearch(curr.siblingNode, index);
        }
    }
}