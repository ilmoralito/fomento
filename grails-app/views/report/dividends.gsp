<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<form class="form-inline" role="form" method="post">
		<div class="form-group">
			<label class="sr-only" for="up">Utilidad del periodo</label>
			<input type="text" class="form-control" id="up" name="up" placeholder="Utilidad del periodo">
		</div>
		<button type="submit" class="btn btn-default">Enviar</button>
	</form>

	<g:hasErrors bean="${cmd}">
		<div class="alert">
			<g:renderErrors bean="${cmd}"/>
		</div>
	</g:hasErrors>

	<g:if test="${partners}">
		<br>
		<div class="row">
			<div class="col-md-6">
				TAS:${tas}
			</div>
			<div class="col-md-6">
				Utilidad del periodo:${up}
			</div>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<th>Socio</th>
				<th>APS</th>
				<th>FPS</th>
				<th>DD</th>
				<th>DP</th>
			</thead>
			<tbody>
				<g:each in="${partners}" var="partner">
					<tr>
						<td>${partner}</td>
						<td><fomento:aps partner="${partner}" period="${period}"/></td>
						<td><fomento:fps tas="${tas}" partner="${partner}" period="${period}"/></td>
						<td>${up.toDouble() / fomento.fps(tas:tas, partner:partner, period:period).toDouble()}</td>
						<td>${(up.toDouble() / fomento.fps(tas:tas, partner:partner, period:period).toDouble()) * 0.1}</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
</body>
</html>
