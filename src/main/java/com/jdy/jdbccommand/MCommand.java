package com.jdy.jdbccommand;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface MCommand {
	
	public int execute(Model model);
	
}
