package repository.produto;

import model.produto.Produto;
import repository.produto.ProdutoJaCadastradoException;
import repository.produto.ProdutoNaoCadastradoException;

import java.util.List;

public interface RepositorioProduto {

  void inserirProduto(Produto produto) throws ProdutoJaCadastradoException;

  void alterarProduto(int id, int op, String novoDado);

  void deletarProduto(int id) throws ProdutoNaoCadastradoException;

  Produto buscarProduto(int id) throws ProdutoNaoCadastradoException;

  List<Produto> getAll();

  int getNewID();

}
