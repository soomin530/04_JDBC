package studentManagement.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import studentManagement.service.StudMgmtService;
import studentManagement.service.StudMgmtServiceImpl;

@WebServlet("/student/add")
public class AddServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			StudMgmtService service = new StudMgmtServiceImpl();
			
			String stdName = req.getParameter("stdName");
			int stdAge = Integer.parseInt(req.getParameter("stdAge"));
			String stdGender = req.getParameter("stdGender");
			String stdScore = req.getParameter("stdScore");
			
			int result = service.studAdd(stdName, stdAge, stdGender, stdScore);
			
			// 4. 성공/실패 메시지 세팅하기
						String message = null;
						if(result > 0) message = "추가 성공!";
						else		   message = "추가 실패...";
						
						// 5. 기존 req를 사용할 수 없기 때문에
						//	  session 을 이용해서 message 를 세팅
						HttpSession session = req.getSession();
						session.setAttribute("message", message);
						
						// 6. 메인 페이지로 redirect(재요청)
						resp.sendRedirect("/");
						// -> @WebServlet("/") 가 작성된 Servlet을 재요청
						
						// redirect는 무조건 GET 방식!!
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
