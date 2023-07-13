package repository.pessoa;

import repository.RepositorioException;

public class PessoaNaoCadastradaException extends RepositorioException{

	public PessoaNaoCadastradaException(String message){
		super(message);
	}
}