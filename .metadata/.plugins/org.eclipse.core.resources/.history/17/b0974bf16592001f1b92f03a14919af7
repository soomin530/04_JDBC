package edu.kh.jdbc.dao;
//DBMS에 접속하여 실제 데이터를 전송하거나

//결과 값을 전달 받는 클래스가 있는 패키지
//가공 처리된 데이터를 DB에 전달

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dto.User;
import static edu.kh.jdbc.common.JDBCTemplate.*;

// DAO(Data Access Object)
// 데이터가 저장된 곳에 접근하는 용도의 객체
// -> DB에 접근하여 Java에서 원하는 결과를 얻기 위해
// 	  sql을 수행하고 결과를 반환 받는 역할
public class UserDAO {

	// 필드
	// - DB 접근 관련한 JDBC 객체 참조 변수 미리 선언
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 전달 받은 Connection을 이용해서 DB에 접근하여 전달 받은 아이디와 일치하는 User 정보 조회하기
	 * 
	 * @param conn  : Service에서 생성한 Connection 객체
	 * @param input : View에서 입력받은 아이디
	 * @return 아이디가 일치하는 회원의 User 또는 null
	 */
	public User selectId(Connection conn, String input) {

		User user = null; // 결과 저장용 변수

		try {

			// SQL 작성
			String sql = "SELECT * FROM TB_USER WHERE USER_ID = ?";

			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// ? (위치 홀더)에 알맞은 값 대입
			pstmt.setString(1, input); // input이 ? 에 들어와야 한다.

			// SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery(); // SELECT문은 executeQuery!

			// 조회 결과가 있을 경우
			// -> 중복되는 아이디가 없을 경우
			// 1행만 조회되기 때문에
			// while문 보다는 if를 사용하는 게 효과적

			if (rs.next()) { // 첫 행에 데이터가 존재한다면

				// 각 컬럼의 값 얻어오기
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");

				// java.sql.Date 활용
				Date enrollDate = rs.getDate("ENROLL_DATE");

				// 조회된 컬럼값을 이용해서 User 객체 생성
				user = new User(userNo, userId, userPw, userName, enrollDate.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			// 예외 발생 하든 안 하든 사용한 JDBC 객체 자원 반환(close)
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt); // 오버로딩 적용

			// Connection 객체는 생성된 Service 에서 close!!!
		}

		return user; // 결과 반환(생성된 User 또는 null)
	}

	/**
	 * User 등록 DAO 메서드
	 * 
	 * @param conn : DB 연결 정보가 담겨있는 Connection 객체
	 * @param user : 입력 받은 id, pw, name이 세팅된 User 객체 (View에서 왔당)
	 * @return INSERT 결과 행의 개수
	 */
	public int insertUser(Connection conn, User user) throws Exception {

		// SQL 수행 중 발생하는 예외를
		// catch로 처리하지 않고, throws를 이용해서 호출부로 던져 처리
		// catch문 필요 없다 !

		// 1. 결과 저장용 변수 선언
		int result = 0;

		try {

			// 2. SQL 작성
			String sql = """
					INSERT INTO TB_USER
					VALUES(SEQ_USER_NO.NEXTVAL, ?, ?, ?, DEFAULT)""";

			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);

			// 4. ?(위치홀더)에 알맞은 값 대입
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());

			// 5. SQL(INSERT) 수행(executeUpdate()) 후 결과(삽입된 행의 개수)반환받기
			result = pstmt.executeUpdate();

			// catch 없는 이유 : View 까지 Exception 던질 거기 때문에!

		} finally {
			// 6. 사용한 jdbc 객체 자원 반환(close)
			close(pstmt);

		}
		// 결과 저장용 변수에 저장된 값 반환
		return result;
	}

	/**
	 * User 전체 조회 DAO 메서드
	 * 
	 * @param conn
	 * @return userList
	 */
	public List<User> selectAll(Connection conn) throws Exception {

		// 1. 결과 저장용 변수 선언
		// -> List 같은 컬렉션을 반환하는 경우에는
		// 변수 선언 시 객체도 같이 생성해두자!
		List<User> userList = new ArrayList<User>();

		try {

			// 2. SQL 작성
			String sql = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월"DD"일"') ENROLL_DATE
					FROM TB_USER
					ORDER BY USER_NO ASC
					""";

			// 3. PreparedStatement 생성
			pstmt = conn.prepareStatement(sql);

			// 4. ?에 알맞은 값 대입(없으면 패스)

			// 5. SQL(SELECT) 수행(executeQuery()) 후
			// 결과 반환(ResultSet) 받기
			rs = pstmt.executeQuery();

			// 6. 조회 결과를 1행씩 접근하여 컬럼 값 얻어오기

			// 몇 행이 조회될 지 모른다 -> while
			// 무조건 1행이 조회된다 -> if

			while (rs.next()) {

				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				// - java.sql.Date타입으로 값을 저장하지 않은 이유
				// -> TO_CHAR()를 이용해서 문자열 형식으로 변환했기 때문에 String

				// userList에 추가
				// -> User 객체 생성해 조회된 값을 담고
				// userList 에 추가하기!

				User user = new User(userNo, userId, userPw, userName, enrollDate);

				userList.add(user);
			}

		} finally {
			close(rs);
			close(pstmt);
		}

		// 조회 결과가 담긴 List 반환
		return userList;
	}

	/**
	 * User 중 이름에 검색어가 포함된 회원 조회
	 * 
	 * @param conn
	 * @return
	 */
	public List<User> selectName(Connection conn, String input) throws Exception {
		
		// 1. 결과 저장용 변수 선언
		// -> List 같은 컬렉션을 반환하는 경우에는
		// 	  변수 선언 시 객체도 같이 생성해두자!
		List<User> userList = new ArrayList<User>();

		try {
			
			String sql = """
					SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
					TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월"DD"일"') ENROLL_DATE
					FROM TB_USER
					WHERE USER_NAME LIKE '%'|| ?||'%'
					ORDER BY USER_NO ASC
					""";
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// ? (위치 홀더)에 알맞은 값 대입
			pstmt.setString(1, input); // input이 ? 에 들어와야 한다.
			
			rs = pstmt.executeQuery(); 
			
			// 몇 행이 조회될 지 모르기 때문에 while
			while(rs.next()) {
			int userNo = rs.getInt("USER_NO");
			String userId = rs.getString("USER_ID");
			String userPw = rs.getString("USER_PW");
			String userName = rs.getString("USER_NAME");
			String enrollDate = rs.getString("ENROLL_DATE");
			// - java.sql.Date타입으로 값을 저장하지 않은 이유
			// -> TO_CHAR()를 이용해서 문자열 형식으로 변환했기 때문에 String
			
			User user = new User(userNo, userId, userPw, userName, enrollDate);

			userList.add(user);
			
			}

			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		

		return userList;
	}

	/** USER_NO를 입력 받아 일치하는 User 조회
	 * @param conn
	 * @param input
	 * @return
	 */
	public User selectUser(Connection conn, int input) throws Exception {
		
		// 결과 저장용 변수 선언
		User user = null;
		
		try {
			
			String sql = """
				SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
				TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월"DD"일"') ENROLL_DATE
				FROM TB_USER
				WHERE USER_NO LIKE ?
				ORDER BY USER_NO ASC
				""";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userNo = rs.getInt("USER_NO");
				String userId = rs.getString("USER_ID");
				String userPw = rs.getString("USER_PW");
				String userName = rs.getString("USER_NAME");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				user = new User(userNo, userId, userPw, userName, enrollDate);
			}
			
			
		} finally {
			
			close(rs);
			close(pstmt);
			
			
		}
		return user;
	}

	/** USER_NO를 입력 받아 일치하는 User 삭제
	 * @param conn
	 * @param input
	 * @return result
	 */
	public int deleteUser(Connection conn, int input) throws Exception {
		
		// 결과 저장용 변수
		int result = 0;
		
		try {
			String sql = """
						DELETE FROM TB_USER
						WHERE USER_NO = ?
						""";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, input);
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	/** ID, PW가 일치하는 회원의 USER_NO 조회
	 * @param conn
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public int selectUser(Connection conn, String userId, String userPw) throws Exception {
		
		int userNo = 0; // 결과 저장용 변수 선언
		
		try {
			
			String sql = """
						SELECT USER_NO
						FROM TB_USER
						WHERE USER_ID = ?
						AND USER_PW = ?
						""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			
			rs = pstmt.executeQuery();
			
			// 조회된 1행이 있을 경우
			if(rs.next()) {
				userNo = rs.getInt("USER_NO");
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		return userNo; // 조회 성공 시 USER_NO, 실패 시 0 반환
	}

	/** userNo가 일치하는 User의 이름 수정 DAO
	 * @param conn
	 * @param userName
	 * @param userNo
	 * @return result
	 */
	public int updateName(Connection conn, String userName, int userNo) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = """
						UPDATE TB_USER
						SET USER_NAME = ?
						WHERE USER_NO = ?
						""";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();		
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}

	/** 아이디 중복 확인 DAO
	 * @param conn
	 * @param userId
	 * @return
	 */
	public int idCheck(Connection conn, String userId) throws Exception {
		
		int count = 0;
		
		try {
			
			String sql = """
						SELECT COUNT(*)
						FROM TB_USER
						WHERE USER_ID = ?
						""";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1); // 컬럼명 또는 컬럼 순서
							  // 조회된 컬럼 순서 이용해 컬럼값 얻어오기
			}
		} finally {
			close(rs);
			close(pstmt);

		}
		return count; // 0이나 null 으로 되어있는지 항상 확인!
	}

}
