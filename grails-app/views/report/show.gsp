<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<g:render template="toolbar"/>
	<div class="row">
		<div class="col-md-10">
			<g:if test="${dividends}">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Nombre del socio</th>
							<th>Dividendo del periodo</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${dividends}" var="dividend">
						<tr>
							<td>${dividend.partner}</td>
							<td>${dividend.dividend}</td>
						</tr>
						</g:each>
					</tbody>
				</table>
			</g:if>
			<g:else>
				<h1>Nada que mostrar</h1>
			</g:else>
		</div>
		<div class="col-md-2">
			<br>
			<div class="panel panel-info">
				<div class="panel-heading">
					Resultados
				</div>
				<div class="panel-body">
					<table class="table">
						<tbody>
							<tr>
								<td>TAS</td>
								<td>${tas}</td>
							</tr>
							<tr>
								<td>UP</td>
								<td>${up}</td>
							</tr>
							<tr>
								<td>Perido</td>
								<td>${params?.period}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
