<table class="table table-hover">
	<thead>
		<th>Socio</th>
		<th>FPS</th>
		<th>Dividendos a pagar Socio</th>
		<th>FPE</th>
		<th>Dividendos a pagar Empresa</th>
	</thead>
	<tbody>
		<g:each in="${partners}" var="partner">
			<g:set var="fps" value="${fomento.fp(partner:partner, period:period, fee:'fee', capital:'capitalization')}"/>
			<g:set var="fpe" value="${fomento.fp(partner:partner, period:period, fee:'factoryFee', capital:'factoryCapital')}"/>
			<tr>
				<td>${partner}</td>
				<td><g:formatNumber number="${fps}" type="number" maxFractionDigits="2"/></td>
				<td><fomento:dd up="${up}" pd="${pds}" fp="${fps}"/></td>
				<td><g:formatNumber number="${fpe}" type="number" maxFractionDigits="2"/></td>
				<td><fomento:dd up="${up}" pd="${pde}" fp="${fpe}"/></td>
			</tr>
		</g:each>
			<tr>
				<td>TOTAL</td>
				<td></td>
				<td><g:formatNumber number="${pds * up}" type="number" maxFractionDigits="2"/></td>
				<td></td>
				<td><g:formatNumber number="${pde * up}" type="number" maxFractionDigits="2"/></td>
			</tr>
	</tbody>
</table>