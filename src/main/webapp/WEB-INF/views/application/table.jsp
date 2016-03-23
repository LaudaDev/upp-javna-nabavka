<%@ include file="./header.jsp" %>
	<table class="table table-bordered">
	    <thead>
	      <tr>
	        <th>Zavrseni procesi (ID - Name - EndTime)</th>
	      </tr>
	    </thead>
	    <tbody>
			<c:if test="${fn:length(finishedInstances) > 0}">
			<c:forEach var="p" items="${finishedInstances}">
	      <tr>
	        <td>${p}</td>
	      </tr>
		  </c:forEach>
	  </c:if>
	    </tbody>
	  </table>

	  <table class="table table-bordered">
		  <thead>
			<tr>
			  <th>Aktivni procesi (ID - Name - StartTime)</th>
			</tr>
		  </thead>
		  <tbody>
			  <c:if test="${fn:length(unfinishedInstances) > 0}">
			  <c:forEach var="p" items="${unfinishedInstances}">
			<tr>
			  <td>${p}</td>
			</tr>
			</c:forEach>
			</c:if>
		  </tbody>
		</table>

		<table class="table table-bordered">
			<thead>
			  <tr>
				<th>Taskovi na cekanju (ID - Name - User)</th>
			  </tr>
			</thead>
			<tbody>
				<c:if test="${fn:length(taskOnWait) > 0}">
				<c:forEach var="p" items="${taskOnWait}">
			  <tr>
				<td>${p}</td>
			  </tr>
			  </c:forEach>
			  </c:if>
			</tbody>
		  </table>
<%@ include file="./footer.jsp" %>
