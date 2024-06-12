import org.junit.*;
import java.io.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream inContent = new ByteArrayInputStream("/Users/qiurui/IdeaProjects/SE/src/test.txt".getBytes());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(inContent);
    }

    @After
    public void restoreStreams() {
        System.setOut(null);
        System.setIn(null);
    }

    @Test
    public void test1() {
        // 调用Main类的getReady方法,读入文件
        //String[] args = {};
        Main.getReady();

        // 验证输出是否符合预期
        String expectedOutput = "Once and a bridge word are : upon";
        Assert.assertEquals(expectedOutput, Main.queryBridgeWords("Once","a"));


    }
    @Test
    public void test2() {
        // 调用Main类的getReady方法,读入文件
        //String[] args = {};
        Main.getReady();

        // 验证输出是否符合预期
        String expectedOutput = "the and of bridge word are : middle flakes frame wood";
        Assert.assertEquals(expectedOutput, Main.queryBridgeWords("the","of"));


    }
    @Test
    public void test3() {
        /// 调用Main类的getReady方法,读入文件
        //String[] args = {};
        Main.getReady();

        // 验证输出是否符合预期

        String expectedOutput = "No word1 or word2!";
        Assert.assertEquals(expectedOutput, Main.queryBridgeWords("ame","maybe"));



    }
    @Test
    public void test4() {
        // 调用Main类的getReady方法,读入文件
        //String[] args = {};
        Main.getReady();

        // 验证输出是否符合预期
        String expectedOutput = "No bridge word!";
        Assert.assertEquals(expectedOutput, Main.queryBridgeWords("Once","upon"));

    }
}