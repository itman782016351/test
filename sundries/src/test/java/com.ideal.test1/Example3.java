package com.ideal.test1;

public class Example3 extends Thread {

	volatile boolean stop = false;

	public static void main(String args[]) throws Exception {
		Example3 thread = new Example3();

		System.out.println("Starting thread...");
		thread.start();

		Thread.sleep(11000);

		System.out.println("Asking thread to stop...");

		/*
		 * ����߳���������������˱���,����interrupt֮���߳̾Ϳ��Ծ�����սᱻ�� ��״ ̬���ܹ������һ������
		 */
		thread.stop = true;

		/*
		 * ��һ����ʵ������ɵ��ǣ����߳��ܵ�����ʱ�׳�һ���ж��źţ������߳̾͵����� ���� ����״̬
		 */
		thread.interrupt();

		Thread.sleep(3000);
		System.out.println("Stopping application...");
//		System.exit(0);
	}

	public void run() {
		while (!stop) {
			System.out.println("Thread running...");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// ���յ�һ���ж��쳣��InterruptedException�����Ӷ�������սᱻ����״̬
				System.out.println("Thread interrupted...");
			}
		}

		System.out.println("Thread exiting under request...");
	}
}
