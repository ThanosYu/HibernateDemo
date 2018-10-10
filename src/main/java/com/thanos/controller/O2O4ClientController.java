package com.thanos.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thanos.model.O2OModule;
import com.thanos.service.O2OCategoryService;
import com.thanos.model.O2OCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Thanos Yu on 2017/3/28.
 */
@Component
@Path("/o2o")
public class O2O4ClientController {

    @Autowired
    private O2OCategoryService categoryService;


    @GET
    @Path("/categories")
    @Produces("application/json; charset=utf-8")
    public List<O2OCategory> getCategories(@Context HttpServletRequest request, @Context UriInfo ui) {
        List<O2OCategory> list = categoryService.getAll();
        return list;
    }


    @POST
    @Path("/modules")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public List<O2OModule> getModules(@Context HttpServletRequest request, Map<String, String> map) {
        String id = map.get("id");
        O2OCategory model = categoryService.get(Integer.valueOf(id));
        List<O2OModule> list = model.getModules();
        return list;
    }

    @POST
    @Path("/save")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json; charset=utf-8")
    public String save(@Context HttpServletRequest request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            O2OCategory category = mapper.readValue(request.getInputStream(), O2OCategory.class);
            categoryService.save(category);
        } catch (IOException e) {
            return "error";
        }
        return "ok";
    }
}
