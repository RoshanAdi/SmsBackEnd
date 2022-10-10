package com.se.smsbackend.Site;

import javax.servlet.http.HttpServletRequest;

public class Utility {

    public static String getSiteURL(HttpServletRequest request){
        System.out.println("getting site url in utility");
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(),"");

    }
}
