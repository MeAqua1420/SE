import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DrawingExample extends JPanel {
    Graph Dgraph;
    public DrawingExample(Graph graph){
        this.Dgraph = graph;
    }
    // 重写 paintComponent 方法来进行自定义绘图
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 设置背景颜色
        this.setBackground(Color.WHITE);

        // 设置绘图颜色
        g.setColor(Color.RED);
        // 绘制矩形
        g.fillRect(50, 50, 100, 100);

        g.setColor(Color.BLUE);
        // 绘制椭圆
        g.fillOval(200, 50, 100, 100);

        g.setColor(Color.GREEN);
        // 绘制线条
        g.drawLine(50, 200, 150, 300);

        g.setColor(Color.BLACK);
        // 绘制字符串
        //g.drawString("Hello, Java!", 200, 200);
        g.drawString(Dgraph.List.get(3),200,200);
        drawArrow(g,150,50,200,50);
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1, y1, x2, y2);

        double angle = Math.atan2(y2 - y1, x2 - x1);
        int arrowHeadLength = 10;
        int arrowHeadAngle = 30;

        int x3 = (int) (x2 - arrowHeadLength * Math.cos(angle - Math.toRadians(arrowHeadAngle)));
        int y3 = (int) (y2 - arrowHeadLength * Math.sin(angle - Math.toRadians(arrowHeadAngle)));

        int x4 = (int) (x2 - arrowHeadLength * Math.cos(angle + Math.toRadians(arrowHeadAngle)));
        int y4 = (int) (y2 - arrowHeadLength * Math.sin(angle + Math.toRadians(arrowHeadAngle)));

        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x2, y2, x4, y4);
    }
    public static void main(String[] args) {
        // 创建一个 JFrame 窗口
//        JFrame frame = new JFrame("Drawing Example");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//
//        // 创建一个自定义的 DrawingExample 面板
//        DrawingExample drawingPanel = new DrawingExample();
//
//        // 将面板添加到窗口中
//        frame.add(drawingPanel);
//
//        // 显示窗口
//        frame.setVisible(true);
    }
}
