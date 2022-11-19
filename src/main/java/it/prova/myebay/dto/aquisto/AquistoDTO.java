package it.prova.myebay.dto.aquisto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import it.prova.myebay.model.Aquisto;
import it.prova.myebay.model.Utente;

public class AquistoDTO {
	private Long id;
	private String descrizione;
	private Date data;
	private Integer prezzo;
	private Utente utenteAquirente;

	public AquistoDTO() {
	}

	public AquistoDTO(Long id, String descrizione, Date data, Integer prezzo, Utente utenteAquirente) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.data = data;
		this.prezzo = prezzo;
		this.utenteAquirente = utenteAquirente;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

	public Utente getUtenteAquirente() {
		return utenteAquirente;
	}

	public void setUtenteAquirente(Utente utenteAquirente) {
		this.utenteAquirente = utenteAquirente;
	}

	public static AquistoDTO buildAquistoFromModel(Aquisto aquistoModel) {
		return new AquistoDTO(aquistoModel.getId(), aquistoModel.getDescrizione(), aquistoModel.getData(),
				aquistoModel.getPrezzo(), aquistoModel.getUtenteAquirente());
	}
	
	public static List<AquistoDTO> createAquistoDTOListFromModelList(List<Aquisto> aquistoList){
		return aquistoList.stream().map(aquistoEntity -> {
			return AquistoDTO.buildAquistoFromModel(aquistoEntity);
		}).collect(Collectors.toList());
	}
}
