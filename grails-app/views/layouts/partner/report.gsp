<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Reporte"/></title>
	<r:layoutResources/>
</head>
<body>
	<!--Navbar-->
	<g:render template="/layouts/main-navbar"/>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<g:render template="toolbar"/>
				<g:render template="/layouts/navbar"/>
			</div>
		</div>

		<div class="row">
			<div class="col-md-10">
				<g:layoutBody/>
			</div>
			<div class="col-md-2">
				<div class="well well-sm">
					<div class="btn-group">
						<fomento:periods/>
					</div>
				</div>
			</div>
		</div>
	</div>

	<r:layoutResources/>
</body>
</html>