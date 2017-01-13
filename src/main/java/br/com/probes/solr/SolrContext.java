package br.com.probes.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(basePackages = {"br.com.probes"}, multicoreSupport = true)
public class SolrContext {

	private final static String SOLR_HOST = "http://localhost:8983/solr";
	
	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient(SOLR_HOST);
	}
    
}
