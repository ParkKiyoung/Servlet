package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private static MemberDAO instance;
	public static MemberDAO getInstance() {
		if(instance==null) {
			instance = new MemberDAO();
		}
		return instance;
	};
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	public void join(MemberDTO md) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, md.getId());
			ps.setString(2, md.getPasswd());
			ps.setString(3, md.getName());
			ps.setString(4, md.getTel());
			ps.setString(5, md.getEmail());
			ps.setString(6, md.getAddr());
			ps.setString(7, md.getAge());
			ps.setString(8, md.getGender());
			ps.setString(9, md.getAdmin());
			ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,ps);
		}
	}
	private void closeCon(Connection con, PreparedStatement ps) {
		try {
		if(con!=null)con.close();
		if(ps!=null)ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	//로그인
	public int login(MemberDTO md) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int log = 0;//회원이 아님
		try {
			con = getConnection();
			String sql = "select * from member where id = '"+md.getId()+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("passwd").equals(md.getPasswd())&&rs.getString("admin").equals("1")) {
					log = 1;//일반 회원 로그인 성공
					return log;
				}else if(rs.getString("passwd").equals(md.getPasswd())&&rs.getString("admin").equals("2")){
					log = 2; //관리자 회원 로그인 성공
					return log;
					
				};
				log = 3;//비번틀림
				return log;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st,rs);
		}
		return log;
	}
	//아이디에 맞는 회원번호 추출
	public int popNum(String id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = getConnection();
			String sql = "select * from member where id = '"+id+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				num = rs.getInt("num");
				return num;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	private void closeCon(Connection con, Statement st, ResultSet rs) {
		try {
		if(con!=null)con.close();
		if(st!=null)st.close();
		if(rs!=null)rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<MemberDTO> memberList() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MemberDTO> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from member order by num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MemberDTO md = new MemberDTO();
				md.setAddr(rs.getString("addr"));
				md.setAge(rs.getString("age"));
				md.setEmail(rs.getString("email"));
				md.setGender(rs.getString("gender"));
				md.setId(rs.getString("id"));
				md.setName(rs.getString("name"));
				md.setNum(rs.getInt("num"));
				md.setPasswd(rs.getString("passwd"));
				md.setTel(rs.getString("tel"));
				md.setAdmin(rs.getString("admin"));
				arr.add(md);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	public MemberDTO memberView(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO md = null;
		try {
			con = getConnection();
			String sql = "select * from member where num = "+num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				md=new MemberDTO();
				md.setAddr(rs.getString("addr"));
				md.setAge(rs.getString("age"));
				md.setEmail(rs.getString("email"));
				md.setGender(rs.getString("gender"));
				md.setId(rs.getString("id"));
				md.setName(rs.getString("name"));
				md.setNum(rs.getInt("num"));
				md.setPasswd(rs.getString("passwd"));
				md.setTel(rs.getString("tel"));
				md.setAdmin(rs.getString("admin"));
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return md;
	}
	public void memberUpdate(MemberDTO md) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update member set name=?,tel=?,gender=?,addr=?,admin=?,age=?,email=? where num = ?";
			ps =con.prepareStatement(sql);
			ps.setString(1, md.getName());
			ps.setString(2, md.getTel());
			ps.setString(3, md.getGender());
			ps.setString(4, md.getAddr());
			ps.setString(5, md.getAdmin());
			ps.setString(6, md.getAge());
			ps.setString(7, md.getEmail());
			ps.setInt(8, md.getNum());
			ps.executeUpdate();
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
	}
	public void memberDelete(int num) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "delete member where num = "+num;
			ps =con.prepareStatement(sql);
			ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
}
