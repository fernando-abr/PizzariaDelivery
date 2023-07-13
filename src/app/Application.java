// Alunos: Adriane Cunha, Erik Rebelo, Fernando Abreu, Iane Ferreira, Kelson Wendrel
// Pizzaria Delivery

package app;

import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ListIterator;
import java.util.Iterator;

import java.lang.InterruptedException;

import facade.*;
import model.pessoa.*;
import model.produto.*;
import model.pedido.*;
import model.item_pedido.*;
import model.pedido.PedidoNaoEncontradoException;

import repository.RepositorioException;
import repository.pessoa.CPFJaCadastradoException;
import repository.produto.ProdutoJaCadastradoException;
import repository.produto.ProdutoNaoCadastradoException;

import java.util.Date;

public class Application {
  static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  private static PedidoFacade facade;
  static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws Exception{
    try{
      System.out.println("Carregando sistema...");
      facade = PedidoFacade.getInstance();
      //dadosTeste();
      int opcao;
      do {
        limpaTela();
        System.out.println("MENU PRINCIPAL");
        System.out.println("==== =========");
        System.out.println();
        System.out.println("<1> CADASTRO CLIENTE");
        System.out.println("<2> CADASTRO ENTREGADOR ");
        System.out.println("<3> CADASTRO DE PIZZAS E OUTROS PRODUTOS");
        System.out.println("<4> PEDIDOS");
        System.out.println("<5> FATURAMENTO");
        System.out.println("<0> Sair");
        System.out.println();
        System.out.print("Escolha uma opção: ");
  
        try {
          opcao = Integer.valueOf(sc.nextLine());
        } catch (Exception e) {
          opcao = 0;
        }
  
        switch (opcao) {
          case 0:
            limpaTela();
            break;
          case 1:
            cadastroClientes();
            break;
          case 2:
            cadastroEntregador();
            break;
          case 3:
            cadastroProduto();
            break;
          case 4:
            opcoesPedido();
            break;
          case 5:
            faturamento();
            break;
        }
      } while (opcao != 0);
      
      facade.saveData();
    }catch (FacadeException e){
      e.printStackTrace();
    }
    
    System.out.println("Programa terminado");
  }

  private static void limpaTela() {
    clearTerminal();
  }

  private static void cadastroClientes() throws CPFJaCadastradoException {
    int opcao;
    do {
      limpaTela();
      System.out.println("CADASTRO CLIENTES");
      System.out.println("======== ========");
      System.out.println();
      System.out.println("<1> Incluir Cliente");
      System.out.println("<2> Alterar Cliente");
      System.out.println("<3> Excluir Cliente");
      System.out.println("<4> Listar Clientes");
      System.out.println("<0> Menu Principal");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(sc.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          incluirPessoa(0);
          break;
        case 2:
          alterarPessoa(0);
          break;
        case 3:
          excluirPessoa(0);
          break;
        case 4:
          listarPessoa(0);
          break;
      }
    } while (opcao != 0);
  }

  private static void cadastroEntregador() throws CPFJaCadastradoException {
    int opcao;
    do {
      limpaTela();
      System.out.println("CADASTRO ENTREGADOR");
      System.out.println("======== ========");
      System.out.println();
      System.out.println("<1> Incluir Entregador");
      System.out.println("<2> Alterar Entregador");
      System.out.println("<3> Excluir Entregador");
      System.out.println("<4> Listar Entregadores");
      System.out.println("<0> Menu");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(sc.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          incluirPessoa(1);
          break;
        case 2:
          alterarPessoa(1);
          break;
        case 3:
          excluirPessoa(1);
          break;
        case 4:
          listarPessoa(1);
          break;
      }
    } while (opcao != 0);
  }

  private static void cadastroProduto() throws RepositorioException {
    int opcao;
    do {
      limpaTela();
      System.out.println("CADASTRO PRODUTOS");
      System.out.println("======== ========");
      System.out.println();
      System.out.println("<1> Incluir Produto");
      System.out.println("<2> Alterar Produto");
      System.out.println("<3> Excluir Produto");
      System.out.println("<4> Listar  Produto");
      System.out.println("<0> Menu Principal");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(sc.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          incluirProduto();
          break;
        case 2:
          alterarProduto();
          break;
        case 3:
          excluirProduto();
          break;
        case 4:
          listarProdutos();
          break;
      }
    } while (opcao != 0);
  }

  public static void incluirPessoa(int op) throws CPFJaCadastradoException {
    limpaTela();
    Pessoa pessoa;
    try {
      if (op == 0) {
        System.out.println("Cadastro de Cliente");
        System.out.println("===================");
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String fone = sc.nextLine();
        pessoa = new Cliente(nome, cpf, fone);
      } else {
        System.out.println("Cadastro de Entregador");
        System.out.println("===================");
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Telefone: ");
        String fone = sc.nextLine();
        pessoa = new Entregador(nome, cpf, fone);
      }
      facade.inserirPessoa(pessoa);
      animacaoCarregamento("cadastro");
      // SalvarJson.salvarObjeto(pessoa, clienteDados());

    } catch (CPFJaCadastradoException ex) {
      System.err.println(ex.getMessage());

    }

    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();

  }

  private static void alterarPessoa(int op) {
    limpaTela();
    if (op == 0) {
      System.out.println("Alterar Cliente");
      System.out.println("==================");
      System.out.print("CPF: ");
      String cpf = sc.nextLine();

      try {
        Pessoa cliente = facade.buscarPessoa(cpf);
        System.out.println();
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println();

        System.out.print("Informe o dado que deseja alterar(c/n/t): ");
        String resposta = sc.nextLine();
        limpaTela();
        if (resposta.equalsIgnoreCase("c")) {
          System.out.print(" Alterar CPF: ");
          String modCpf = sc.nextLine();
          cliente.setCpf(modCpf);
          facade.alterarPessoa(cliente);
          System.out.println();
          System.out.println("Dado alterado com sucesso!");

        }
        if (resposta.equalsIgnoreCase("n")) {
          System.out.print("Alterar nome: ");
          String modNome = sc.nextLine();
          cliente.setNome(modNome);
          facade.alterarPessoa(cliente);
          System.out.println();
          System.out.println("Dado alterado com sucesso!");

        }
        if (resposta.equalsIgnoreCase("t")) {
          System.out.print("Alterar Telefone: ");
          String modTelefone = sc.nextLine();
          cliente.setTelefone(modTelefone);
          facade.alterarPessoa(cliente);
          System.out.println();
          System.out.println("Dado alterado com sucesso!");
        }

      } catch (RepositorioException ex) {
        System.err.println(ex.getMessage());
      }

    } else {

      System.out.println("Alterar Entregador");
      System.out.println("==================");
      System.out.print("CPF: ");
      String cpf = sc.nextLine();

      try {
        Pessoa entregador = facade.buscarPessoa(cpf);
        System.out.println();
        System.out.println("Nome: " + entregador.getNome());
        System.out.println("Telefone: " + entregador.getTelefone());
        System.out.println();

        System.out.print("Informe o dado que deseja alterar(c/n/t)?");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("c")) {
          System.out.println("CPF: ");
          String modCpf = sc.nextLine();
          entregador.setCpf(modCpf);
          facade.alterarPessoa(entregador);
          System.out.println();
          System.out.println("Dado alterado com sucesso!");

        }
        if (resposta.equalsIgnoreCase("n")) {
          System.out.println("Nome: ");
          String modNome = sc.nextLine();
          entregador.setNome(modNome);
          facade.alterarPessoa(entregador);
          System.out.println();
          System.out.println("Dado alterado com sucesso!");
        }
        if (resposta.equalsIgnoreCase("t")) {
          System.out.println("Telefone: ");
          String modTelefone = sc.nextLine();
          entregador.setTelefone(modTelefone);
          facade.alterarPessoa(entregador);
          System.out.println();
          System.out.println("Dado alterado com sucesso!");
        }

      } catch (RepositorioException ex) {
        System.err.println(ex.getMessage());
      }

    }
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void excluirPessoa(int op) {
    limpaTela();

    if (op == 0) {
      System.out.println("Excluir Cliente");
      System.out.println("==================");
      System.out.print("CPF: ");
      String cpf = sc.nextLine();

      try {
        Pessoa cliente = facade.buscarPessoa(cpf);
        System.out.println();
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getTelefone());
        System.out.println();

        System.out.print("Exclui esse cliente? (s/n)?");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
          facade.excluirPessoa(cliente);
          animacaoCarregamento("exclusão");
          System.out.println("Cliente excluído!");
        }

      } catch (RepositorioException ex) {
        System.err.println(ex.getMessage());
      }
    } else {
      System.out.println("Excluir Cliente");
      System.out.println("==================");
      System.out.print("CPF: ");
      String cpf = sc.nextLine();
      try {
        Pessoa entregador = facade.buscarPessoa(cpf);
        System.out.println();
        System.out.println("Nome: " + entregador.getNome());
        System.out.println("Telefone: " + entregador.getTelefone());
        System.out.println();

        System.out.print("Exclui esse cliente? (s/n)?");
        String resposta = sc.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
          facade.excluirPessoa(entregador);
          animacaoCarregamento("exclusão");
          System.out.println("Entregador excluído!");
        }

      } catch (RepositorioException ex) {
        System.err.println(ex.getMessage());
      }
    }
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void listarPessoa(int op) {
    limpaTela();
    if (op == 0) {
      List<Pessoa> clientes = facade.getAllPessoas();
      System.out.printf("CPF          Nome                  Telefone\n");
      System.out.printf("============== =================== ===============\n");
      for (Pessoa cliente : clientes) {
        if (cliente instanceof Cliente) {
          System.out.printf("%14s ", cliente.getCpf());
          System.out.printf("%-20s ", cliente.getNome());
          System.out.printf("%15s\n", cliente.getTelefone());
        }

      }
    } else {
      List<Pessoa> entregadores = facade.getAllPessoas();
      System.out.printf("CPF          Nome                  Telefone\n");
      System.out.printf("============== =================== ===============\n");
      for (Pessoa entregador : entregadores) {
        if (entregador instanceof Entregador) {
          System.out.printf("%14s ", entregador.getCpf());
          System.out.printf("%-20s ", entregador.getNome());
          System.out.printf("%15s\n", entregador.getTelefone());
        }
      }
    }
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  // Area de produtos
  private static void incluirProduto() throws ProdutoJaCadastradoException {
    limpaTela();
    try {
      System.out.println("Cadastro de Produto");
      System.out.println("===================");
      System.out.print("Nome do Produto: ");
      String nome = sc.nextLine();
      System.out.print("Descrição: ");
      String descricao = sc.nextLine();
      System.out.print("Preço: ");
      double preco;
      try {
        preco = Double.parseDouble(sc.nextLine());
      } catch (Exception e) {
        throw new RepositorioException("Digite um numero válido!");
      }
      
      int id = facade.getNewIDProduto();
      Produto produto = new Produto(id, nome, preco, descricao);
      facade.inserirProduto(produto);
      animacaoCarregamento("Cadastro");
    } catch (RepositorioException ex) {
      System.err.println(ex.getMessage());
    }
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void alterarProduto() throws ProdutoNaoCadastradoException {
    limpaTela();
    System.out.println("Alterar Produto");
    System.out.println("===============");
    System.out.print("ID: ");
    int id;

    Produto produtoAlterar;
    try {
      
      try {
        id = Integer.parseInt(sc.nextLine());
      } catch (Exception e) {
        throw new RepositorioException("Digite um id válido!");
      }
      
      animacaoCarregamento("Busca");
      
      produtoAlterar = facade.buscarProduto(id);

      int opcao;
      do {
        System.out.println();
        System.out.println("Produto: " + produtoAlterar.getNome());
        System.out.println("Descrição: " + produtoAlterar.getDescricao());
        System.out.println("Preço: " + produtoAlterar.getPreco());
        System.out.println();
        System.out.println("O que deseja alterar?");
        System.out.println("=====================");
        System.out.println("<1> Alterar Nome");
        System.out.println("<2> Alterar Descriçao");
        System.out.println("<3> Alterar Preço");
        System.out.println("<0> voltar");
        System.out.println();
        System.out.print("Escolha uma opção: ");

        try {
          opcao = Integer.valueOf(sc.nextLine());
        } catch (Exception e) {
          opcao = 0;
        }

        String resposta;

        switch (opcao) {
          case 0:
            limpaTela();
            break;
          case 1:// caso para alterar o nome do produto
            System.out.println();
            System.out.print("Novo nome: ");
            String novoNome = sc.nextLine();
            System.out.println();
            System.out.print("Concluir Alteração? (s/n)? ");
            resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
              facade.alterarProduto(produtoAlterar.getId(), 1, novoNome);
              opcao = 0;
            }
            break;
          case 2:
            System.out.println();
            System.out.print("Nova Descrição: ");
            String novaDescricao = sc.nextLine();
            System.out.println();
            System.out.print("Concluir Alteração? (s/n)? ");
            resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
              facade.alterarProduto(produtoAlterar.getId(), 2, novaDescricao);
              opcao = 0;
            }
            break;
          case 3:
            System.out.println();
            System.out.print("Novo preço: ");
            String novoPreco = sc.nextLine();
            System.out.println();
            System.out.print("Concluir Alteração? (s/n)? ");
            resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
              facade.alterarProduto(produtoAlterar.getId(), 3, novoPreco);
              opcao = 0;
            }
            break;
        }

        limpaTela();
      } while (opcao != 0);

      animacaoCarregamento("Alteração");
      System.out.println("\nProdtuto Alterado!");

    } catch (RepositorioException ex) {
      System.err.println(ex.getMessage());
    }
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void mostrarProdutos() {
    List<Produto> produtoLista = facade.getAllProdutos();
    Iterator<Produto> produtoIterator = produtoLista.iterator();
    System.out.printf("ID       Nome                 Descrição                      Preço\n");
    System.out.printf("======== ==================== ============================== ==========\n");
    if (produtoIterator.hasNext()) {
      while (produtoIterator.hasNext()) {
        Produto p = produtoIterator.next();
        System.out.printf("%8s ", p.getId());
        System.out.printf("%-20s ", p.getNome());
        System.out.printf("%-30s ", p.getDescricao());
        System.out.printf("%-10s\n", p.getPreco());
      }
    } else {
      for (int i = 0; i < 71; i++)
        System.out.printf("-");
      System.out.printf("\nNenhum produto cadastrado!\n");
    }
    System.out.println();
  }

  private static void listarProdutos() {
    limpaTela();
    mostrarProdutos();
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void excluirProduto() {
    limpaTela();
    System.out.println("Excluir Produto");
    System.out.println("==================");
    System.out.print("ID: ");
    int id;

    try {
      try {
        id = Integer.parseInt(sc.nextLine());
      } catch (Exception e) {
        throw new RepositorioException("Digite um id válido!");
      }
      
      Produto produto = facade.buscarProduto(id);
      System.out.println();
      System.out.println("Id: " + produto.getId());
      System.out.println("Nome: " + produto.getNome());
      System.out.println("Descrição: " + produto.getDescricao());
      System.out.println("Preço: " + produto.getPreco());
      System.out.println();

      System.out.print("Excluir esse produto? (s/n)?");
      String resposta = sc.nextLine();

      if (resposta.equalsIgnoreCase("s")) {
        facade.excluirProduto(id);
        animacaoCarregamento("exclusão do produto");
        System.out.println("Produto excluído!");
      }

    } catch (RepositorioException ex) {
      System.err.println(ex.getMessage());
    }
    System.out.println();
    System.out.println("tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void opcoesPedido() throws RepositorioException {
    int opcao;
    do {
      limpaTela();
      System.out.println("PEDIDO");
      System.out.println("======== ========");
      System.out.println();
      System.out.println("<1> Novo Pedido");
      System.out.println("<2> Listar Pedidos");
      System.out.println("<3> Confirmar Entrega");
      //System.out.println("<4> Deletar Pedido");
      System.out.println("<0> Menu");
      System.out.println();
      System.out.print("Escolha uma opção: ");

      try {
        opcao = Integer.valueOf(sc.nextLine());
      } catch (Exception e) {
        opcao = 0;
      }

      switch (opcao) {
        case 0:
          limpaTela();
          break;
        case 1:
          novoPedido();
          break;
        case 2:
          listarPedidos();
          break;
        case 3:
          confirmarEntrega();
          break;
        case 4:
          // confirmar entrega
          break;
        case 5:
          // confirmar entrega
          break;
      }
    } while (opcao != 0);
  }

  private static void novoPedido() throws ProdutoAusenteException {
    limpaTela();
    System.out.println("Novo Pedido");
    System.out.println("============");
    System.out.print("CPF do Cliente: ");
    String cpfCliente = sc.nextLine();
    System.out.print("CPF do Entregador: ");
    String cpfEntregador = sc.nextLine();

    try {
       Cliente cliente;
      try{
        cliente = (Cliente) facade.buscarPessoa(cpfCliente);
      } catch(Exception e){
        throw new RepositorioException("\nCPF inválido: o número de CPF fornecido pertence a um perfil de usuário incorreto. "
						+ "\nCertifique-se de inserir o CPF correto para o tipo de usuário adequado.");
      }
      
      Entregador entregador;
      
      try{
        entregador = (Entregador) facade.buscarPessoa(cpfEntregador);
      } catch(Exception e){
        throw new RepositorioException("\nCPF inválido: o número de CPF fornecido pertence a um perfil de usuário incorreto. "
						+ "\nCertifique-se de inserir o CPF correto para o tipo de usuário adequado.");
      }
      System.out.print("Endereço de entrega: ");
      String endereco = sc.nextLine();

      Pedido pedido = new Pedido(cliente, entregador, endereco, facade.getNewIDPedido());
      String opcao;
      do {
        limpaTela();
        mostrarProdutos();
        System.out.println();
        System.out.print("ID do Produto (-1 para sair): ");
        int idProduto;
        try {
          idProduto = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
          throw new RepositorioException("Digite um id válido!");
        }
        
        if (idProduto == -1) {
          break;
        }

        System.out.print("Quantidade: ");
        int quantidade;
        
        try {
          quantidade = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
          throw new RepositorioException("Digite uma quantidade válida!");
        }
        
        ItemPedido item = null;
        System.out.print("Observacao: ");
        String observacao = sc.nextLine();
        
        Produto produto = facade.buscarProduto(idProduto);

        try {
          item = new ItemPedido(produto, quantidade, observacao);
        } catch (QuantidadeInvalidaException e) {
          System.out.println(e.getMessage());
        }

        pedido.adicionarItem(item);

        System.out.print("Deseja adicionar mais produtos? (s/n): ");
        opcao = sc.nextLine();
      } while (opcao.equalsIgnoreCase("s"));
      facade.adicionarPedido(pedido);
      animacaoCarregamento("Pedido ");
      System.out.println(pedido.toString());
    } catch (RepositorioException ex) {
      System.err.println(ex.getMessage());
    }

    System.out.println();
    System.out.println("Tecle <enter> para voltar");
    sc.nextLine();
  }

  private static void listarPedidos() {
    limpaTela();

    List<Pedido> pedidos = facade.getAll();
    System.out.printf("ID    Data e Hora          Cliente               Entregador        Status\n");
    System.out.printf("===== ==================== ===================== ================= ==================\n");
    for (Pedido p : pedidos) {
      System.out.printf("%5s ", p.getID());
      System.out.printf("%20s ", sdf.format(p.getMomentoPedido()));
      System.out.printf("%20s ", p.getCliente().getNome());
      System.out.printf("%18s ", p.getEntregador().getNome());
      System.out.printf("%18s ", p.getStatus());
      System.out.println();
    }
    System.out.println("Pressione <Enter> para continuar.");
    sc.nextLine();
  }

  private static void confirmarEntrega() throws RepositorioException {
    limpaTela();
    System.out.println("Confirmar Entrega");
    System.out.println("=================");

    System.out.print("Digite o ID do pedido que deseja confirmar a entrega: ");
    int id;
    
    try {
      try {
        id = Integer.parseInt(sc.nextLine());
      } catch (Exception e) {
        throw new PedidoNaoEncontradoException("Digite um id válido!");
      }
      
      Pedido pedido = facade.buscarPedido(id);

      if (pedido == null) {
        System.out.println("Pedido não encontrado.");
        System.out.println("Pressione <Enter> para continuar.");
        sc.nextLine();
        return;
      }

      System.out.println("Detalhes do Pedido:");
      System.out.println(pedido.toString());

      System.out.print("Tem certeza de que deseja confirmar a entrega deste pedido? (s/n): ");
      String opcao = sc.nextLine();
      if (opcao.equalsIgnoreCase("s")) {
        pedido.setStatus(StatusPedido.ENTREGUE);
        animacaoCarregamento("confirmação do pedido");
        System.out.println("Entrega confirmada com sucesso.");
      } else {
        System.out.println("Operação cancelada.");
      }
    } catch (PedidoNaoEncontradoException e) {
      System.out.println(e.getMessage());
    }

    System.out.println("Pressione <Enter> para continuar.");
    sc.nextLine();
  }

  private static boolean dataValida(String value) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false); // Configurando para não ser leniente

    try {
      sdf.parse(value);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  private static void faturamento() {
    limpaTela();
    System.out.println("FATURAMENTO TOTAL");
    System.out.println("=================");
    double valorTotal = facade.calcularFaturamentoTotal();
    if (valorTotal > 0)
        System.out.println("Faturamento Total: R$ " + String.format("%.2f", valorTotal));
    else
      System.out.println("Sem Faturamento!");

    System.out.println();
    System.out.println();
    System.out.println("FATURAMENTO DO DIA");
    System.out.println("==================");
    System.out.print("Digite uma data: ");
    String dataProcurar = sc.nextLine();

    boolean valida = dataValida(dataProcurar);
    if (valida) {
      
      double valorDia = facade.calcularFaturamentoDia(dataProcurar);
      if (valorDia > 0)
        System.out.println("Faturamento do dia " + dataProcurar + ": R$ " + String.format("%.2f", valorDia));
      if (valorDia <= 0)
        System.out.println(dataProcurar + ": Sem Faturamento nesse dia!");
    } else {
      System.out.println("Data inválida!");
    }
    System.out.println();
    System.out.println("Pressione <Enter> para continuar.");
    sc.nextLine();
  }

  private static void clearTerminal() {
    String sistemaOperacional = System.getProperty("os.name");
    boolean isWindows = sistemaOperacional.toLowerCase().contains("windows");
    if (isWindows) {
      try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      // Limpa a tela para o mac/linux e m outro ai
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }

  private static void animacaoCarregamento(String texto) {
    String[] animacao = { "\\", "|", "/", "-" };
    System.out.print("Realizando " + texto);
    for (int i = 0; i < 4; i++) {
      for (String frame : animacao) {
        System.out.print(frame);
        try {
          Thread.sleep(75);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.print("\b");
      }
    }
    limpaTela();
    System.out.println(texto + " realizado(a) com Sucesso!");
  }

  private static void dadosTeste() {
    Cliente c = new Cliente("João", "111.111.111-11", "999999999");
    Entregador e = new Entregador("Maria", "111.111.111-12", "999999998");
    Produto p = new Produto(123, "Pizza Mussarela", 40.00, "pizza");
    Produto p1 = new Produto(124, "Pizza de Calabrsa", 40.00, "pizza");
    Produto p2 = new Produto(125, "Pizza Portuguesa", 40.00, "pizza");
    Produto p3 = new Produto(126, "Pizza de Frango com Catupiry", 40.00, "pizza");
    Produto p4 = new Produto(6, "Coca", 12.00, "1L");

    try {
      facade.inserirPessoa(c);
      facade.inserirPessoa(e);
    } catch (CPFJaCadastradoException ex) {
      System.out.println(ex.getMessage());
    }

    try {
      facade.inserirProduto(p);
      facade.inserirProduto(p1);
      facade.inserirProduto(p2);
      facade.inserirProduto(p3);
      facade.inserirProduto(p4);
    } catch (ProdutoJaCadastradoException ex) {
      System.out.println(ex.getMessage());
    }
  }
}