package syntax_analysis.ll1.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Node {
    String value;
    List<Node> children;

    public Node(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public String getValue(){
        return value;
    }
    public void addChild(Node child) {
        children.add(child);
    }
    public List<Node> getChildren(String name) {
        return children;
    }
    public Node getNodeByName(String nodeName) {
        if (this.value.equals(nodeName)) {
            return this; // 当前节点就是目标节点
        }
        for (Node child : children) {
            Node foundNode = child.getNodeByName(nodeName);
            if (foundNode != null) {
                return foundNode; // 在子树中找到了目标节点
            }
        }
        return null; // 在当前节点及其子树中都没有找到目标节点
    }
    public void printBeautifulTree(String prefix) {
        System.out.println(prefix + (prefix.isEmpty() ? "" : "└──") + value);

        for (int i = children.size() -1; i >= 0; i--) {
            String newPrefix = prefix + (i < children.size() - 1 ? "│   " : "    ");
            children.get(i).printBeautifulTree(newPrefix);
        }
    }

    // 递归打印树的方法
    public void printTree(int depth) {
        // 打印当前节点值，前面加上表示深度的缩进
        for (int i = 0; i < depth; i++) {
            System.out.print("\t"); // 使用制表符进行缩进
        }
        System.out.println(value);

        // 对每个子节点递归调用此方法，增加缩进深度
        for (int i = children.size() -1; i >= 0; i--) {
            children.get(i).printTree(depth + 1);
        }
    }

    public void printTxtTree(Node node, int depth, String filePath) {
        try {
            PrintStream ps = new PrintStream(filePath);
            System.setOut(ps);
            for (int i = 0; i < depth; i++) {
                System.out.print("\t"); // 使用制表符进行缩进
            }
            System.out.println(value);

            // 对每个子节点递归调用此方法，增加缩进深度
            for (int i = children.size() -1; i >= 0; i--) {
                children.get(i).printTree(depth + 1);
            }
            ps.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public void printGraphTree(String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            printRecursive("", this, writer);
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + filePath);
            e.printStackTrace();
        }
    }

    // 递归方法同样接收 PrintWriter 参数
    private void printRecursive(String prefix, Node node, PrintWriter writer) {
        writer.println(prefix + (prefix.isEmpty() ? "" : "└──") + node.value);

        // 从children的最后一个元素开始向前遍历
        for (int i = node.children.size() - 1; i >= 0; i--) {
            String newPrefix = prefix + (i < node.children.size() - 1 ? "    " : "│   ");
            printRecursive(newPrefix, node.children.get(i), writer);
        }
    }
}