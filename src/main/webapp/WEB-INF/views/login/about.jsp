<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

[Holler]
<br />
ID : <c:out value="${userVO.memId }" />
<br />
이름 : <c:out value="${userVO.memNm }" />
<br />
<sec:authentication property="principal" var="principal" />
<c:set var="userVO2" value="${principal.memVO }" />
ID : <c:out value="${userVO2.memId}" />
<br />
이름 : <c:out value="${userVO2.memNm }" />
<br />
