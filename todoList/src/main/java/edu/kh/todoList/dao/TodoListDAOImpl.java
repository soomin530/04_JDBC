package edu.kh.todoList.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todoList.dto.Todo;
import static edu.kh.todoList.common.JDBCTemplate.*;

public class TodoListDAOImpl implements TodoListDAO {
	
	// JDBC 객체 참조변수 + Properties 참조 변수 선언
	// sql.xml 파일의 내용을 여기서 읽어와야 하기 때문에 Properties 필요하다!!
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public TodoListDAOImpl() { // 기본생성자
		// TOdoListDAOImpl 객체가 생성될 때
		// sql.xml 파일의 내용을 읽어와
		// Properties prop 객체에 K:V 형태로 세팅
		
		try {
			
			String filePath = TodoListDAOImpl.class
					.getResource("/xml/sql.xml").getPath();
			// sql.xml 과 연결하는 통로
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			// sql.xml에 작성된 것 K:V꼴로 읽어들임
			
			
		} catch (Exception e) {
			System.out.println("sql.xml 로드 중 예외 발생");
			// 해당 문장이 출력될 경우
			// 여기 아니면 sql.xml에서 문제가 발생되고 있는 거임!!
			e.printStackTrace();
		}
		
	}

	@Override   // throws Exception class, interface 둘 다 작성해야 에러 발생 안 함!!
	public List<Todo> todoListFullView(Connection conn) throws Exception {
		
		// 결과 저장용 변수 선언
		List<Todo> todoList = new ArrayList<Todo>();
		
		try {
			
			String sql = prop.getProperty("todoListFullView"); 
			// sql.xml에 있는 todoListFullView 가 sql 이라는 변수에 들어감 
			// prop에 저장된 K:V 중 todoListFullView를 가져옴
			
			stmt = conn.createStatement(); 
			// 객체 생성 X
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// Builder 패턴 : 특정 값으로 초기화 된 객체를 쉽게 만드는 방법
				// -> Lombok에서 제공하는 @Builder 어노테이션을 DTO에 작성해두면
				// 	  사용 가능한 패턴
				
				boolean complete = rs.getInt("TODO_COMPLETE") == 1; // 1이면 true, 0이면 false
				
				Todo todo = Todo.builder()
							.todoNo(rs.getInt("TODO_NO"))
							.title(rs.getString("TODO_TITLE")) 
							.complete(complete) 
							.regDate(rs.getString("REG_DATE"))
							.build();
				
				todoList.add(todo);
				
			}
			
		} finally {
			close(rs);
			close(stmt);
			
		}
		
		return todoList;
	}

	@Override
	public int getCompleteCount(Connection conn) throws Exception {
		
		int completeCount = 0;
		
		try {
			
			String sql = prop.getProperty("getCompleteCount");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				completeCount = rs.getInt(1);
			}
			
			
		} finally {
			close(rs);
			close(stmt);
			
		}
		return completeCount;
	}
	

	@Override
	public int todoAdd(Connection conn, String title, String detail) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoAdd");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, detail);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		
		return result;
	}
	

	@Override
	public Todo todoDetailView(Connection conn, int todoNo) throws Exception {
		
		Todo todo = null; // 결과 저장용 변수 선언
		
		try {
			
			String sql = prop.getProperty("todoDetailView");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, todoNo); 
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				boolean complete = rs.getInt("TODO_COMPLETE") == 1;
	
				todo = Todo.builder()
						.todoNo(rs.getInt("TODO_NO"))
						.title(rs.getString("TODO_TITLE"))
						.detail(rs.getString("TODO_DETAIL"))
						.complete(complete)
						.regDate(rs.getString("REG_DATE"))
						.build();
			}
			
		} finally {
			
			close(rs);
			close(pstmt);	
			
		}
		
		return todo;
	}

	@Override
	public int todoComplete(Connection conn, int todoNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoComplete");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todoNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	@Override
	public int todoUpdate(Connection conn, int todoNo, String title, String detail) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("todoUpdate");
			
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, title);
			pstmt.setString(2, detail);
			pstmt.setInt(3, todoNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
		}
		return result;
	}

	@Override
	public int todoDelete(Connection conn, int todoNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("todoDelete");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todoNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
		}
		return result;
	}

}
