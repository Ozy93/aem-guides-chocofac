package com.adobe.aem.guides.chocofac.core.models.Impl;

import com.adobe.aem.guides.chocofac.core.models.BreadCrumbOfChocolate;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class BreadCrumbOfChocolateImpl implements BreadCrumbOfChocolate {
    private static final Logger log = LoggerFactory.getLogger(BreadCrumbOfChocolateImpl.class);

    @Inject
    Resource resource;

    @Inject
    @Optional
    private String title;

    @Override
    public String getTitle() {
        return title;
    }
}
