package com.adobe.aem.guides.chocofac.core.servlets;


import com.adobe.aem.guides.chocofac.beans.ArticleListDataBean;
import com.adobe.aem.guides.chocofac.core.models.ArticleListModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;


@Component(service = {Servlet.class})
@SlingServletResourceTypes(
    resourceTypes = "sling/servlet/default",
        selectors = "getArticleList",
        extensions = "json",
        methods = HttpConstants.METHOD_POST)

public class MyServlet extends SlingAllMethodsServlet {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyServlet.class);

    private final static String RESOURCE_PATH =  "/content/chocofac/us/en/jcr:content/root/container/container/articlelist";

    @Override
    protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {

//    this servlet is being called from postman from this url http://localhost:4502/content/chocofac/us/en.getArticleList.json
//        AEM Headless Architecture (Point of View)
//        Introduction to AEM Sling Servlet
//        AEM Headless - Servlet Implementation by SANKHAM

        LOGGER.debug("Servlet Code Started: ");
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource resource = resourceResolver.getResource(RESOURCE_PATH);

        ArticleListModel articleListModel = resource.adaptTo(ArticleListModel.class);
        List<ArticleListDataBean>  articleListDataBeanList = articleListModel.getArticleListDataBeanArray();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(articleListDataBeanList);

        response.setContentType("application/json");
        response.getWriter().write(json);

    }


}
