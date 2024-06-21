package com.jdy.jdbctest;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdy.jdbccommand.MJoinCommand;
import com.jdy.jdbcdao.MemberDao;
import com.jdy.jdbcdto.MemberDto;

@Controller
public class JdbcController {
	
	@RequestMapping(value = "/test")
	public void test() {
		
		/* String driverName = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/jdy_spring_projectdb";
		String username ="root";
		String password ="12345"; */
		
		String driverName = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://192.168.0.100:3306/jdy_spring_project_db";
		String username ="guest01";
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	// 회원 가입 part
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
 	
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		model.addAttribute("request", request);
		
		MJoinCommand mJoinCommand = new MJoinCommand();
		mJoinCommand.execute(model);
		int success = mJoinCommand.execute(model);
		
//		String mid = request.getParameter("mid");
//		String mpw = request.getParameter("mpw");
//		String mname = request.getParameter("mname");
//		String memail = request.getParameter("memail");
		
//		MemberDao memberDao = new MemberDao();
		
//		int success = memberDao.joinMember(mid, mpw, mname, memail);
	
		if(success == 1) {
			model.addAttribute("mid", request.getParameter("mid"));
			model.addAttribute("mname", request.getParameter("mname"));
			return "joinOk";
			
		} else {
			model.addAttribute("error", "회원가입이 실패하였습니다. 다시 시도해주세요.");
			return "join";
		}
		
	}
	
	// 회원 탈퇴 part
	@RequestMapping(value = "/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@RequestMapping(value = "/drawCheck")
	public String drawCheck(HttpServletRequest request, Model model) {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		String mid = request.getParameter("mid");
		
		MemberDao memberDao = new MemberDao();
		
		int success = memberDao.drawMember(mid);
		
		if(success == 1) {
			model.addAttribute("mid", mid);
			return "drawCheck";
			
		} else {
			model.addAttribute("error", "회원탈퇴에 실패하였습니다. 아이디를 다시 확인해주세요.");
			// System.out.println("회원탈퇴실패");
			return "withdraw";
		}
			
	}
	
	
}
