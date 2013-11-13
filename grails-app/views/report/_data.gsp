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
			<tr>
				<td>${partner}</td>
				<td><fomento:fp partner="${partner}" period="${period}" fee="fee" capital="capitalization"/></td>
				<td><fomento:dd up="${up}" pd="${pds}" fp="${fomento.fp(partner:partner, period:period, fee:'fee', capital:'capitalization')}"/></td>
				<td><fomento:fp partner="${partner}" period="${period}" fee="factoryFee" capital="factoryCapital"/></td>
				<td><fomento:dd up="${up}" pd="${pde}" fp="${fomento.fp(partner:partner, period:period, fee:'factoryFee', capital:'factoryCapital')}"/></td>
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