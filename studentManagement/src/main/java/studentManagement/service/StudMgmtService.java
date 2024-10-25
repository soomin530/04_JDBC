package studentManagement.service;

import java.util.List;

import student.dto.Student;

public interface StudMgmtService {

	/** 모든 학생 목록 출력 서비스
	 * @return
	 * @throws Exception
	 */
	List<Student> studListFullview() throws Exception;

	/** 상세조회 서비스
	 * @param studNo
	 * @return
	 * @throws Exception
	 */
	Student studDetailView(int studNo) throws Exception;

	/** 수정 서비스
	 * @param studNo
	 * @param stdName
	 * @param stdAge
	 * @param stdScore
	 * @return
	 */
	int studUpdate(int studNo, String stdName, String stdAge, String stdScore) throws Exception;

	/** 삭제 서비스
	 * @param studNo
	 * @return
	 */
	int studDelete(int studNo) throws Exception;

	/** 학생 추가 서비스
	 * @param stdName
	 * @param stdAge
	 * @param stdGender
	 * @param stdScore
	 * @return
	 */
	int studAdd(String stdName, int stdAge, String stdGender, String stdScore) throws Exception;

}
