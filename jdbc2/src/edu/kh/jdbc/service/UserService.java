package edu.kh.jdbc.service; 
//데이터 가공 처리
//DB 연결, 반환 코드
//트렌잭션 처리

import static edu.kh.jdbc.common.JDBCTemplate.*;
//JDBCTemplate에 getConnection() 만들어와서 여기 import 함!
// import static : 지정된 경로에 존재하는 static 구문을 모두 얻어와
// 				   클래스명.메서드명()이 아닌 메서드명()만 작성해도
// 				   호출 가능하게 함

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.common.JDBCTemplate;
import edu.kh.jdbc.dao.UserDAO;
import edu.kh.jdbc.dto.User;

// 요청, 전달할 게 없기 때문에 Controller는 없음!
public class UserService {
	
	// 필드
	private UserDAO dao = new UserDAO();
	

	/** 전달받은 아이디와 일치하는 User 정보 반환 서비스
	 * @param input (입력된 아이디)
	 * @return 아이디가 일치하는 회원 정보, 없으면 null
	 */
	public User selectId(String input) {
		
		// 커넥션 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 메서드 호출 후 결과 반환 받기
		User user = dao.selectId(conn, input);
		
		// 다 쓴 커넥션 닫기
		JDBCTemplate.close(conn);
		
		return user; // User 객체 반환
					 // DB에서 조회한 결과 반환
	}


	/** User 등록 서비스
	 * @param user : 입력받은 id, pw, name이 세팅된 객체
	 * @return 삽입 성공한 결과 행의 개수
	 */
	public int insertUser(User user) throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = /*JDBCTemplate.*/getConnection();
		// import static edu.kh.jdbc.common.JDBCTemplate.*; 
		//  ㄴ 상단에 해당 구문 작성 시 getConnection();만 작성해도 됨.
		
		
		// 2. 데이터 가공(할 게 없으면 넘어감)
		
		// 3. DAO 메서드 호출(INSERT) 후
		//	  결과(삽입 성공한 행 개수, int) 반환 받기
		int result = dao.insertUser(conn, user);
		
		// 4. INSERT(DML) 시 수행 결과에 따라 트랜젝션 제어 처리
		if(result > 0) { // INSERT 성공
			commit(conn);
			
		} else { // INSERT 실패
			rollback(conn);
		}
		
		// 5. Connection 반환하기
		close(conn);
		
		// 6. 결과 반환
		return result;
	}


	/** User 전체 조회 서비스
	 * @return 조회된 User가 담겨있는 List
	 */
	public List<User> selectAll() throws Exception {
		
		// 1. 커넥션 생성
		Connection conn = getConnection();
		
		// 2. 데이터 가공(없으면 넘어감)
		
		// 3. DAO 메서드 호출(SELECT) 후 결과(List<User>) 반환받기
		List<User> userList = dao.selectAll(conn);
		
		// 4. DML인 경우 트렌잭션 처리
		// 	  SELECT는 안 해도 된다!
		
		// 5. Connection 반환
		close(conn);
		
		// 6. 결과 반환
		return userList;
	}


	/** User 중 이름에 검색어가 포함된 회원 조회
	 * @param input : 입력한 값
	 * @return userList : 조회된 회원 리스트
	 */
	public List<User> selectName(String input) throws Exception {
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// DAO 메서드 호출(SELECT) 후 결과(List<User>) 반환받기
		List<User> userList = dao.selectName(conn, input);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return userList;
	}


	/** USER_NO를 입력 받아 일치하는 User 조회(SELECT)
	 * @param input : 입력한 사용자 번호
	 * @return selectUser : 조회된 회원 or null
	 */
	public User selectUser(int input) throws Exception {
		
		Connection conn = getConnection();
		
		User user = dao.selectUser(conn, input);
		
		return user;
	}


	/** USER_NO를 입력 받아 일치하는 User 삭제
	 * @param input : 입력한 사용자 번호
	 * @return result
	 */
	public int deleteUser(int input) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteUser(conn, input);
		
		//  result에 뭐가 있는지에 따라 트렌잭션 제어 처리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}


	/** ID, PW가 일치하는 회원의 USER_NO 조회
	 * @param userId
	 * @param userPw
	 * @return
	 */
	public int selectUserNo(String userId, String userPw) throws Exception {
		
		Connection conn = getConnection();
		
		int userNo = dao.selectUser(conn, userId, userPw); // 오버로딩 적용 
		
		close(conn); // 커넥션 반환
		
		return userNo; // 결과 반환
	}


	/** userNo가 일치하는 User의 이름 수정 서비스
	 * @param userName
	 * @param userNo
	 * @return result
	 */
	public int updateName(String userName, int userNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateName(conn, userName, userNo);
		
		// 트렌잭션 제어 처리
		if(result > 0) { // 수정 성공
			commit(conn);
			
		} else { // 수정 실패
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


	/** 아이디 중복 확인 서비스
	 * @param userId
	 * @return count
	 */
	public int idCheck(String userId) throws Exception {
		
		Connection conn = getConnection();
		
		int count = dao.idCheck(conn, userId);
		
		close(conn);
		
		return count;
	}
	
	/** userList에 있는 모든 user INSERT 서비스
	 * @param userList
	 * @return result : 삽입된 행의 개수
	 */
	public int multiInsertUser(List<User> userList) throws Exception{
		
		Connection conn = getConnection();
		
		// 다중 INSERT 방법
		// 1) SQL 을 이용한 다중 INSERT
		// 2) Java 반복문을 이용한 다중 INSERT (이거 사용!)
		
		int count = 0; // 삽입 성공한 행의 개수 count
		
		// 1행씩 삽입
		for(User user : userList) {
			int result = dao.insertUser(conn, user);
			count += result; // 삽입 성공한 행의 개수를 count에 누적
		}
		
		//count--; // 강제 실패
		
		// 트랜잭션 제어 처리 
		// 전체 삽입 성공 시 commit / 아니면 rollback(일부 삽입, 전체 실패)
		if(count == userList.size()) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return count;
	}

}
