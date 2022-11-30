<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
	<div class="card o-hidden border-0 shadow-lg my-5">
		<div class="card-body p-0">
			<!-- Nested Row within Card Body -->
			<div class="row">
				<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
				<div class="col-lg-7">
					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
						</div>
						<form id="registForm" class="user">
							<div class="form-group">
								<input type="text" class="form-control form-control-user" id="memId" name="memId" placeholder="ID">
							</div>

							<div class="form-group row">
                <div class="col-sm-6 mb-3 mb-sm-0">
                  <input type="text" class="form-control form-control-user" id="memNm" name="memNm" placeholder="Name">
                </div>
                <div class="col-sm-6">
                  <input type="email" class="form-control form-control-user" id="memEml" name="memEml" placeholder="Email Address">
                </div>
              </div>

							<div class="form-group row">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<input type="password" class="form-control form-control-user" id="memPswd" name="memPswd" placeholder="Password" maxlength="16" >
								</div>
								<div class="col-sm-6">
									<input type="password" class="form-control form-control-user" id="re_memPswd" placeholder="Repeat Password" maxlength="16" >
								</div>
							</div>
							<a href="#" id="registBtn" class="btn btn-primary btn-user btn-block">Register Account</a>
							<hr>
							<a href="#" class="btn btn-google btn-user btn-block">
								<i class="fab fa-google fa-fw"></i>
								Register with Google
							</a> <a href="#" class="btn btn-facebook btn-user btn-block">
								<i class="fab fa-facebook-f fa-fw"></i>
								Register with Facebook
							</a>
						</form>
						<hr>
						<div class="text-center">
							<a class="small" href="#">Forgot Password?</a>
						</div>
						<div class="text-center">
							<a class="small" href="${CPATH }/loginForm.do">Already have an account? Login!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script>

	$('#registBtn').on('click', function() {

		var memId = $.trim($('#memId').val());

		if(!memId.length > 0) {
			alert("아이디를 입력하지 않았습니다.");
			$('#memId').focus();
			return;
		}

		var memNm = $.trim($('#memNm').val());
		var memEml = $.trim($('#memEml').val());

		if(!memNm.length > 0) {
			alert('이름을 입력하지 않았습니다.');
			$('#memNm').focus();
			return;
		} else if (!memEml.length > 0) {
			alert('이메일을 입력하지 않았습니다.');
      $('#memEml').focus();
      return;
		} else if(!/^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[.]+[a-zA-Z]+[.]*[a-zA-Z]*$/.test(memEml)) {
			alert('이메일 형식에 맞지 않습니다.');
			$('#memEml').focus();
			return;
		}
		if(!$('#memPswd').val().length > 0) {
			alert("비밀번호를 입력하지 않았습니다.");
			$('#memPswd').focus();
			return;
		}

		var memPswd = $.trim($('#memPswd').val());
		var reMemPswd = $.trim($('#re_memPswd').val());
		if(!reMemPswd.length > 0 || memPswd != reMemPswd) {
			alert("비밀번호가 일치하지 않습니다.");
			$('#re_memPswd').focus();
			return;
		}

		$.ajax ({
			  url : '${CPATH}/api/v1/member/check'
			, data : {
				  memId : memId
			  }
			, dataType : 'text'
			, success : function(data) {
				console.log(data);
				if(data == 0) {
					  $.ajax ({
							  url : '${CPATH}/api/v1/member'
							, type : 'post'
							, data : $('#registForm').serialize()
						  , dataType : 'text'
						  , success : function(resp) {
							   if(resp === "success") {
								   location.href = '${CPATH}/loginForm.do';
							   } else {
								   alert("문제가 발생했습니다.")
								   console.log(resp);
								   return;
							   }
						  }
					  })
				} else {
					  alert("존재하는 아이디입니다.");
					  $('#memId').focus();
					  return;
				}
			}
		});
	});
</script>
