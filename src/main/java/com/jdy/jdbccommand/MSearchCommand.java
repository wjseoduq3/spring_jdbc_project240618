package com.jdy.jdbccommand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.scheduling.commonj.WorkManagerTaskExecutor;
import org.springframework.ui.Model;

import com.jdy.jdbcdao.MemberDao;
import com.jdy.jdbcdto.MemberDto;

public class MSearchCommand implements MCommand {

	@Override
	public int execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String mid = request.getParameter("mid");
				
		MemberDao memberDao = new MemberDao();
		MemberDto memberDto = memberDao.searchMember(mid);
		
		if(memberDto !=null) {
			model.addAttribute("memberDto", memberDto);
		} else {
			model.addAttribute("memberDto", memberDto);
			model.addAttribute("error", "회원정보가 없는 아이디입니다. 다시 확인해주세요.");
		}
		
		model.addAttribute("memberDto", memberDto);
		
		return 0;
	}

}
