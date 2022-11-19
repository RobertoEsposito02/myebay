package it.prova.myebay.dto.categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public class CategoriaDTO {
	private Long id;
	private String descrizione;
	private String codice;
	private List<Annuncio> annunci = new ArrayList<>();

	public CategoriaDTO() {
	}

	public CategoriaDTO(Long id, String descrizione, String codice, List<Annuncio> annunci) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.codice = codice;
		this.annunci = annunci;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public List<Annuncio> getAnnunci() {
		return annunci;
	}

	public void setAnnunci(List<Annuncio> annunci) {
		this.annunci = annunci;
	}

	public static CategoriaDTO buildCategoriaFromModel(Categoria categoriaModel) {
		return new CategoriaDTO(categoriaModel.getId(), categoriaModel.getDescrizione(), categoriaModel.getCodice(),
				categoriaModel.getAnnunci());
	}
	
	public static List<CategoriaDTO> createCategoriaDTOListFromModelSet(Set<Categoria> categoriaSet){
		return categoriaSet.stream().map(categoriaEntity -> {
			return CategoriaDTO.buildCategoriaFromModel(categoriaEntity);
		}).collect(Collectors.toList());
	}
}
