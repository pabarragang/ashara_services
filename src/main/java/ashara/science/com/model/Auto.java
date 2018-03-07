/**
 * 
 */
package ashara.science.com.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author andrea
 *
 */
@Entity
@Table(name = "arbol")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Auto {

	@NotBlank
	private Float n_pos_x;

	@NotBlank
	private Float n_pos_y;

	@NotBlank
	private Float n_pos_z;

	@NotBlank
	private Float n_cantidad;

	@NotBlank
	private Date f_auto;

	@NotBlank
	private Integer n_hora;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "centro_k_centro")
	private Centro centro;

}
