import java.util.LinkedList;
public class Graph {
    int E;
    int V;

    LinkedList<String> List = new LinkedList<String>();

    int table[][] = new int[100][100];

    int a[][] = new int[100][100];

    int path[][] = new int[100][100];

    public String getPath(int[][] path, int i, int j,int [][]table) {

        if (path[i][j] == -1 && table[i][j] != 0) {
            return " "+i+" "+j;
        } else if(path[i][j] != -1){
            int k = path[i][j];
            return getPath(path, i, k,table)+" "+getPath(path, k, j,table)+" ";
        }
        else {
            return " ";
        }
    }
    public void floyd(){
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    //a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
                    if(a[i][k] + a[k][j] < a[i][j]){
                        path[i][j] = k;
                        a[i][j] = a[i][k] + a[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                System.out.println(i + " " + j + ":" + a[i][j]);
            }
        }
    }
    public void init(){
        for(int i = 0;i<100;i++){
            for(int j = 0;j<100;j++){
                path[i][j] = -1;
                if(table[i][j] != 0){
                    a[i][j] = table[i][j];
                }
                else{
                    a[i][j] = Integer.MAX_VALUE >> 1;
                }
            }
        }
    }

    public int AddNode(String prev,String value){
        if(List.indexOf(value) == -1){
            List.add(value);
            if(V != 0){
                int indexP = List.indexOf(prev);
                int indexN = List.indexOf(value);
                table[indexP][indexN]++;
                E++;
            }
            V++;
        }
        else{
            if(V != 0){
                int indexP = List.indexOf(prev);
                int indexN = List.indexOf(value);
                table[indexP][indexN]++;
            }
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
