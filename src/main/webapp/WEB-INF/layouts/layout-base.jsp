<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 공통변수 처리 -->
<c:set var="CPATH" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="RPATH" value="${CPATH}/resources" scope="application"/>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">

	  <title><tiles:insertAttribute name="title" /></title>

	  <!-- Custom fonts for this template-->
		<link href="${RPATH }/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
		<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

		<!-- Custom styles for this template-->
		<link href="${RPATH }/css/sb-admin-2.min.css" rel="stylesheet">

	  <!-- Bootstrap core JavaScript-->
	  <script src="${RPATH }/vendor/jquery/jquery.min.js"></script>
	  <script src="${RPATH }/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	  <!-- Core plugin JavaScript-->
	  <script src="${RPATH }/vendor/jquery-easing/jquery.easing.min.js"></script>

	  <!-- Custom scripts for all pages-->
	  <script src="${RPATH }/js/sb-admin-2.min.js"></script>

		<meta name="description" content="">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<script type="text/javascript">
			var _CPATH = '${CPATH}';
			var _RPATH = '${RPATH}';
		</script>

		<link rel="stylesheet" href="${RPATH}/css/common.css">
	</head>

	<body id="page-top">
		<!-- Page Wrapper -->
	  <div id="wrapper">
			<tiles:insertAttribute name="left"/>

		  <!-- Content Wrapper -->
		  <div id="content-wrapper" class="d-flex flex-column">
		   <!-- Main Content -->
		   <div id="content">
				<tiles:insertAttribute name="header" />

				<!-- Begin Page Content -->
	      <div class="container-fluid">
		 			<tiles:insertAttribute name="body"/>
	      </div>

	  	 </div>

	  	 <tiles:insertAttribute name="foot" />

		   <!--// Main Content -->
		  </div>
		  <!--// Content Wrapper -->
		</div>
		<!--// Page Wrapper -->
	</body>
</html>