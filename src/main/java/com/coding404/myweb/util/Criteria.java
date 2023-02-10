package com.coding404.myweb.util;

import lombok.Data;

//sql���� ��������ȣ, �����Ͱ��� �������� Ŭ����
@Data
public class Criteria {
	
	private int page; //��������ȣ
	private int amount; //�����Ͱ���
	
	private String searchType; //�˻�Ÿ��
	private String searchName; //�˻���

	public Criteria() {
		this.page = 1;
		this.amount = 10;
	}

	public Criteria(int page, int amout) {
		super();
		this.page = page;
		this.amount = amout;
	}

	//limit�Լ��� ���������� �κп� �� getter
	//������ �������� ���� �κ��� ��ȯ���ִ� ���� limit 0 , 10 ���� 0.
	public int getPageStart(){
		return (page - 1) * amount;
	}
	
}
