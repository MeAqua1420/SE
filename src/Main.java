import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Main {
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
                for(int k = 0;k<=6;k++){
                    for(int j = 0;j<=6;j++){
                        System.out.printf("%d ",graph.table[k][j]);
                    }
                    System.out.println(" ");
                }
            }
        }
        catch (IOException e){
            System.out.println("IOErr");
        }
    }
}