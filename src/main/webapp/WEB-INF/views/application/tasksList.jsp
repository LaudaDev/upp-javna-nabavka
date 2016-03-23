<%@ include file="./header.jsp" %>
	<!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1>Pregled zadataka</h1>
                <p class="lead">${message}</p>
				<c:if test="${fn:length(myTasks) > 0}">

				<ul class="list-unstyled">
					<c:forEach var="task" items="${myTasks}">
                    	<li><a href="${pageContext.request.contextPath}/application/showTask/${task.id}"><c:out value="${task.name}, id = ${task.id}"/></a></li>
                    </c:forEach>
				</ul>
                </c:if>

				<c:if test="${fn:length(candidateTasks) > 0}">
				<p class="lead">Zadaci koje mozete prihvatiti</p>
				<ul class="list-unstyled">
				<c:forEach var="task" items="${candidateTasks}">
					<li><a href="${pageContext.request.contextPath}/application/claim/${task.id}"><c:out value="${task.name}, id = ${task.id}"/></a></li>
				</c:forEach>
				</ul>
				</c:if>
            </div>
        </div>
    </div>
<jsp:include page="./footer.jsp" />
