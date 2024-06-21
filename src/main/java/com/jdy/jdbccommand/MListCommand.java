package com.jdy.jdbccommand;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.jdy.jdbcdao.MemberDao;
import com.jdy.jdbcdto.MemberDto;

public class MListCommand implements MCommand {

	@Override
	public int execute(Model model) {
		// TODO Auto-generated method stub
		MemberDao memberDao = new MemberDao();
		ArrayList<MemberDto> memberDtos = memberDao.listMember();
		
		model.addAttribute("memberDtos", memberDtos);
		
		return 0;
	}

}
