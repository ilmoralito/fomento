<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap-css, bootstrap-dropdown, app"/>
</head>
<body>
	<g:set var="currentPeriod" value="${fomento.currentYear().toInteger()}"/>
	<g:render template="toolbar"/>
	<br>
	<div class="row">
		<div class="col-md-9">
			<g:if test="${partners}">
				<g:if test="${tap==0}">
					<div class="alert alert-danger">
						<strong>NO EXISTEN APORTES DE SOCIOS Y DE EMPRESA PARA CALCULAR DIVIDENDOS EN ESTE PERIODO!!!</strong>
					</div>
				</g:if>
				<g:else>
					<g:render template="data" model="[results:partners]"/>
				</g:else>
			</g:if>
			<g:else>
				<h3>...</h3>
			</g:else>
		</div>
		<div class="col-md-3">
			<form action="dividends" method="post" autocomplete="off">
				<div class="form-group">
					<label class="sr-only" for="period">Periodo</label>
					<g:select name="period" from="${2010..currentPeriod}" value="${(params?.period) ?: currentPeriod}" class="form-control"/>
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
					<g:render template="panel-heading"/>
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
