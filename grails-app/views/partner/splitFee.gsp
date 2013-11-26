<!doctype html>
<html lang="es">
<head>
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:form action="splitFee" role="form" class="form-inline">
		<g:hiddenField name="id" value="${params?.id}"/>
		<div class="form-group">
    		<label class="sr-only" for="range">Rango</label>
    		<g:textField name="range" value="${partner?.affiliation?.range}" class="form-control" placeholder="${partner?.affiliation?.fee}"/>
  		</div>
  		<button type="submit" class="btn btn-default">Aplicar</button>
	</g:form>
</body>
</html>
