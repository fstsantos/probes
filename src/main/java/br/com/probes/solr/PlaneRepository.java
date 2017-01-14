package br.com.probes.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

import br.com.probes.model.Plane;

public interface PlaneRepository extends SolrCrudRepository<Plane, String> {
}
