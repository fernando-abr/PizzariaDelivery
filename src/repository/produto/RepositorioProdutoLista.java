
package repository.produto;

import model.produto.Produto;
import repository.produto.ProdutoNaoCadastradoException;
import repository.produto.ProdutoJaCadastradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

import java.io.Serializable;

public class RepositorioProdutoLista implements RepositorioProduto, Serializable {

  private List<Produto> produtos;
  private transient Iterator<Produto> produtoIterator;
  private String arq = "dadosProduto.json";
  

  public RepositorioProdutoLista() {
    this.produtos = new ArrayList<Produto>();
  }

  @Override
  public void inserirProduto(Produto produto) throws ProdutoJaCadastradoException {
    try {
      buscarProduto(produto.getId());
      throw new ProdutoJaCadastradoException();
    } catch (ProdutoNaoCadastradoException ex) {
      produtos.add(produto);
    }
  }

  @Override
  public void alterarProduto(int id, int op, String novoDado){
    this.produtoIterator();
    while (this.produtoIterator.hasNext()){
      Produto p = this.produtoIterator.next();
      
      //voce vai trocar o if (op == 1) por um switch
      switch (op) {
        case 1:
        // Alterar nome
          if (p.getId() == id) {
            p.setNome(novoDado);
          }
          break;
        case 2:
        // Alterar descrição
          if (p.getId() == id) {
            p.setDescricao(novoDado);
          }
          break;
        case 3:
        // Alterar preço
          if (p.getId() == id) {
              double novoPreco = Double.parseDouble(novoDado);
              p.setPreco(novoPreco);
            }
            break;
      //Aqui ta acabado, agora configurar outro arquivo
      //Application.java
      }
    }
  }

  @Override
  public void deletarProduto(int id) throws ProdutoNaoCadastradoException {
    // if (!produtos.remove(produto)) {
    //   throw new ProdutoNaoCadastradoException();
    // }
    boolean encontrou=false;
    this.produtoIterator();
    while (this.produtoIterator.hasNext()){
      Produto p = this.produtoIterator.next();
      if (p.getId() == id){
        this.produtoIterator.remove();
        encontrou = true;
      }
    }
    if(!encontrou){
      throw new ProdutoNaoCadastradoException();
    }
  }

  @Override
  public Produto buscarProduto(int id) throws ProdutoNaoCadastradoException {
    this.produtoIterator();
    while (this.produtoIterator.hasNext()){
      Produto p = this.produtoIterator.next();
      if (p.getId() == id) {
        return p;
      }
    }
    throw new ProdutoNaoCadastradoException();
  }

  @Override
  public List<Produto> getAll() {
    return new ArrayList<>(produtos);
  }

  @Override
  public int getNewID(){
    this.produtoIterator();
    
    int contar = 0;
    if (produtos.isEmpty()){
      return (contar + 1);
    }
    
    while (this.produtoIterator.hasNext()){
        Produto p = this.produtoIterator.next();
        contar = Math.max(contar, p.getId());
    }   
    return (contar + 1);
  }
  

  private void produtoIterator(){
        //Atualiza caso for preciso
        this.produtoIterator = produtos.iterator();
    }
}