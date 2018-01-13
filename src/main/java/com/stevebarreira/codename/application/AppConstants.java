package com.stevebarreira.codename.application;

public final class AppConstants {
	
	public static final String GAMECHANGE_SOCKET_INBOUND = "/gamechange/{id}/{roundNumber}";
	public static final String JS_ANTPATTERN = "/javascripts/**";
	public static final String CSS_ANTPATTERN = "/css/**";

	public static final String LOGOUT_URL = "/logout";
	public static final String LOGIN_URL = "/login";

	public static final String WS_ALLOW_ORIGIN = "*";
    public static final String WS_ENDPOINT = "/ws";
    public static final String WS_BROKER = "/topic";
    public static final String WS_DESTINATION ="/app";
}
