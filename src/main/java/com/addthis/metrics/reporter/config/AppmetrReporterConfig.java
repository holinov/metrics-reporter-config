/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.addthis.metrics.reporter.config;


import com.pixonic.jmx.consumer.AppmetrReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;


public class AppmetrReporterConfig extends AbstractReporterConfig
{
    private static final Logger log = LoggerFactory.getLogger(AppmetrReporterConfig.class);

    public String getDeployToken() {
        return deployToken;
    }

    public void setDeployToken(String deployToken) {
        this.deployToken = deployToken;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @NotNull
    private String deployToken;

    @NotNull
    private String apiUrl;


    @Override
    public boolean enable()
    {
        log.info("Enabling AppMetrReporter to "+apiUrl+" with deployToken "+deployToken);
        try
        {
            final AppmetrReporter appmetrReporter = new AppmetrReporter(deployToken, apiUrl, getMetricPredicate());
            appmetrReporter.start(getPeriod(),getRealTimeunit());
        }
        catch (Exception e)
        {
            log.error("Failure while Enabling AppMetrReporter", e);
            return false;
        }
        return true;
    }

}
