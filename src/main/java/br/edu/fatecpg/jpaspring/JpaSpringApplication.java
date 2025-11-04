package br.edu.fatecpg.jpaspring;

import br.edu.fatecpg.jpaspring.model.Endereco;
import br.edu.fatecpg.jpaspring.repository.EnderecoRepository;
import br.edu.fatecpg.jpaspring.service.BuscaEndereco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class JpaSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JpaSpringApplication.class, args);
	}

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BuscaEndereco buscaEndereco;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando sistema!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        String cep = "11725000";

        ObjectMapper mapper = new ObjectMapper();

        Optional<Endereco> existente = enderecoRepository.findByCep(cep);

        if(existente.isPresent()){
            System.out.println("Esse cep ja existe no banco!!!!");
            System.out.println(existente.get());
        }else{
            String json = buscaEndereco.obterEndereco(cep);

            Endereco endereco = mapper.readValue(json, Endereco.class);

            enderecoRepository.save(endereco);

            System.out.println("Cep salvo!!!!");
            System.out.println(endereco);
        }
    }
}
