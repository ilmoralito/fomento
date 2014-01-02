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
		body{font-family: arial; font-size: 12px;}
		.pan, .toolbar, .cabtr{display: none;}
		.tablaSaldo{margin-left:auto;margin-right: auto; padding: 3px; border-spacing: 0;border-collapse: collapse; top:150px;}
		.tablaSaldo td{padding:5px; width: 70px; height: 20px;}
		.cabecera{display: block;width: 100%;height: 200px;}
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
					<g:each in= "${[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]}" var= "num" >
						<tr>
							<td>
								<g:if test="${num==position}">
									2013-12-02
								</g:if>
							</td>
							<td>
								<g:if test="${num==position}">
									<g:formatNumber number="${socio}" type="number" maxFractionDigits="2" />
								</g:if>
							</td>
							<td>
								<g:if test="${num==position}">
									<g:formatNumber number="${empresa}" type="number" maxFractionDigits="2" />
								</g:if>
							</td>
							<td>
								<g:if test="${num==position}">
									<g:formatNumber number="${capitalizacion}" type="number" maxFractionDigits="2" />
								</g:if>
							</td>
							<td>
								<g:if test="${num==position}">
									<g:formatNumber number="${retiro}" type="number" maxFractionDigits="2" />
								</g:if>
							</td>
							<td>
								<g:if test="${num==position}">
									_________
								</g:if>
							</td>	
						</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>