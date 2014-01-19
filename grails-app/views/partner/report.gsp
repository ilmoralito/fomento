<!doctype html>
<html lang="es">
<head>
	<r:require modules="bootstrap, app"/>
</head>
<body>
	<style type="text/css" media="print">
		.navbar{display: none;}
		.btnListaS{display: none;}
		.well{display: none;}
		ul{display: none;}
		.partner{display:block;}
		.CabeceraReporte{display:block; text-align: center;}
		.Cuotas{text-align: center;}
		body{font-family: arial;}
		.tablaInforme{margin-left:auto;margin-right: auto; border: 1px solid #ddd; padding: 3px; border-spacing: 0;border-collapse: collapse; margin-bottom: 10px;}
		.tablaInforme th, .tablaInforme td{text-align:center; border: 1px solid #ddd;}
		.tablaInforme th{padding:5px;}
		tr .editOption{border-right:0 hidden;}
		tr .numCuotas{padding: 3px;}
		.panelUp{margin-bottom: 10px;}
		.cabValor, .valor{display: inline;}
		.valor{text-align: right;}
		.cabValor, .panel-heading{font-weight:bold;}
		.panel-heading{text-align: center;}
		.panel-heading, .panel-body{border:1px solid #ddd; padding: 5px;}
	</style>

	<g:if test="${fees}">
		<h2 class="CabeceraReporte">Junta de Fomento JUFOLI</h2>
		<h3 class="CabeceraReporte">Estado de Cuenta</h3>
		<h4 class="partner">${partner.fullName}</h4>
		<h3 class="Cuotas">Cuotas del periodo ${params?.period}</h3>

		<table class="table table-hover tablaInforme">
			<thead>
				<tr>
					<th width="1" class="numCuotas"></th>
					<th width="1" class="editOption"><span class="glyphicon glyphicon-pencil"></span></th>
					<th>Cuota de socio</th>
					<th>Cuota de empresa</th>
					<th>Fecha de cuota</th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${fees}" var="fee" status="i">
					<tr>
						<td class="numCuotas">${i + 1}</td>
						<td  class="editOption"><g:link controller="fee" action="show" params="[id:fee.id, partner:fee.partner.id, period:params?.period]"><span class="glyphicon glyphicon-pencil"></span></g:link></td>
						<td>${fee}</td>
						<td>${fee.factoryFee}</td>
						<td>${fee.dateCreated.format("yyyy-MM-dd")}</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
</body>
</html>
