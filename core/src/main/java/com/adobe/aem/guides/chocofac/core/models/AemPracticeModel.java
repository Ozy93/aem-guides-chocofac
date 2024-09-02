package com.adobe.aem.guides.chocofac.core.models;

import java.util.List;
import java.util.Map;

public interface AemPracticeModel {
    //String getImage();
    String getImagePath();

    Boolean isBackgroundImage();

    Boolean isAuthorRunMode();

    public Boolean isPublishRunMode();

    String getTitle();



    List<Map<String, String>> getNavigationItems();
}
