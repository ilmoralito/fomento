<g:form controller= "user" action="profile">
    <g:hiddenField name="flag" value="personaldata"/>
   	<div class="form-group">
        <g:textField type="text" class="form-control" name="fullName" autofocus="true" placeholder="${message(code:'org.fomento.fullname')}"/>
    </div>
     <div class="form-group">
     	<g:textField type="text" class="form-control" name="email" autofocus="true" placeholder="${message(code:'org.fomento.email')}"/>
     </div>
<g:submitButton name="btnregistration" value="${message(code:'org.fomento.confirmchange')}"class="btn btn-default pull-right"/>
</g:form>