package controllers;

import dao.EventMapper;
import dao.MybatisMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;
import java.util.Map;

//import javax.inject.Inject;

public class Application extends Controller {

    public static Result index() {
        MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        EventMapper eventMapper = session.getMapper(EventMapper.class);

        List<Map<Integer, String>> countries = eventMapper.getCountries();


        return ok(index.render());
    }

    public static Result about() {
        return ok(about.render());
    }

    public static Result help() {
        return ok(help.render());
    }

}
