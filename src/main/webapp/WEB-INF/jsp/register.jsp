<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it">
	<head>
	  <meta charset="utf-8">
		<title>Register</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
 		<style>
		    .error_field {
		        color: red; 
		    }
		</style>
	
		 <!-- Custom styles for login -->
	    <link href="assets/css/signin.css" rel="stylesheet">
	</head>
	
	<body class="-flex flex-column h-100" style="background-color: #08082b;">
		<main class="flex-shrink-0">
			  <div class="container mb-5">
			  
			  		<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="register_utente_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card w-50' style="margin: auto">
				    <div class='card-header' style="background-color: #08082b;">
				        <h5 class="text-info">Register</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>
		
		
							<form:form modelAttribute="register_utente_attr" method="post" action="executeRegister" novalidate="novalidate" class="row g-3">
					
							
								<div class="col-md-12">
									<label for="nome" class="form-label">Nome <span class="text-danger">*</span></label>
									<spring:bind path="nome">
										<input type="text" name="nome" id="nome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il nome" value="${register_utente_attr.nome }" required>
									</spring:bind>
									<form:errors  path="nome" cssClass="error_field" />
								</div>
								
								<div class="col-md-12">
									<label for="cognome" class="form-label">Cognome <span class="text-danger">*</span></label>
									<spring:bind path="cognome">
										<input type="text" name="cognome" id="cognome" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire il cognome" value="${register_utente_attr.cognome }" required>
									</spring:bind>
									<form:errors  path="cognome" cssClass="error_field" />
								</div>
								<div class="col-md-12">
									<label for="username" class="form-label">Username <span class="text-danger">*</span></label>
									<spring:bind path="username">
										<input type="text" class="form-control ${status.error ? 'is-invalid' : ''}" name="username" id="username" placeholder="Inserire Username" value="${register_utente_attr.username }" required>
									</spring:bind>
									<form:errors  path="username" cssClass="error_field" />
								</div>
								 
								<div class="col-md-6">
									<label for="password" class="form-label">Password <span class="text-danger">*</span></label>
									<spring:bind path="password">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="password" id="password" placeholder="Inserire Password"  required>
									</spring:bind>
									<form:errors  path="password" cssClass="error_field" />
								</div>
								
								<div class="col-md-6">
									<label for="confermaPassword" class="form-label">Conferma Password <span class="text-danger">*</span></label>
									<spring:bind path="confermaPassword">
										<input type="password" class="form-control ${status.error ? 'is-invalid' : ''}" name="confermaPassword" id="confermaPassword" placeholder="Confermare Password"  required>
									</spring:bind>
									<form:errors  path="confermaPassword" cssClass="error_field" />
								</div>
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
		
						</form:form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
	</body>
</html>