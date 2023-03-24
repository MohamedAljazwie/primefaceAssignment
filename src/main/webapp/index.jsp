<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<fmt:setBundle basename="resources.application" /> 

<html>
<head>
  <title><fmt:message key="welcome.title"/></title>
</head>
<body bgcolor="white">
<c:redirect url="employee/employees.xhtml"/>

</body>
</html>