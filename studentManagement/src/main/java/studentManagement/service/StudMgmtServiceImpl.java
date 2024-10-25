package studentManagement.service;

import static studentManagement.common.JDBCTemplate.close;
import static studentManagement.common.JDBCTemplate.commit;
import static studentManagement.common.JDBCTemplate.getConnection;
import static studentManagement.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import student.dto.Student;
import studentManagement.dao.StudMgmtDAO;
import studentManagement.dao.StudMgmtDAOImpl;

public class StudMgmtServiceImpl implements StudMgmtService {

	private StudMgmtDAO dao = new StudMgmtDAOImpl();

	@Override
	public List<Student> studListFullview() throws Exception { // 전체조회

		Connection conn = getConnection();

		List<Student> studList = dao.studListFullview(conn);

		close(conn);

		return studList;
	}

	@Override
	public Student studDetailView(int studNo) throws Exception { // 상세조회

		Connection conn = getConnection();

		Student student = dao.studDetailView(conn, studNo);

		close(conn);

		return student;
	}

	@Override
	public int studUpdate(int studNo, String stdName, String stdAge, String stdScore) throws Exception { // 학생 수정

		Connection conn = getConnection();

		int result = dao.studUpdate(conn, studNo, stdName, stdAge, stdScore);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);

		return result;
	}

	@Override
	public int studDelete(int studNo) throws Exception { // 학생 삭제
		
		Connection conn = getConnection();
		
		int result = dao.studDelete(conn, studNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	@Override
	public int studAdd(String stdName, int stdAge, String stdGender, String stdScore) throws Exception { // 학생 추가
		
		Connection conn = getConnection();
		
		int result = dao.studAdd(conn, stdName, stdAge, stdGender, stdScore);
		
		close(conn);
		
		return result;
	}

}
