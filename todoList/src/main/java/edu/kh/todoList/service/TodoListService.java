package edu.kh.todoList.service;

import java.util.Map;

public interface TodoListService  {

	/** 할 일 목록 반환 서비스
	 * @return todoList + 완료 개수 = Map으로 묶어서 반환!
	 */
	Map<String, Object> todoListFullView() throws Exception; // 중괄호 없음 -> 추상메서드
	// 묵시적으로 public 
	

	/** 할 일 추가 서비스
	 * @param title
	 * @param detail
	 * @return int 성공 시 추가된 행의 개수 / 실패 시 0 반환
	 */
	int todoAdd(String title, String detail) throws Exception;

}
