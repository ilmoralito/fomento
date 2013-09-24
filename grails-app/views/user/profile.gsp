<!doctype html>
<html lang="en">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	
	<div class="col-md-4">
		Datos Generales
	</div>
	<div class="col-md-8">
		
		<ul class="nav nav-tabs">
		  <li class="${activepersonal}"><a href="#profile" data-toggle="tab">${message(code:'org.fomento.personaldata')}</a></li>
		  <li class="${activepassword}"><a href="#password" data-toggle="tab">${message(code:'org.fomento.changepass')}</a></li>
		</ul>
 
	  <diAv class="tab-content">
	    <div id="profile" class="tab-pane ${activepersonal}">
	      <br>
	     	<div class="col-md-6">
	     		<g:render template="frpersonaldata"/>
	      </div>
	    </div>

	    <div id="password" class="tab-pane ${activepassword}">
	      <br>
	      <div class="col-md-6">
	        <g:render template="frchangepass"/>  
	      </div>
	    </div>
	  </div>

	</div>
	
</body>
</html>