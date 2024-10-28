<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title> 학생 관리 프로그램 </title>

<%-- css 파일 연결 (webapp 폴더 기준으로 경로 작성)--%>
<link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
	<h1>[ 학생 관리 프로그램 ]</h1>
	<hr>
	<h2>학생 목록</h2>

	<table id="studList" border="1">
		<thead>
			<tr>
				<th>학생 번호</th>
				<th>학생 이름</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${studList}" varStatus="vs" var="student">
				<tr>

					<th>${vs.current.studNo}</th>

					<td>
						<%-- 이름 클릭 시 그 학생의 studList 요소 내용을 상세 조회하기
							--%> <a href="/student/detail?studNo=${student.studNo}">${student.stdName}</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

