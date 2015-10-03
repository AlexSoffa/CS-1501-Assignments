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
        for(int i = 0; i < string.length - 1; i++)
        {
            if(iterator.value == string[i])
            {
                if(i == (string.length - 1) && iterator.isWord == true)
                    found = true;
                else 
                    iterator = iterator.childNode;
            }
            else if(iterator.value != string[i])
            {
                if(iterator.value == ' ')
                    found = false;
                else
                {
                    iterator = iterator.siblingNode;
                    i--;
                }
            }     
        }
        return found;
    }
    public void exhaustiveSearch()
    {
        Node n = root;
        int i = 0;
        char [] e = new char[5];
        exhaustiveSearch(n, i, i, e, i);
    }
    private void exhaustiveSearch(Node curr, int childIndex, int sibIndex, char [] w, int firstBranch)
    {
        w[childIndex] = curr.value;
        if(curr.isWord)
            for(int i = 0; i < childIndex + 1; i++)
                System.out.print(w[i]);
        System.out.println();
        if(curr.hasChild(curr))
        {
            exhaustiveSearch(curr.childNode, ++childIndex, sibIndex, w, firstBranch);
        }
        if(firstBranch == 0)
            firstBranch = childIndex;   
        if(childIndex != firstBranch)
        {
            childIndex = childIndex - 1;
        }
        if(curr.hasSibling(curr))
        {       
            exhaustiveSearch(curr.siblingNode, childIndex, ++sibIndex, w, firstBranch);
        }
        if(childIndex == firstBranch)
        {
            childIndex = firstBranch - 1;
            sibIndex = 0;
            firstBranch = 0;
        }        
    }
}