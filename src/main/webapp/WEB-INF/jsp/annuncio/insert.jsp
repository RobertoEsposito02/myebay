<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	 	 <style>
		    .error_field {
		        color: red; 
		    }
		</style>
	   
	   <title>Modifica Elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100" style="background-color: #08082b;">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="insert_annuncio_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card' >
				    <div class='card-header' style="background-color: #08082b;">
				        <h5 class="text-info">Inserisci annuncio</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="insert_annuncio_attr" method="post" action="${pageContext.request.contextPath}/annuncio/executeInsert" novalidate="novalidate" class="row g-3">
								<input type="hidden" name="id" value="${insert_annuncio_attr.id }">
							
								<div class="col-md-6">
									<label for="testoAnnuncio" class="form-label">Testo Annuncio <span class="text-danger">*</span></label>
									<spring:bind path="testoAnnuncio">
										<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${insert_annuncio_attr.testoAnnuncio }" required>
									</spring:bind>
									<form:errors  path="testoAnnuncio" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo <span class="text-danger">*</span></label>
									<spring:bind path="prezzo">
										<input type="text" name="prezzo" id="prezzo" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il prezzo" value="${insert_annuncio_attr.prezzo }" required>
									</spring:bind>
									<form:errors  path="prezzo" cssClass="error_field" />
								</div>

								<div class="col-md-6">
									<input type="hidden" name="data" id="data" class="form-control ${status.error ? 'is-invalid' : ''}" value="${insert_annuncio_attr.data }" required>
								</div>

								<div class="col-md-6">
									<input type="hidden" name="data" id="data" class="form-control ${status.error ? 'is-invalid' : ''}" value="${edit_annuncio_attr.categorie }" required>
								</div>

								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
									<a class="btn btn-outline-secondary ml-2" href="${pageContext.request.contextPath}/annuncio/listMyAnnunci">Torna alla Lista</a>
								</div>
		
						</form:form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>