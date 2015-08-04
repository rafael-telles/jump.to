<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout title="Registrar">
	<div class="row">
		<div class="col-xs-12">
			<spring:hasBindErrors name="user">
				<div class="alert alert-danger" role="alert">
					<span><strong>Ops...</strong> Você não preencheu os dados corretamente!</span>
		        </div>
			</spring:hasBindErrors>
			<div class="well">
				<form id="registerForm" method="POST" action="register">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="firstName" class="control-label">Primeiro nome</label> <input
									type="text" class="form-control" id="firstName"
									name="firstName" required title="Digite seu primeiro nome"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<label for="surName" class="control-label">Sobrenome</label> <input type="text" class="form-control" id="surName"
									name="surName" required> <span
									class="help-block"></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="control-label">E-mail</label> <input
							type="email" class="form-control" id="email" name="email"
							required placeholder="example@gmail.com"> <span class="help-block"></span>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="password" class="control-label">Senha</label> <input
									type="password" class="form-control" id="password"
									name="password" required> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="password" class="control-label">Confirme sua
									senha</label> <input type="password" class="form-control" id="password2"
									name="password2" required oninput="validate()"> <span
									class="help-block"></span>
							</div>
						</div>
					</div>

					<button type="submit" class="btn btn-success btn-block" onsubmit="validate()">Registrar</button>
          			<input type="hidden" name="action" value="RegisterUser" />
				</form>
			</div>
		</div>
	</div>
	
	<script>
		function validate() {
			if ($('#password')[0].value !== $('#password2')[0].value) {
				$('#password2')[0].setCustomValidity("Essas senhas não coincidem.");
				return false;
			}
			$('#password2')[0].setCustomValidity("");
			return true;
		}
	</script>
</t:narrow_layout>