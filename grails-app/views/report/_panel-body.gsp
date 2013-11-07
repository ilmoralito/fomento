<table class="table">
	<tbody>
		<tr>
			<td>TAS</td>
			<td>${partnerTAS}</td>
		</tr>
		<tr>
			<td>TAE</td>
			<td>${factoryTAS}</td>
		</tr>
		<tr>
			<td>TAP</td>
			<td><fomento:tap tas="${partnerTAS}" tae="${factoryTAS}"/></td>
		</tr>
		<tr>
			<td>FPS</td>
			<td><fomento:dd tas="${partnerTAS}" tae="${factoryTAS}" flag="partner"/>%</td>
		</tr>
		<tr>
			<td>FPE</td>
			<td><fomento:dd tas="${partnerTAS}" tae="${factoryTAS}"/>%</td>
		</tr>
		<tr>
			<td>UP</td>
			<td>${up}</td>
		</tr>
		<tr>
			<td>Perido</td>
			<td>${params?.period ?: period}</td>
		</tr>
	</tbody>
</table>