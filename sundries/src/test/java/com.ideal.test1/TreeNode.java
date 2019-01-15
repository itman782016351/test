package com.ideal.test1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

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
	
	
	@Test
	public void test3() throws Exception {
		final Lock lock = new ReentrantLock();
//		lock.lock();
		Thread.sleep(1000);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("��ʼ��ȡ��.....");
				Thread.currentThread().interrupt();
				lock.lock();
				System.out.println("��ȡ������.....");
				System.out.println(Thread.currentThread().getName() + " interrupted.");
			}
		});
		t1.start();
		Thread.sleep(1000);
		// t1.interrupt();
		// Thread.sleep(1000000);
	}
}
