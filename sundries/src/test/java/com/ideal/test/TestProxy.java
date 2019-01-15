package com.ideal.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//��̬�����ʹ�ã���ᷴ���Ƕ�̬���ԵĹؼ�
interface Subject {
	void action();
}

// ��������
class RealSubject implements Subject {
	public void action() {
		System.out.println("���Ǳ������࣬�ǵ�Ҫִ����Ŷ��ôô~~");
	}
}

class MyInvocationHandler implements InvocationHandler {
	Object obj;// ʵ���˽ӿڵı�������Ķ��������

	// �ٸ�������Ķ���ʵ�����ڷ���һ��������Ķ���
	public Object blind(Object obj) {
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), this);
	}
	//��ͨ��������Ķ�����Ա���д�ķ����ĵ���ʱ������ת��Ϊ�����µ�invoke�����ĵ���
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//method�����ķ���ֵʱreturnVal
		Object returnVal = method.invoke(obj, args);
		return returnVal;
	}

}

public class TestProxy {
	public static void main(String[] args) {
		//1.��������Ķ���
		RealSubject real = new RealSubject();
		//2.����һ��ʵ����InvacationHandler�ӿڵ���Ķ���
		MyInvocationHandler handler = new MyInvocationHandler();
		//3.����blind()��������̬�ķ���һ��ͬ��ʵ����real������ʵ�ֵĽӿ�Subject�Ĵ�����Ķ���
		Object obj = handler.blind(real);
		Subject sub = (Subject)obj;//��ʱsub���Ǵ�����Ķ���
		
		sub.action();//ת����InvacationHandler�ӿڵ�ʵ�����invoke()�����ĵ���
		
		//�پ�һ��
		NikeClothFactory nike = new NikeClothFactory();
		AdidasClothFactory adidas=new AdidasClothFactory();
		ClothFactory proxyCloth = (ClothFactory)handler.blind(nike);//proxyCloth��Ϊ������Ķ���
		proxyCloth.productCloth();
		proxyCloth = (ClothFactory)handler.blind(adidas);//proxyCloth��Ϊ������Ķ���
		proxyCloth.productCloth();
		proxyCloth = (ClothFactory)handler.blind(nike);
		proxyCloth.productCloth(); 
		
		
	}
}
