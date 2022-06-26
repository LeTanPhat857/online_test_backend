package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.rest.mapper.ResultMapper;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.service.ResultService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("results")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResultREST {

    @HeaderParam("Authorization")
    private String authorization;

    @Inject
    private JWTAuthenticationService jwtAuthenticationService;

    @Inject
    private ResultService resultService;

    @Inject
    private ResultMapper resultMapper;

    // TODO implement CRUD for admin
}
