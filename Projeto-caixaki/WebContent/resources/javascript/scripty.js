function invalidarSession(context, pagina){
	
	document.location = (context + pagina + ".jsf");
}

function validarSenhaLogin(){
	
	j_username = document.getElementById("j_username").value;
	j_password = document.getElementById("j_password").value;
	
	if(j_username === null || j_username.trim() === ''){
		alert("informe o login");
		$('#input-login').focus();
		return false;
	}
	
	if(j_senha === null || j_senha.trim() === ''){
		alert("informe a senha");
		$('#input-senha').focus();
		return false;
	}
	
	return true;
}