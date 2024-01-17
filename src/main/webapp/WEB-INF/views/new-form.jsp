
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <meta charset="UTF-8">
 <title>Title</title>
</head>
<body>
<!-- 상대경로 사용(재사용을 위함), [현재 URL이 속한 계층 경로 + /save], WEB-INF에 쓰인 거는 외부에서 직접 접속X, 항상 컨트롤러(서블릿) 거쳐서 포이딩 해야 -->
<form action="save" method="post">
 username: <input type="text" name="username" />
 age: <input type="text" name="age" />
 <button type="submit">전송</button>
</form>
</body>
</html>