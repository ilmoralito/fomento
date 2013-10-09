<!doctype html>
<html lang="es">
<head>
	<r:require modules="bootstrap, list, effect"/>
</head>
<body>
	<g:render template="toolbar"/>

	<g:if test="${partners}">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>${partners.size()} Socio${partners.size() > 1 ? 's' : ''} </th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${partners}" var="partner">
					<tr>
						<td><g:link action="show" id="${partner.id}">${partner.fullName}</g:link></td>
						<td style="width:1px;">
							<sec:ifAllGranted roles="ROLE_ADMIN">
								<g:link action="delete" id="${partner.id}">
									<span class="glyphicon glyphicon-trash"></span>
								</g:link>
							</sec:ifAllGranted>
						</td>
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
