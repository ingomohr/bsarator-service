package org.ingomohr.bsarator.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ingomohr.bsarator.service.internal.adapter.SimpleBSAratorTextAdapter;

@Path("/bsarator-service/adapt/simple")
public class BSAratorAdapterResource {

	@GET
	@Path("/{text}")
	@Produces(MediaType.TEXT_PLAIN)
	public String adapt(@PathParam("text") String text) {
		if (text != null) {
			return new SimpleBSAratorTextAdapter().adapt(text);
		}
		return null;
	}

}
