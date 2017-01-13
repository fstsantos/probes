package br.com.probes.solr.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import br.com.probes.plane.Plane;

@SolrDocument(solrCoreName = "plane")
public class PlaneDocument {

	@Id
	@Indexed(name = "id", type = "string")
	private String id;

	@Indexed(name = "bottom_x", type = "int")
	private int bottomX;

	@Indexed(name = "bottom_y", type = "int")
	private int bottomY;
	
	@Indexed(name = "upper_x", type = "int")
	private int upperX;
	
	@Indexed(name = "upper_y", type = "int")
	private int upperY;

	public PlaneDocument() {
	}

	public PlaneDocument(Plane plane) {
		this.id = plane.getId();
		this.bottomX = plane.getBottomLimit().getX();
		this.bottomY = plane.getBottomLimit().getY();
		this.upperX = plane.getUpperLimit().getX();
		this.upperY = plane.getUpperLimit().getY();
	}

	public String getId() {
		return id;
	}

	public int getBottomX() {
		return bottomX;
	}

	public int getBottomY() {
		return bottomY;
	}

	public int getUpperX() {
		return upperX;
	}

	public int getUpperY() {
		return upperY;
	}
	
}
