package com.sohu.packages;

import com.sun.jersey.api.core.PackagesResourceConfig;

/**
 * reset packages scan
 *
 * @author XiongHui
 */
public class MultilanguagePackagesResourceConfig extends PackagesResourceConfig {
  public MultilanguagePackagesResourceConfig() {
    super("com.sohu.multilanguage.provider", "com.sohu.multilanguage.rest");
  }
}
