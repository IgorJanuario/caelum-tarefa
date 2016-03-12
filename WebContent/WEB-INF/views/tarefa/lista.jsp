<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar</title>
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript">
	function finalizaAgora(id)
	{
		$.post("finalzaTarefa",{"id" : id}, function(resposta){
			
			$("#tarefa_"+ id).html("Finalizado");
		});
	}
</script>
</head>
<body>
	
	<a href="novaTarefa">Criar nova tarefa</a>
	<br/><br/>
	<table>
		<tr>
			<td>Id</td>
			<td>Descrição</td>
			<td>Finalização</td>
			<td>Data de Finalização</td>
			<td>Excluir</td>
			<td>Alterar</td>
		</tr>
		<core:forEach var="tarefa" items="${tarefas}">
			<tr>
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<core:if test="${tarefa.finalizado eq false }">
					<td id="tarefa_${tarefa.id}"><a href="#" onclick="finalizarAgora(${tarefa.id})">Finalizar agora !</a></td>
				</core:if>
				<core:if test="${tarefa.finalizado eq true }">
					<td>FInalizado</td>
				</core:if>
				<td> <fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/></td>
				<td><a href="removeTarefa?id=${tarefa.id}">Remover</a></td>
				<td><a href="mostrarTarefa?id=${tarefa.id}">Alterar</a></td>
			</tr>
		</core:forEach>
	</table>
</body>
</html>