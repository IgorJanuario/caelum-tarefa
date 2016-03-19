package br.com.caelum.tarefas.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Tarefa;
@Repository
public class JpaTarefaDao implements TarefaDao{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Tarefa buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return manager.find(Tarefa.class, id);
	}

	@Override
	public List<Tarefa> lista() {
		// TODO Auto-generated method stub
		return manager.createQuery("select t from Tarefa t").getResultList();
	}

	@Override
	public void adiciona(Tarefa tarefa) {
		// TODO Auto-generated method stub
		manager.persist(tarefa);
	}

	@Override
	public void alterar(Tarefa tarefa) {
		// TODO Auto-generated method stub
		manager.merge(tarefa);
	}

	@Override
	public void remove(Tarefa tarefa) {
		// TODO Auto-generated method stub
		Tarefa tarefaARemover = buscaPorId(tarefa.getId());
		manager.remove(tarefaARemover);
	}

	@Override
	public void finaliza(Long id) {
		// TODO Auto-generated method stub
		Tarefa tarefa = buscaPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
	}

}
