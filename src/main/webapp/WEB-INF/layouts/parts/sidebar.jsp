<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
	<!-- Sidebar - Brand -->
	<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${CPATH}/">
	    <div class="sidebar-brand-icon rotate-n-15">
	        <i class="fas fa-laugh-wink"></i>
	    </div>
	    <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item active">
	  <c:if test="${not empty userInfo }">
	    <a class="nav-link" href="<c:url value='/logout' />">
	      <i class="fas fa-fw fa-tachometer-alt"></i>
	      <span>Logout</span>
      </a>
	  </c:if>
	  <c:if test="${empty userInfo }">
		  <a class="nav-link" href="<c:url value='/loginForm.do' />">
		    <i class="fas fa-fw fa-tachometer-alt"></i>
		    <span>Login</span>
		  </a>
	  </c:if>
	</li>

	<li class="nav-item active">
	  <a class="nav-link" href="<c:url value='/' />">
	    <i class="fas fa-fw fa-tachometer-alt"></i>
	    <span>Dashboard</span>
	  </a>
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">
	    MENU
	</div>

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item">
	    <a class="nav-link collapsed" href="${CPATH }/home.do">
	        <i class="fas fa-fw fa-cog"></i>
	        <span>홈</span>
	    </a>
	</li>

	<li class="nav-item">
	    <a class="nav-link collapsed" href="${CPATH }/cmn/code/codeGrpList.do">
	        <i class="fas fa-fw fa-wrench"></i>
	        <span>코드그룹관리</span>
	    </a>
	</li>

	<li class="nav-item">
	    <a class="nav-link collapsed" href="${CPATH }/cmn/code/codeGrpList.do">
	        <i class="fas fa-fw fa-wrench"></i>
	        <span>코드관리</span>
	    </a>
	</li>

	<!-- Nav Item - Utilities Collapse Menu -->
  <li class="nav-item">
      <a class="nav-link collapsed" href="${CPATH }/board">
          <i class="fas fa-fw fa-table"></i>
          <span>게시판</span>
      </a>
  </li>

  <!-- Nav Item - Utilities Collapse Menu -->
  <li class="nav-item">
      <a class="nav-link collapsed" href="${CPATH }/admin/role">
          <i class="fas fa-fw fa-mug-hot"></i>
          <span>권한 관리</span>
      </a>
  </li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block" />

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
	    <button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

	<!-- Sidebar Message -->
  <div class="sidebar-card d-none d-lg-flex">
      <img class="sidebar-card-illustration mb-2" src="${RPATH}/img/undraw_rocket.svg" alt="...">
      <p class="text-center mb-2"><strong>SB Admin Pro</strong> is packed with premium features, components, and more!</p>
      <a class="btn btn-success btn-sm" href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to Pro!</a>
  </div>
</ul>
<!-- End of Sidebar -->