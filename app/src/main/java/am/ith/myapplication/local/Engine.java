package am.ith.myapplication.local;

import java.util.List;

import am.ith.myapplication.model.AppResponse;

public class Engine {
    private static Engine engine=null;
    private List<AppResponse.Metadatum> appResponse;

    public List<AppResponse.Metadatum> getAppResponse() {
        return appResponse;
    }

    public void setAppResponse(List<AppResponse.Metadatum> appResponse) {
        this.appResponse = appResponse;
    }

    private Engine() {
    }
    public static Engine getInstance(){
        return (engine==null)?engine=new Engine():engine;
    }
}

