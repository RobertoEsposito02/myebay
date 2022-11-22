<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
  <!-- Fixed navbar -->
 <nav class="navbar navbar-expand-lg navbar-dark" aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/home">Home</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/home/#ricercaAnnunci">Ricerca Annunci</a></li>
            </ul> 
          </li>
           <sec:authorize access="isAuthenticated()">
           	<sec:authorize access="hasRole('ADMIN')">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestione Utenze</a>
		        <div class="dropdown-menu" aria-labelledby="dropdown01">
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/search">Ricerca Utenti</a>
		          <a class="dropdown-item" href="${pageContext.request.contextPath}/utente/insert">Inserisci Utente</a>
		        </div>
		      </li>
		     </sec:authorize>
		   </sec:authorize>
		   
		   
        </ul>
      </div>
      <c:if test="${userInfo == null}">
	   	  <div class="col-md-3 text-end" >
	    	<a class="text-light" style="text-decoration: none" href="${pageContext.request.contextPath}/login">Login</a>
	      </div>
	  </c:if>
      	<sec:authorize access="isAuthenticated()">
	   	  <div class="col-md-3 text-end" >
	       	<p style="margin-bottom: 0px;" class="navbar-text">Utente: <sec:authentication property="name"/> (${userInfo.nome } ${userInfo.cognome })
	    	</p>
	      </div>
	   	</sec:authorize>
	   	<sec:authorize access="isAuthenticated()">
		  	<div class="col-md-1 text-end" >
		  		<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			  		<li class="nav-item dropdown">
			  			<a class="nav-link dropdown-toggle text-light" href="#" id="dropdown01" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account</a>
				  		<div class="dropdown-menu" aria-labelledby="dropdown01">
				    		<a class="nav-item text-dark text-center" style="text-decoration: none; margin-left: 10px" href="${pageContext.request.contextPath}/utente/personalpage">Personal Page</a>
				    		<div class="dropdown-divider"></div>
				    		<a class="nav-item text-dark text-center" style="text-decoration: none; margin-left: 10px" href="${pageContext.request.contextPath}/account/cambiaPassword">Change Password</a>
				    		<div class="dropdown-divider"></div>
				    		<a class="text-dark" style="text-decoration: none; margin-left: 10px" href="${pageContext.request.contextPath}/logout">Logout</a>
				    	</div>
			    	</li>
		    	</ul>
		    </div>
	     </sec:authorize>
    </div>
  </nav>
  
  
</header>
