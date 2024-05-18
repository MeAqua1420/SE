import javax.swing.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {

    public static void BridgeWord(String left, String right, Graph g){
        LinkedList<Integer> Bridge =  g.Bridge(left,right);
        if(Bridge.get(0) == -1){
            System.out.println("No word1 or word2!");
        }
        else if(Bridge.get(0) == -2){
            System.out.println("No bridge word!");
        }
        else{
            int i = 0;
            System.out.printf("%s and %s bridge word are :",left,right);
            while(i<Bridge.size() && Bridge.get(i) != -2) {
                System.out.printf(" " + g.List.get(Bridge.get(i)));
                i++;
            }
        }
    }
    public static void generateNewText(String inputText,Graph graph){
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

    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner Input = new Scanner(System.in);
        System.out.println("请输入文件路径：");
        String Path = Input.nextLine();
        System.out.println(Path);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(Path))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 将每行数据按空格分割
                String[] data = line.split(" ");
                int i = 0;
                while(i< data.length){
                    if(i == 0){
                        graph.AddNode(data[i],data[i]);
                    }
                    else {
                        graph.AddNode(data[i-1],data[i]);
                    }
                    i++;
                }
                System.out.println("num of Node = "+graph.V);
//                 for(int k = 0;k<=20;k++){
//                    for(int j = 0;j<=20;j++){
//                        System.out.printf("%d ",graph.table[k][j]);
//                    }
//                    System.out.println(" ");
//                }
            }
        }
        catch (IOException e){
            System.out.println("IOErr");
        }
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
        System.out.println("请输入待补全的句子：");
        String newtxt = Input.nextLine();
        generateNewText(newtxt,graph);   
    }
}