<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="global" class="main.Global" />

<t:narrow_layout title="Registrar">
	<link
		href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
		rel="stylesheet">

	<div class="row">
		<div class="col-xs-12">
			<div class="well">
				<form id="registerForm" method="POST" action="controller">
					<div class="row">
						<div class="col-md-4">
							<div class="form-group">
								<label for="password" class="control-label">Primeiro nome</label> <input
									type="text" class="form-control" id="firstname"
									name="firstname" required title="Digite seu primeiro nome"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="col-md-8">
							<div class="form-group">
								<label for="password" class="control-label">Sobrenome</label> <input type="text" class="form-control" id="surname"
									name="surname" required> <span
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