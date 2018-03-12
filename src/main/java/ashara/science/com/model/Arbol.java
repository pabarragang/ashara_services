/**
 * 
 */
package ashara.science.com.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Arbol {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long k_centro;

	@NotBlank
	private Float n_pos_x;

	@NotBlank
	private Float n_pos_y;

	@NotBlank
	private Float n_pos_z;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "centro_k_centro")
	private Centro centro;

	public Arbol(Long k_centro, @NotBlank Float n_pos_x, @NotBlank Float n_pos_y, @NotBlank Float n_pos_z,
			Centro centro) {
		super();
		this.k_centro = k_centro;
		this.n_pos_x = n_pos_x;
		this.n_pos_y = n_pos_y;
		this.n_pos_z = n_pos_z;
		this.centro = centro;
	}

	public Long getK_centro() {
		return k_centro;
	}

	public void setK_centro(Long k_centro) {
		this.k_centro = k_centro;
	}

	public Float getN_pos_x() {
		return n_pos_x;
	}

	public void setN_pos_x(Float n_pos_x) {
		this.n_pos_x = n_pos_x;
	}

	public Float getN_pos_y() {
		return n_pos_y;
	}

	public void setN_pos_y(Float n_pos_y) {
		this.n_pos_y = n_pos_y;
	}

	public Float getN_pos_z() {
		return n_pos_z;
	}

	public void setN_pos_z(Float n_pos_z) {
		this.n_pos_z = n_pos_z;
	}

	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

}
