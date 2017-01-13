package br.com.probes.solr.document;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import br.com.probes.model.Plane;

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

	@Dynamic @Field("*_probe_pos")
	private Map<String, String> positionMap;
	
	public PlaneDocument() {
	}

	public PlaneDocument(Plane plane) {
		this.id = plane.getId();
		this.bottomX = plane.getBottomLimit().getX();
		this.bottomY = plane.getBottomLimit().getY();
		this.upperX = plane.getUpperLimit().getX();
		this.upperY = plane.getUpperLimit().getY();
		this.positionMap = new HashMap<String, String>();
		for (Entry<Point, String> entry : )
		plane.getPositionMap().entrySet().stream().map(e -> positionMap.put(e.getKey().toString(), e.getValue()));
		System.out.println();
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

	public Map<String, String> getPositionMap() {
		if (positionMap == null) positionMap = new HashMap<String, String>();
		return positionMap;
	}

}
