<!-- 引用该分页jsp时，请包含在主页面的form表单内 -->
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<style>
.pagebar .prev,
.pagebar .next {
	background-image: url(/assets/images/sprite/icons-png8.png);
}
</style>
<input type="hidden" name="pageCount" id="pageCount" value="${pageModel.pageCount}"/>
<input type="hidden" name="currentPage" id="currentPage" value="${pageModel.pageNo}"/>
<span class="count">${pageModel.pageNo}/${pageModel.pageCount}，${LANG['website.common.pagination.some.record.total']} ${pageModel.rowsCount} ${LANG['website.common.pagination.some.record.tag']}</span>
<div class="jumpto">
	<span class="to">
		${LANG['website.common.pagination.jump.tag'] }<input type="text" class="input-text"  name="pageNo" id="pageNo" value="${pageModel.pageNo}">
		${LANG['website.common.pagination.jump.page.name'] }
	</span>
	<button class="go" type="button" onclick="jumpTo();">${LANG['website.common.pagination.jump.button'] }</button>
</div>

<ul>
	<li class="first"><a onclick="return first();">${LANG['website.common.pagination.first'] }</a></li>
	<li class="prev"><a onclick="return previous()" title="${LANG['website.common.pagination.previous'] }"></a></li>
	
	<c:set var="pageStart" value="1" />
	<c:set var="pageStop" value="${pageModel.pageCount}" />
	<c:if test="${pageModel.pageNo + 2 >= pageModel.pageCount && pageModel.pageCount > 5}">
		<c:set var="pageStart" value="${pageModel.pageCount - 4}" />
	</c:if>
	<c:if test="${pageModel.pageNo + 2 < pageModel.pageCount && pageModel.pageCount > 5}">
		<c:set var="pageStart" value="${pageModel.pageNo - 2}" />
		<c:set var="pageStop" value="${pageModel.pageNo + 2}" />
		<c:if test="${pageModel.pageNo < 3}">
			<c:set var="pageStart" value="1" />
			<c:set var="pageStop" value="5" />
		</c:if>
	</c:if>
	
	<c:forEach var ="iNum" begin="${pageStart}" end="${pageStop}" step="1">
  		<c:if test="${pageModel.pageNo != iNum}">
  			<li class="def"><a onclick="return jumpPage(${iNum})">${iNum}</a></li>
  		</c:if>
  		<c:if test="${pageModel.pageNo == iNum}">
			<li class="cur"><a>${iNum}</a></li>
  		</c:if>
    </c:forEach>
    
	<li class="next"><a onclick="return next()" title="${LANG['website.common.pagination.next'] }"></a></li>
	<li class="last"><a onclick="return end()">${LANG['website.common.pagination.end'] }</a></li>
</ul>

<script type="text/javascript">
	var pageCount = "${pageModel.pageCount}"; 
</script>
<script type="text/javascript" src="/assets/js/apps/biz.page.js"></script>
