package it.prova.myebay.repository.annuncio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.myebay.model.Annuncio;
import it.prova.myebay.model.Categoria;

public class CustomAnnuncioRepositoryImpl implements CustomAnnuncioRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Annuncio> findByExample(Annuncio example) {
		Map<String, Object> parameterValue = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		
		StringBuilder queryBuilder = new StringBuilder("from Annuncio a left join fetch a.utenteInserimento u where aperto = 1 ");

		if(!example.getTestoAnnuncio().isEmpty()) {
			whereClause.add(" a.testoAnnuncio like :testoAnnuncio ");
			parameterValue.put("testoAnnuncio","%" + example.getTestoAnnuncio() + "%");
		}
		if(example.getPrezzo() != null) {
			whereClause.add(" a.prezzo >= :prezzo ");
			parameterValue.put("prezzo", example.getPrezzo());
		}
		/*
		if(!example.getCategorie().isEmpty()) {
			for (Categoria categoria : example.getCategorie()) {
				
			}
		}*/
		
		queryBuilder.append(!whereClause.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClause, "and"));
		TypedQuery<Annuncio> query = entityManager.createQuery(queryBuilder.toString(),Annuncio.class);
		
		for (String key : parameterValue.keySet()) {
			query.setParameter(key, parameterValue.get(key));
		}
		
		return query.getResultList();
	}

	@Override
	public List<Annuncio> findByExampleNotMyAnnunci(Annuncio example, Long id) {
		Map<String, Object> parameterValue = new HashMap<>();
		List<String> whereClause = new ArrayList<>();
		
		StringBuilder queryBuilder = new StringBuilder("from Annuncio a left join fetch a.utenteInserimento u where not a.id  = :id and aperto = 1 ");
		parameterValue.put("id", id);
		
		if(!example.getTestoAnnuncio().isEmpty()) {
			whereClause.add(" a.testoAnnuncio like :testoAnnuncio ");
			parameterValue.put("testoAnnuncio","%" + example.getTestoAnnuncio() + "%");
		}
		if(example.getPrezzo() != null) {
			whereClause.add(" a.prezzo >= :prezzo ");
			parameterValue.put("prezzo", example.getPrezzo());
		}
		
		queryBuilder.append(!whereClause.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClause, "and"));
		TypedQuery<Annuncio> query = entityManager.createQuery(queryBuilder.toString(),Annuncio.class);
		
		for (String key : parameterValue.keySet()) {
			query.setParameter(key, parameterValue.get(key));
		}
		
		return query.getResultList();
	}

	@Override
	public void scollegaAnnuncio(Long id) {
		entityManager.createNativeQuery("delete from categoria_annunci where annunci_id = :id").setParameter("id", id).executeUpdate();
		entityManager.createNativeQuery("delete from annuncio_categoria where annuncio_id = :id").setParameter("id", id).executeUpdate();
	}

}
