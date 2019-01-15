package com.ideal.test;

public class ThreadStopUnSafe {
	public static User user = new User();

	// �ı�user�������߳�
	public static class ChangeObjectThread extends Thread {
		@Override
		public void run() {

			while (true) {
				synchronized (ThreadStopUnSafe.class) {
					int v = (int) (System.currentTimeMillis() / 1000);
					user.setId(v);
					// to do sth
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					user.setName(String.valueOf(v));
				}
				// �ó�CPU���������߳�ִ��
				Thread.yield();
			}

		}

	}

	// ��ȡuser�������߳�
	public static class ReadObjectThread extends Thread {
		@Override
		public void run() {

			while (true) {
				synchronized (ThreadStopUnSafe.class) {
//					System.out.println(user.toString());
					if (user.getId() != Integer.parseInt(user.getName())) {
						System.out.println(user.toString());
					}
				}
				// �ó�CPU���������߳�ִ��
				Thread.yield();
			}

		}
	}

	// ����
	public static void main(String[] args) throws InterruptedException {
		new ReadObjectThread().start();
		while (true) {
			Thread t = new ChangeObjectThread();
			t.start();
			System.out.println("change�߳�������"+t.getName());
			Thread.sleep(1000);
			// ʹ��stop()������ǿ��ֹͣ�߳�
			t.stop();
		}
	}
}
