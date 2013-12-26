<table class="table table-hover">
	<thead>
		<th>Socio</th>
		<th>FPS</th>
		<th>DS</th>
		<th>10% Socio</th>
		<th>Dividendo Neto</th>
		<th>FPE</th>
		<th>DE</th>
		<th>10% Empresa</th>
		<th>Dividendo Neto</th>
	</thead>
	<tbody>
		<g:each in="${results}" var="partner">
			<g:set var="fps" value="${fomento.fp(partner:partner, period:period, fee:'fee', capital:'capitalization')}"/>
			<g:set var="fpe" value="${fomento.fp(partner:partner, period:period, fee:'factoryFee', capital:'factoryCapital')}"/>
			<g:set var="partnerPercentage" value="${fomento.dd(up:up, pd:pds, fp:fps)}"/>
			<tr>
				<td>${partner}</td>
				<td><g:formatNumber number="${fps}" type="number" maxFractionDigits="2"/></td>
				<td><fomento:dd up="${up}" pd="${pds}" fp="${fps}"/></td>
				<td>${partnerPercentage * 0.1}</td>
				<td>divsocios- 10%</td>
				<td><g:formatNumber number="${fpe}" type="number" maxFractionDigits="2"/></td>
				<td><fomento:dd up="${up}" pd="${pde}" fp="${fpe}"/></td>
				<td>divempre * 0.1</td>
				<td>divempre - 10%</td>
			</tr>
		</g:each>
		<tr>
			<td>TOTAL</td>
			<td></td>
			<td><g:formatNumber number="${pds * up}" type="number" maxFractionDigits="2"/></td>
			<td></td>
			<td></td>
			<td></td>
			<td><g:formatNumber number="${pde * up}" type="number" maxFractionDigits="2"/></td>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>