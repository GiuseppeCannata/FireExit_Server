<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="entity.Nodo"%>
<%@ page import="java.lang.*"%>


<% 
  Nodo nodo =(Nodo) request.getAttribute("nodo");    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FireExit Server</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/functions.js"></script>
</head>

<body>
	<div id="header">
		<jsp:include page="../layout/_header.jsp"></jsp:include>
	</div>
	<div id="topnav">
		<jsp:include page="../layout/_topnav.jsp"></jsp:include>
	</div>

	<h4>Modifica del nodo</h4>
	
	
    <p> <b>Attenzione! </b> Il tipo Incendio pu� essere modificato solo in caso di emergenza </p>
	<p class="errore">${errorString}</p>

	<form id="form" method="POST" action="${pageContext.request.contextPath}/ModificaNodo">
		<input type="hidden" name="Id" value="${nodo.getId()}" />
		<table border="0">
			<tr>
				<td>Beacon Id</td>
				<td><input type="text" name="BeaconId"
					value="${nodo.getBeaconId()}" /></td>
			</tr>
			<tr>
				<td>Piano</td>
				<td><input name="mappaId" readonly="readonly" type="text"
					value="${nodo.getmappaId()}"></td>
			</tr>
			<tr>
				<td>X</td>
				<td><select name="X">
						<% 
		                    for(int i=1; i<= 100; i++){
		                    	
		                    	if(i != nodo.getX())
		                    	   out.print("<option>"+i+"</option>");
		                    	else
		                    		 out.print("<option selected>"+nodo.getX()+"</option>");
		                    }
		                %>
				</select></td>
			</tr>
			<tr>
				<td>Y</td>
				<td><select name="Y">
						<% 
		                    for(int i=1; i<= 100; i++){
		                    	
		                    	if(i != nodo.getY())
		                    	   out.print("<option>"+i+"</option>");
		                    	else
		                    		 out.print("<option selected>"+nodo.getY()+"</option>");
		                    }
		                %>
				</select></td>
			</tr>
			<tr>
				<td>Tipo Incendio</td>
				<c:choose>
					    <c:when test="${statoEmergenza=='0'}">
					    <td><select name="TipoIncendio">
									<% 
				                    	if(nodo.isTipoIncendio())
				                    	   out.print("<option selected>True</option>");
				                    	else
				                    	   out.print("<option selected>False</option>");
					                %>
				            </select></td>
					        
					    </c:when>    
					    <c:otherwise>
					       <td><select name="TipoIncendio">
									<% 
				                    	if(nodo.isTipoIncendio()){
				                    	   out.print("<option selected>True</option>");
				                    	   out.print("<option>False</option>");
				                    	}
				                    	else{
				                    	   out.print("<option selected>False</option>");
				                     	   out.print("<option>True</option>");
				                    	}
					                    
					                %>
				            </select></td>
					    </c:otherwise>
					</c:choose>
			</tr>
			<tr>
				<td>Tipo Uscita</td>
				<td><select name="TipoUscita">
						<% 
	                    	if(nodo.isTipoUscita()){
	                    	   out.print("<option selected>True</option>");
	                    	   out.print("<option>False</option>");
	                    	}
	                    	else{
	                    	   out.print("<option selected>False</option>");
	                     	   out.print("<option>True</option>");
	                    	}
		                    
		                %>
				</select></td>
			</tr>
			<tr>
				<td colspan="2">
					<button onclick="confirmActionForm(event)">Fatto</button> 
					<a id="indietro" href="#" onclick="back(event,'${pageContext.request.contextPath}/CaricaMappa?piano=${nodo.getmappaId()}')">Indietro</a>
				</td>
			</tr>
		</table>
	</form>

	<div id="footer">
		<jsp:include page="../layout/_footer.jsp"></jsp:include>
	</div>
</body>
</html>