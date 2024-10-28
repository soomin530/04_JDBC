<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세 조회</title>
<%-- css 파일 연결 (webapp 폴더 기준으로 경로 작성)--%>
<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>

<!-- 선택된 학생에 대한 상세 정보 출력
	(이름/나이/성별/성적)
	 목록으로 버튼
	 수정 버튼 (클릭 시 수정 화면으로 이동)
	 삭제 버튼 (성공 시 메인페이지로, 실패 시 상세조회 화면으로)
-->


	<h2>학생 상세 정보</h2>
	<hr>
	<table id="studList" border="1">
		<thead>
			<tr>
				<th>학생 번호</th>
				<th>학생 이름</th>
				<th>학생 나이</th>
				<th>학생 성별</th>
				<th>학생 성적</th>
			</tr>
		</thead>

		<tbody>
		<tr>
			<td>${student.studNo}</td>
			<td>${student.stdName}</td>
			<td>${student.stdAge}</td>
			<td>${student.stdGender}</td>
			<td>${student.stdScore}</td>
		</tr>
		</tbody>
	</table>



	<div class="btn-container">
		<div>
			<button id="goToList">목록으로</button>
			<button id="updateBtn">수정</button>
			<button id="deleteBtn">삭제</button>
		</div>
	</div>

	<%-- session에 message가 있다면 --%>
	<c:if test="${not empty sessionScope.message}">
		<script>
			alert("${message}");
		</script>
		
		<c:remove var="message" scope="session"/>
		
	</c:if>


	<script src="/resources/js/detail.js"></script>


</body>
</html>