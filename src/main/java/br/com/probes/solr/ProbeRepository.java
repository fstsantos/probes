package br.com.probes.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

import br.com.probes.model.Probe;

public interface ProbeRepository extends SolrCrudRepository<Probe, String> {

	Probe findById(String id);
	
}
