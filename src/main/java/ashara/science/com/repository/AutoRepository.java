/**
 * 
 */
package ashara.science.com.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ashara.science.com.model.Auto;

/**
 * @author andrea
 *
 */
public interface AutoRepository extends JpaRepository<Auto, Long> {

	List<Auto> findAllByCentro(Long k_centro);
	
	List<Auto> findAllByHora(Date f_auto);
	
	Long countByCentro(Long k_centro);
	
	Long countByHora(Date f_auto);

}
