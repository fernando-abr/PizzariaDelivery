package model.pedido;

import java.io.Serializable;

public enum StatusPedido implements Serializable {
  AGUARDANDO_CONFIRMACAO,
  FEITO,
  AGUARDANDO_ENTREGA,
  ENTREGUE;
}
