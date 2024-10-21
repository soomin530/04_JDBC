package edu.kh.jdbc.dto;
// 데이터베이스의 각 컬럼 개체(entity) 저장용 클래스가 있는 패키지로
// DB의 도메인과 일치하지 않으며 어떤 로직에 사용되는 영속적이지 않은 정보를 DTO에 구현

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// DTO(Data Transfer Object) : 값을 묶어서 전달하는 용도의 객체
// -> DB에 데이터를 전달하거나, 가져올 때에 사용
// == DB 특정 테이블의 한 행의 데이터를 저장할 수 있는 형태로 class 작성

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String enrollDate;
	// --> enrollDate를 왜 java.sql.Date 타입이 아니라 String 형으로 만들었는가?
	//  ->  DB 조회 시 날짜 데이터를 원하는 형태의 문자열로 
	//		변환하여 조회할 예정 -> TO_CHAR() 이용

}
