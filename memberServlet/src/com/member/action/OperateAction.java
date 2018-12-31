package com.member.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class OperateAction
 */
@WebServlet("/view/operate.do")
public class OperateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.memberDelete(num);
		response.sendRedirect("ManagerList.do");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String admin = request.getParameter("admin");//회원등급 - 일반회원 = 1, 관리자 = 2
		
		MemberDTO md = new MemberDTO();
		
		md.setNum(num);
		md.setAddr(addr);
		md.setAge(age);
		md.setEmail(email);
		md.setName(name);
		md.setPasswd(passwd);
		md.setTel(tel);
		md.setId(id);
		md.setGender(gender);
		md.setAdmin(admin);
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.memberUpdate(md);
		
		response.sendRedirect("ManagerList.do");
		
	}

}
