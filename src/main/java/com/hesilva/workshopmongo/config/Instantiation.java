package com.hesilva.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.hesilva.workshopmongo.domain.Post;
import com.hesilva.workshopmongo.domain.User;
import com.hesilva.workshopmongo.dto.AuthorDTO;
import com.hesilva.workshopmongo.dto.ComentDTO;
import com.hesilva.workshopmongo.repository.PostRepository;
import com.hesilva.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "viajarei para SP, abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Partiu Viagem", "viajarei para SP, abraços!", new AuthorDTO(maria));

		ComentDTO coment1 = new ComentDTO("Boa viagem Mano!", sdf.parse("21/03/2023"), new AuthorDTO(maria));
		ComentDTO coment2 = new ComentDTO("otimo dia!", sdf.parse("21/03/2023"), new AuthorDTO(bob));
		ComentDTO coment3 = new ComentDTO("Testes realizados na aplicação!", sdf.parse("21/03/2023"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(coment1, coment2));
		post2.getComments().addAll(Arrays.asList(coment3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
		
	}

}
