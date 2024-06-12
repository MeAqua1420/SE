import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
public class Main {

    static Graph graph = new Graph();

    public static void getReady(){
        Scanner Input = new Scanner(System.in);
        System.out.println("请输入文件路径：");
        String Path = Input.nextLine();
        //System.out.println(Path);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(Path))){
            String line;
            String last = null;
            int count_line = 0;
            while ((line = bufferedReader.readLine()) != null) {
                // 将每行数据按空格分割
                count_line++;
                String[] data = line.split(" ");
                int i = 0;
                while(i< data.length){
                    if(i == 0){
                        if(count_line>1){
                            graph.AddNode(last,data[i]);
                        }
                        graph.AddNode(data[i],data[i]);
                    }
                    else {
                        graph.AddNode(data[i-1],data[i]);
                    }
                    i++;
                }
                last = data[i-1];
/*                System.out.println("num of Node = "+graph.V);
                  for(int k = 0;k<=20;k++){
                    for(int j = 0;j<=20;j++){
                        System.out.printf("%d ",graph.table[k][j]);
                    }
                    System.out.println(" ");
                }
*/          }
        }
        catch (IOException e){
            System.out.println("IOErr");
        }
        graph.init();
        graph.floyd();
    }
    public static boolean isEmptyOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static String calcShortestPath(String word1,String word2){
        String Path = graph.getPath(graph.path, graph.List.indexOf(word1),graph.List.indexOf(word2),graph.table );
        Path = Path.trim();
        String test1 = "";
        if(Objects.equals(word1, word2)){
            System.out.printf(" %s -> %s\n",word1,word2);
            test1 = " "+word1+" -> "+word2;
            return test1;
        }
        if(isEmptyOrWhitespace(Path)== true){
            System.out.printf(" %s -> %s have no road\n",word1,word2);
            test1 = " "+word1+" -> "+word2+" have no road";
            return test1;
        }
        else{
            String [] Path_S = Path.split("\\s+");

            for(int i = 0;i<Path_S.length-1;i++){
                if(Objects.equals(Path_S[i], Path_S[i + 1])){
                    i++;
                }
                System.out.printf(" %s ->",graph.List.get(Integer.valueOf(Path_S[i])));
                test1+=" ";
                test1+=graph.List.get(Integer.valueOf(Path_S[i]));
                test1+=" ->";
            }
            System.out.printf(" %s ",graph.List.get(Integer.valueOf(Path_S[Path_S.length-1])));
            test1+=" ";
            test1+=graph.List.get(Integer.valueOf(Path_S[Path_S.length-1]));
            test1+=" ";
            System.out.printf("\n");
            return test1;
        }
    }

    public static String queryBridgeWords(String left, String right){
        LinkedList<Integer> Bridge =  graph.Bridge(left,right);
        String test1;
        if(Bridge.get(0) == -1){
            System.out.println("No word1 or word2!");
            test1 = "No word1 or word2!";
        }
        else if(Bridge.get(0) == -2){
            System.out.println("No bridge word!");
            test1 = "No bridge word!";
        }
        else{
            int i = 0;
            System.out.printf("%s and %s bridge word are :",left,right);
            test1 = left+" and "+right+" bridge word are :";
            while(i<Bridge.size() && Bridge.get(i) != -2) {
                System.out.printf(" " + graph.List.get(Bridge.get(i)));
                test1+=" ";
                test1+=graph.List.get(Bridge.get(i));
                i++;
            }
        }
        return test1;
    }
    public static void generateNewText(String inputText){
        String[] newdata = inputText.split(" ");
        int wn;
        for(wn = 0; wn < newdata.length-1 ;wn++){
            LinkedList<Integer> Bridge =  graph.Bridge(newdata[wn],newdata[wn+1]);
            if (Bridge.get(0) == -2 ||Bridge.get(0) == -1) {
                System.out.printf(newdata[wn]+' ');
            }
            else{
                System.out.printf(newdata[wn]+' ');
                System.out.printf(graph.List.get(Bridge.get(0))+' ');
            }
        }
        System.out.printf(newdata[wn]);
    }
    public static void randomWalk() {
        int walktable[][] = new int[100][100];
        Random random = new Random();
        int rand = random.nextInt(graph.List.size());
        LinkedList<String> randstr = new LinkedList<String>();
        randstr.add(graph.List.get(rand));
        int flag = end(rand,graph);
        int randnext;
        while (flag !=0) {
            randnext = random.nextInt(flag);
            int num = 0;
            int next;
            for(next = 0;next<graph.List.size();next++){
                
                if (graph.table[rand][next] != 0) {
                    if (num == randnext) {
                        break;
                    }else{
                        num++;
                    }
                }
            }
            if (walktable[rand][next] != 1) {
            walktable[rand][next] = 1;
            randstr.add(graph.List.get(next));
            rand = next;
            flag =  end(rand,graph);  
            }else{
                randstr.add(graph.List.get(next));
                flag = 0;
            }
        }
        writeFile(randstr);

    }
    public static void showDirectedGraph(){
        JFrame frame = new JFrame("Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(4000, 4000);
        // 创建一个自定义的 DrawingExample 面板
        DrawingExample drawingPanel = new DrawingExample(graph);
        // 将面板添加到窗口中
        frame.add(drawingPanel);
        // 显示窗口
        frame.setVisible(true);
    }
    public static void writeFile(LinkedList<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            int i;
            for(i=0;i<list.size();i++){
                bw.write(list.get(i)+' ');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public static int end(int i ,Graph graph) {
        int flag = 0;
        int j;
        for(j = 0;j<graph.List.size();j++){
            if(graph.table[i][j]!=0)
            flag ++;
        }
        return flag;
    }
    public static void main(String[] args) {

        Scanner Input = new Scanner(System.in);
        System.out.println("请输入文件路径：");
        String Path = Input.nextLine();
        System.out.println(Path);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(Path))){
            String line;
            String last = null;
            int count_line = 0;
            while ((line = bufferedReader.readLine()) != null) {
                // 将每行数据按空格分割
                count_line++;
                String[] data = line.split(" ");
                int i = 0;
                while(i< data.length){
                    if(i == 0){
                        if(count_line>1){
                            graph.AddNode(last,data[i]);
                        }
                        graph.AddNode(data[i],data[i]);
                    }
                    else {
                        graph.AddNode(data[i-1],data[i]);
                    }
                    i++;
                }
                last = data[i-1];
/*                System.out.println("num of Node = "+graph.V);
                  for(int k = 0;k<=20;k++){
                    for(int j = 0;j<=20;j++){
                        System.out.printf("%d ",graph.table[k][j]);
                    }
                    System.out.println(" ");
                }
*/          }
        }
        catch (IOException e){
            System.out.println("IOErr");
        }
        graph.init();
        graph.floyd();
        do{
            System.out.println("请输入指令：");
            System.out.println("1：展示有向图");
            System.out.println("2：查询桥接词");
            System.out.println("3：根据桥接词生成新文本");
            System.out.println("4：计算最短路径");
            System.out.println("5：随机游走");
            System.out.println("-1：退出程序");
            int int_i = Input.nextInt();
            Input.nextLine();
            switch (int_i){
                case 1:
                    showDirectedGraph();
                    break;
                case 2:
                    System.out.println("查询桥接词，请输入两个单词");
                    String shortest = Input.nextLine();
                    String[] newdata = shortest.split(" ");
                    String hh = queryBridgeWords(newdata[0],newdata[1]);
                    System.out.println(hh);
                    break;
                case 3:
                    System.out.println("请输入待补全的句子：");
                    String newtxt = Input.nextLine();
                    generateNewText(newtxt);
                    break;
                case 4:
                    System.out.println("计算最短路径，请输入起点和终点");
                    String shortest2 = Input.nextLine();
                    String[] newdata2 = shortest2.split(" ");
                    if(newdata2.length == 2){
                        calcShortestPath(newdata2[0],newdata2[1]);
                    }
                    if(newdata2.length == 1){
                        for(int i = 0;i< graph.V;i++){
                            calcShortestPath(newdata2[0],graph.List.get(i));
                        }
                    }
                    break;
                case 5:
                    randomWalk();
                    break;
                case -1:
                    Input.close();
                    System.exit(0);
            }
        }while(true);


//        for(int k = 0;k<=20;k++){
//            for(int j = 0;j<=20;j++){
//                System.out.printf("%d ",graph.path[k][j]);
//            }
//            System.out.println(" ");
//        }
//


//        BridgeWord("To","out",graph);

/*         JFrame frame = new JFrame("Drawing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(4000, 4000);
        // 创建一个自定义的 DrawingExample 面板
        DrawingExample drawingPanel = new DrawingExample(graph);
        // 将面板添加到窗口中
        frame.add(drawingPanel);
        // 显示窗口
        frame.setVisible(true);
*/
//        System.out.println("请输入待补全的句子：");
//        String newtxt = Input.nextLine();
//        generateNewText(newtxt,graph);

/*        System.out.println("计算最短路径，请输入起点和终点");
        String shortest = Input.nextLine();
        String[] newdata = shortest.split(" ");
        if(newdata.length == 2){
            calcShortestPath(newdata[0],newdata[1],graph);
        }
        if(newdata.length == 1){
            for(int i = 0;i< graph.V;i++){
                calcShortestPath(newdata[0],graph.List.get(i),graph);
            }
        }
*/   


    }
}