package com.tistory.byrage.springboot.webserver.component;

import java.util.Map;

public interface DataBinding {

    Map<String, Class<?>> getDataBinder();
}
