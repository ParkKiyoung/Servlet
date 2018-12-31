package com.guestbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class GuestDAO {
	private static GuestDAO instance;
	public static GuestDAO getInstance() {
		if(instance==null) {
			instance = new GuestDAO();
		}
		return instance;
	};
	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/member");
		return ds.getConnection();
	}
	public void guestInsert(GuestDTO gd) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into guestbook values(guestbook_seq.nextval,?,?,?,to_char(sysdate,'yyyy/mm/dd'),?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, gd.getName());
			ps.setString(2, gd.getContent());
			ps.setString(3, gd.getGrade());
			ps.setString(4, gd.getIpaddr());
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
	public ArrayList<GuestDTO> guestList(int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from (select rownum rn, aa.* from (select * from guestbook order by num desc)aa) "
					+ "where rn >= ? and rn <= ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();
			while(rs.next()) {
				GuestDTO gd = new GuestDTO();
				gd.setContent(rs.getString("content"));
				gd.setCreated(rs.getString("created"));
				gd.setGrade(rs.getString("grade"));
				gd.setIpaddr(rs.getString("ipaddr"));
				gd.setName(rs.getString("name"));
				gd.setNum(rs.getInt("num"));
				arr.add(gd);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,st,rs);
		}
		return arr;
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
	public int guestCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from guestbook";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				cnt = rs.getInt("count(*)");
				return cnt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return cnt;
	}
	public GuestDTO fview(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		GuestDTO gd = null;
		try {
			con = getConnection();
			String sql = "select * from guestbook where num ="+num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				gd=new GuestDTO();
				gd.setContent(rs.getString("content"));
				gd.setCreated(rs.getString("created"));
				gd.setGrade(rs.getString("grade"));
				gd.setIpaddr(rs.getString("ipaddr"));
				gd.setName(rs.getString("name"));
				gd.setNum(rs.getInt("num"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return gd;
	}
	public int guestLogin(String userid, String pwd) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int log = 0;//회원이 아님
		try {
			con = getConnection();
			String sql = "select * from member where id = '"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(rs.getString("passwd").equals(pwd)&&rs.getString("admin").equals("1")) {
					log = 1;//일반 회원 로그인 성공
					return log;
				}else if(rs.getString("passwd").equals(pwd)&&rs.getString("admin").equals("2")){
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
	public MemberDTO getMember(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberDTO mdto = null;
		try {
			con = getConnection();
			String sql= "select * from member where id = '"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mdto = new MemberDTO();
				mdto.setId(rs.getString("id"));
				mdto.setAddr(rs.getString("addr"));
				mdto.setAge(rs.getString("age"));
				mdto.setEmail(rs.getString("email"));
				mdto.setGender(rs.getString("gender"));
				mdto.setName(rs.getString("name"));
				mdto.setNum(rs.getInt("num"));
				mdto.setPasswd(rs.getString("passwd"));
				mdto.setTel(rs.getString("tel"));
				mdto.setAdmin(rs.getString("admin"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return mdto;
	}
	public void guestDelete(int num) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "delete guestbook where num = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			ps.executeQuery();		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
	public int guestCount(String field, String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from guestbook where "+field+" like '%"+word+"%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				cnt = rs.getInt("count(*)");
				return cnt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return cnt;
	}
	public ArrayList<GuestDTO> guestSearch(String field, String word, int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<GuestDTO> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from (select rownum rn, aa.* from (select * from guestbook where "+field+" like '%"+word+"%' order by num desc)aa) "
					+ "where rn >= ? and rn <= ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();
			while(rs.next()) {
				GuestDTO gd = new GuestDTO();
				gd.setContent(rs.getString("content"));
				gd.setCreated(rs.getString("created"));
				gd.setGrade(rs.getString("grade"));
				gd.setIpaddr(rs.getString("ipaddr"));
				gd.setName(rs.getString("name"));
				gd.setNum(rs.getInt("num"));
				arr.add(gd);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,st,rs);
		}
		return arr;
	}
	
}
