<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="frm" name="frm" action="<c:url value='/cmn/code/insertCodeGrp.do' />" method="post">
<table>
  <caption>코드 그룹 등록</caption>
  <colgroup>
    <col width="20%">
    <col width="80%">
  </colgroup>
  <tbody>
    <tr>
      <th><label for="cdGrp">코드그룹</label></th>
      <td>
        <input type="text" id="cdGrp" name="cdGrp" title="코드그룹" placeholder="코드 그룹을 입력하세요." />
      </td>
    </tr>
    <tr>
      <th><label for="cdGrpNm">코드그룹명</label></th>
      <td>
        <input type="text" id="cdGrpNm" name="cdGrpNm" title="코드그룹명" placeholder="코드 그룹명을 입력하세요." />
      </td>
    </tr>
    <tr>
      <th><label for="ord">순서</label></th>
      <td>
        <input type="text" id="ord" name="ord" title="순서" placeholder="코드 순서을 입력하세요." />
      </td>
    </tr>
    <tr>
      <th><label for="useYn">사용여부</label></th>
      <td>
        <select id="useYn" name="useYn" title="사용여부">
          <option value="Y">사용</option>
          <option value="N">미사용</option>
        </select>
      </td>
    </tr>
  </tbody>
</table>
</form>

<button onclick="fn_save();">저장</button>

<script>
  function fn_save() {
	  var cdGrp = $('#cdGrp').val();
	  if ($.trim(cdGrp) == '') {
		  alert("코드 그룹을 입력해 주세요.");
		  $('#cdGrp').focus();
		  return;
	  }

	  $('#frm').submit();
  }
</script>