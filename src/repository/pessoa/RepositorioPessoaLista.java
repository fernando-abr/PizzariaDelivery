package repository.pessoa;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import model.pessoa.Pessoa;

public class RepositorioPessoaLista implements RepositorioPessoa, Serializable {

  private List<Pessoa> pessoas;

  public RepositorioPessoaLista() {
    pessoas = new ArrayList<Pessoa>();
  }

  @Override
  public void inserirPessoa(Pessoa pessoa) throws CPFJaCadastradoException {
    try {
      buscarPessoa(pessoa.getCpf());
      throw new CPFJaCadastradoException("CPF já cadastrado");
    } catch (PessoaNaoCadastradaException ex) {
      pessoas.add(pessoa);
    }
  }

  @Override
  public void alterarPessoa(Pessoa pessoa) throws PessoaNaoCadastradaException {
    buscarPessoa(pessoa.getCpf());
  }

  @Override
  public void deletarPessoa(Pessoa pessoa) throws PessoaNaoCadastradaException {
    if (!pessoas.remove(pessoa)) {
      throw new PessoaNaoCadastradaException("Pessoa não Cadastrada");
    }
  }

  
  public Pessoa buscarPessoa(String cpf) throws PessoaNaoCadastradaException {
    for (Pessoa p : pessoas) {
      if (p.getCpf().equals(cpf)) {
        return p;
      }
    }
    throw new PessoaNaoCadastradaException("Pessoa não Cadastrada");
  }

  public List<Pessoa> getAll() {
    return pessoas;
  }

}