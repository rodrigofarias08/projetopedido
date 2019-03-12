package br.senai.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.senai.sc.domain.Categoria;
import br.senai.sc.domain.Produto;
import br.senai.sc.repositories.CategoriaRepository;
import br.senai.sc.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoPedidoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPedidoApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private ProdutoRepository prod;

	@Override
	public void run(String... args) throws Exception {
		Categoria i = new Categoria(null, "Informática");
		Categoria e = new Categoria(null, "Escritório");
		repo.save(i);
		repo.save(e);
		
		Produto c = new Produto(null, "cadeira", 100.2);
		Produto m = new Produto(null, "mesa", 200.3);
		prod.save(c);
		prod.save(m);
	}

}
