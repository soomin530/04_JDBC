package studentManagement.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import student.dto.Student;
import studentManagement.service.StudMgmtService;
import studentManagement.service.StudMgmtServiceImpl;

//"/main" 요청을 매핑하여 처리하는 서블릿
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	/*
	 *   메인페이지 
	 *   
	 * - 등록되어있는 모든 학생 목록 출력
	 * - 학생 목록에서 이름 클릭 시 학생 상세조회 화면 이동
	 * - 학생 추가 버튼 (클릭 시 학생 추가 화면 이동)    
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			StudMgmtService service = new StudMgmtServiceImpl();
			
			List<Student> studList = service.studListFullview();
			
			req.setAttribute("studList", studList);
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
