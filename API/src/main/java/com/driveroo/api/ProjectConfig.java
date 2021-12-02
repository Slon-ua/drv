package com.driveroo.api;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})  //туту задается путь к файлу где находятся наши проперти.  classpath: - значит корневая папка src/...
public interface ProjectConfig extends Config{

    @DefaultValue("sandbox")
    String env();

    @Key("${env}.host")
    String host();



    @Key("servers.${env}.hostname")
    String hostname();

    @Key("servers.${env}.port")
    Integer port();

    @Key("servers.${env}.user")
    String user();

    @Key("servers.${env}.password")
    String password();



}
