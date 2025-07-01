package br.com.codebydias.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.codebydias.todolist.user.IUserRepostitory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepostitory userRepostitory;

    @Override
    protected void doFilterInternal(@org.springframework.lang.NonNull HttpServletRequest request,
            @org.springframework.lang.NonNull HttpServletResponse response,
            @org.springframework.lang.NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Validar se usuario esta resgistrado para conseguir criar um tarefa
        var servletPath = request.getServletPath();
        if (servletPath.startsWith("/tasks/") 

        // evitar usar equals 
        // || servletPath.equals("/tasks/listing") 
        // || servletPath.equals("/tasks/update/{id}") 
        )  {

            var authorization = request.getHeader("Authorization");
            System.out.println("Authorization = " + authorization);

            var passwordEncoded = authorization.substring("Basic".length()).trim();
            // System.out.println("passwordEncoded = " + passwordEncoded);

            byte[] authDecode = Base64.getDecoder().decode(passwordEncoded);
            var authString = new String(authDecode);
            System.out.println("authDecode = " + authDecode);
            System.out.println("authDecodeString = " + authString);

            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];
            System.out.println("username = " + username);
            System.out.println("password = " + password);

            var user = this.userRepostitory.findByUsername(username);
            if (user == null) {
                response.sendError(401, "Usuario não esta autorizado");
            } else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }

        } else {
            filterChain.doFilter(request, response);
        }

    }

}
