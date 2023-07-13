package repository.produto;

import repository.RepositorioException;

public class ProdutoNaoCadastradoException extends RepositorioException {
    public ProdutoNaoCadastradoException() {
        super("Produto não cadastrado!");
    }   
}