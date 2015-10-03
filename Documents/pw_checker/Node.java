/**
 *
 * @author Alex Soffa
 */
public class Node {
    Node childNode;
    Node siblingNode;
    char value;
    boolean isWord = false;
    int childCount;
    int siblingCount;
    
    public Node(char c){
        this.value = c;
    }
    public boolean hasSibling(Node n)
    {
        if(n.siblingNode.value != ' ')
            return true;
        else
            return false;
    }
    public boolean hasChild(Node n)
    {
        if(n.childNode.value != ' ')
            return true;
        else 
            return false;
    }
}