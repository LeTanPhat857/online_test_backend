package com.sunflower.onlinetest.rest;

import com.sunflower.onlinetest.entity.ExamEntity;
import com.sunflower.onlinetest.entity.ResultEntity;
import com.sunflower.onlinetest.rest.mapper.DoExamMapper;
import com.sunflower.onlinetest.rest.mapper.ResultMapper;
import com.sunflower.onlinetest.rest.request.DoExamRequest;
import com.sunflower.onlinetest.rest.response.DoExamDTO;
import com.sunflower.onlinetest.rest.response.ResponseObject;
import com.sunflower.onlinetest.rest.response.ResultDTO;
import com.sunflower.onlinetest.service.ExamService;
import com.sunflower.onlinetest.service.JWTAuthenticationService;
import com.sunflower.onlinetest.service.ResultService;
import com.sunflower.onlinetest.util.CustomBase64;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("do-exam")
@RequestScoped
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DoExamREST {

    @HeaderParam("Authorization")
    private String authorization;

    @Inject
    private JWTAuthenticationService jwtAuthenticationService;

    @Inject
    private ExamService examService;

    @Inject
    private DoExamMapper doExamMapper;

    @Inject
    private ResultService resultService;

    @Inject
    private ResultMapper resultMapper;

    @GET
    @Path("{code}")
    public Response takeExam(@PathParam("code") String examCode) {
        try {
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            ExamEntity examEntity = examService.getByCode(examCode);
            ResultEntity resultEntity = resultService.create(jwtAuthenticationService.getUserId(authorization), examEntity);
            DoExamDTO doExamDTO = doExamMapper.entityToDTO(examEntity);
            doExamDTO.setStartTime(resultEntity.getStartTime());
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(doExamDTO)
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
    @Path("submit/{code}")
    public Response submitExam(@PathParam("code") String examCode, DoExamRequest doExamRequest) {
        try {
            Integer examId = CustomBase64.decodeAsInteger(examCode);
            jwtAuthenticationService.checkAuthorizedToken(authorization);
            Integer userId = jwtAuthenticationService.getUserId(authorization);
            resultService.checkSubmitPerson(userId, examId);
            ResultDTO resultDTO = resultMapper.entityToDTO(resultService.update(userId, examId, doExamRequest));
            ResponseObject responseObject = ResponseObject.builder()
                    .message("successfully")
                    .data(resultDTO)
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
