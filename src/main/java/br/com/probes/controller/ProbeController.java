package br.com.probes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.probes.model.Probe;
import br.com.probes.service.ProbeService;

@RestController
public class ProbeController {

	@Autowired
	private ProbeService probeService;

	@RequestMapping(value = "/probe/{probeId}", method = RequestMethod.GET)
	public Probe getProbe(@PathVariable String probeId) throws Exception {
		return probeService.getProbe(probeId);
	}

	@RequestMapping(value = "/probe/{probeId}", method = RequestMethod.DELETE)
	public void deleteProbe(@PathVariable String probeId) throws Exception {
		probeService.deleteProbe(probeId);
	}

	@RequestMapping(value = "/probe", method = RequestMethod.DELETE)
	public void deleteAllProbes() throws Exception {
		probeService.deleteAllProbes();
	}

}
