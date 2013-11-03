<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Aportes de socio"/></title>
	<r:layoutResources/>
</head>
<body>
	<g:render template="/layouts/main-navbar"/>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<g:render template="/partner/toolbar"/>
				<g:render template="/layouts/navbar"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-10">
				<g:layoutBody/>
				<g:if test="${flash.message}"><br><strong>${flash.message}</strong></g:if>
			</div>
			<div class="col-md-2">
				<div class="well well-sm">
					<div class="btn-group">
						<fomento:periods/>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">Personal</div>
					<div class="panel-body">
						${total}
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">Empresa</div>
					<div class="panel-body">
						${totalFactoryFee}
					</div>
				</div>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>