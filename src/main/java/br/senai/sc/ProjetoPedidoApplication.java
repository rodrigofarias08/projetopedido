package br.senai.sc;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.senai.sc.domain.Categoria;
import br.senai.sc.domain.Cidade;
import br.senai.sc.domain.Cliente;
import br.senai.sc.domain.Endereco;
import br.senai.sc.domain.Estado;
import br.senai.sc.domain.ItemPedido;
import br.senai.sc.domain.PagamentoComBoleto;
import br.senai.sc.domain.PagamentoComCartao;
import br.senai.sc.domain.Pedido;
import br.senai.sc.domain.Produto;
import br.senai.sc.domain.enums.EstadoPagamento;
import br.senai.sc.domain.enums.TipoCliente;
import br.senai.sc.repositories.CategoriaRepository;
import br.senai.sc.repositories.CidadeRepository;
import br.senai.sc.repositories.ClienteRepository;
import br.senai.sc.repositories.EnderecoRepository;
import br.senai.sc.repositories.EstadoRepository;
import br.senai.sc.repositories.ItemPedidoRepository;
import br.senai.sc.repositories.PagamentoRepository;
import br.senai.sc.repositories.PedidoRepository;
import br.senai.sc.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoPedidoApplication implements CommandLineRunner {


	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private EstadoRepository estadoRepo;
	@Autowired
	private CidadeRepository cidadeRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	@Autowired
	private PedidoRepository pedidoRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepo;
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoPedidoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().add(p1);
		cat1.getProdutos().add(p2);
		cat1.getProdutos().add(p3);
		cat2.getProdutos().add(p2);

		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p2.getCategorias().add(cat2);
		p3.getCategorias().add(cat1);
		

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().add(c1);
		est2.getCidades().add(c2);
		est2.getCidades().add(c3);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", c1, null);
		Endereco e2 = new Endereco(null, "avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, null);
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getEnderecos().add(e1);
		cli1.getEnderecos().add(e2);
		

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		cli1.getTelefones().add("27363323");
		cli1.getTelefones().add("93838393");

		e1.setCliente(cli1);
		e2.setCliente(cli1);

		ItemPedido it1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.0);
		ItemPedido it2 = new ItemPedido(ped1, p3, 0.0, 2, 80.0);
		ItemPedido it3 = new ItemPedido(ped2, p2, 1000.0, 1, 800.0);

		PagamentoComCartao pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
		PagamentoComBoleto pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf2.parse("20/10/2017"), null);
		ped2.setPagamento(pagto2);
		
		estadoRepo.save(est1);
		estadoRepo.save(est2);
		cidadeRepo.save(c1);
		cidadeRepo.save(c2);
		cidadeRepo.save(c3);
		categoriaRepo.save(cat1);
		categoriaRepo.save(cat2);
		produtoRepo.save(p1);
		produtoRepo.save(p2);
		produtoRepo.save(p3);
		clienteRepo.save(cli1);
		enderecoRepo.save(e1);
		enderecoRepo.save(e2);
		pedidoRepo.save(ped1);
		pedidoRepo.save(ped2);
		itemPedidoRepo.save(it1);
		itemPedidoRepo.save(it2);
		itemPedidoRepo.save(it3);
		pagamentoRepo.save(pagto1);
		pagamentoRepo.save(pagto2);
		
	}

}
