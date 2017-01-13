package br.com.probes.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

import br.com.probes.solr.document.ProbeDocument;

public interface ProbeRepository extends SolrCrudRepository<ProbeDocument, String> {

	ProbeDocument findById(String id);
	
}
