<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>죄송합니다. --${uri}</h1>
<p>
사용자의 요청이 갑자기 많아져서
서비스에 일시적인 장애가 발생하였습니다.
잠시후, 다시 시도해 주세요.<br>
</p><hr>
	<pre style="color:red; font-weight:bold">	
${error }
	</pre>
</body>
</html>