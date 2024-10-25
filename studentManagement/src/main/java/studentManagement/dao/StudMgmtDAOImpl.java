package studentManagement.dao;

import static studentManagement.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import student.dto.Student;



public class StudMgmtDAOImpl implements StudMgmtDAO { 
	
	// JDBC 객체 참조변수 + Properties 참조변수 선언
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public StudMgmtDAOImpl() {
		// StudMgmtDAOImpl 객체가 생성될 때
		// sql.xml 파일의 내용을 읽어와
		// Properties prop 객체에 K:V 세팅
		
		try {
			
			String filePath = StudMgmtDAOImpl.class
					.getResource("/xml/sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath)); 
			
		} catch (Exception e) {
			System.out.println("sql.xml 로드 중 예외발생");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Student> studListFullview(Connection conn) throws Exception {
		
		List<Student> studList = new ArrayList<Student>();
		
		try {
			
			String sql = prop.getProperty("studListFullview");
			// sql.xml에 있는 studListFullview 가 sql 이라는 변수에 들어감 
			// prop에 저장된 K:V 중 studListFullview를 가져옴
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// Builder 패턴 : 특정 값으로 초기화 된 객체를 쉽게 만드는 방법
				// -> Lombok에서 제공하는 @Builder 어노테이션을 DTO에 작성해두면
				// 	  사용 가능한 패턴
				Student student = Student.builder()
						.studNo(rs.getInt("STD_NO"))
						.stdName(rs.getString("STD_NAME"))
						.build();
				
				studList.add(student);
			}
			
			
			
		} finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return studList;
	}

	@Override
	public Student studDetailView(Connection conn, int studNo) throws Exception {
		
		Student student = null;  
		
		try {
			
			String sql = prop.getProperty("studDetailView");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, studNo); 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				student = Student.builder()
						.studNo(rs.getInt("STD_NO"))
						.stdName(rs.getString("STD_NAME"))
						.stdAge(rs.getInt("STD_AGE"))
						.stdGender(rs.getString("STD_GENDER"))
						.stdScore(rs.getString("STD_SCORE"))
						.build();
			} 
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return student;
	}

	@Override
	public int studUpdate(Connection conn, int studNo, String stdName, String stdAge, String stdScore)
			throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("todoUpdate");
			
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, stdName);
			pstmt.setString(2, stdAge);
			pstmt.setString(3, stdScore);
			pstmt.setInt(4, studNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
		}
		return result;
	}

	@Override
	public int studDelete(Connection conn, int studNo) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("studDelete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, studNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	@Override
	public int studAdd(Connection conn, String stdName, int stdAge, String stdGender, String stdScore)
			throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("studAdd");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, stdName);
			pstmt.setInt(2, stdAge);
			pstmt.setString(3, stdGender);
			pstmt.setString(4, stdScore);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		return result;
	}

}
