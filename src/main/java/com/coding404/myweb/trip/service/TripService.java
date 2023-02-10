package com.coding404.myweb.trip.service;

import java.util.ArrayList;

import com.coding404.myweb.command.TripVO;
import com.coding404.myweb.util.Criteria;

public interface TripService {
	
	public int noticeRegist(TripVO vo); //���
//	public ArrayList<TripVO> getList(); //��ȸ
	public ArrayList<TripVO> getList(Criteria cri); //������
	public int getTotal(Criteria cri); //��ü�Խñۼ�
	
	
	public TripVO getContent(int tno); //����ȸ
	public int noticeModify(TripVO vo); //����
	public int noticeDelete(int tno);//����
	
	public void upHit(int tno); //��ȸ��
	public ArrayList<TripVO> getPrevNext(int tno); //������, ������
}
