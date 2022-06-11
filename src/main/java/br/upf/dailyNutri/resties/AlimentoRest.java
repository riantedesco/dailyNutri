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

import br.upf.dailyNutri.entities.AlimentoEntity;
import br.upf.dailyNutri.jwt.TokenJwt;
import br.upf.dailyNutri.repositories.AlimentoRepository;

@RestController
@RequestMapping(value = "/dailyNutri/alimento")
public class AlimentoRest {

	@Autowired
	private AlimentoRepository repository;
	
	@GetMapping(value = "/findAll")
	public List<AlimentoEntity> findAll(@RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<AlimentoEntity> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/findById")
	public AlimentoEntity findById(@RequestHeader("token") String token, @RequestHeader(value = "id") Long id) {
		TokenJwt.validarToken(token);
		AlimentoEntity result = repository.findById(id).get();
		return result;
	}
	
	@GetMapping(value = "/findByNome")
	public List<AlimentoEntity> findByNome(@RequestHeader("token") String token, @RequestHeader(value = "nome") String nome) {
		TokenJwt.validarToken(token);
		List<AlimentoEntity> entityList = repository.findByNome(nome);
		return entityList;
	}
	
	@GetMapping(value = "/findByPartNome")
    public List<AlimentoEntity> findByPartTitulo(@RequestHeader("token") String token, @RequestHeader("partNome") String partNome) {
		TokenJwt.validarToken(token);
		List<AlimentoEntity> entityList = repository.findByPartNome(partNome);
		return entityList;
    }
	
	@PostMapping(value = "/insert")
	public AlimentoEntity insert(@RequestHeader("token") String token, @RequestBody AlimentoEntity alimento) {
		TokenJwt.validarToken(token);
		AlimentoEntity result = repository.save(alimento);
		return result;
	}
	
	@PutMapping(value = "/update")
	public AlimentoEntity update(@RequestHeader("token") String token, @RequestBody AlimentoEntity alimento) {
		TokenJwt.validarToken(token);
		AlimentoEntity result = repository.save(alimento);
		return result;
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestHeader("token") String token, @RequestHeader(value = "id") Long id) {
		TokenJwt.validarToken(token);
		repository.deleteById(id);
	}
}
