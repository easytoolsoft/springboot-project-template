package com.easytoolsoft.template.web.springmvc2.rest.v1;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.easytoolsoft.template.web.springmvc2.domain.Event;
import com.easytoolsoft.template.web.springmvc2.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhiwei.deng
 * @date 2017-03-28
 **/
@Slf4j
@Component("EventResourceV1")
@Path("/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = " Event resource", produces = "application/json")
public class EventResource {
    @Resource
    private EventService eventService;

    @GET
    @Path("event/{id}")
    @Consumes("application/vnd.springboot-template-v1+json")
    @Produces("application/vnd.springboot-template-v1+json")
    @ApiOperation(value = "Gets a  Event resource. Version 1", response = Event.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = " Event resource found"),
        @ApiResponse(code = 404, message = " Event resource not found")
    })
    public Response getEventById(@ApiParam @PathParam("id") Integer id) {
        log.info("get EventById() v1");
        Event result = eventService.getById(id);
        return Response.status(Status.OK).entity(result).build();
    }

    @POST
    @Path("event")
    @Consumes("application/vnd.springboot-template-v1+json")
    @ApiOperation(value = "Creates Event resource. Version 1", response = Event.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = " Event resource created", responseHeaders = {
            @ResponseHeader(name = "Location", description = "The URL to retrieve created resource",
                response = String.class)
        })
    })
    public Response createEvent(Event event, @Context UriInfo uriInfo) {
        log.info("create Event() v1");
        eventService.add(event);
        return Response.status(Status.OK).entity(event).build();
    }
}