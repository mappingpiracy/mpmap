package controllers;

import org.apache.ibatis.mapping.Environment;
import play.*;
import play.mvc.*;

import views.html.*;

//import javax.inject.Inject;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    //@Inject
    //private Environment myBatisEnv;

    //public Result mybatistest() {
        //return ok("Your app is running with ds " + myBatisEnv.getDataSource());
    //}

    public static Result about() {
        return ok(about.render());
    }

    public static Result help() {
        return ok(help.render());
    }

}
