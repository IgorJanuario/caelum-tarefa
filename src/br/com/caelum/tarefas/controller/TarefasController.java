package br.com.caelum.tarefas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.tarefas.dao.TarefaDao;
import br.com.caelum.tarefas.modelo.Tarefa;

@Transactional
@Controller
public class TarefasController {
	@Autowired
	private TarefaDao dao;
	
	@RequestMapping("/novaTarefa")
	public String form()
	{
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adiciona(@Valid Tarefa tarefa, BindingResult result)
	{
		if(result.hasFieldErrors("descricao"))
		{
			return "tarefa/formulario";
		}
		dao.adiciona(tarefa);
		
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("/listaTarefas")
	public String lista(Model model)
	{
		model.addAttribute("tarefas", dao.lista());
		
		return "tarefa/lista";
	}
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa)
	{
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("mostrarTarefa")
	public String mostrar(Long id, Model model)
	{
		model.addAttribute("tarefa",dao.buscaPorId(id));
		return "tarefa/mostra";
	}
	
	@RequestMapping("alterarTarefa")
	public String alterar(Tarefa tarefa)
	{
		dao.alterar(tarefa);
		return "redirect:listaTarefas";
	}
	
	@ResponseBody
	@RequestMapping("finalizaTarefa")
	public String finaliza(Long id)
	{
		dao.finaliza(id);
		
		Date dataDeFinalizacao = dao.buscaPorId(id).getDataFinalizacao().getTime();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataDeFinalizacao);
		
		return data;
	}
}
