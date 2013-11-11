<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<br>
	<div class="row">
		<div class="col-md-9">
			<g:if test="${partners}">
				<g:render template="data"/>
			</g:if>
			<g:else>
				<h3>...</h3>
			</g:else>
		</div>
		<div class="col-md-3">
			<form action="dividends" method="post">
				<div class="form-group">
					<label class="sr-only" for="period">Periodo</label>
					<g:select name="period" from="${fomento.currentYear()}" value="${params?.period}" class="form-control"/>
				</div>
				<div class="form-group">
					<label class="sr-only" for="up">Utilidad del periodo</label>
					<g:textField name="up" class="form-control" value="${params?.up}" placeholder="Utilidad del periodo" autofocus="true"/>
				</div>
				<button type="submit" class="btn btn-default">Calcular</button>
			</form>
			<br>
			<g:if test="${tas && tae}">
				<div class="panel panel-info">
					<div class="panel-heading">
						Resultados
					</div>
					<div class="panel-body">
						<g:render template="panel-body"/>
						<g:form action="applyDividends">
							<g:render template="info"/>

							<button type="submit" class="btn btn-default">
								<span class="glyphicon glyphicon-floppy-save"></span>
							</button>
						</g:form>
					</div>
				</div>
			</g:if>

			<g:hasErrors bean="${cmd}">
				<div class="alert">
					<g:renderErrors bean="${cmd}"/>
				</div>
			</g:hasErrors>
		</div>
	</div>
</body>
</html>
