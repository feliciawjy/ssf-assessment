package sg.edu.nus.iss.ibfb4ssfassessment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.ibfb4ssfassessment.model.Login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    // TODO: Task 6
    @GetMapping(path = { "/", "/index.html", "/login" })
    public String login(Model model) {
        model.addAttribute("login", new Login());
        return "view0";

    }

    // TODO: Task 7
    @PostMapping(path = "/login")
    public ModelAndView processLogin(HttpSession session, @ModelAttribute("login") @Valid Login login,
            BindingResult bindings) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(login);
        if (bindings.hasErrors()) {
            mav.addObject(login);
            mav.setViewName("view0");
            // System.out.println("Global error: " + bindings.getGlobalErrors());
            // System.out.println("Field error:" + bindings.getFieldErrors());
        } else {
            mav.setViewName("view1");
            session.setAttribute("login", login);
            // System.out.println(login.toString());
        }
        return mav;
    }

    // For the logout button shown on View 2
    // On logout, session should be cleared
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/login";

    }

}
