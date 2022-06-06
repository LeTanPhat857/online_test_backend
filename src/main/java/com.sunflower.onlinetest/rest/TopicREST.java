package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.rest.mapper.TopicMapper;
import com.sunflower.onlinetest.rest.request.TopicRequest;
import com.sunflower.onlinetest.rest.response.ResponseObject;
import com.sunflower.onlinetest.rest.response.TopicDTO;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.service.TopicService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("topics")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicREST {

    @HeaderParam("Authorization")
    private String authorization;

    @Inject
    private JWTAuthenticationService jwtAuthenticationService;

    @Inject
    private TopicService topicService;

    @Inject
    private TopicMapper topicEntityMapper;

    @GET
    @Path("")
    public Response getAllByUserId() {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            List<TopicDTO> topicDTOS = topicEntityMapper.entityToDTOs(topicService.getAllByUserId(jwtAuthenticationService.getUserId(authorization)));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(topicDTOS)
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObject responseObject = ResponseObject.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }

    @GET
    @Path("{code}")
    public Response getByCode(@PathParam("code") String code) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), CustomBase64.decodeAsInteger(code));
            TopicDTO topicDTO = topicEntityMapper.entityToDTO(topicService.getByCode(code));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(topicDTO)
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObject responseObject = ResponseObject.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }

    @PUT
    @Path("create")
    public Response create(TopicRequest topicRequest) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            TopicDTO topicDTO = topicEntityMapper.entityToDTO(topicService.create(jwtAuthenticationService.getUserId(authorization), topicRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(topicDTO)
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObject responseObject = ResponseObject.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }

    @POST
    @Path("update/{code}")
    public Response update(@PathParam("code") String code, TopicRequest topicRequest) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), CustomBase64.decodeAsInteger(code));
            TopicDTO topicDTO = topicEntityMapper.entityToDTO(topicService.update(code, topicRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(topicDTO)
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObject responseObject = ResponseObject.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }

    @DELETE
    @Path("delete/{code}")
    public Response update(@PathParam("code") String code) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), CustomBase64.decodeAsInteger(code));
            TopicDTO topicDTO = topicEntityMapper.entityToDTO(topicService.delete(code));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(topicDTO)
                    .build();
            return Response.status(Response.Status.OK)
                    .entity(responseObject)
                    .build();
        } catch (Exception e) {
            ResponseObject responseObject = ResponseObject.builder()
                    .message(e.getMessage())
                    .build();
            return Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(responseObject)
                    .build();
        }
    }
}