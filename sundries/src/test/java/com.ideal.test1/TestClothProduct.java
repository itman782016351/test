package com.ideal.test1;
//��̬����ģʽ
//�ӿ�
interface ClothFactory{
	void productCloth();
}
//��������
class NikeClothFactory implements ClothFactory{

	@Override
	public void productCloth() {
		System.out.println("Nike��������һ���·�");
	}	
}
//��������
class AdidasClothFactory implements ClothFactory{

	@Override
	public void productCloth() {
		System.out.println("Adidas��������һ���·�");
	}	
}
//������
class ProxyFactory implements ClothFactory{
	ClothFactory cf;
	//����������Ķ���ʱ��ʵ�ʴ���һ����������Ķ���
	public ProxyFactory(ClothFactory cf){
		this.cf = cf;
	}
	
	@Override
	public void productCloth() {
		System.out.println("�����࿪ʼִ�У��մ����$1000");
		cf.productCloth();
	}
	
}

public class TestClothProduct {
	public static void main(String[] args) {
		NikeClothFactory nike = new NikeClothFactory();//������������Ķ���
		ProxyFactory proxy = new ProxyFactory(nike);//����������Ķ���
		proxy.productCloth();
	}
}
