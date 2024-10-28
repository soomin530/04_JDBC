<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Todo List</title>

  <%-- css 파일 연결 (webapp 폴더 기준으로 경로 작성)--%>
  <link rel="stylesheet" href="/resources/css/main.css">
</head>
<body>
  <h1>Todo List</h1>

  <h3>전체 Todo 개수 :  / 
      완료된 Todo 개수 : </h3>

  <hr>

  <h4>할 일 추가</h4>
  <form action="/todo/add" method="post" id="addForm">
    <div>
      제목 : <input type="text" name="title">
    </div>
    <div>
      <textarea name="detail" 
        rows="3" cols="50" placeholder="상세 내용"></textarea>
    </div>

    <button>추가</button>
  </form>

  <hr>

  <%-- 할 일 목록 출력 --%>
  <table id="todoList" border="1">
    <thead>
      <tr>
        <th>출력 번호</th> <!-- 페이지에서 보이는 용도의 단순 출력 번호 -->
        <th>todo 번호</th> <!-- 실제 이 데이터의 todoNo 고유번호 -->
        <th>할 일 제목</th>
        <th>완료 여부</th>
        <th>등록 날짜</th>
      </tr>
    </thead>
  
    <tbody>
      
        <tr>
        	
          <th></th> <%-- 단순 출력 번호 --%>
          <th></th> <%-- todoNo --%>

          <td>
          <%-- 제목 --%>
            <a href=""></a>
          </td>

          <%-- 완료 여부 --%>
          <th></th>

			
          <td></td><%-- 등록일 --%>
        </tr>
    </tbody>
  </table>


  <%-- JS 연결 --%>
  <script src=""></script>
</body>
</html>