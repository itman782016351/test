package com.ideal.test;

public class TreeNode {
public int data;

public TreeNode leftChild;
public TreeNode rightChild;

public static void inOrderTraversal(TreeNode node) {
if (node == null) {
return;
} else {
inOrderTraversal(node.leftChild);
System.out.println(node.data);
inOrderTraversal(node.rightChild);
}
}
}