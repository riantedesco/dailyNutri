package br.upf.dailyNutri.entities;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedNativeQueries({
    //Querie nativa responsável por buscar um regsitro utilizando parte do título
    @NamedNativeQuery(name = "AlimentoEntity.findByPartNome",
            query = "SELECT * FROM tb_alimento AS alimento WHERE alimento.nome ~* ?",
            resultClass = AlimentoEntity.class)
})

@Entity
@Table(name = "tb_alimento")
@NamedQueries({
	@NamedQuery(name = "AlimentoEntity.findByNome", query = "SELECT u FROM AlimentoEntity u WHERE u.nome = :nome")})
public class AlimentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String unidademedida;
	private Double quantidadeporcao;
	private Date datacadastro;
	
	public AlimentoEntity() {
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUnidademedida() {
		return unidademedida;
	}

	public void setUnidademedida(String unidademedida) {
		this.unidademedida = unidademedida;
	}

	public Double getQuantidadeporcao() {
		return quantidadeporcao;
	}

	public void setQuantidadeporcao(Double quantidadeporcao) {
		this.quantidadeporcao = quantidadeporcao;
	}

	public Date getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
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
		AlimentoEntity other = (AlimentoEntity) obj;
		return Objects.equals(id, other.id);
	}
	
}
