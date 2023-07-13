package repository.pessoa;

import repository.RepositorioException;

public class CPFJaCadastradoException extends RepositorioException{
	
	public CPFJaCadastradoException(String message){
		super(message);
    }
}