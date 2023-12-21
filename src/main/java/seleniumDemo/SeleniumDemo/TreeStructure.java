package seleniumDemo.SeleniumDemo;

import java.util.ArrayList;
import java.util.List;

class MyTreeNode {
    String data;
    List<MyTreeNode> children;

    public MyTreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(MyTreeNode child) {
        this.children.add(child);
    }

    public List<MyTreeNode> getChildren() {
        return children;
    }

    public String getData() {
        return data;
    }
}

class Tree {
    private MyTreeNode root;

    public Tree(String rootData) {
        this.root = new MyTreeNode(rootData);
    }

    public MyTreeNode getRoot() {
        return root;
    }

    public void show() {
        display(root, 0);
    }

    private void display(MyTreeNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node.getData());

        for (MyTreeNode child : node.getChildren()) {
            display(child, depth + 1);
        }
    }
}

public class TreeStructure {
    public static void main(String[] args) {
        Tree tree = new Tree("Main");
        MyTreeNode jane = new MyTreeNode("1");
        MyTreeNode bill = new MyTreeNode("2");
        MyTreeNode diane = new MyTreeNode("sub1");
        MyTreeNode mary = new MyTreeNode("Mary");
        MyTreeNode mark = new MyTreeNode("sub2");

        tree.getRoot().addChild(jane);
        tree.getRoot().addChild(bill);
        jane.addChild(diane);
        diane.addChild(mary);
        jane.addChild(mark);

        tree.show();
    }
}
