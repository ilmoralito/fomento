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
			<td><fomento:tap tas="${tas}" tae="${tae}"/></td>
		</tr>
		<tr>
			<td>FPS</td>
			<td><fomento:dd tas="${tas}" tae="${tae}" flag="partner"/>%</td>
		</tr>
		<tr>
			<td>FPE</td>
			<td><fomento:dd tas="${tas}" tae="${tae}"/>%</td>
		</tr>
		<tr>
			<td>UP</td>
			<td>${up}</td>
		</tr>
		<tr>
			<td>Perido</td>
			<td>${(params.period) ?: period}</td>
		</tr>
	</tbody>
</table>