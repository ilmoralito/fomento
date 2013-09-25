<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	
	
	<div class="col-md-12">
		
		<ul class="nav nav-tabs">
		  <li class="${activegeneral}"><a href="#generaldata" data-toggle="tab">${message(code:'org.fomento.generaldata')}</a></li>
		  <li class="${activepassword}"><a href="#password" data-toggle="tab">${message(code:'org.fomento.changepass')}</a></li>
		</ul>
 
	  <div class="tab-content">
	    <div id="generaldata" class="tab-pane ${activegeneral}">
	      <br>
	     	<div>
		     	<div class="col-md-5">
		     		<g:render template="frgeneraldata"/>
		     	</div>
		     	<div class="col-md-7">
		     		<g:if test="${er=="ok"}">
		     			<g:render template="men-error"/>
		     		</g:if>
		     		<g:if test="${men=="ok"}">
		     			<g:render template="men-success"/>
		     		</g:if>
		     	</div>
	        </div>
	    </div>

	    <div id="password" class="tab-pane ${activepassword}">
	      <br>
	      <div class="col-md-5">
	        <g:render template="frchangepass"/>  
	      </div>
	    </div>
	  </div>

	</div>
	
</body>
</html>