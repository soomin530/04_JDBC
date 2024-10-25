package studentManagement.dao;

import java.sql.Connection;
import java.util.List;

import student.dto.Student;

public interface StudMgmtDAO {

	/** 학생 목록 전체 조회
	 * @param conn
	 * @return
	 */
	List<Student> studListFullview(Connection conn) throws Exception;

	/** 학생 정보 상세조회
	 * @param conn
	 * @param studNo
	 * @return
	 */
	Student studDetailView(Connection conn, int studNo) throws Exception;

	/** 학생 정보 수정 
	 * @param conn
	 * @param studNo
	 * @param stdName
	 * @param stdAge
	 * @param stdScore
	 * @return
	 */
	int studUpdate(Connection conn, int studNo, String stdName, String stdAge, String stdScore) throws Exception;

	/** 학생 정보 삭제
	 * @param conn
	 * @param studNo
	 * @return
	 */
	int studDelete(Connection conn, int studNo) throws Exception;

	/** 학생 정보 추가
	 * @param conn
	 * @param stdName
	 * @param stdAge
	 * @param stdGender
	 * @param stdScore
	 * @return
	 */
	int studAdd(Connection conn, String stdName, int stdAge, String stdGender, String stdScore) throws Exception; 

}
 