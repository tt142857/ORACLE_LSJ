<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="srchFrm" name="srchFrm" action="${CPATH }/cmn/code/codeGrpList.do">
  <div>
    <label for="srchCdGrp">코드그룹</label>
    <input type="text" id="srchCdGrp" name="srchCdGrp" value="<c:out value='${param.srchCdGrp }' />" />
    <label for="srchCdGrpNm">코드그룹명</label>
    <input type="text" id="srchCdGrpNm" name="srchCdGrpNm" value="<c:out value='${searchVO.srchCdGrpNm }' />" />
    <input type="reset" value="초기화" />
    <input type="submit" value="검색" />
  </div>
</form>

<table>
  <caption>코드 그룹 목록</caption>
  <colgroup>
    <col width="20%">
    <col width="20%">
    <col width="20%">
    <col width="20%">
    <col width="20%">
  </colgroup>
  <thead>
    <tr>
      <th>코드그룹</th>
      <th>코드그룹명</th>
      <th>사용여부</th>
      <th>작성자ID</th>
      <th>작성일</th>
    </tr>
  </thead>
  <tbody>
    <c:if test="${not empty list }">
      <c:forEach var="vo" items="${list }">
        <tr>
          <td><c:out value="${vo.cdGrp }" /></td>
          <td><c:out value="${vo.cdGrpNm }" /></td>
          <td><c:out value="${vo.useYn }" /></td>
          <td><c:out value="${vo.rgstId }" /></td>
          <td><c:out value="${vo.rgstDt }" /></td>
        </tr>
      </c:forEach>
    </c:if>
    <c:if test="${empty list }">
      <tr>
        <td colspan="5">데이터가 없습니다.</td>
      </tr>
    </c:if>
  </tbody>
</table>

<button onclick="fn_goInsert();">등록</button>

<script>
  function fn_goInsert() {
	  location.href = "${CPATH}/cmn/code/insertCodeGrp.do";
  }
</script>