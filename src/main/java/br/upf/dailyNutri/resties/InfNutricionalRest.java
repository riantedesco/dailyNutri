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

import br.upf.dailyNutri.entities.InfNutricionalEntity;
import br.upf.dailyNutri.jwt.TokenJwt;
import br.upf.dailyNutri.repositories.InfNutricionalRepository;

@RestController
@RequestMapping(value = "/dailyNutri/infnutricional")
public class InfNutricionalRest {

	@Autowired
	private InfNutricionalRepository repository;
	
	@GetMapping(value = "/findAll")
	public List<InfNutricionalEntity> findAll(@RequestHeader("token") String token) {
		TokenJwt.validarToken(token);
		List<InfNutricionalEntity> result = repository.findAll();
		return result;
	}
	
	@GetMapping(value = "/findById")
	public InfNutricionalEntity findById(@RequestHeader("token") String token, @RequestHeader(value = "id") Long id) {
		TokenJwt.validarToken(token);
		InfNutricionalEntity result = repository.findById(id).get();
		return result;
	}
	
	@GetMapping(value = "/findByAlimentoId")
	public List<InfNutricionalEntity> findByAlimentoId(@RequestHeader("token") String token, @RequestHeader(value = "alimentoId") Long alimentoId) {
		TokenJwt.validarToken(token);
		List<InfNutricionalEntity> entityList = repository.findByAlimentoId(alimentoId);
		return entityList;
	}
	
	@PostMapping(value = "/insert")
	public InfNutricionalEntity insert(@RequestHeader("token") String token, @RequestBody InfNutricionalEntity infNutricional) {
		TokenJwt.validarToken(token);
		InfNutricionalEntity result = repository.save(infNutricional);
		return result;
	}
	
	@PutMapping(value = "/update")
	public InfNutricionalEntity update(@RequestHeader("token") String token, @RequestBody InfNutricionalEntity infNutricional) {
		TokenJwt.validarToken(token);
		InfNutricionalEntity result = repository.save(infNutricional);
		return result;
	}
	
	@DeleteMapping(value = "/delete")
	public void delete(@RequestHeader("token") String token, @RequestHeader(value = "id") Long id) {
		TokenJwt.validarToken(token);
		repository.deleteById(id);
	}
}
