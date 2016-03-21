<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">



<title>Javna Nabavka :: Komisija</title>

<link href="<c:url value="/resources/stylesheets/styles.css"/>"
	rel="stylesheet" />

<link href="<c:url value="/resources/stylesheets/reset.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/stylesheets/animate.css" />"
	rel="stylesheet">
</head>
<body>
	<div class="content">
		<div class="container">
			<h2>Zadatak ${task.name}</h2>


			<c:if test="${fn:length(formProperties) > 0}">
				<form name='f' class="form-horizontal"
					action="<c:url value='/application/execute/${task.id}' />"
					method="POST">

					<fieldset>

						<!-- 
Treba jos dodati validaciju (da li su uneta required obelezja, 
da li su uneta slova iako je tip long - ili obezbediti da se to onemoguci
Pokriti unos datuma
Pokusati smestiti u poseban jsp fajl forme, pa ukljuciti pomocu include
 -->
			</c:if>

			<c:forEach var="formProperty" items="${formProperties}">

				<c:if test="${formProperty.readable == true}">

					<div class="form-group">
						<label for="textinput">${formProperty.name}</label>


						<c:if
							test="${formProperty.type.name.equals('string') || formProperty.type.name.equals('long') || formProperty.type.name.equals('double')
	||  formProperty.type.name.equals('date') }">
								<input type="text"
									<c:if test="${formProperty.writable==true}" > name="${formProperty.id}"</c:if>
									<c:if test="${formProperty.writable==false}"> disabled </c:if>
									value="${formProperty.value}" />
						</c:if>

						<c:if test="${formProperty.type.name.equals('boolean')}">
								<div class="radio">
									<input type="checkbox"
										<c:if test="${formProperty.writable==true}"> name="${formProperty.id}"</c:if>
										<c:if test="${formProperty.writable==false}"> disabled </c:if>
										<c:if test="${formProperty.value==true}">checked </c:if> />
								</div>
						</c:if>

						<c:if test="${formProperty.type.name.equals('enum')}">
								<select class="form-control"
									<c:if test="${formProperty.writable==true}"> name="${formProperty.id}"</c:if>
									<c:if test="${formProperty.writable==false}"> disabled </c:if>>

									<c:forEach var="key"
										items="${formProperty.getType().getInformation('values').keySet()}">
										<option value="${key}">${formProperty.getType().getInformation('values').get(key)}</option>
									</c:forEach>
								</select>

						</c:if>
					</div>
					<c:if test="${formProperty.readable == false}">
						<input type="hidden" name="${formProperty.id}"
							value="${formProperty.value}" />
					</c:if>
					<br />
				</c:if>
			</c:forEach>
			<div class="form-group">
				<label >Službenik za javne
					nabavke</label>
					<select id="sluzbenici" name="sluzbenici">
						<c:forEach var="sluzbenik" items="${narucilacList}">
							<option value="${sluzbenik.id}">${sluzbenik.firstName} ${sluzbenik.lastName}</option>
						</c:forEach>
					</select>
			</div>
			<div class="form-group">
				<label>Lice sa drugim
					stepenom obrazovanja na pravnom fakultetu</label>
					<select class="form-control" name="pravnik">
						<c:forEach var="pravnik" items="${pravnikList}">
							<option value="${pravnik.id}">${pravnik.firstName} ${pravnik.lastName}</option>
						</c:forEach>
					</select>
			</div>
			<br />
			<div class="form-group">
				<label>Stručno strano lice</label>
					<select id="stranaLica" name="stranaLica">
						<c:forEach var="stranoLice" items="${stranaLicaList}">
							<option value="${stranoLice.id}">${stranoLice.firstName} ${stranoLice.lastName}</option>
						</c:forEach>
					</select>
			</div>
			<br />

				<input type="submit" value="Potvrda">
			</fieldset>
			</form>


		</div>
	</div>
</body>
</html>
