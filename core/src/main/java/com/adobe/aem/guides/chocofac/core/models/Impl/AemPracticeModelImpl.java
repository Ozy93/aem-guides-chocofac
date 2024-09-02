package com.adobe.aem.guides.chocofac.core.models.Impl;

import com.adobe.aem.guides.chocofac.core.models.AemPracticeModel;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.settings.SlingSettingsService;
import com.day.cq.commons.Externalizer;

import javax.inject.Inject;
import java.util.*;


//AEM Tutorial #10 | Sling Model #1 | Create Component with Sling Model in AEM 6.5
@Model(adaptables = Resource.class,
        adapters = AemPracticeModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)


public class AemPracticeModelImpl implements AemPracticeModel {
    private static final Logger LOG = LoggerFactory.getLogger(AemPracticeModelImpl.class);

    @Inject
    Resource resource;

    @Inject
    SlingSettingsService slingSettingsService;
    @Inject
    @Via("resource")
    @Default(values = "AEM")
    private String path;

    @Inject
    @Via("resource")
    private Boolean isback;

    @ValueMapValue
    @Default(values = "Title Default")
    private String title;




    /*@Override
    public String getImage() {
        return path;
    }*/
    @ValueMapValue(name = "imagePath")
    private String imagePath;

    public String getImagePath() {
        return path;
    }

    public Boolean isAuthorRunMode() {
        Set<String> runModes = slingSettingsService.getRunModes();
        return runModes.contains(Externalizer.AUTHOR);
    }

    public Boolean isPublishRunMode() {
        Set<String> runModes = slingSettingsService.getRunModes();
        return runModes.contains(Externalizer.PUBLISH );
    }



    @Override
    public Boolean isBackgroundImage() {
        return isback;
    }

    @Override
    public String getTitle() {
        return title;
    }



    @Override
    public List<Map<String, String>> getNavigationItems() {
        List<Map<String, String>> bookDetailsMap=new ArrayList<>();
        try {
            Resource bookDetail=resource.getChild("navigationitemswithmap");
            if(bookDetail!=null){
                for (Resource book : bookDetail.getChildren()) {
                    Map<String,String> bookMap=new HashMap<>();
                    String newtitnav = "TEST__" + book.getValueMap().get("titlenav",String.class);
                    //bookMap.put("multititle",book.getValueMap().get("titlenav",String.class));
                    bookMap.put("multititle",newtitnav);
                    bookMap.put("multiurl",book.getValueMap().get("pathnav",String.class));
                    bookDetailsMap.add(bookMap);
                }
            }
        }catch (Exception e){
            LOG.info("\n ERROR while getting Book Details {} ",e.getMessage());
        }
        return bookDetailsMap;
    }

}
