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

    public LinkedList<Integer> Bridge(String left,String right){
        LinkedList<Integer> result = new LinkedList<Integer>();
        int index_l = List.indexOf(left);
        int index_r = List.indexOf(right);

        if(index_l == -1 ||index_r == -1 ){
            result.add(-1);
            return result;
        }
        for(int i = 0;i<V;i++){
            if(table[index_l][i] != 0 && table[i][index_r] != 0){

                result.add(i);
            }
        }
        result.add(-2);
        return result;
    }
}
