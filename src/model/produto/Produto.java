
package model.produto;

import java.util.ListIterator;

import java.io.Serializable; 

public class Produto implements Serializable{
  public int id;
  public String nome;
  public double preco;
  public String descricao;

  
  public Produto (int id, String nome, double preco, String descricao){
    this.id = id;
    this.nome = nome;
    this.preco = preco;
    this.descricao = descricao;
  }
  
  public String getNome() {
        return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public double getPreco() {
        return preco;
  }
  
  public void setPreco(double preco) {
    this.preco = preco;
  }
  
  public String getDescricao() {
        return descricao;
  }
  
  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }
}