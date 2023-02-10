package com.coding404.myweb.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.TripVO;
import com.coding404.myweb.trip.service.TripService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/trip")
public class NoticeController {
	
	@Autowired
	@Qualifier("tripService")
	private TripService tripService;
	
	//���ȭ��
	@RequestMapping("/notice_list")
	public String notice_list(Criteria cri, Model model) {
		
		/*
		 * service, mapper ������ getList �Լ��� �����ϰ�
		 * ��Ϲ�ȣ �������� �����͸� ��ȸ�ؼ� ������ ���ɴϴ�.
		 * model�� ��Ƽ�
		 * ȭ�鿡���� �ݺ������� ó��.
		 */

		//������
//		ArrayList<TripVO> list = tripService.getList(cri);
		
		//���������̼�
//		int total = tripService.getTotal();
//		PageVO pageVO = new PageVO(cri, total);
//		System.out.println(pageVO.toString());
		
		//������ �˻�ó��
		/*
		 * 1. ȭ�鿡���� page, amount, searchType, searchName�� �ѱ��.
		 * 2. criteria���� �˻����� �޴´�
		 * 3. sql�� �ٲ۴�. (��������)
		 * 4. total sql�� �ٲ۴�. 
		 * 5. ������ a�±� Ŭ���� searchType, searchName�� ������Ʈ������ �ѱ��.
		 * 6. �˻�Ű������ ����
		 */
		System.out.println(cri.toString());
		
		ArrayList<TripVO> list = tripService.getList(cri);
		
		int total = tripService.getTotal(cri);
		
		PageVO pageVO = new PageVO(cri, total);
		
		
		model.addAttribute("list",list);
		model.addAttribute("pageVO", pageVO);
		
		
		return "trip/notice_list";
	}
	
	//��ȭ��
	@RequestMapping("/notice_view")
	public String notice_view(@RequestParam("tno") int tno,
							  Model model,
							  HttpServletResponse response,
							  HttpServletRequest request) {
		//Ŭ���� �� ��ȣ�� ���� ������ ��ȸ
		TripVO vo = tripService.getContent(tno);
		model.addAttribute("vo", vo);
		
		//��ȸ�� - Cookie or ���� �̿��ؼ� ��ȸ�� �ߺ� ����
		tripService.upHit(tno);
		
//		Cookie cookie = new Cookie("key", "1");
//		cookie.setMaxAge(30);
//		response.addCookie(cookie);
		
		//������ ������
		ArrayList<TripVO> list = tripService.getPrevNext(tno);
		model.addAttribute("list", list);
		
		
		return "trip/notice_view";
	}
	
	//�۵��ȭ��
	@RequestMapping("/notice_write")
	public String notice_write() {
		return "trip/notice_write";
	}
	


	//����ȭ��
	@RequestMapping("/notice_modify")
	public String notice_modify(@RequestParam("tno") int tno,
								Model model) { 
		
		//Ŭ���� �� ��ȣ�� ���� ������ ��ȸ
		TripVO vo = tripService.getContent(tno);
		model.addAttribute("vo", vo);
		
		return "trip/notice_modify";
	}
	

	
	//�۵��
	@RequestMapping(value="/registForm", method = RequestMethod.POST)
	public String registForm(TripVO vo, RedirectAttributes ra) {

		int result = tripService.noticeRegist(vo);
		String msg = result == 1 ? "���ǻ����� ���� ��ϵǾ����ϴ�" : "���� ��Ͽ� �����߽��ϴ�";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/trip/notice_list";
		
	}
	
	//�ۼ���
	@RequestMapping(value="/modifyForm", method = RequestMethod.POST)
	public String modifyFrom(TripVO vo,
							 RedirectAttributes ra) {
		
		//������Ʈ�۾� - ȭ�鿡���� tno�� �ʿ��ϱ� ������ hidden�±׸� �̿��ؼ� �Ѱ��ּ���.
		int result = tripService.noticeModify(vo);
		String msg = result == 1 ? "���ǻ����� �����Ǿ����ϴ�" : "������ �����߽��ϴ�";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/trip/notice_view?tno=" + vo.getTno();
	}
	
	//����, �� ȭ���� ���� �����ϴٸ�
//	@RequestMapping({"notice_view", "notice_modify"})
//	public void notice_view(@RequestParam("tno") int tno,
//							Model model) {
//		TripVO vo = tripService.getContent(tno);
//		model.addAttribute("vo", vo);
//	}
	
	//�ۻ���
	@RequestMapping(value="deleteForm", method = RequestMethod.POST)
	public String deleteForm(@RequestParam("tno") int tno,
							 RedirectAttributes ra) {
		
		/*
		 * service, mapper ���� noticeDelete �޼���� ������ ���� 
		 * ���� ���Ŀ��� listȭ������ �̵����ָ� �˴ϴ�.
		 */
		int result = tripService.noticeDelete(tno);
		String msg = result == 1 ? "���� �Ǿ����ϴ�" : "������ �����߽��ϴ�";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/trip/notice_list";
	}
	
}
