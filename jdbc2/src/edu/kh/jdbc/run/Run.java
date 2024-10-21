package edu.kh.jdbc.run;

import edu.kh.jdbc.view.UserView;

// 애플리케이션 실행을 위해 main 메서드를 가지고 있는 패키지
public class Run {
	public static void main(String[] args) {
		
		UserView view = new UserView();
		// view.test();
		view.mainMenu();
		
	}
}
