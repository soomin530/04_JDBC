package studentManagement.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import student.dto.Student;
import studentManagement.service.StudMgmtService;
import studentManagement.service.StudMgmtServiceImpl;

@WebServlet("/student/detail")
public class DetailServlet extends HttpServlet {

	/*
	 * 상세조회 화면
	 * 
	 * - 선택된 학생에 대한 상세 정보 출력(이름/나이/성별/성적)
	 * - 목록으로 버튼
	 * - 수정 버튼 (클릭 시 수정 화면으로 이동) 
	 * - 삭제 버튼 (성공 시 메인페이지로, 실패 시 상세조회 화면으로)
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			int studNo = Integer.parseInt(req.getParameter("studNo"));
			// String 형이기 때문에 int형으로 형변환 해주기

			// 서비스 객체 생성
			StudMgmtService service = new StudMgmtServiceImpl();

			Student student = service.studDetailView(studNo);

			// student가 존재하지 않을 경우(null일 경우)
			// -> 메인페이지(/)로 redirect 
			// "학생이 존재하지 않습니다"
			// alert 출력
			if (student == null) {

				// session 객체 생성 후 message 세팅하기
				HttpSession session = req.getSession();
				session.setAttribute("message", "학생이 존재하지 않습니다.");

				resp.sendRedirect("/");
				return;
			}

			// stud가 존재하는 경우
			// detail.jsp로 forward 해서 응답
			req.setAttribute("student", student);

			// JSP 파일 경로 (webapp 폴더 기준으로 작성)
			String path = "/WEB-INF/views/detail.jsp";

			// 요청 발송자를 이용해서 요청 위임
			req.getRequestDispatcher(path).forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
