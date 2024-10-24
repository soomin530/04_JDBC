package studentManagement.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import studentManagement.service.StudMgmtService;
import studentManagement.service.StudMgmtServiceImpl;

//"/main" 요청을 매핑하여 처리하는 서블릿
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	/*
	 * 왜 "/main" 메인 페이지 요청을 처리하는 서블릿을 만들었는가?
	 * 
	 * - Servlet(Back-End) 에서 추가한 데이터(DB에서 조회한 데이터)를 메인페이지에서 사용할 수 있게 하려고
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// Service 객체 생성
			// 요청 -> Controller -> Service -> DAO -> DB
			// 응답 <- View <- <- <- <-
			StudMgmtService service = new StudMgmtServiceImpl();
			

			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
