<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="it" class="h-100">
<head>
	<!-- Common imports in pages -->
	<jsp:include page="../header.jsp" />
	<title>Visualizza elemento</title>
	
</head>
<body class="d-flex flex-column h-100" style="background-color: #08082b;">
	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp" />
	
	<!-- Begin page content -->
	<main class="flex-shrink-0">
	  	<div class="container">
			
			<div class='card'>
			    <div class='card-header' style="background-color: #08082b;">
			        <h3 class="text-info">Dettaglio Annuncio</h3>
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Descrizione:</dt>
					  <dd class="col-sm-10">${show_annuncio_attr.testoAnnuncio}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Prezzo:</dt>
					  <dd class="col-sm-10">${show_annuncio_attr.prezzo}$</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Data Inserzione:</dt>
					  <dd class="col-sm-10"><fmt:formatDate type = "date" value = "${show_annuncio_attr.data}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
			    		<dt class="col-sm-2 text-right">Categorie:</dt>
			    		<c:forEach items="${show_annuncio_attr.categorie}" var="categoriaItem">
			    			<dd class="col-sm-2">${categoriaItem.descrizione} </dd>
			    		</c:forEach>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Venditore:</dt>
					  <dd class="col-sm-10">${show_annuncio_attr.utenteInserimento.nome} ${show_annuncio_attr.utenteInserimento.cognome}</dd>
			    	</dl>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			        <a href="${pageContext.request.contextPath }/annuncio/list" class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>