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
 * Servlet implementation class SearchAction
 */
@WebServlet("/guestbook/search")
public class SearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			String field = request.getParameter("field");
			String word = request.getParameter("word");
			
			GuestDAO dao = GuestDAO.getInstance();
			
			int count = dao.guestCount(field,word);// 게시물 수
			
			int currentPage = Integer.parseInt(pageNum);// 정수화
			int pageSize = 5;// 한화면에 보여주는 게시물 양

			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			
			int totPage = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int blockPage = 3;
			int startPage = ((currentPage - 1) / blockPage) * blockPage + 1;// 4(현재 페이지가5일때)
			int endPage = startPage + blockPage - 1;// 6
			
			if(endPage>totPage)endPage=totPage;
			
			request.setAttribute("totPage", totPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("blockPage",blockPage);//빈 객체에도 담을 수 있다.
					
			ArrayList<GuestDTO> arr = dao.guestSearch(field,word,startRow,endRow);
			request.setAttribute("field", field);
			request.setAttribute("word", word);
			request.setAttribute("lists", arr);
			request.setAttribute("count", count);
			RequestDispatcher rd = request.getRequestDispatcher("searchResult.jsp");
			rd.forward(request, response);
		
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
