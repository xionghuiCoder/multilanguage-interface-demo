package com.sohu.multilanguage.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

@Component
@Path("/language")
@Produces("application/json; charset=UTF-8")
public class MultilanguageApi {

  @GET
  public Map<String, Object> getMap() {
    Map<String, Object> retMap = new HashMap<String, Object>();
    retMap.put("key", "value");
    return retMap;
  }
}
