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
			        <h3 class="text-info">Dettaglio Annuncio Da eliminare</h3>
			    </div>
			
			    <div class='card-body'>
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Descrizione:</dt>
					  <dd class="col-sm-10">${delete_annuncio_attr.testoAnnuncio}</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Prezzo:</dt>
					  <dd class="col-sm-10">${delete_annuncio_attr.prezzo}$</dd>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Data Inserzione:</dt>
					  <dd class="col-sm-10"><fmt:formatDate type = "date" value = "${delete_annuncio_attr.data}" /></dd>
			    	</dl>
			    	
			    	<dl class="row">
			    		<dt class="col-sm-2 text-right">Categorie:</dt>
			    		<c:forEach items="${delete_annuncio_attr.categorie}" var="categoriaItem">
			    			<dd class="col-sm-2">${categoriaItem.descrizione} </dd>
			    		</c:forEach>
			    	</dl>
			    	
			    	<dl class="row">
					  <dt class="col-sm-2 text-right">Venditore:</dt>
					  <dd class="col-sm-10">${delete_annuncio_attr.utenteInserimento.nome} ${delete_annuncio_attr.utenteInserimento.cognome}</dd>
			    	</dl>
			    	
			    <!-- end card body -->
			    </div>
			    
			    <div class='card-footer'>
			         <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmOperationModal">
					  Elimina
					</button>
			        <a href="${pageContext.request.contextPath}/annuncio/list"  class='btn btn-outline-secondary' style='width:80px'>
			            <i class='fa fa-chevron-left'></i> Back
			        </a>
			    </div>
			<!-- end card -->
			</div>	
	
		<!-- end container -->  
		</div>
		
	</main>
	<jsp:include page="../footer.jsp" />
	<div class="modal fade" id="confirmOperationModal" tabindex="-1"  aria-labelledby="confirmOperationModalLabel"
	    aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered" >
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="confirmOperationModalLabel">Conferma Eliminazione</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                Continuare con l'aquisto?
	            </div>
	            <form method="post" action="${pageContext.request.contextPath}/annuncio/executeDelete" >
		            <div class="modal-footer">
		                <button type="button" class="btn btn-secondary modal" data-bs-dismiss="modal">Chiudi</button>
		            	<input type="hidden" name="idAnnuncio" id="idAnnuncio" value="${delete_annuncio_attr.id}">
		            	<input type="hidden" name="idUtente" id="idUtente" value="${userinfo.id}">
		                <input type="submit" value="Continua"  class="btn btn-danger">
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
	<!-- end Modal -->
	<script type="text/javascript">
		
	</script>
</body>
</html>