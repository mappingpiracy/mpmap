package controllers;

import dao.EventMapper;
import dao.MybatisMapper;
import models.Country;
import org.apache.ibatis.session.SqlSession;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

public class Application extends Controller {

    public static Result index() {
       return ok(index.render());
    }
}
