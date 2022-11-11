package com.szwenni.filtertesting.impl;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;
import com.szwenni.filtertesting.api.RestApiIpComponent;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService ({RestApiIpComponent.class})
@Named ("myPluginComponent")
public class RestApiIpComponentImpl implements RestApiIpComponent
{
        @ComponentImport
        private final ApplicationProperties applicationProperties;

        @Inject
        public RestApiIpComponentImpl(final ApplicationProperties applicationProperties)
    {
        this.applicationProperties = applicationProperties;
    }

    public String getName()
    {
        if(null != applicationProperties)
        {
            return "myComponent:" + applicationProperties.getDisplayName();
        }
        
        return "myComponent";
    }
}