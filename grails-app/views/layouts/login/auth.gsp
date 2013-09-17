<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Iniciar sesion"/></title>
	<r:layoutResources/>
</head>
<body>
	<div class="container main">
		<div class="row">
			<div class="col-md-6">
				<h4>Lorem ipsum dolor sit amet.</h4>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Est, nostrum, cupiditate itaque obcaecati recusandae fugiat numquam facilis voluptatibus aliquam quos veniam accusantium odio qui sed quis vitae quasi corrupti optio.</p>
			</div>
			<div class="col-md-6">
				<g:layoutBody/>
				<g:if test="${flash.message}"><strong>${flash.message}</strong></g:if>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>