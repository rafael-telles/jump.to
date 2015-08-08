<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="global" class="to.jump.Global" />

<t:narrow_layout title="Login">
	<div class="row">
		<spring:hasBindErrors name="user">
			<div class="alert alert-danger" role="alert">
				<span><strong>Ops...</strong> Você não preencheu os dados corretamente!</span>
	        </div>
		</spring:hasBindErrors>
		<div class="col-lg-6">
			<div class="well">
				<form id="loginForm" method="POST" action="login">
					<div class="form-group">
						<label for="email" class="control-label">E-mail</label> <input
							type="email" class="form-control" id="email" name="email"
							required title="Digite seu e-mail"
							placeholder="example@gmail.com"> <span class="help-block"></span>
					</div>
					<div class="form-group">
						<label for="password" class="control-label">Senha</label> <input
							type="password" class="form-control" id="password"
							name="password" required
							title="Digite sua senha"> <span
							class="help-block"></span>
					</div>
					
					<button type="submit" class="btn btn-success btn-block">Login</button>
          			<input type="hidden" name="action" value="Login" />
				</form>
			</div>
		</div>
		<div class="col-lg-6">
			<p class="lead">
				Registre-se agora <span class="text-success">DE GRAÇA</span>
			</p>
			<ul class="list-unstyled" style="line-height: 2">
				<li><span class="fa fa-check text-success"></span> Gerencie suas URLs</li>
				<li><span class="fa fa-check text-success"></span> Veja suas estatísticas</li>
			</ul>
			<p>
				<a href="/register" class="btn btn-info btn-block">Ok, quero me registrar!</a>
			</p>
		</div>
	</div>
</t:narrow_layout>