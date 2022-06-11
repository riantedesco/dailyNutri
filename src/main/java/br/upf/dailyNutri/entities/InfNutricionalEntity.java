package br.upf.dailyNutri.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_infnutricional")
@NamedQueries({
	@NamedQuery(name = "InfNutricionalEntity.findByAlimentoId", 
			query = "SELECT u FROM InfNutricionalEntity u WHERE u.alimento.id = :alimentoId")})
public class InfNutricionalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String calorias;
	private Double carboidratos;
	private Double proteinas;
	private Double gordurastotais;
	private Double colesterol;
	private Double fibras;
	private Double sodio;
	private Double potassio;
	
	@ManyToOne
	@JoinColumn(name = "alimento_id")
	private AlimentoEntity alimento;
	
	public InfNutricionalEntity() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCalorias() {
		return calorias;
	}

	public void setCalorias(String calorias) {
		this.calorias = calorias;
	}

	public Double getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(Double carboidratos) {
		this.carboidratos = carboidratos;
	}

	public Double getProteinas() {
		return proteinas;
	}

	public void setProteinas(Double proteinas) {
		this.proteinas = proteinas;
	}

	public Double getGordurastotais() {
		return gordurastotais;
	}

	public void setGordurastotais(Double gordurastotais) {
		this.gordurastotais = gordurastotais;
	}

	public Double getColesterol() {
		return colesterol;
	}

	public void setColesterol(Double colesterol) {
		this.colesterol = colesterol;
	}

	public Double getFibras() {
		return fibras;
	}

	public void setFibras(Double fibras) {
		this.fibras = fibras;
	}

	public Double getSodio() {
		return sodio;
	}

	public void setSodio(Double sodio) {
		this.sodio = sodio;
	}

	public Double getPotassio() {
		return potassio;
	}

	public void setPotassio(Double potassio) {
		this.potassio = potassio;
	}

	public AlimentoEntity getAlimento() {
		return alimento;
	}

	public void setAlimento(AlimentoEntity alimento) {
		this.alimento = alimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfNutricionalEntity other = (InfNutricionalEntity) obj;
		return Objects.equals(id, other.id);
	}
	
}
