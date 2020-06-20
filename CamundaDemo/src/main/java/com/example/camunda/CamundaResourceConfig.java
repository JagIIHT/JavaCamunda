package com.example.camunda;

import java.net.URI;

import javax.ws.rs.Path;

import org.camunda.bpm.engine.rest.DeploymentRestService;
import org.camunda.bpm.engine.rest.impl.AbstractProcessEngineRestServiceImpl;
import org.camunda.bpm.engine.rest.impl.DefaultProcessEngineRestServiceImpl;

@Path(DefaultProcessEngineRestServiceImpl.PATH)
public class CamundaResourceConfig extends AbstractProcessEngineRestServiceImpl {

	public static final String PATH = "";
	
	@Override
	protected URI getRelativeEngineUri(String engineName) {
		return URI.create("/");
	}

	@Path(DeploymentRestService.PATH)
	public DeploymentRestService getDeploymentRestService() {
		return super.getDeploymentRestService(null);
	}

}
