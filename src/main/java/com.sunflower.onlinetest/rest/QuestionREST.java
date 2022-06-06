package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.rest.mapper.QuestionMapper;
import com.sunflower.onlinetest.rest.request.QuestionRequest;
import com.sunflower.onlinetest.rest.response.QuestionDTO;
import com.sunflower.onlinetest.rest.response.ResponseObject;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.service.QuestionService;
import com.sunflower.onlinetest.service.TopicService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("topics/{topicCode}/questions")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuestionREST {

    @HeaderParam("Authorization")
    private String authorization;

    @PathParam("topicCode")
    private String topicCode;

    @Inject
    private JWTAuthenticationService jwtAuthenticationService;

    @Inject
    private TopicService topicService;

    @Inject
    private QuestionService questionService;

    @Inject
    private QuestionMapper questionMapper;

    @GET
    @Path("")
    public Response getAllByTopicId() {
        try {
            Integer topicId = CustomBase64.decodeAsInteger(topicCode);
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), topicId);
            List<QuestionDTO> questionDTOS = questionMapper.entityToDTOs(questionService.getAllByTopicId(topicId));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(questionDTOS)
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
            Integer topicId = CustomBase64.decodeAsInteger(topicCode);
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), topicId);
            questionService.checkTopicContainQuestion(topicId, CustomBase64.decodeAsInteger(code));
            QuestionDTO questionDTO = questionMapper.entityToDTO(questionService.getByCode(code));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(questionDTO)
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
    public Response create(QuestionRequest questionRequest) {
        try {
            Integer topicId = CustomBase64.decodeAsInteger(topicCode);
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), topicId);
            QuestionDTO questionDTO = questionMapper.entityToDTO(questionService.create(jwtAuthenticationService.getUserId(authorization), topicId, questionRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(questionDTO)
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
    public Response update(@PathParam("code") String code, QuestionRequest questionRequest) {
        try {
            Integer topicId = CustomBase64.decodeAsInteger(topicCode);
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), topicId);
            questionService.checkTopicContainQuestion(topicId, CustomBase64.decodeAsInteger(code));
            QuestionDTO questionDTO = questionMapper.entityToDTO(questionService.update(code, questionRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(questionDTO)
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
            Integer topicId = CustomBase64.decodeAsInteger(topicCode);
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            topicService.checkUserOwnTopic(jwtAuthenticationService.getUserId(authorization), topicId);
            questionService.checkTopicContainQuestion(topicId, CustomBase64.decodeAsInteger(code));
            QuestionDTO questionDTO = questionMapper.entityToDTO(questionService.delete(code));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(questionDTO)
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
