package br.upf.dailyNutri.resties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.upf.dailyNutri.entities.UserEntity;
import br.upf.dailyNutri.repositories.UserRepository;
import br.upf.dailyNutri.util.Utils;

@RestController
@RequestMapping(value = "/dailyNutri/user")
public class UserRest {

	@Autowired
	private UserRepository repository;
	
	@GetMapping(value = "/findAll")
	public List<UserEntity> findAll() {
		List<UserEntity> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/findById")
	public UserEntity findById(@RequestHeader(value = "id") Long id) {
		UserEntity result = repository.findById(id).get();
		return result;
	}
	
	@GetMapping(value = "/findByEmail")
	public UserEntity findByEmail(@RequestHeader(value = "email") String email) {
		UserEntity result = repository.findByEmail(email);
		return result;
	}
	
	@PostMapping(value = "/insert")
	public UserEntity insert(@RequestBody UserEntity user) {
		UserEntity result = repository.save(user);
		return result;
	}
	
	@PutMapping(value = "/update")
	public UserEntity update(@RequestBody UserEntity user) {
		UserEntity result = repository.save(user);
		return result;
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestHeader(value = "id") Long id) {
		repository.deleteById(id);
	}
	
	@GetMapping(value = "/authorize")
    public UserEntity authorize(@RequestHeader("email") String email, @RequestHeader("senha") String senha) {
		UserEntity entity;
        //verificando se usuário e senha foram enviados corretamente...
        if (email != null && !email.isEmpty() && email != null && !senha.isEmpty()) {
            entity = repository.findByEmail(email);
            //se encontrar o usuário no banco...
            if (entity.getId() != null) {
                //se a senha não estiver correta...
                if (entity.getSenha().equals(senha)) {
                    //adicionar o token no retorno da entidade...
                    entity.setToken(Utils.processarTokenJWT(email));
                    return entity;
                } else {
                    //se a senha não estiver correta...
                    return null;
                }
            } else {
                //se não encontrar o usuário no banco...
                return null;
            }
        } else {
            //se não enviou usuário e senha corretamente...
            return null;
        }
    }
}
