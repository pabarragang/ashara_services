/**
 * 
 */
package ashara.science.com.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ashara.science.com.exception.ResourceNotFoundException;
import ashara.science.com.model.Arbol;
import ashara.science.com.model.Centro;
import ashara.science.com.repository.ArbolRepository;
import ashara.science.com.repository.CentroRepository;

/**
 * @author josdavidmo
 *
 */
@RestController
@RequestMapping("/api")
public class ArbolController {

	@Autowired
	ArbolRepository arbolRepository;

	@Autowired
	CentroRepository centroRepository;

	@GetMapping("/arboles")
	public List<Arbol> getAllArboles() {
		return arbolRepository.findAll();
	}
	
	@GetMapping("/arboles/count")
	public Long countAllCentros() {
		return arbolRepository.count();
	}

	@GetMapping("/arboles/sector/{id}")
	public List<Arbol> getArbolesBySector(@PathVariable(value = "id") Long k_centro) {
		Centro centro = centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Centro", "id", k_centro));
		return arbolRepository.findAllByCentro(centro.getK_centro());
	}
	
	@GetMapping("/arboles/sector/{id}/count")
	public Long countArbolesBySector(@PathVariable(value = "id") Long k_centro) {
		Centro centro = centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Centro", "id", k_centro));
		return arbolRepository.countByCentro(centro.getK_centro());
	}

	@PostMapping("/arboles")
	public Arbol createArbol(@Valid @RequestBody Arbol arbol) {
		return arbolRepository.save(arbol);
	}

	@GetMapping("/arboles/{id}")
	public Arbol getArbolById(@PathVariable(value = "id") Long k_centro) {
		return arbolRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Arbol", "id", k_centro));
	}

	@PutMapping("/arboles/{id}")
	public Arbol updateAuto(@PathVariable(value = "id") Long k_centro, @Valid @RequestBody Arbol arbolDetails) {

		Arbol arbol = arbolRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Arbol", "id", k_centro));

		arbol.setK_centro(arbolDetails.getK_centro());
		arbol.setN_pos_x(arbolDetails.getN_pos_x());
		arbol.setN_pos_y(arbolDetails.getN_pos_y());
		arbol.setN_pos_z(arbolDetails.getN_pos_z());
		arbol.setCentro(arbolDetails.getCentro());

		Arbol updatedArbol = arbolRepository.save(arbol);
		return updatedArbol;
	}

	@DeleteMapping("/arboles/{id}")
	public ResponseEntity<?> deleteArbol(@PathVariable(value = "id") Long k_centro) {
		Arbol arbol = arbolRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", k_centro));

		arbolRepository.delete(arbol);

		return ResponseEntity.ok().build();
	}

}
