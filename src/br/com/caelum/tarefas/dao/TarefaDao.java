package br.com.caelum.tarefas.dao;

import java.util.List;

import br.com.caelum.tarefas.modelo.Tarefa;

public interface TarefaDao {
	
	Tarefa buscaPorId(Long id);
	List<Tarefa> lista();
	void adiciona(Tarefa t);
	void alterar(Tarefa t);
	void remove(Tarefa t);
	void finaliza(Long id);
}
