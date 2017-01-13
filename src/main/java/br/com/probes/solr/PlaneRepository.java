package br.com.probes.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

import br.com.probes.solr.document.PlaneDocument;

public interface PlaneRepository extends SolrCrudRepository<PlaneDocument, String> {

	PlaneDocument findById(String id);
	
}
