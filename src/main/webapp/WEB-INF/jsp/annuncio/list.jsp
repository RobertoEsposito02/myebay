<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<jsp:include page="../header.jsp" />
	<title>Pagina dei risultati</title>
	
</head>
<body class="d-flex flex-column h-100" style="background-color: #08082b;">
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  <div class="container">
	
			<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
			  ${successMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
			  ${errorMessage}
			  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
			</div>
			
			<div class='card'>
			    <div class='card-header' style="background-color: #08082b;">
			        <h5 class="text-info" >Lista dei risultati</h5> 
			    </div>
			    <div class='card-body'>
			    	<a href="${pageContext.request.contextPath}/home" class='btn btn-outline-secondary' >
				            <i class='fa fa-chevron-left'></i> Torna alla HomePage
				        </a>
			    
			        <div class='table-responsive'>
			            <table class='table table-striped ' >
			                <thead>
			                    <tr>
			                        <th>Descrizione</th>
			                        <th>Prezzo</th>
			                        <th>Azioni</th>
			                    </tr>
			                </thead>
			                <tbody>
			                	<c:forEach items="${list_annuncio_attr }" var="annuncioItem">
									<tr>
										<td>${annuncioItem.testoAnnuncio }</td>
										<td>${annuncioItem.prezzo }</td>
										<td>
											<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/annuncio/show/${annuncioItem.id }">Visualizza</a>
											<c:if test="${userInfo.username eq annuncioItem.utenteInserimento.username and annuncioItem.aperto == true }">
												<a class="btn btn-sm btn-outline-primary" href="${pageContext.request.contextPath}/annuncio/edit/${annuncioItem.id }">Edit</a>
											</c:if>
											<c:if test="${userInfo.username eq annuncioItem.utenteInserimento.username and annuncioItem.aperto == true }">
												<a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/annuncio/delete/${annuncioItem.id }">Delete</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
			                </tbody>
			            </table>
			        </div>
			   
				<!-- end card-body -->			   
			    </div>
			</div>	
	
		</div>	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>