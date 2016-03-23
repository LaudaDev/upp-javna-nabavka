<jsp:include page="./header.jsp" />
	<!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <h1>${message}</h1>
                <c:if test="${canInitiate == true}"><p class="lead"><a href="./newInstance">Pokretanje nove instance</a></p></c:if>
            </div>
        </div>
    </div>
    <!-- /.container -->
<jsp:include page="./footer.jsp" />
