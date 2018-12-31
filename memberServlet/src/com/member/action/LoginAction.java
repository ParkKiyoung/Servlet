package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/view/login.do")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDTO md = new MemberDTO();
		md.setId(id);
		md.setPasswd(passwd);
			
		MemberDAO dao = MemberDAO.getInstance();
		int log = dao.login(md);
		
		if(log==1) {//일반 회원 로그인 성공
			int num = dao.popNum(id);
			
			/*HttpSession session =request.getSession();
			session.setAttribute("num", num);*/
			
			response.setContentType("text/html;charset=utf-8");
			RequestDispatcher rd = request.getRequestDispatcher("MemberView.do?num="+num);
			rd.forward(request, response);
			
		}else if(log==2){//관리자 로그인 성공
			
			HttpSession session =request.getSession();
			session.setAttribute("id", id);
			RequestDispatcher rd = request.getRequestDispatcher("ManagerList.do");
			rd.forward(request, response);
			
		}else if(log==3){//비밀번호 틀림
			request.setAttribute("log", log);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {//회원 아님
			request.setAttribute("log", log);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
