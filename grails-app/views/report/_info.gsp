<g:hiddenField name="tas" value="${(params.tas) ?: tas}"/>
<g:hiddenField name="tae" value="${(params.tae) ?: tae}"/>

<g:hiddenField name="tap" value="${(params.tap) ?: fomento.tap(tas:tas, tae:tae)}"/>
<g:hiddenField name="fps" value="${(params.fps) ?: fomento.dd(tas:tas, tae:tae, flag:"partner")}"/>
<g:hiddenField name="fpe" value="${(params.fpe) ?: fomento.dd(tas:tas, tae:tae)}"/>

<g:hiddenField name="up" value="${(params.up) ?: up}"/>
<g:hiddenField name="period" value="${(params.period) ?: period}"/>