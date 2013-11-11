<table class="table">
	<tbody>
		<tr>
			<td>TAS</td>
			<td>${tas}</td>
		</tr>
		<tr>
			<td>TAE</td>
			<td>${tae}</td>
		</tr>
		<tr>
			<td>TAP</td>
			<td>${tap}</td>
		</tr>
		<tr>
			<td>PDS</td>
			<td><g:formatNumber number="${pds}" type="number" maxFractionDigits="2"/>%</td>
		</tr>
		<tr>
			<td>PDE</td>
			<td><g:formatNumber number="${pde}" type="number" maxFractionDigits="2"/>%</td>
		</tr>
		<tr>
			<td>UP</td>
			<td>${up}</td>
		</tr>
		<tr>
			<td>Periodo</td>
			<td>${(params.period) ?: period}</td>
		</tr>
	</tbody>
</table>