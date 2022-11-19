package it.prova.myebay.dto.annuncio;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;
import it.prova.myebay.model.Utente;

public class AnnuncioDTO {
	private Long id;
	private String testoAnnuncio;
	private Integer prezzo;
	private Date data;
	private boolean aperto;

	private Utente utenteInserimento;

	private Set<Categoria> categorie = new HashSet<>();

	public AnnuncioDTO() {
	}

	public AnnuncioDTO(Long id, String testoAnnuncio, Integer prezzo, Date data, boolean aperto,
			Utente utenteInserimento) {
		super();
		this.id = id;
		this.testoAnnuncio = testoAnnuncio;
		this.prezzo = prezzo;
		this.data = data;
		this.aperto = aperto;
		this.utenteInserimento = utenteInserimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestoAnnuncio() {
		return testoAnnuncio;
	}

	public void setTestoAnnuncio(String testoAnnuncio) {
		this.testoAnnuncio = testoAnnuncio;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isAperto() {
		return aperto;
	}

	public void setAperto(boolean aperto) {
		this.aperto = aperto;
	}

	public Utente getUtenteInserimento() {
		return utenteInserimento;
	}

	public void setUtenteInserimento(Utente utenteInserimento) {
		this.utenteInserimento = utenteInserimento;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	public static AnnuncioDTO buildAnnuncioFromModel(Annuncio annuncioModel) {
		return new AnnuncioDTO(annuncioModel.getId(), annuncioModel.getTestoAnnuncio(), annuncioModel.getPrezzo(),
				annuncioModel.getData(), annuncioModel.isAperto(), annuncioModel.getUtenteInserimento());
	}

	public static List<AnnuncioDTO> createAnnuncioDTOListFromModelList(List<Annuncio> annuncioList){
		return annuncioList.stream().map(annuncioEntity -> {
			return AnnuncioDTO.buildAnnuncioFromModel(annuncioEntity);
		}).collect(Collectors.toList());
	}
}
