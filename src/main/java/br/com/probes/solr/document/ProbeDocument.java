package br.com.probes.solr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import br.com.probes.model.Probe;

@SolrDocument(solrCoreName = "probe")
public class ProbeDocument {

	@Id
	@Indexed(name = "id", type = "string")
	private String id;
	
	@Indexed(name = "x", type = "int")
	private int x;
	
	@Indexed(name = "y", type = "int")
	private int y;
	
	@Indexed(name = "direction", type = "string")
	private String direction;
	
	public ProbeDocument(Probe probe) {
		this.id = probe.getId();
		this.x = probe.getPosition().getX();
		this.y = probe.getPosition().getY();
		this.direction = probe.getDirection().name();
	}
	
	public String getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getDirection() {
		return direction;
	}
	
}
