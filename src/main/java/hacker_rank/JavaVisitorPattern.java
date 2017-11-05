/*
 * HackerRank problem for Java Visitor Pattern.
 * Stopping early on this problem since 1% of the problem is about java visitor pattern,
 * and 99% of the problem is about an ambiguous way to create a graph.
 * Need to focus on other problems that will help me better learn Java.
 */

package hacker_rank;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.ArrayList;

import java.util.Scanner;

enum Color {
    RED, GREEN
}

class TreeConstants {
    public static int PRODUCT_RED_NODES_HI_LIMIT = 1000000007;
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}


abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int leavesSum = 0; /* Make this a class variable since we cannot modify the class method's signature */
    private int totalSum = 0;

    public int getResult() {
        //implement this
        return this.leavesSum;
    }

    public void visitNode(TreeNode node) {
        if (node == null) {
            return;
        }
        //implement this
        /* If this node does not have any children then it is a leaf, so don't count it. */
        if (node instanceof TreeLeaf) {
            this.visitLeaf(node);
        }
        this.totalSum += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
        this.leavesSum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private int redNodesProduct = 1;
    public int getResult() {
        //implement this
        return redNodesProduct;
    }

    public void visitNode(TreeNode node) {
        if (node == null) {
            return;
        }
        if (node instanceof TreeLeaf) {
            this.visitLeaf(node);
        }
        //implement this
        if (node.getColor() == Color.RED) {
            this.redNodesProduct *= node.getValue();
            this.redNodesProduct %= TreeConstants.PRODUCT_RED_NODES_HI_LIMIT;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
    /* Do nothing for a leaf */
        return;
    }
}

class FancyVisitor extends TreeVis {
    private int result = 0;
    private int nonLeftEvenDepthNodesSum = 0;
    private int greenLeafNodesSum = 0;
    public int getResult() {
        //implement this
        return Math.abs(this.nonLeftEvenDepthNodesSum - this.greenLeafNodesSum);
    }

    public void visitNode(TreeNode node) {
        if (node == null) {
            return;
        }
        //implement this
        if (node.getColor() == Color.GREEN) {
            this.greenLeafNodesSum += node.getValue();
        }

        if (!(node instanceof LeafNode) && (node.getDepth() % 2 == 0)) {
            this.nonLeftEvenDepthNodesSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        //implement this
    /* Do nothing */
        return;
    }
}

public class JavaVisitorPattern {

    public static Tree solve() {
        //read the tree from STDIN and return its root as a return value of this function
        Scanner sc = new Scanner(System.in);
        try {
            int row = 0;
            int col = 0;
            int totalNodes = 0;
            int item;
            int edge;
            int vertex;
            int[][] treeDetails; /* Put only on stack; row 1 = value, row 2 = color */
            int[][] vertices; /* Put only on stack; row 1 = value, row 2 = color */

            Tree[] tree;

            Tree root = null;
            assert sc.hasNextInt() : "Error: Scanner did not detect any inputs!";
            totalNodes = sc.nextInt();

            /* Create a n row * 2 columns matrix (column 1 = value; column 2 = color) */
            treeDetails = new int[totalNodes][2];
            vertices = new int[totalNodes][totalNodes];

            /* Allocate memory for nodes */
            tree = new Tree[totalNodes];

            /* Grab all values */
            for (row = 0; row < totalNodes; row++) {
                assert sc.hasNextInt() : "Error: incomplete user inputs while reading each value";
                item = sc.nextInt();
                treeDetails[row][0] = item;
            }

            /* Grab all colors */
            for (row = 0; row < totalNodes; row++) {
                assert sc.hasNextInt() : "Error: incomplete user inputs detected while reading each color!";
                item = sc.nextInt();
                treeDetails[row][1] = item;
            }

            /* Store all vertices */
            while (sc.hasNextInt()) {
                vertex = sc.nextInt();
                assert sc.hasNextInt() : "Invalid input: An edge was specified without a vertex";
                edge = sc.nextInt();
                vertices[vertex][edge] = 1;
            }

            for (i = 0; i < totalNodes; i++) {

            }

        }
        finally {
            sc.close();
        }
        return tree;
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
