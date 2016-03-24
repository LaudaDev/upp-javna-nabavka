<%@ include file="./header.jsp" %>
	<!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1>Javna Nabavka - Proces ${task.name}</h1>
                <c:if test="${fn:length(formProperties) > 0}">
					<div class="form-group">
					<form name='f' action="<c:url value='/application/execute/${task.id}' />" method="POST">
					<c:forEach var="formProperty" items="${formProperties}">
						<c:if test="${formProperty.readable == true}">
							<label for="${formProperty.name}">${formProperty.name}</label>

							<c:if test="${formProperty.type.name.equals('string') || formProperty.type.name.equals('date')}">
							<input type="text" class="form-control" <c:if test="${formProperty.writable==true}"> name="${formProperty.id}"</c:if>
							    <c:if test="${formProperty.writable==false}"> disabled </c:if> <c:if test="${formProperty.required==true}"> required </c:if> value="${formProperty.value}" />
							</c:if>

							<c:if test="${formProperty.type.name.equals('long') || formProperty.type.name.equals('double')}">
							<input type="text" class="form-control" <c:if test="${formProperty.writable==true}"> name="${formProperty.id}"</c:if>
							    <c:if test="${formProperty.writable==false}"> disabled </c:if> <c:if test="${formProperty.required==true}"> required </c:if> value="${formProperty.value}" />
							</c:if>

							<c:if test="${formProperty.type.name.equals('boolean')}">
								<input type="checkbox" class="checkbox" <c:if test="${formProperty.writable==true}"> name="${formProperty.id}"</c:if>
								 <c:if test="${formProperty.writable==false}"> disabled </c:if> <c:if test="${formProperty.value==true}">checked </c:if>/>
							</c:if>

							<c:if test="${formProperty.type.name.equals('enum')}">
								<select class="form-control" <c:if test="${formProperty.writable==true}"> name="${formProperty.id}"</c:if>
							  	<c:if test="${formProperty.writable==false}"> disabled </c:if> >

							<c:forEach var="key" items="${formProperty.getType().getInformation('values').keySet()}">
								<option value="${key}">${formProperty.getType().getInformation('values').get(key)}</option>
							</c:forEach>
							</select>
						</c:if>

						</c:if>
				<c:if test="${formProperty.readable == false}">
					<input type="hidden"  name="${formProperty.id}" value="${formProperty.value}" />
				</c:if>
			</c:forEach>
  			</div>
			<button type="submit" class="btn btn-default">Potvrda</button>
			</form>
			</c:if>
            </div>
        </div>
    </div>
<jsp:include page="./footer.jsp" />
