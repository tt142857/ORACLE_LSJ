<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.fr {
	float: right;
}

.flex-row-baseline {
	display: flex;
	flex-direction: row;
	align-items: baseline;
}

#ta {
	width: 100%;
	min-height: 400px;
	resize: none;
	overflow-y: auto;
}

textarea:focus {
	outline: none;
}

.x-btn {
  cursor: pointer;
}
</style>

<form id="writeForm" enctype="multipart/form-data" method="post">
<div class="card shadow mb-4">
	<div class="card-header py-3">
		<div class="row">
			<div class="col-sm-12 col-md-6">
				<div class="flex-row-baseline">
					<select name="boardCat" class="custom-select custom-select-sm form-control form-control-sm" style="width: 70px; margin-right: 15px;">
						<option value="BC01">일상</option>
						<option value="BC02">잡담</option>
						<option value="BC03">유머</option>
						<option value="BC04">기타</option>
					</select>
					<span style="width: 50px;">제목:</span>
					<input name="boardTtl" type="text" class="form-control form-control-sm"/>
				</div>
				<label for="file" class="d-none d-sm-inline-block btn btn-sm btn-secondary shadow-sm" style="margin-top: 20px;">파일 선택</label>
				<input id="file" name="file" type="file" style="display: none;" />
				<input id="files" name="files" type="file" style="display: none;"/>
				<div id="fileListBody"></div>
			</div>
			<div class="col-sm-12 col-md-6">
				<div class="fr">
					<input id="writeBtn" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="button" value="작성완료">
				</div>
			</div>
		</div>
	</div>
	<div class="card-body">
		<textarea name="boardCont" id="ta"></textarea>
	</div>
</div>

	<!-- 파일이 있으면 BD02로 변경하기 -->
	<input id="boardDiv" name="boardDiv" type="hidden" value="BD01"/>
</form>

<script>

  // 등록
	$('#writeBtn').on('click', function () {
		var boardTtl = $('input[name=boardTtl]');
		if ($.trim(boardTtl.val()).length <= 0) {
			alert("제목을 입력해 주세요.");
			boardTtl.focus();
			return;
		}

		if (fileList.length > 0) {
			$('#boardDiv').val('BD02');
		}

		fn_cnvrtFileData();

	  var formData = new FormData($('#writeForm')[0]);
		$.ajax({
			url: '/api/v1/boards'
			, type: 'post'
			, data: formData
			, dataType: 'text'
			, enctype: 'multipart/form-data'
			, contentType : false
		  , processData : false
			, success: function(data) {
				if(data === "failed") {
					alert("로그인해야 등록할 수 있습니다.");
					location.href = '${CPATH}/board';
					return;
				}

				var boardNo = data;
				location.href = '${CPATH}/board/view?no=' + boardNo;
			}
		})
	});
	// 등록 끝

	var fileList = [];
	$('#file').on('change', function(e) {
		var file = e.target.files[0];
		fileList.push(file);

		$('#file').val('');
		fn_loadUploadList();
	});

	var fn_loadUploadList = function() {
		var fileListBody = $("#fileListBody");
		fileListBody.empty();

	  $.each(fileList, function(i, file) {
		  var xBtn = '<span class="x-btn">'
		      + '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">'
          + '<path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />'
          + '</svg>'
          + '</span>';

		  var html = $('<div>').attr('id', file.lastModified);
		  html.append($('<span style="color: #224ACC; margin-right: 5px;">').text(file.name));
		  html.append($('<span>').text(fn_getCnvrtSize(file.size)));
		  html.append(xBtn);

		  fileListBody.append(html);
	  })
	}

	$(document).on('click', '.x-btn', function() {
		var fileId = $(this).parent().attr('id');

		$.each(fileList, function(i, file) {
			if(file.lastModified == fileId) {
				fileList.splice(i, 1);
				$('#' + file.lastModified).remove();
				return;
			}
		})
	})

	var fn_getCnvrtSize = function(fileSize) {
		var s = ["B", "KB", "MB", "GB", "TB", "PB"];

		var idx = Math.floor(Math.log(fileSize) / Math.log(1024));
		var formattedData = fileSize / Math.pow(1024, Math.floor(idx));

		var result = formattedData.toFixed(2) + " " + s[idx];
		return result;
	}


	var fn_cnvrtFileData = function() {
		var dataTransfer = new DataTransfer();

		fileList.forEach(file => {
			dataTransfer.items.add(file);
		});
		$('#files')[0].files = dataTransfer.files;
	}


</script>