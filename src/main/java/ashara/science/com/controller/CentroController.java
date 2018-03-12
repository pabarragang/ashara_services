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
import ashara.science.com.model.Centro;
import ashara.science.com.repository.CentroRepository;

/**
 * @author josdavidmo
 *
 */
@RestController
@RequestMapping("/api")
public class CentroController {

	@Autowired
	CentroRepository centroRepository;

	@GetMapping("/centros")
	public List<Centro> getAllCentros() {
		return centroRepository.findAll();
	}
	
	@GetMapping("/centros/count")
	public Long countAllCentros() {
		return centroRepository.count();
	}

	@PostMapping("/centros")
	public Centro createCentro(@Valid @RequestBody Centro centro) {
		return centroRepository.save(centro);
	}

	@GetMapping("/centros/{id}")
	public Centro getCentroById(@PathVariable(value = "id") Long k_centro) {
		return centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Arbol", "id", k_centro));
	}

	@PutMapping("/centros/{id}")
	public Centro updateCentro(@PathVariable(value = "id") Long k_centro, @Valid @RequestBody Centro centroDetails) {

		Centro centro = centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Centro", "id", k_centro));
		
		
		centro.setK_centro(centroDetails.getK_centro());
		centro.setN_pos_x(centroDetails.getN_pos_x());
		centro.setN_pos_y(centroDetails.getN_pos_y());
		centro.setN_pos_z(centroDetails.getN_pos_z());

		Centro updatedCentro = centroRepository.save(centro);
		return updatedCentro;
	}

	@DeleteMapping("/centros/{id}")
	public ResponseEntity<?> deleteCentro(@PathVariable(value = "id") Long k_centro) {
		Centro centro = centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Centro", "id", k_centro));

		centroRepository.delete(centro);

		return ResponseEntity.ok().build();
	}

}
