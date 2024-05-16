import java.util.LinkedList;
public class Graph {
    int E;
    int V;

    LinkedList<String> List = new LinkedList<String>();

    int table[][] = new int[100][100];

    public int AddNode(String prev,String value){
        if(List.indexOf(value) == -1){
            List.add(value);
            if(V != 0){
                int indexP = List.indexOf(prev);
                int indexN = List.indexOf(value);
                table[indexP][indexN]++;
            }
            V++;
            E++;
        }
        return 0;
    }
}
