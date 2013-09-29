<!doctype html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><g:layoutTitle default="Socios"/></title>
	<r:layoutResources/>
</head>
<body>
	<!--Navbar-->
	<g:render template="/layouts/main-navbar"/>

	<!--container to allow sidebar effect-->
	<div id="st-container" class="st-container">
		<!--sidebar-->
		<nav class="st-menu st-effect-1" id="menu-1">
			<h2><span class="glyphicon glyphicon-calendar"></span></h2>
			<!--search by dates form-->
			<g:form action="list">
				<div class="form-group">
					<label class="sr-only" for="from">Desde</label>
					<g:textField name="from" class="form-control" placeholder="Desde..."/>
				</div>

				<div class="form-group">
					<label class="sr-only" for="to">Hasta</label>
					<g:textField name="to" class="form-control" placeholder="Hasta..."/>
				</div>

				<g:submitButton name="send" value="Buscar" class="btn btn-default"/>
			</g:form>
		</nav>

		<div class="st-pusher">
			<div class="st-content">
				<div class="st-content-inner">
					<div class="container list-main">
						<div class="row">
							<div class="col-md-12">
								<g:layoutBody/>
								<g:if test="${flash.message}"><br><strong>${flash.message}</strong></g:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<r:layoutResources/>
</body>
</html>