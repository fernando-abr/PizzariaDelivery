package repository.produto;

import repository.RepositorioException;

public class ProdutoJaCadastradoException extends RepositorioException {

    public ProdutoJaCadastradoException() {
        super("Produto já cadastrado");
    }
}