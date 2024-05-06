package com.cucumber.utility;

import java.util.HashMap;
import java.util.Map;

import com.cucumber.enums.Context;

public class ScenarioContext {
	
	/*private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}*/
	
	private  Map<String, Object> scenarioContext;
	 
    public ScenarioContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(Context key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(Context key){
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(Context key){
        return scenarioContext.containsKey(key.toString());
    }

}
