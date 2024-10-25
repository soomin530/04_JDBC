package studentManagement.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import student.dto.Student;
import studentManagement.service.StudMgmtService;
import studentManagement.service.StudMgmtServiceImpl;



@WebServlet("/student/update")
public class UpdateServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 전달 받은 파라미터 얻어오기
			int studNo = Integer.parseInt(req.getParameter("studNo"));
			
			// 수정 화면에는 기존의 제목, 상세내용이
			// input, textarea에 채워져 있는 상태여야 한다!
			// -> 수정 전 제목/내용 조회 == 상세조회 서비스 재호출
			StudMgmtService service = new StudMgmtServiceImpl();
			Student student = service.studDetailView(studNo);
			
			if(student == null) {
				// 메인페이지로 redirect
				resp.sendRedirect("/");
				return;
			}
			
			// request scope에 todo 객체 세팅
			req.setAttribute("student", student);
			
			// 요청 발송자를 통해 forward
			String path = "/WEB-INF/views/update.jsp";
			req.getRequestDispatcher(path).forward(req, resp); // 요청 위임
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 전달 받은 파라미터 얻어오기 (이름, 나이, 성적, 번호)
			String stdName = req.getParameter("stdName");  			   // 이름
			String stdAge = req.getParameter("stdAge");				   // 나이
			String stdScore = req.getParameter("stdScore");			   // 성적
			int studNo = Integer.parseInt(req.getParameter("studNo")); // 번호
			
			StudMgmtService service = new StudMgmtServiceImpl();
			int result = service.studUpdate(studNo, stdName, stdAge, stdScore); 
			
			// 수정 성공 시
			// 상세 조회 페이지로 redirect
			// "수정 되었습니다" message 출력
			
			// 수정 실패 시
			// 수정 화면으로 redirect 후
			// "수정 실패" message 출력
			String url = null;
			String message = null;
			
			if(result > 0) { // 성공
				url = "/todo/detail?studNo=" + studNo;  
				message = "수정 되었습니다";
				
				
			} else { // 실패
				url = "todo/update?studNo=" + studNo;
				message = "수정 실패";
				
			}
			
			// session 객체에 속성 추가
			req.getSession().setAttribute("message", message);
			
			// redirect는 GET 방식 요청
			resp.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
