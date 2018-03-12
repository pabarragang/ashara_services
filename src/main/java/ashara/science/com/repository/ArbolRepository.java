/**
 * 
 */
package ashara.science.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ashara.science.com.model.Arbol;

/**
 * @author andrea
 *
 */
public interface ArbolRepository extends JpaRepository<Arbol, Long> {

	@Query(value = "SELECT * FROM arbol WHERE centro_k_centro = ?1",
		   nativeQuery = true)
	List<Arbol> findAllByCentro(Long k_centro);
	
	Long countByCentro(Long k_centro);

}
