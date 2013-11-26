<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Reporte"/></title>
	<r:layoutResources/>
</head>
<body>
	<g:set var="range" value="${partner?.affiliation?.range}"/>
	<g:set var="fee" value="${partner?.affiliation?.fee}"/>
	<g:set var="second" value="${(range) ? fee - range : 0}"/>

	<g:render template="/layouts/main-navbar"/>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<g:render template="toolbar"/>
				<g:render template="/layouts/navbar"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9">
				<g:if test="${range}">
					<p>Para dejar una sola cuota, dejar en blanco la caja de texto y hacer click en el boton aplicar</p>
				</g:if>
				<g:layoutBody/>
				<g:if test="${flash.message}">
					<p>
						<br>
						<strong>${flash.message}</strong>
					</p>
				</g:if>
			</div>
			<div class="col-md-3">
				<g:if test="${partner?.affiliation?.range}">
					<h4>Cuota selccionada</h4>
					${partner?.affiliation?.fee}
					<h4>Primer pago</h4>
					${range}
					<h4>Segundo pago</h4>
					${second}
				</g:if>
			</div>
		</div>
	</div>

	<r:layoutResources/>
</body>
</html>
