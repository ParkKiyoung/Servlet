package com.guestbook.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guestbook.model.GuestDAO;
import com.guestbook.model.MemberDTO;


/**
 * Servlet implementation class GuestLogin
 */
@WebServlet("/guestbook/login")
public class GuestLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		GuestDAO dao = GuestDAO.getInstance();
		int log = dao.guestLogin(userid,pwd);
		String msg = "";
		String url = "";
		if(log==1||log==2) {
			HttpSession session =request.getSession();
			MemberDTO mdto = dao.getMember(userid);
			session.setAttribute("mdto", mdto);
			msg = "로그인 성공";
			url = "guestInsert.jsp";
		}else if(log==3){
			
			msg = "비번 안맞음";
			url = "login.jsp";
		}else {
			msg = "회원 아님";
			url = "login.jsp";
		}
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(url);
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
