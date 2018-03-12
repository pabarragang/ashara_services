/**
 * 
 */
package ashara.science.com.controller;

import java.util.Date;
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
import ashara.science.com.model.Auto;
import ashara.science.com.model.Centro;
import ashara.science.com.repository.AutoRepository;
import ashara.science.com.repository.CentroRepository;

/**
 * @author josdavidmo
 *
 */
@RestController
@RequestMapping("/api")
public class AutoController {

	@Autowired
	AutoRepository autoRepository;

	@Autowired
	CentroRepository centroRepository;

	@GetMapping("/autos")
	public List<Auto> getAllAutos() {
		return autoRepository.findAll();
	}
	
	@GetMapping("/autos/count")
	public Long countAllCentros() {
		return autoRepository.count();
	}

	@GetMapping("/autos/sector/{id}")
	public List<Auto> getAutosBySector(@PathVariable(value = "id") Long k_centro) {
		Centro centro = centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Centro", "id", k_centro));
		return autoRepository.findAllByCentro(centro.getK_centro());
	}

	@GetMapping("/autos/sector/{id}/count")
	public Long countAutosBySector(@PathVariable(value = "id") Long k_centro) {
		Centro centro = centroRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Centro", "id", k_centro));
		return autoRepository.countByCentro(centro.getK_centro());
	}

	@GetMapping("/autos/hora/{hora}/count")
	public Long countAutosByHora(@PathVariable(value = "hora") Date f_auto) {
		return autoRepository.countByHora(f_auto);
	}

	@PostMapping("/autos")
	public Auto createAuto(@Valid @RequestBody Auto auto) {
		return autoRepository.save(auto);
	}

	@GetMapping("/autos/{id}")
	public Auto getAutoById(@PathVariable(value = "id") Long k_centro) {
		return autoRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Auto", "id", k_centro));
	}

	@GetMapping("/autos/hora/{hora}/")
	public List<Auto> getAutosByHora(@PathVariable(value = "hora") Date f_auto) {
		return autoRepository.findAllByHora(f_auto);
	}

	@GetMapping("/autos/total")
	public Long getTotalAutos() {
		return autoRepository.count();
	}

	@PutMapping("/autos/{id}")
	public Auto updateAuto(@PathVariable(value = "id") Long k_centro, @Valid @RequestBody Auto autoDetails) {

		Auto auto = autoRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Arbol", "id", k_centro));

		auto.setCentro(autoDetails.getCentro());
		auto.setF_auto(autoDetails.getF_auto());
		auto.setK_centro(autoDetails.getK_centro());
		auto.setN_cantidad(autoDetails.getN_cantidad());
		auto.setN_hora(autoDetails.getN_hora());
		auto.setN_pos_x(autoDetails.getN_pos_x());
		auto.setN_pos_y(autoDetails.getN_pos_y());
		auto.setN_pos_z(autoDetails.getN_pos_z());

		Auto updatedAuto = autoRepository.save(auto);
		return updatedAuto;
	}

	@DeleteMapping("/autos/{id}")
	public ResponseEntity<?> deleteAuto(@PathVariable(value = "id") Long k_centro) {
		Auto auto = autoRepository.findById(k_centro)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", k_centro));

		autoRepository.delete(auto);

		return ResponseEntity.ok().build();
	}

}
