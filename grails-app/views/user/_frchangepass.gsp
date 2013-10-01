<g:form controller= "user" action="updatePassword">
 <div class="form-group">
    <g:passwordField class="form-control" name="currentpassword" placeholder="${message(code:'org.fomento.currentpassword')}"/>
  </div>
  <div class="form-group">
    <g:passwordField class="form-control" name="password" placeholder="${message(code:'org.fomento.newpassword')}"/>
  </div>
  <div class="form-group">
    <g:passwordField class="form-control" name="confirmpassword" placeholder="${message(code:'org.fomento.confirmpassword')}"/>
  </div>
  <g:submitButton name="btnregistration" value="${message(code:'org.fomento.confirmchange')}"class="btn btn-default pull-right"/>
</g:form>