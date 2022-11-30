<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.fr {
	float: right;
}

textarea {
	width: 100%;
	min-height: 75px;
	resize: none;
}

textarea:focus {
	outline: none;
}

.comment {
	background-color: #F4F4F4;
}

.updtCmnt, .delCmnt {
	cursor: pointer;
}
</style>

<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
</c:if>

<div class="card shadow mb-4">
	<div class="card-header py-3">
		<div id="boardBody" class="row">

		</div>
	</div>
	<div id="boardCont" class="card-body">

		<div>댓글 <span id="cmntTotal"></span></div>
		<!-- 댓글 -->
		<div class="card mb-4 comment">
			<div class="card-body">
				<div id="cmntList">
				</div>

				<div class="card">
					<div class="card-body">
						<!-- 비로그인 -->
						<c:if test="${empty userInfo }">
							<textarea placeholder="로그인 후 댓글을 작성할 수 있습니다." readonly="readonly"></textarea>
						</c:if>
						<!-- 로그인 -->
						<c:if test="${not empty userInfo }">
							<span style="font-weight: bold;">
								<c:out value="${userInfo.memNm }" />님
							</span>
							<textarea id="cmntCont" placeholder="댓글을 입력해 주세요."></textarea>
							<input id="cmntWriteBtn"
								class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"
								type="button" value="등록" />
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!--// 댓글 -->
	</div>
</div>


<script>

	var boardNo = '<c:out value="${param.no}" />';
	var userId = '<c:out value="${userInfo.memNm}" />';
	$(function () {
		$.ajax({
			  url: '${CPATH}/api/v1/boards/' + boardNo
			, type: 'get'
		  , dataType: 'json'
		  , success: function(data) {
			  var board = data;
			  var html = $('<div class="col-sm-12 col-md-6">');
			  html.append($('<h3>').text(board.boardTtl));
			  html.append($('<div>').text("분류: " + board.boardCatNm));
			  html.append($('<div>').text("작성자: " + board.rgstId));
			  html.append($('<div>').text("등록일: " + board.rgstDt));
			  html.append($('<div>').text("조회수: " + board.boardHit));

			  if(board.atchList != null && board.atchList.length > 0) {
				  var atchTag = null;

				  $.each(board.atchList, function(i, atch) {
					  atchTag = $('<div>');
					  var aTag = $('<a>').attr('href', '${CPATH}/download/' + atch.atchId + '/' + atch.atchSeq);
					  var atchName = atch.atchOrigNm;
					  if(atch.atchExtn != null && atch.atchExtn != "") {
						  atchName = atchName + "." + atch.atchExtn;
					  }
						aTag = aTag.text(atchName);
						atchTag.append(aTag);
					  atchTag.append($('<span style="margin-left: 5px;">').text(atch.cnvrtSize));

						html.append(atchTag);
				  })
			  }

			  $('#boardBody').html(html);
			  if(board.rgstId === userId) {
				  var btnHtml = $('<div class="col-sm-12 col-md-6">')
				  var frTag = $('<div class="fr">').append($('<input id="updtBtn" class="d-none d-sm-inline-block btn btn-sm btn-info shadow-sm" type="button" value="수정">').css('margin-right', '5px'))
				                                   .append(('<input id="delBtn" class="d-none d-sm-inline-block btn btn-sm btn-danger shadow-sm" type="button" value="삭제">'));
				  btnHtml.append(frTag);
				  html.after(btnHtml);
				}

			  var contTag = $('<div style="min-height: 400px;">').text(board.boardCont);
			  $('#boardCont').prepend(contTag);
		  }

		})

		loadCmntList();
	});

	$('#cmntWriteBtn').on('click', function () {
		var cmntContTa = $('#cmntCont');
		var cmntCont = cmntContTa.val();

		if(cmntCont == null || $.trim(cmntCont).length <= 0) {
			alert("댓글을 입력해 주세요.");
			cmntContTa.focus();
			return;
		}
		$.ajax({
			  url: '${CPATH}/api/v1/cmnts'
			, type: 'post'
			, data: {
				  boardNo: boardNo
				, cmntCont: cmntCont
			}
			, dataType: 'text'
			, success: function (data) {
				console.log(data);
				loadCmntList();
				cmntContTa.val('');
			}
		})
	});

	var loadCmntList = function () {
		$.ajax({
			  url: '${CPATH}/api/v1/cmnts'
			, data: {
				no: boardNo
			}
			, dataType: 'json'
			, success: function (data) {
				var cmntTotal = $('#cmntTotal');
				cmntTotal.text('[' + data.length + ']');

				var cmntList = $('#cmntList');
				cmntList.empty();
				var html = null;
				if (data != null && data.length > 0) {
					$.each(data, function (i, cmnt) {
						html = $('<div>').attr('id', 'cmntSeq_' + cmnt.cmntSeq).addClass('cmntSeq');
						html.append($('<label style="font-weight: bold;">').text(cmnt.rgstId));

						if (userId != null && $.trim(userId).length > 0 && userId == cmnt.rgstId) {
							html.append($('<span style="font-size: 12px;">').append($('<label style="margin-left: 5px;">').addClass('updtCmnt').text('수정'))
								.append(' | ')
								.append($('<label>').addClass('delCmnt').text('삭제'))
							);
						}

						html.append($('<div>').addClass('cmntCont').text(cmnt.cmntCont));
						var cmntDt = null;
						if (cmnt.updtDt != null && $.trim(cmnt.upDt).length) {
							cmntDt = cmnt.updtDt;
						} else {
							cmntDt = cmnt.rgstDt;
						}
						html.append($('<span style="font-size: 12px;">').addClass('cmntDt').text(cmntDt));
						html.append($('<hr>'));
						cmntList.append(html);
					});

				}
				// if end
			}
			// success end
		})
		// ajax end
	}

	// 게시판 수정 페이지 이동
	$(document).on('click', '#updtBtn', function() {
		location.href = "${CPATH}/board/update?no=" + boardNo;
	});

	// 게시판 삭제
	$(document).on('click', '#delBtn', function() {
		if (confirm("삭제하시겠습니까?")) {
		      $.ajax({
		          url: '${CPATH}/api/v1/boards'
		        , type: 'delete'
		        , data: {
		          boardNo : boardNo
		        }
		        , dataType: 'text'
		        , success: function(data) {
		          if(data === "success") {
		            location.href = "${CPATH}/board"
		          } else if(data === "failed") {
		            alert("작성자가 아닙니다.");
		          } else if(data === "none") {
		            alert("없는 게시판입니다.")
		            location.href = "${CPATH}/board"
		          } else {
		            console.log("오류");
		          }

		        }
		      })
		    }
	});

	// 댓글 수정
	$(document).on('click', '.updtCmnt', function () {
		var cmntSeq = $(this).parents('.cmntSeq');
		var cmntSeqId = cmntSeq.attr('id');
		cmntSeqId = cmntSeqId.split('_')[cmntSeqId.split('_').length - 1];
		var cmntCont = cmntSeq.children('.cmntCont');
		var cmntDt = cmntSeq.children('.cmntDt');
		var rgstId = $(this).parents('.cmntSeq').children('.rgstId').val();

		var html = $('<div id="cmntUpdateDiv">');
		html.append($('<textarea name="cmntCont" placeholder="댓글을 입력해 주세요.">').val($.trim(cmntCont.text())));
		html.append($('<input id="cmntUpdateBtn" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="button" value="수정" />').css('margin-right', '5px'));
		html.append($('<input class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm cnclBtn" type="button" value="취소" />'));

		cmntCont.after(html).hide();
		cmntDt.hide();

		// 댓글 수정 등록 시
		$(document).on('click', '#cmntUpdateBtn', function () {
			var cmntContTa = $(this).parent().children('[name=cmntCont]');
			var cmntCont = cmntContTa.val();
			if(cmntCont == null || $.trim(cmntCont).length <= 0) {
				alert("수정 내용을 입력하세요.");
				cmntContTa.focus();
				return;
			}

			$.ajax({
				  url: '${CPATH}/api/v1/cmnts/' + cmntSeqId
				, type: 'put'
				, data: {
					  boardNo: boardNo
					, cmntSeq: cmntSeqId
					, cmntCont: cmntCont
					, rgstId: rgstId
				}
				, dataType: 'text'
				, success: function(data) {
					console.log(data);

					loadCmntList();
				}
			})
		});

		// 댓글 수정 취소 버튼
		$('.cnclBtn').on('click', function () {
			$('#cmntUpdateDiv').remove();
			cmntCont.show();
			cmntDt.show();
		});
	});


	// 댓글 삭제
	$(document).on('click', '.delCmnt', function () {
		if (confirm("삭제하시겠습니까?")) {
			var cmntSeq = $(this).parents('.cmntSeq');
			var cmntSeqId = cmntSeq.attr('id');
			cmntSeqId = cmntSeqId.split('_')[cmntSeqId.split('_').length - 1];
			var rgstId = $(this).parents('.cmntSeq').children('.rgstId').val();

			$.ajax({
				  url: '${CPATH}/api/v1/cmnts/' + cmntSeqId
				, type: 'delete'
				, data: {
					boardNo: boardNo
					, cmntSeq: cmntSeqId
					, rgstId: rgstId
				}
				, dataType: 'text'
				, success: function (data) {
					console.log(data);

					loadCmntList();
				}
			})
		}
	});
</script>