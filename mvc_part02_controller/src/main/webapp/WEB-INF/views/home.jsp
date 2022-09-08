<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>home.jsp</h1>
<h1>
	${sessionScope.a }
</h1>
	<form action="doA" method="post">
		<input type="submit" value="확인" />
	</form>
	<a href="doB">doB</a> <br/>
	<a href="doC">doC</a> <br/>
	<!-- param - name : msg | value : hi  -->
	<a href="doD?msg=hi">doD</a> <br/>
	<form action="doD" method="post">
		<input type="text" name="msg" required />
		<input type="number" name="age" required />
		<input type="submit" value="doD Post" />
	</form>
	<br/>
	<hr/>
	
	<a href="product">product</a> <br/>
	<a href="doH">doH</a> <br/>
	<form action="productWrite" method="post">
		<input type="text" name="name" required /> <br/>
		<input type="number" name="price" required /> <br/>
		<input type="submit" value="전송" />
	</form>
	<br/>
	<hr/>
	
	<a href="redirect">redirect</a> <br/>
	
</body>
</html>
