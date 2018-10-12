package com.bazar.sistema.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bazar.sistema.model.Produto;
import com.bazar.sistema.repository.ProdutoRepository;

@Controller
public class ProdutoResource {

	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {	
		model.addAttribute("produto", new Produto());
		return "/index";
	}
	
	@RequestMapping(value = "/produtos", method = RequestMethod.GET)	
	public String listar(Model model) { 
		List<Produto> produtos = produtoRepository.findAll();		
		model.addAttribute("produtos", produtos);
		return "/produtos";
	}
		
	@RequestMapping(value = "/cadastrar")
	public String novo() {
		return "/cadastrar";
	}
	
	@RequestMapping(value = "/salvar")	
	public ModelAndView salvar(@RequestBody @RequestParam(value = "nome", required=false) String nome, 
			 				   @RequestParam(value = "preco", required=false) Double preco) {
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(preco);
		produtoRepository.save(produto);
		
		return new ModelAndView("redirect:/produtos");
		
	}
	
	@RequestMapping(value = "/produtos/excluir/{codigo}")
	public ModelAndView excluir(@PathVariable("codigo") Long id) {
		produtoRepository.deleteById(id);
		
		return new ModelAndView("redirect:/produtos");
		
	}
	
	@RequestMapping(value = "/produtos/atualizar/{codigo}", method = RequestMethod.GET)
	public String atualizar(@PathVariable(value = "codigo") Long id, Model model) {
		Produto produto = produtoRepository.findById(id).get();
		model.addAttribute("produto", produto);
		return "/atualizar";
	}
	
	@RequestMapping(value = "/editar")
	public ModelAndView editar(@RequestParam(value = "codigo") Long codigo, 
								  @RequestParam(value = "nome", required=false) String nome, 
								  @RequestParam(value = "preco", required=false) Double preco) {
		
		Produto produto = produtoRepository.findById(codigo).get();
		produto.setNome(nome);
		produto.setPreco(preco);
		produtoRepository.save(produto);
		
		return new ModelAndView("redirect:/produtos");
		
	}
	
	
}
