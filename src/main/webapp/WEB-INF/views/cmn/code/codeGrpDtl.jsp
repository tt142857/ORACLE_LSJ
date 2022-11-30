<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table>
  <caption>코드 그룹 상세</caption>
  <colgroup>
    <col width="20%">
    <col width="80%">
  </colgroup>
  <tbody>
    <tr>
      <th>코드그룹</th>
      <td><c:out value="${data.cdGrp }" /></td>
    </tr>
    <tr>
      <th>코드그룹명</th>
      <td><c:out value="${data.cdGrpNm }" /></td>
    </tr>
    <tr>
      <th>순서</th>
      <td><c:out value="${data.ord }" /></td>
    </tr>
    <tr>
      <th>사용여부</th>
      <td><c:out value="${data.useYn }" /></td>
    </tr>
    <tr>
      <th>등록자</th>
      <td><c:out value="${data.rgstId }" /></td>
    </tr>
    <tr>
      <th>등록일</th>
      <td><c:out value="${data.rgstDt }" /></td>
    </tr>
  </tbody>
</table>

<button onclick="fn_goUpdate();">수정</button>
<button onclick="fn_delete();">삭제</button>
<button onclick="fn_goList();">목록</button>

<form id="frm" name="frm">
  <input type="hidden" id="cdGrp" name="cdGrp" />
</form>

<script>
  const FRM_PATH = "${CPATH}/cmn/code/";

  // 수정 페이지 이동
  function fn_goUpdate() {
  // location.href = '${CPATH}/cmn/code/updateCodeGrp.do?cdGrp=<c:out value="${data.cdGrp}" />';
	  var frm = $('#frm');
	  frm.attr('action', FRM_PATH + 'updateCodeGrp.do');
	  frm.attr('method', 'get');
	  $('#cdGrp').val('<c:out value="${data.cdGrp}" />');
	  frm.submit();
  }

  // 코드 그룹 삭제
  function fn_delete() {
	  if (confirm('삭제 하시겠습니까?')) {
		    var frm = $('frm');
		    frm.attr('action', FRM_PATH + 'deleteCodeGrp.do');
		    frm.attr('method', 'post');
		    $('#cdGrp').val('<c:out value="${data.cdGrp}" />');
		    frm.submit();
	  }
  }

  // 목록 이동
  function fn_goList() {
	  var srchCdGrp = '<c:out value="${param.srchCdGrp}" />';
	  location.href = '${CPATH}/cmn/code/codeGrpList.do?srchCdGrp=' + srchCdGrp;
  }
</script>