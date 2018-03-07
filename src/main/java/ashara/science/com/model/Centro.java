/**
 * 
 */
package ashara.science.com.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author andrea
 *
 */
@Entity
@Table(name = "centro")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Centro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long k_centro;

	@NotBlank
	private Float n_pos_x;

	@NotBlank
	private Float n_pos_y;

	@NotBlank
	private Float n_pos_z;

}
