package com.naukma.practice.myPet.services;

import com.naukma.practice.myPet.db.UserRepository;
import com.naukma.practice.myPet.db.entity.User;
import com.naukma.practice.myPet.exceptions.InvalidDataException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AuthenticationService implements AuthenticationServiceInterface {
//
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final String LOGIN_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{8,32}$";
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public String loginUser(String login,
                            HttpServletRequest request) throws Exception {

        String forward = null;
        HttpSession session = request.getSession();
        HttpSession checkSession = request.getSession(false);
        String checkUserRole = (String) checkSession.getAttribute("userRole");

        if (checkUserRole != null) {
            throw new AuthenticationException("User is already signed in");
        }

        log.info("Request parameter: login --> " + login);

        User user;
        if (userRepository.findUserByLogin(login).isPresent()) {
            user = userRepository.findUserByLogin(login).get();
            log.trace("Found in DB: user --> " + user);
        } else {
            throw new InvalidDataException("Cannot find user with such login");
        }

        String userRole = user.getRole();
        if (userRole.equals("HOST")) {
            forward = "/host/info";
        } else if (userRole.equals("OWNER")) {
            forward = "/owner/info";
        }
        setUserToSession(session, user, userRole);

        return request.getContextPath() + forward;
    }

    @Override
    public void registrationUser(String login, String password, String email, String password_confirm,
                                 HttpServletRequest request) throws Exception {

        HttpSession checkSession = request.getSession(false);
        String checkUserRole = (String) checkSession.getAttribute("userRole");
        log.trace("Session attribute: userRole --> " + checkUserRole);

        if (checkUserRole != null)
            throw new AuthenticationException("User is already signed in");

        log.trace("Request parameter: login --> " + login);
        log.trace("Request parameter: password --> " + password);
        log.trace("Request parameter: email --> " + email);
        log.trace("Request parameter: password_confirm --> " + password_confirm);

//        if (!validateData(login, password, email, password_confirm))
//            throw new InvalidDataException("Input data is invalid or empty");

        User user;
        if (userRepository.findUserByLogin(login).isPresent()) {
            user = userRepository.findUserByLogin(login).get();
            log.trace("Found in DB: user --> " + user);
        }else{
            user = null;
        }

        if (user != null && !user.getEmail().equals(email)) {
            throw new InvalidDataException("User with this login is already registered");
        } else {
            String bcryptHashString = passwordEncoder.encode(password);
            User insertUser = User.createUser(login, email, bcryptHashString);
            User result = userRepository.save(insertUser);
            //TODO - add new owner or host to table
            System.out.println("result ->" + result);
        }
    }

    static void setUserToSession(HttpSession session, User insertUser, String userRole) {
        //TODO - save only user id in session (for next queries)
        session.setAttribute("user", insertUser);
        session.setAttribute("userRole", userRole);
        log.info("User " + insertUser + " logged as " + userRole.toLowerCase());
    }

    @Override
    public boolean validateData(String login, String password) {
        return login != null && password != null && !login.isEmpty() && !password.isEmpty();
    }

    @Override
    public boolean validateData(String login, String password, String email, String password_confirm) {
        if (!validateData(login, password) ||
                email == null || email.isEmpty()
                || password_confirm == null || password_confirm.isEmpty()) {
            return false;
        } else
            return (isValid(login, LOGIN_PATTERN) &&
                    isValid(password, PASSWORD_PATTERN)
                    && isValid(email, EMAIL_PATTERN));
    }

    private static boolean isValid(final String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
