package net.therap.filter;

import net.therap.dao.GameDao;
import net.therap.domain.Game;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
<<<<<<< HEAD
 * user: pritom
=======
 * User: pritom
>>>>>>> 958d29b8c056bfe7629bdb3f882003e9797e4933
 * Date: 6/10/12
 * Time: 11:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopGamesFilter extends GenericFilterBean{

    GameDao gameDao;

    public GameDao getGameDao() {
        return gameDao;
    }

    public void setGameDao(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();

        List<Game> topRankedGames = gameDao.getTopGames();

        session.setAttribute("topGames", topRankedGames);

        filterChain.doFilter(servletRequest,servletResponse);

    }
}
