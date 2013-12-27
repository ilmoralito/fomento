<!doctype html>
<html lang="es">
<head>
	<meta name="layout" content="main">
	<r:require modules="bootstrap, app, datepicker"/>
	
</head>
<body>
	<div class="row">
		<div class="col-md-7">
			<h3>Periodo ${params?.period}</h3>
		</div>
		<div class="col-md-5">
			<g:form action="elist" class="form-inline pull-right">
				<div class="form-group">
					<label for="period" class="sr-only">Periodo</label>
					<g:select from="${2013..new Date()[Calendar.YEAR]}" name="peri" value="${(params?.period) ?: fomento.currentYear()}" class="form-control"/>
					<g:hiddenField name="max" value="30"/>
					<g:hiddenField name="offset" value="0"/>
				</div>
					<g:submitButton name="name" value="Mostrar" class="btn btn-default"/>
			</g:form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<g:if test="${partners}">
			
				
				<table class="table table-bordered table-striped tablelist">
					<thead>
						<tr>
							<th colspan="2">Socio</th>
							<th>Ene</th>
							<th>Feb</th>
							<th>Mar</th>
							<th>Abr</th>
							<th>May</th>
							<th>Jun</th>
							<th>Jul</th>
							<th>Agos</th>
							<th>Sept</th>
							<th>Oct</th>
							<th>Nov</th>
							<th>Dic</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						
						<g:each in="${partners}" var="par" status="i">
							<tr>
								<td rowspan="2" class="cabpart">${partners[i].fullName}</td>
								<th>S</th>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="0" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="1" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="2" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="3" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="4" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="5" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="6" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="7" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="8" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="9" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="10" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="soc" mth="11" peri="${peri}"/></td>
								<td><fomento:parTotal parN="${par}" flag="soc" peri="${peri}"/></td>
							</tr>
							<tr>
								<th>E</th>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="0" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="1" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="2" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="3" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="4" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="5" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="6" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="7" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="8" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="9" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="10" peri="${peri}"/></td>
								<td><fomento:partnerFee parN="${par}" flag="em" mth="11" peri="${peri}"/></td>
								<td><fomento:parTotal parN="${par}" flag="em" peri="${peri}"/></td>
							</tr>
						</g:each>
						<tr>
							<th>APORTE SOCIOS</th>
							<th colspan="2"><fomento:aporteTotal flag="soc" peri="${peri}"/></th>
							<th colspan="2">APORTE EMPRESA</th>
							<th colspan="2"><fomento:aporteTotal flag="em" peri="${peri}"/></th>
						</tr>
						<tr>
							<td colspan="15">
								<div>
									<ul class="pagination">
										<li>
											<g:paginate conatroller="fee" action="elist" total="${partnerTotal}" params="[max:"${max}", offset:"${offset}", flag:"OK", peri:"${peri}"]" omitPrev="true" omitNext="true"/>
										</li>
										<g:if test="${all=="ALL"}">
											<li>
												<g:link controller="fee" action="elist" title="Paginar" class="toolback step" data-placement="top" params="[max:"${30}", offset:"${0}", flag:"OK", peri:"${peri}"]"> 
													<span class="glyphicon glyphicon-zoom-out"></span>
												</g:link>
											</li>
										</g:if>
										<li>
											<g:link controller="fee" action="elist" title="Ver Todos" class="toollist step" data-placement="top" params="[max:"${partnerTotal}", offset:"${0}", flag:"OK", peri:"${peri}", all:"ALL"]"> 
												<span class="glyphicon glyphicon-list"></span>
											</g:link>
										</li>
									</ul>
								</div>
							</td>
						</tr>					
							
					</tbody>
					</table>
			</g:if>
			<g:else>
				<h2>...</h2>
			</g:else>
		</div>
		
	</div>
</body>
</html>