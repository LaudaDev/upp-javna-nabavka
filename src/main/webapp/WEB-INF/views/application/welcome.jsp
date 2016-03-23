<%@ include file="./header.jsp" %>
	<!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1>${message}</h1>
				<p class="lead">
                <c:if test="${canInitiate == true}"><a href="./newInstance">Pokretanje nove instance</a></c:if>
				</p>
            </div>
        </div>
    </div>
    <!-- /.container -->
<jsp:include page="./footer.jsp" />
