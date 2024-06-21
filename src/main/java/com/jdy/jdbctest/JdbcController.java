package com.jdy.jdbctest;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdy.jdbccommand.MCommand;
import com.jdy.jdbccommand.MDeleteCommand;
import com.jdy.jdbccommand.MJoinCommand;
import com.jdy.jdbccommand.MListCommand;
import com.jdy.jdbccommand.MModifyCommand;
import com.jdy.jdbccommand.MSearchCommand;
import com.jdy.jdbcdao.MemberDao;
import com.jdy.jdbcdto.MemberDto;

@Controller
public class JdbcController {
	
	MCommand command = null;
	
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
		
		command = new MJoinCommand();
		int success = command.execute(model);
		
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
		
		model.addAttribute("request", request);
				
		command = new MDeleteCommand();
		int success = command.execute(model);
					
		if(success == 1) {
			// model.addAttribute("mid", request.getParameter("mid"));
			model.addAttribute("message", "회원 탈퇴 성공");
			// return "drawCheck";
			
		} else {
			model.addAttribute("message", "회원탈퇴에 실패하였습니다. 아이디를 다시 확인해주세요."); 
			// System.out.println("회원탈퇴실패");
			// return "withdraw";
		}
		
		return "drawCheck";
	 }	
	 // 회원 검색 part	
	 @RequestMapping(value = "/search")	
	 public String search() {
		return "search";
	 }
	 @RequestMapping (value = "/searchOk")
	 public String searchOk(HttpServletRequest request, Model model) {
		 
		 model.addAttribute("request", request);
		 
		 command = new MSearchCommand();
		 command.execute(model);
		 
		 return "searchOk";		 
	 }	
	 
	 @RequestMapping(value = "/list")
	 public String list(HttpServletRequest request, Model model) {
		 
		 command = new MListCommand();
		 command.execute(model);
		 
		 return "list";
	 }
	 
	 @RequestMapping(value = "/modifyOk")
	 public String modifyOk(HttpServletRequest request, Model model) {
		 
		 model.addAttribute("request", request);
		 
		 command = new MModifyCommand();
		 int success = command.execute(model);
		 
		 if(success == 1) {
			 
			 command = new MSearchCommand();
			 command.execute(model);
			 		  
			 return "modifyOk"; 
		 } else {
			 return "search";
		 }	 
	 } 
	 
	 @RequestMapping(value = "/")
	 public String index() {
		 
		 return "index";
	 }
	 
	 
	 
}
