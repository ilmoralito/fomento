<table class="table table-hover">
	<thead>
		<tr>
			<th></th>
			<th colspan="4">Socio</th>
			<th colspan="4">Empresa</th>
		</tr>
		<tr>
			<th>Socio</th>
			<th>FPS</th>
			<th>Dividendo</th>
			<th>10%</th>
			<th>DN</th>
			<th>FPE</th>
			<th>Dividendo</th>
			<th>10%</th>
			<th>DN</th>
		</tr>
	</thead>
	<tbody>
		<g:each in="${results}" var="partner">
			<g:set var="fps" value="${fomento.fp(partner:partner, period:period, flag:'socio')}"/>
			<g:set var="fpe" value="${fomento.fp(partner:partner, period:period, flag:'empresa')}"/>

			<g:set var="dds" value="${fomento.dd(up:up, pd:pds, fp:fps)}"/>
			<g:set var="dde" value="${fomento.dd(up:up, pd:pde, fp:fpe)}"/>

			<g:set var="partnerPercentage" value="${fomento.dd(up:up, pd:pds, fp:fps).toDouble() * 0.1}"/>
			<g:set var="factoryPercentage" value="${fomento.dd(up:up, pd:pde, fp:fpe).toDouble() * 0.1}"/>

			<!--Dividendo neto socio-->
			<g:set var="dns" value="${dds.toDouble() - partnerPercentage}"/>

			<!--Dividendo neto empresa-->
			<g:set var="dne" value="${dde.toDouble() - factoryPercentage}"/>

			<tr>
				<td>${partner}</td>
				<td><g:formatNumber number="${fps}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${dds}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${partnerPercentage}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${dns}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${fpe}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${dde}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${factoryPercentage}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
				<td><g:formatNumber number="${dne}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
			</tr>
		</g:each>
		<tr>
			<td>TOTAL</td>
			<td></td>
			<td><g:formatNumber number="${pds * up}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td><g:formatNumber number="${pde * up}" type="number" maxFractionDigits="2" roundingMode="UNNECESSARY"/></td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>
