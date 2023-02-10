package com.coding404.myweb.util;

import lombok.Data;

//ȭ�鿡 �׷����� ���������̼��� ���� ����ϴ� Ŭ����
@Data
public class PageVO {
	
	private int end; // ���������̼� ����ȣ
	private int start; //���������̼� ���۹�ȣ
	private boolean next; //������ư Ȱ��ȭ����
	private boolean prev; //������ư Ȱ��ȭ����
	
	private int realEnd; //���������̼� ��������ȣ
	
	private int page; //����ڰ� ��ȸ�ϴ� ��������ȣ
	private int amount; //ȭ�鿡 �� �������� ��Ÿ���� �����Ͱ���
	private int total; //��ü�Խñ� �� 
	
	private Criteria cri; //����������
	
	private int pageCnt = 5; //���������̼� ����
	
	//������ - pageVO�� ������� �� cri, total�� �޴´�
	public PageVO(Criteria cri, int total) {
		//��꿡 �ʿ��� ��(��������ȣ, �����Ͱ���, ��ü�Խñۼ�, cri)�� �ʱ�ȭ
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//1. �������� ���
		//page�� 1~10 -> �������� 10
		//page�� 11~20 -> �������� 20
		//(int)Math.ceil(��������ȣ/10.0) * ���������̼� ��
		this.end = (int)Math.ceil(this.page / (double)pageCnt) * pageCnt;
		
		//2. ������������ȣ ��� -> �� ������ ����� ù��° ������ ��ȣ�� ���ϴ°�.
		// end - ���������̼� �� + 1
		this.start = this.end - pageCnt + 1;
		
		//3. ��������ȣ ���
		//�����Ͱ� 60����� ������ ��, 5�������� ��ȸ end = 6
		//�����Ͱ� 112����� ������ ��, 11�������� ��ȸ�� end = 12
		//�����Ͱ� 356����� ������ ��, 23�������� ��ȸ�� end = 36
		//(int)Math.ceil(��ü�Խñ� ��/�����Ͱ���)
		this.realEnd = (int)Math.ceil(this.total /(double)this.amount);
		
		//4. ������ ��������ȣ�� �ٽ� ���
		//�����Ͱ� 112����� ������ ��, 5�������� ��ȸ��, end=10, realEnd=12 ====> 10
		//�����Ͱ� 112����� ������ ��, 11�������� ��ȸ��, end=20, realEnd=12 ====> 12
		//����ȣ > ��������ȣ ��� ��������ȣ�� ���� 
		this.end = this.end > this.realEnd ? this.realEnd : this.end;
		
		//5. ������ư
		//start�� 1, 11, 21, 31 .... �� �����Ǵµ� 1���� ũ�� true 
		this.prev = this.start > 1;
		
		//6. ������ư
		//���� - realEnd�� end���� ũ�� true
		this.next = this.realEnd > this.end;
		
	}
 

}
