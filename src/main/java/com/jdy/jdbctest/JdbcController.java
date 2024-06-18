package com.jdy.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdy.jdbcdao.MemberDao;
import com.jdy.jdbcdto.MemberDto;

@Controller
public class JdbcController {
	
	@RequestMapping(value = "/test")
	public void test() {
		
		String driverName = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/jdy_spring_projectdb";
		String username ="root";
		String password ="12345";
		
		Connection conn = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) {
					conn.close();
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
 	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mnname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		MemberDao memberDao = new MemberDao();
		memberDao.joinMember(mid, mpw, mnname, memail);
		
		return "joinOk";
	}
	
	
	
}
