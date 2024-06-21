package com.jdy.jdbcdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jdy.jdbcdto.MemberDto;

public class MemberDao {

	// String driverName = "com.mysql.jdbc.Driver";
	// String url ="jdbc:mysql://localhost:3306/jdy_spring_projectdb";
	// String username ="root";
	// String password ="12345";
	
	String driverName = "com.mysql.jdbc.Driver";
	String url ="jdbc:mysql://192.168.0.100:3306/jdy_spring_project_db";
	String username ="guest01";
	String password ="12345";

	
	// 회원가입, 회원탈퇴, 회원리스트조회, 회원검색, 회원정보수정
	
	// 1. 회원가입
	public int joinMember(String mid, String mpw, String mname, String memail) {
		
		String sql ="INSERT INTO members(mid, mpw, mname, memail) VALUES (?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		int success = 0;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			pstmt.setString(3, mname);
			pstmt.setString(4, memail);
			
			success = pstmt.executeUpdate(); // 성공하면 1 반환
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}		
		return success;
	}	
	
	// 2. 회원탈퇴
		public int drawMember(String mid) {
			
			String sql ="DELETE FROM members WHERE mid=?";
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			
			int success = 0;
			
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, mid);
				
				success = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}		
			return success;
		}	
		
		// 3. 회원 검색 part
		public MemberDto searchMember(String serchId) {
			
			String sql ="SELECT * FROM members WHERE mid=?";
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			MemberDto memberDto = null;	
			
					
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, serchId);
				
				rs = pstmt.executeQuery();  // rs는 반드시 1개 아니면 0개 레코드를 반환
				
				if(rs.next()) {
					String mid = rs.getString("mid");
					String mpw = rs.getString("mpw");
					String mname = rs.getString("mname");
					String memail = rs.getString("memail");
					String mdate = rs.getString("mdate");
					
					memberDto = new MemberDto(mid, mpw, mname, memail, mdate);			
				} 	
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
						if(rs != null) {
							rs.close();
						}
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}		
			return memberDto;
		}	
		
		// 3. 회원 리스트 조회 part
		public ArrayList<MemberDto> listMember() {
					
			String sql ="SELECT * FROM members";
					
			Connection conn = null;
			PreparedStatement pstmt= null;
			ResultSet rs = null;
			MemberDto memberDto = null;	
			ArrayList<MemberDto> memberDtos = new ArrayList<MemberDto>();
							
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(sql);
						
				rs = pstmt.executeQuery();  // rs는 반드시 1개 아니면 0개 레코드를 반환
						
				while(rs.next()) {
					String mid = rs.getString("mid");
					String mpw = rs.getString("mpw");
					String mname = rs.getString("mname");
					String memail = rs.getString("memail");
					String mdate = rs.getString("mdate");
							
					memberDto = new MemberDto(mid, mpw, mname, memail, mdate);	
					memberDtos.add(memberDto);
				} 	
						
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(pstmt != null) {
						pstmt.close();
					}
					if(conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}		
			return memberDtos;
		}	
		
		public int modifyMember(String mname, String memail, String mid) {
			
			String sql ="UPDATE members SET mname=?, memail=? WHERE mid=?";
			
			Connection conn = null;
			PreparedStatement pstmt= null;
			
			int success = 0;
			
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, username, password);
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, mname);
				pstmt.setString(2, memail);
				pstmt.setString(3, mid);
				
				success = pstmt.executeUpdate(); // 성공하면 1 반환
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
						if(pstmt != null) {
							pstmt.close();
						}
						if(conn != null) {
							conn.close();
						}
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}		
			return success;
		}	
					
}
