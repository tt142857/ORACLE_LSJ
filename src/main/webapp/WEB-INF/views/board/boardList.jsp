<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.fl {
  float: left;
}

.fr {
	float: right;
}

.flex-row-baseline {
	display: flex;
	flex-direction: row;
	align-items: baseline;
}

.ml-5px {
	margin-left: 5px;
}

.ml-15px {
	margin-left: 15px;
}

.align-center {
  align-items: center;
}

.searchCondition {
  margin-left: 10px;
}

.searchBar {
    position: absolute;
    left: 135px;
    width: 50%;
}
</style>

<c:if test="${not empty message }">
  <script>
    alert("${message}");
  </script>
</c:if>

<form id="pagingForm">
<div class="card shadow mb-4">
	<div class="card-header py-3">
	  <div class="row">
	    <div class="col-sm-12 col-md-6 align-center flex-row-baseline">
				<h6 class="m-0 font-weight-bold text-primary">게시판</h6>
	    </div>
	    <div class="col-sm-12 col-md-6">
	      <div class="fr flex-row-baseline">
				  <input onclick="fn_moveBoardWrite()" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm" type="button" value="등록">
				</div>
	    </div>
	  </div>
	</div>
	<div class="card-body">
		<div class="table-responsive">
			<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
				<div class="row">
				  <div class="col-sm-12 col-md-6">
						<div id="dataTable_filter" class="dataTables_filter">
						  <div class="flex-row-baseline">
								<label class="fl flex-row-baseline" style="position: relative;">
								  Search:
								</label>
							  <select name="searchType" id="searchType" class="searchCondition custom-select custom-select-sm form-control form-control-sm" style="width: 70px; margin-right: 15px;">
			             <option value="">전체</option>
                   <option value="boardTtl">제목</option>
                   <option value="boardCont">내용</option>
                   <option value="boardTtlCont">제목+내용</option>
			          </select>
							  <input name="searchWord" id="searchWord" type="search" class="ml-5px form-control form-control-sm searchBar" placeholder="" aria-controls="dataTable">
						  </div>
						</div>
				  </div>
					<div class="col-sm-12 col-md-6">
					  <div class="dataTables_length" id="dataTable_length">
              <label class="fr flex-row-baseline">Show
                <select name="screenSize" id="entries" name="dataTable_length" aria-controls="dataTable" class="ml-5px custom-select custom-select-sm form-control form-control-sm">
                    <option value="10">10</option>
                    <option value="25">25</option>
                    <option value="50">50</option>
                    <option value="100">100</option>
                </select>
                <span class="ml-5px">entries</span>
              </label>
            </div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12">
						<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info">
						  <colgroup>
						    <col width="10%" />
						    <col width="10%" />
						    <col width="40%" />
						    <col width="10%" />
						    <col width="20%" />
						    <col width="10%" />
						  </colgroup>
							<thead>
								<tr role="row">
									<th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" aria-sort="ascending">번호</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable">분류</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable">제목</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable">등록자</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable">등록일</th>
									<th class="sorting" tabindex="0" aria-controls="dataTable">조회수</th>
								</tr>
							</thead>
							<tbody id="boardListBody">
						  </tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-5">
						<div class="dataTables_info" id="dataTable_info" name="listInfo" role="status" aria-live="polite"></div>
					</div>
					<div class="col-sm-12 col-md-7">
						<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
							<ul id="pagingBody" class="pagination fr">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<input id="currentPage" name="currentPage" type="hidden" />
</form>

<script>

  $(function() {
	  $('#currentPage').val('${paging.currentPage }');
	  $('#entries').val('${paging.screenSize }');
	  $('#searchType').val('${paging.searchVo.searchType }');
	  $('#searchWord').val('${paging.searchVo.searchWord }');

	  // ajax start
	  $.ajax({
        url: '${CPATH}/api/v1/boards'
      , type: 'get'
      , data: $('#pagingForm').serialize()
      , dataType: 'json'
      , success: function(data) {
        var currentPage = $('#currentPage').val();
        var screenSize = $('#entries').val();
        var searchWord = $('#searchWord').val();
        var searchType = $('#searchType').val();

        var paging = data;
        var boardList = paging.itemList;
        var boardListBody = $('#boardListBody');
        boardListBody.empty();

        if(boardList != null && boardList.length > 0) {
          var boardListTag = null;
          $.each(boardList, function(i, board) {

            boardListTag = $('<tr>');
            boardListTag.append($('<td class="sorting_1">').text(board.boardNo));
            boardListTag.append($('<td>').text(board.boardCatNm));
            boardListTag.append($('<td>').append($('<a>').attr('href', '${CPATH}/board/view?no=' + board.boardNo).text(board.boardTtl)));
            boardListTag.append($('<td>').text(board.rgstId));
            boardListTag.append($('<td>').text(board.rgstDt));
            boardListTag.append($('<td>').text(board.boardHit));

            boardListBody.append(boardListTag);
          });
        } else {
          boardListBody.append($('<td colspan="6">').text("데이터가 없습니다."));
        }

        // 페이징 데이터 저장
        if($('#currentPage').val() === "" && paging.currentPage != "" && paging.currentPage != null) {
          $('#currentPage').val(paging.currentPage);
        }
        $('#entriesValue').val(paging.screenSize);

        $('[name="listInfo"]').text('Showing ' + paging.startRow + ' to ' + paging.endRow + ' of ' + paging.totalRecord + ' entries');

        var pagingBody = $('#pagingBody');
        pagingBody.empty();


        if(paging.hasPrev) {
          pagingBody.append($('<li class="paginate_button page-item previous" id="dataTable_previous">')
                            .append($('<a aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">')
                                    .attr('href', '${CPATH}/board?currentPage=' + paging.prevPage + '&screenSize=' + screenSize + '&searchType=' + searchType + '&searchWord=' + searchWord)
                                    .text('Previous'))
          );
        }

        var pageTag = null;
        for(var idx = paging.startPage; idx <= paging.endPage; idx++) {
          pageTag = $('<li class="paginate_button page-item">')
                    .append($('<a aria-controls="dataTable" tabindex="0" class="page-link">')
                             .attr('href', '${CPATH}/board?currentPage=' + idx + '&screenSize=' + screenSize + '&searchType=' + searchType + '&searchWord=' + searchWord)
                             .text(idx))
          if(paging.currentPage === idx) {
            pageTag.addClass('active');
          }
          pagingBody.append(pageTag);
        }

        if(paging.hasNext) {
          pagingBody.append($('<li class="paginate_button page-item next" id="dataTable_previous">')
                            .append($('<a aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">')
                                    .attr('href', '${CPATH}/board?currentPage=' + paging.nextPage + '&screenSize=' + screenSize + '&searchType=' + searchType + '&searchWord=' + searchWord)
                                    .text('Next'))
          );
        }
      }
      // success end
    });
    // ajax end
  });

  var fn_moveBoardWrite = function() {
	  location.href = "${CPATH}/board/write";
  }

  var fn_loadBoardList = function() {
    var currentPage = $('#currentPage').val();
    var screenSize = $('#entries').val();
    var searchWord = $('#searchWord').val();
    var searchType = $('#searchType').val();

	  location.href = '${CPATH}/board?currentPage=' + currentPage + '&screenSize=' + screenSize + '&searchType=' + searchType + '&searchWord=' + searchWord;
  }

  var fn_movePage = function(page) {
	  $('#currentPage').val(page);
    fn_loadBoardList();
  }

  $('#entries').on('change', function() {
	  $('#currentPage').val(1);
	  fn_loadBoardList();
  });

  $('#searchWord').on('keyup', function(e) {
	  if(e.keyCode == 13) {
		  $('#currentPage').val(1);
		  fn_loadBoardList();
	  }
  });


</script>