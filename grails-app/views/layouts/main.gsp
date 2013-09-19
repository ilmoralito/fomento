<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Socios"/></title>
	<r:layoutResources/>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	  <div class="container">
	  	<div class="navbar-header">
	  	  <a class="navbar-brand" href="#">Brand</a>
	  	</div>
	  	<div class="collapse navbar-collapse navbar-ex1-collapse">
	  	  <ul class="nav navbar-nav">
	  	    <li class="active"><a href="#">Link</a></li>
	  	    <li><a href="#">Link</a></li>
	  	  </ul>
	  	</div>
	  </div>
	</nav>

	<div class="container main">
		<div class="row">
			<div class="col-md-12">
				<g:layoutBody/>
				<g:if test="${flash.message}"><strong>${flash.message}</strong></g:if>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>