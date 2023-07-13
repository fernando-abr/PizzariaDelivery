package model.item_pedido;

public class ProdutoAusenteException extends RuntimeException {
  public ProdutoAusenteException(String message) {
    super(message);
    
  }
}
// lança exceção se o produto existe antes de adicionar a alista