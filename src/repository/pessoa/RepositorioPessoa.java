package repository.pessoa;

import java.util.List;

import model.pessoa.Pessoa;

public interface RepositorioPessoa  {
  void inserirPessoa(Pessoa pessoa) throws CPFJaCadastradoException;

    void alterarPessoa(Pessoa pessoa) throws PessoaNaoCadastradaException;

    void deletarPessoa(Pessoa pessoa) throws PessoaNaoCadastradaException;
  
    Pessoa buscarPessoa(String cpf) throws PessoaNaoCadastradaException;

	List<Pessoa> getAll();
}