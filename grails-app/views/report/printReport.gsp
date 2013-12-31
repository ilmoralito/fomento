<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="layout" content="main">
	<title>Capitalizar</title>
	<r:require modules="bootstrap, capitalize"/>
</head>
<body>
	<style type="text/css" media="print">
		.navbar{display: none;}
		.btnListaS{display: none;}
		.well{display: none;}
		body{font-family: arial; font-size: 13px;}
		.pan, .toolbar, .cabtr{display: none;}
		.tablaSaldo{border:1px solid black;padding: 3px; border-spacing: 0;border-collapse: collapse;}
		.tablaSaldo td{border:1px solid black;padding:5px}
		.cabecera{display: block;width: 100%;height: 150px; border:1px solid black;background: red;}
	</style>

	<g:render template="toolbar"/>
	<div class="row pan">
		<div class="col-md-6">
			<div class="panel panel-info">
		        <div class="panel-heading">
		          <h3 class="panel-title">Reporte del Periodo, ${period}</h3>
		        </div>
		        <div class="panel-body">
		        	<h3>${partner.fullName}</h3>
		        	<div class="col-md-6">
						<h4>Saldo del Socio:</h4>
					</div>
					<div class="col-md-6">
						<h4>C$ <g:formatNumber number="${socio}" type="number" maxFractionDigits="2" /></h4>
					</div>
					<div class="col-md-6">
						<h4>Saldo Empresa:</h4>
					</div>
					<div class="col-md-6">
						<h4>C$ <g:formatNumber number="${empresa}" type="number" maxFractionDigits="2" /></h4>
					</div>
					<div class="col-md-6">
						<h4>Capitalizacion del Periodo:</h4>
					</div>
					<div class="col-md-6">
						<h4>C$ <g:formatNumber number="${capitalizacion}" type="number" maxFractionDigits="2" /></h4>
					</div>
					<div class="col-md-6">
						<h4>Retiro del Periodo:</h4>
					</div>
					<div class="col-md-6">
						<h4>C$ <g:formatNumber number="${retiro}" type="number" maxFractionDigits="2" /></h4>
					</div>
		        </div>
    		</div>
		</div>
	</div>

	<div class="row cabecera">
		<div class="col-md-6">
			hola mundo
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<table class="table table-hover tablaSaldo">
				<thead>
					<tr class="cabtr">
						<th>Fecha</th>
						<th>Saldo</th>
						<th>Empresa</th>
						<th>Capitaliz.</th>
						<th>Retiro</th>
						<th>Firma</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>2013-12-02</td>
						<td><g:formatNumber number="${socio}" type="number" maxFractionDigits="2" /></td>
						<td><g:formatNumber number="${empresa}" type="number" maxFractionDigits="2" /></td>
						<td>
							<g:formatNumber number="${capitalizacion}" type="number" maxFractionDigits="2" />
						</td>
						<td><g:formatNumber number="${retiro}" type="number" maxFractionDigits="2" /></h4></td>
						<td>_________</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>