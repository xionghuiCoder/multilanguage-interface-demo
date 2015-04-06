package com.sohu.multilanguage.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Component;

import com.sohu.multilanguage.core.Multilanguage;

/**
 * 多语接口
 *
 * @author XiongHui
 */
@Component
@Path("/multilanguage")
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
public class MultilanguageRest {
  private static final String SPLIT = "\\|";

  private static final String TOP_SPLIT = ",";

  @GET
  @Path("/all_tips")
  public Map<String, String> getFileMap(@QueryParam("relative_path") String relativePath,
      @QueryParam("langtype") String langType, @QueryParam("folder_name") String folderName,
      @QueryParam("file_name") String fileName) {
    Multilanguage language = new Multilanguage(relativePath);
    Map<String, String> fileMap = language.getFileMap(langType, folderName, fileName);
    return fileMap;
  }

  @GET
  @Path("/tip")
  public String getTip(@QueryParam("relative_path") String relativePath,
      @QueryParam("langtype") String langType, @QueryParam("folder_name") String folderName,
      @QueryParam("file_name") String fileName, @QueryParam("langcode") String langcode) {
    Multilanguage language = new Multilanguage(relativePath);
    String tip = language.getTip(langType, folderName, fileName, langcode);
    return tip;
  }

  @GET
  @Path("/tips")
  public Map<String, String> getTips(@QueryParam("relative_path") String relativePath,
      @QueryParam("langtype") String langType, @QueryParam("folder_name") String folderName,
      @QueryParam("file_name") String fileName, @QueryParam("langcodes") String langcodeStr) {
    Multilanguage language = new Multilanguage(relativePath);
    String[] langcodes = langcodeStr.split(SPLIT);
    Map<String, String> tipsMap = language.getTips(langType, folderName, fileName, langcodes);
    return tipsMap;
  }

  @GET
  @Path("/params_tip")
  public String getTipByParams(@QueryParam("relative_path") String relativePath,
      @QueryParam("langtype") String langType, @QueryParam("folder_name") String folderName,
      @QueryParam("file_name") String fileName, @QueryParam("langcode") String langcode,
      @QueryParam("params") String paramStr) {
    Multilanguage language = new Multilanguage(relativePath);
    String[] params = paramStr.split(SPLIT);
    String tip = language.getTipByParams(langType, folderName, fileName, langcode, params);
    return tip;
  }

  @GET
  @Path("/params_tips")
  public Map<String, String> getTipsByParams(@QueryParam("relative_path") String relativePath,
      @QueryParam("langtype") String langType, @QueryParam("folder_name") String folderName,
      @QueryParam("file_name") String fileName, @QueryParam("langcodes") String langcodeStr,
      @QueryParam("params") String paramStr) {
    Multilanguage language = new Multilanguage(relativePath);
    String[] langcodes = langcodeStr.split(SPLIT);
    String[] params = paramStr.split(SPLIT);
    Map<String, String> tipMap =
        language.getTipsByParams(langType, folderName, fileName, langcodes, params);
    return tipMap;
  }

  @GET
  @Path("/paramses_tips")
  public Map<String, String> getTipsByParamses(@QueryParam("relative_path") String relativePath,
      @QueryParam("langtype") String langType, @QueryParam("folder_name") String folderName,
      @QueryParam("file_name") String fileName, @QueryParam("langcodes") String langcodeStr,
      @QueryParam("paramses") String paramStr) {
    Multilanguage language = new Multilanguage(relativePath);
    String[] langcodes = langcodeStr.split(SPLIT);
    String[] params = paramStr.split(TOP_SPLIT);
    String[][] paramses = new String[params.length][];
    for (int i = 0, len = params.length; i < len; i++) {
      paramses[i] = params[i].split(SPLIT);
    }
    Map<String, String> tipMap =
        language.getTipsByParamses(langType, folderName, fileName, langcodes, paramses);
    return tipMap;
  }
}
