package com.guestbook.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.GuestDTO;

/**
 * Servlet implementation class GuestList
 */
@WebServlet("/guestbook/list")
public class GuestList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		GuestDAO dao = GuestDAO.getInstance();

		String pageNum = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		
		int currentPage = Integer.parseInt(pageNum);// 정수화
		int pageSize = 5;// 한화면에 보여주는 게시물 양

		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		int count = dao.guestCount();// 게시물 수

		int totPage = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int blockPage = 3;
		int startPage = ((currentPage - 1) / blockPage) * blockPage + 1;// 4(현재 페이지가5일때)
		int endPage = startPage + blockPage - 1;// 6

		/* 예) count 가 23일때 
		 * totPage = 23/5+1 = 5-> 123[다음], [이전]45
		 * startPage = 4
		 * endPage = 6 ->5까지 나와야함
		 */
		if(endPage>totPage) endPage=totPage;
		request.setAttribute("totPage", totPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("blockPage",blockPage);//빈 객체에도 담을 수 있다.
		
		
		ArrayList<GuestDTO> arr = dao.guestList(startRow, endRow);// 게시물
		request.setAttribute("lists", arr);
		request.setAttribute("count", count);
		RequestDispatcher rd = request.getRequestDispatcher("listResult.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
