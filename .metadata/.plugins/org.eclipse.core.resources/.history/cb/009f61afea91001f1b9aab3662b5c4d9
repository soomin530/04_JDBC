package edu.kh.jdbc.common;
// 클래스 내부의 중복코드를 처리하는 클래스가 담겨있는 패키지로
// Connection 생성, Connection/Statement/PreparedStatement 반환 메소드, 
// 트랜젝션(commit, rollback)이 묶여있음	

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/*
 * Template : 양식, 틀, 주형
 * -> "미리 만들어뒀다" 의미
 * 
 * JDBCTemplate : JDBC 관련 작업을 위한 코드를
 * 				  미리 작성해서 제공하는 클래스
 * 
 * - Connection 생성
 * - AutoCommit false
 * - commit / rollback
 * - 각종 close()
 * 
 * 
 *  ********* 중요 *********
 *  어디서든 JDBCTemplate 클래스를
 *  객체로 만들지 않고도 메서드를 사용할 수 있도록 하기 위해
 *  모든 메서드를 public static 으로 선언!!!!!
 * 
 * */

// driver.xml에 있는 파일의 내용을 JDBCTemplate에서 읽어옴

public class JDBCTemplate {

	// 필드
	private static Connection conn = null;
	// -> static 메서드에서 사용 가능한 필드로 static 필드 선언

	// 메서드

	/**
	 * 호출 시 Connection 객체를 생성해서 반환하는 메서드 + AutoCommit 끄기
	 * 
	 * @return conn
	 */
	public static Connection getConnection() {

		try {

			// 이전에 참조하던 Connection 객체가 존재하고
			// 아직 close 된 상태가 아니라면
			// 새로 만들지 않고 기존 Connection 반환

			if (conn != null && !conn.isClosed()) {
				// conn이 null이 아니고 close가 되지 않았다면 ( conn 앞에 ! 붙이기)
				return conn;
			}

			/*
			 * driver.xml 파일 만들어 내용 써놓고 여기서 읽어오기
			 * 
			 * 이유 1 : 보안상의 이유 (Github에 DB 연결 정보 등 올리면 해킹하라는 뜻..) --> .gitignore 파일에
			 * driver.xml 작성하여 git이 관리 못하게.
			 * 
			 * 이유 2: 혹시라도 DB 연결 정보가 변경될 경우 Java 코드가 아닌 읽어오는 파일의 내용을 수정하면 되기 때문에 Java 코드 수정 X
			 * -> 추가 재컴파일 필요 X
			 * 
			 */

			// 1. Properties 객체 생성
			// 	  - Map 의 자식 클래스
			// 	  - K, V가 모두 String 타입
			// 	  - xml 파일 입출력을 쉽게 할 수 있는 메서드 제공
			// 	    Properties.storeToXML() -> xml 파일 만들기
			// 	    Properties.loadFromXML() -> xml 파일 읽어오기
			Properties prop = new Properties();

			// 2. Properties 메서드를 이용해서
			//    driver.xml 파일 내용을 읽어오기
			
			// resources 폴더의 파일을 읽어오는 방법
			String filePath = "driver.xml";
			// -> 빌드 시 컴파일된 JDBCTemplate.class 파일의 위치에서
			// /xml/driver.xml 파일을 찾아 실제 경로를 얻어오는 방법
			// (src/main/resources 폴더 기준으로 경로 작성!!)
			
			
			System.out.println(filePath); 

			prop.loadFromXML(new FileInputStream(filePath));

			// prop에 저장된 값(driver.xml에서 읽어온 값)을 이용해서
			// Connection 객체 생성

			// prop.getProperty("key") : key가 일치하는 value 를 반환
			Class.forName(prop.getProperty("driver"));
			// driver.xml에서 dirver 키에 작성된 값 oracle.jdbc.driver.OracleDriver 반환

			String url = prop.getProperty("url");
			// jdbc:oracle:thin:@localhost:1521:XE

			String userName = prop.getProperty("userName");
			// todoList_jdbc

			String password = prop.getProperty("password");
			// todoList1234

			conn = DriverManager.getConnection(url, userName, password);

			// 만들어진 Connection 에서 AutoCommit 끄기
			conn.setAutoCommit(false);
			// 원할 때 commit, rollback 할 수 있도록!

		} catch (Exception e) {
			System.out.println("커넥션 생성 중 예외 발생..");
			e.printStackTrace();
		}

		return conn;
	}

	// --------------------------------------------------------------------

	/**
	 * 전달받은 커넥션에서 수행한 SQL을 COMMIT 하는 메서드
	 * 
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * 전달받은 커넥션에서 수행한 SQL을 ROLLBACK 하는 메서드
	 * 
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------------------------------------------------------------------

	/**
	 * 전달받은 커넥션을 close(자원 반환)하는 메서드
	 * 
	 * @param conn
	 */
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 전달받은 Statment + PreparedStatement 둘 다 close() + 다형성 업캐스팅 적용 ->
	 * PreparedStatement는 Statement의 자식
	 * 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 전달받은 ResultSet 을 close() 하는 메서드
	 * 
	 * @param rs
	 */
	public static void close(ResultSet rs) { // 오버로딩 적용되어 같은 메서드명이지만
											 // 매개변수 타입이 다르기 때문에 에러 X

		try {
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

