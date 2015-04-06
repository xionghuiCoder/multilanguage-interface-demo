package com.sohu.multilanguage.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Provider
public class FastJsonProvider implements MessageBodyWriter<Object> {
  public FastJsonProvider() {
    features =
        new SerializerFeature[] {SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.QuoteFieldNames, SerializerFeature.SortField,
            SerializerFeature.DisableCircularReferenceDetect};
  }

  private SerializerFeature[] features;

  public SerializerFeature[] getFeatures() {
    return features;
  }

  public void setFeatures(SerializerFeature[] features) {
    this.features = features;
  }

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    if (!isJsonType(mediaType)) {
      return false;
    }
    return true;
  }

  /**
   * Helper method used to check whether given media type is JSON type or sub type. Current
   * implementation essentially checks to see whether {@link MediaType#getSubtype} returns "json" or
   * something ending with "+json".
   */
  protected boolean isJsonType(MediaType mediaType) {
    /*
     * As suggested by Stephen D, there are 2 ways to check: either being as inclusive as possible
     * (if subtype is "json"), or exclusive (major type "application", minor type "json"). Let's
     * start with inclusive one, hard to know which major types we should cover aside from
     * "application".
     */
    if (mediaType != null) {
      // Ok: there are also "xxx+json" subtypes, which count as well
      String subtype = mediaType.getSubtype();
      return "json".equalsIgnoreCase(subtype) || subtype.endsWith("+json");
    }
    /*
     * Not sure if this can happen; but it seems reasonable that we can at least produce json
     * without media type?
     */
    return true;
  }

  @Override
  public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType) {
    return -1;
  }

  @Override
  public void writeTo(Object value, Class<?> type, Type genericType, Annotation[] annotations,
      MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
      throws IOException, WebApplicationException {
    if ((value instanceof String) && httpHeaders.containsKey("exception")) {
      httpHeaders.remove("exception");
      entityStream.write(((String) value).getBytes("UTF-8"));
    } else {
      entityStream.write(JSON.toJSONBytes(value, features));
    }
  }
}
