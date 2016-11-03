/*
 * Copyright (c) 2014 Jacob D. Parr
 *
 * This software may not be used without permission.
 */
package com.example.selenium.spree.useragent;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserAgentModel {

  private String agentType;
  private String agentName;
  private String agentVersion;
  private String agentLanguage;
  private String agentLanguageTag;
  private String osType;
  private String osName;
  private String osProducer;
  private String osProducerURL;
  private String osVersionName;
  private String osVersionNumber;
  private String linuxDistribution;

  @JsonCreator
  public UserAgentModel(@JsonProperty("agent_type") String agentType,
                        @JsonProperty("agent_name") String agentName,
                        @JsonProperty("agent_version") String agentVersion,
                        @JsonProperty("agent_language") String agentLanguage,
                        @JsonProperty("agent_languageTag") String agentLanguageTag,
                        @JsonProperty("os_type") String osType,
                        @JsonProperty("os_name") String osName,
                        @JsonProperty("os_producer") String osProducer,
                        @JsonProperty("os_producerURL") String osProducerURL,
                        @JsonProperty("os_versionName") String osVersionName,
                        @JsonProperty("os_versionNumber") String osVersionNumber,
                        // Purposely spelled wrong.
                        @JsonProperty("linux_distibution") String linuxDistribution) {

    this.agentType = clean(agentType);
    this.agentName = clean(agentName);
    this.agentVersion = clean(agentVersion);
    this.agentLanguage = clean(agentLanguage);
    this.agentLanguageTag = clean(agentLanguageTag);
    this.osType = clean(osType);
    this.osName = clean(osName);
    this.osProducer = clean(osProducer);
    this.osProducerURL = clean(osProducerURL);
    this.osVersionName = clean(osVersionName);
    this.osVersionNumber = clean(osVersionNumber);
    this.linuxDistribution = clean(linuxDistribution);
  }

  private String clean(String value) {
    if (value == null) {
      return null;
    } else if ("null".equalsIgnoreCase(value)) {
      return null;
    } else if (value.trim().isEmpty()){
      return null;
    } else {
      return value;
    }
  }

  public String getAgentType() {
    return agentType;
  }
  public String getAgentName() {
    return agentName;
  }
  public String getAgentVersion() {
    return agentVersion;
  }
  public String getAgentLanguage() {
    return agentLanguage;
  }
  public String getAgentLanguageTag() {
    return agentLanguageTag;
  }
  public String getOsType() {
    return osType;
  }
  public String getOsName() {
    return osName;
  }
  public String getOsProducer() {
    return osProducer;
  }
  public String getOsProducerURL() {
    return osProducerURL;
  }
  public String getOsVersionName() {
    return osVersionName;
  }
  public String getOsVersionNumber() {
    return osVersionNumber;
  }
  public String getLinuxDistribution() {
    return linuxDistribution;
  }
}
