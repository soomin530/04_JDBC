package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.dto.Todo;
import edu.kh.todoList.service.TodoListService;
import edu.kh.todoList.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// 전달 받은 파라미터 얻어오기
			int todoNo = Integer.parseInt(req.getParameter("todoNo"));

			// 서비스 호출 후 결과 반환받기
			TodoListService service = new TodoListServiceImpl();
			int result = service.todoDelete(todoNo); 
			
			// session scope 객체 얻어오기
			HttpSession session = req.getSession();
			
			// 삭제 성공 시
			if(result > 0) {
			
				session.setAttribute("message", "할 일이 삭제되었습니다."); 
				resp.sendRedirect("/");
				return;
			}
			
			
			// 삭제 실패 시
			session.setAttribute("message", "todo가 존재하지 않습니다."); 
			resp.sendRedirect("/");
			
			
			// Controller 이름 : DeleteServlet o
			// 삭제 성공 시 : "할 일이 삭제되었습니다" alert 창 띄우기 o
			// 삭제 실패 시 : "todoNo가 존재하지 않습니다" alert 창 띄우기 o
			// 성공/실패 메인페이지로 redirect o

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
