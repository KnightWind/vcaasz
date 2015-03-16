<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/const-tag" prefix="cc"%>
<%@ taglib uri="/WEB-INF/jstltags/licfn.tld" prefix="myfn"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:if test="${ctx=='/'}">
	<c:set var="ctx" value="" />
</c:if>
