package controllers;

import dao.EventMapper;
import dao.MybatisMapper;
import models.Country;
import org.apache.ibatis.session.SqlSession;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.List;

//import javax.inject.Inject;

public class Application extends Controller {

    public static Result index() {
       /* MybatisMapper mapper = new MybatisMapper();
        SqlSession session = mapper.getSession();
        EventMapper eventMapper = session.getMapper(EventMapper.class);

        List<Country> countries = eventMapper.getCountries();
         */

        return ok(index.render());
    }
}
