<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="pull-right">
				<div class="btn-group">
					<g:link action="create" class="btn btn-default">Crear socio</g:link>
				</div>
			</div>
		</div>
	</div>

	<g:if test="${partners}">
		<table class="table">
			<thead>
				<tr>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${partners}" var="partner">
					<tr>
						<td><g:link action="show" id="${partner.id}">${partner.fullName}</g:link></td>
						<td style="width:1px;"><g:link action="delete" id="${partner.id}"><span class="glyphicon glyphicon-trash"></span></g:link></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<p><br>Nada que mostrar</p>
	</g:else>
</body>
</html>
