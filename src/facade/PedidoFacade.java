package facade;

//carregar dados import
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


import repository.produto.ProdutoJaCadastradoException;
import repository.produto.ProdutoNaoCadastradoException;
import repository.produto.RepositorioProduto;
import repository.produto.RepositorioProdutoLista;

import repository.pessoa.RepositorioPessoa;
import repository.pessoa.RepositorioPessoaLista;
import repository.pessoa.CPFJaCadastradoException;
import repository.pessoa.PessoaNaoCadastradaException;

import repository.pedido.*;

import java.util.List;
import model.pedido.PedidoNaoEncontradoException;
import model.pessoa.Pessoa;
import model.produto.Produto;
import model.pedido.Pedido;

import java.util.Date;

public class PedidoFacade{
  // Arquivo para salvar os dados
	private final File FILE = new File("database.dat");

  
  RepositorioPessoa repositorioPessoa;
  RepositorioProduto repositorioProduto;
  RepositorioPedido repositorioPedido;

  private static PedidoFacade instance = null;
  
  private PedidoFacade() throws FacadeException{
    if (FILE.exists()){
      loadData();
    }else {
      repositorioPessoa = new RepositorioPessoaLista();
      repositorioProduto = new RepositorioProdutoLista();
      repositorioPedido = new RepositorioPedidoLista();
    }
  }

  public static PedidoFacade getInstance() throws FacadeException {

		if (instance == null) {
			instance = new PedidoFacade();
		}

		return instance;
	}
  
  public void inserirPessoa(Pessoa pessoa) throws CPFJaCadastradoException {
      repositorioPessoa.inserirPessoa(pessoa);
  }
    
  public void alterarPessoa(Pessoa pessoa) throws PessoaNaoCadastradaException {
      repositorioPessoa.alterarPessoa(pessoa);
  }
    
  public Pessoa buscarPessoa(String cpf) throws PessoaNaoCadastradaException {
      return repositorioPessoa.buscarPessoa(cpf);
  }
    
  public void excluirPessoa(Pessoa pessoa) throws PessoaNaoCadastradaException {
	    repositorioPessoa.deletarPessoa(pessoa);
  }
  
  public List<Pessoa> getAllPessoas(){
	  return repositorioPessoa.getAll();
  }
  
  public List<Produto> getAllProdutos(){
	  return repositorioProduto.getAll();
  }
  
  public void inserirProduto(Produto produto) throws ProdutoJaCadastradoException {
	  repositorioProduto.inserirProduto(produto);
  }
  
  public void alterarProduto(int id, int op, String novoDado){
	  repositorioProduto.alterarProduto(id, op, novoDado);
  }
  
  public Produto buscarProduto(int id) throws ProdutoNaoCadastradoException {
	  return repositorioProduto.buscarProduto(id);
  }
  
  public void excluirProduto(int id) throws ProdutoNaoCadastradoException {
	  repositorioProduto.deletarProduto(id);
  }

  public int getNewIDProduto(){
    return repositorioProduto.getNewID();
  }


  public int getNewIDPedido(){
    return repositorioPedido.getNewID();
  }

  public void adicionarPedido(Pedido pedido) {
      repositorioPedido.adicionarPedido(pedido);
  }

  public void removerPedido(Pedido pedido) {
      repositorioPedido.removerPedido(pedido);
  }

  public Pedido buscarPedido(int id) throws PedidoNaoEncontradoException{
      return repositorioPedido.buscarPedido(id);
  }

  public List<Pedido> getAll() {
      return repositorioPedido.getAll();
  }

  public List<Pedido> listarPedidosPorCliente(String cpf) {
      return repositorioPedido.getAllPedidosPorCliente(cpf);
  }

  public List<Pedido> listarPedidosPorEntregador(String cpf) {
      return repositorioPedido.getAllPedidosPorEntregador(cpf);
  }

  public double calcularFaturamentoDia(String data){
      return repositorioPedido.calcularFaturamentoDia(data);
  }

  public double calcularFaturamentoTotal(){
      return repositorioPedido.calcularFaturamentoTotal();
  }


  // salvar e carregar dados

  private void loadData() throws FacadeException {
		try {
			FileInputStream f = new FileInputStream(FILE);
      ObjectInputStream o = new ObjectInputStream(f);

			repositorioPessoa = (RepositorioPessoa) o.readObject();
			repositorioProduto = (RepositorioProduto) o.readObject();
      repositorioPedido = (RepositorioPedido) o.readObject();

			o.close();
			f.close();
		} catch (Exception e) {
      throw new FacadeException("Erro ao carregar dados do arquivo", e);
		}
	}

	public void saveData() throws FacadeException {
		try {
			FileOutputStream f = new FileOutputStream(FILE);
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Salvar meus dados
			o.writeObject(repositorioPessoa);
			o.writeObject(repositorioProduto);
      o.writeObject(repositorioPedido);

			o.close();
			f.close();
		} catch (IOException e) {
      throw new FacadeException("Erro ao salvar dados no arquivo", e);
		}
	}
}