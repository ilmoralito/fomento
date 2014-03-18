<p>
	<div class="row">
		<div class="col-md-6">TAS</div>
		<div class="col-md-6"><g:formatNumber number="${tas}" type="number" maxFractionDigits="2"/></div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">TAE</div>
		<div class="col-md-6"><g:formatNumber number="${tae}" type="number" maxFractionDigits="2"/></div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">TAP</div>
		<div class="col-md-6"><g:formatNumber number="${tap}" type="number" maxFractionDigits="2"/></div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">PDS</div>
		<div class="col-md-6"><g:formatNumber number="${pds * 100}" type="number" maxFractionDigits="2"/>%</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">PDE</div>
		<div class="col-md-6"><g:formatNumber number="${pde * 100}" type="number" maxFractionDigits="2"/>%</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">UP</div>
		<div class="col-md-6">${up}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">IR</div>
		<div class="col-md-6">${ir}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">Reserva</div>
		<div class="col-md-6">${backup}</div>
	</div>
</p>

<p>
	<div class="row">
		<div class="col-md-6">Periodo</div>
		<div class="col-md-6">${(params.period) ?: period}</div>
	</div>
</p>
