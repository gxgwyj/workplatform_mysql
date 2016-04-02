package com.junyang.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ShowMsg {
    public String smg(@WebParam(name = "name")String name);
}
