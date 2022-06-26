package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.rest.mapper.ExamMapper;
import com.sunflower.onlinetest.rest.request.ExamRequest;
import com.sunflower.onlinetest.rest.response.ExamDTO;
import com.sunflower.onlinetest.rest.response.ResponseObject;
import com.sunflower.onlinetest.service.ExamService;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("exams")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExamREST {

    @HeaderParam("Authorization")
    private String authorization;

    @Inject
    private JWTAuthenticationService jwtAuthenticationService;

    @Inject
    private ExamService examService;

    @Inject
    private ExamMapper examMapper;

    @GET
    @Path("")
    public Response getAllByUserId() {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            List<ExamDTO> examDTOS = examMapper.entityToDTOs(examService.getAllByUserId(jwtAuthenticationService.getUserId(authorization)));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(examDTOS)
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
            examService.checkUserOwnExam(jwtAuthenticationService.getUserId(authorization), CustomBase64.decodeAsInteger(code));
            ExamDTO examDTO = examMapper.entityToDTO(examService.getByCode(code));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(examDTO)
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
    public Response create(ExamRequest examRequest) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            ExamDTO examDTO = examMapper.entityToDTO(examService.create(jwtAuthenticationService.getUserId(authorization), examRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(examDTO)
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
    public Response update(@PathParam("code") String code, ExamRequest examRequest) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            examService.checkUserOwnExam(jwtAuthenticationService.getUserId(authorization), CustomBase64.decodeAsInteger(code));
            ExamDTO examDTO = examMapper.entityToDTO(examService.update(code, examRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(examDTO)
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
            examService.checkUserOwnExam(jwtAuthenticationService.getUserId(authorization), CustomBase64.decodeAsInteger(code));
            ExamDTO examDTO = examMapper.entityToDTO(examService.delete(code));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(examDTO)
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
