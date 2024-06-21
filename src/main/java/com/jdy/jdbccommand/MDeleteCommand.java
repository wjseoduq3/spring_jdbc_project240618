package com.jdy.jdbccommand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.jdy.jdbcdao.MemberDao;

public class MDeleteCommand implements MCommand {
	
	public int execute(Model model) {

			
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest)map.get("request");
			
			String mid = request.getParameter("mid");
			
			MemberDao memberDao = new MemberDao();
			
			int success = memberDao.drawMember(mid);
			
			return success;
		}
	

}
