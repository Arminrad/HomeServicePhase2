package com.phase2.homeService.controller;

import com.phase2.homeService.entities.base.User;
import com.phase2.homeService.entities.email.ConfirmationToken;
import com.phase2.homeService.repository.ConfirmationTokenRepository;
import com.phase2.homeService.repository.UserRepository;
import com.phase2.homeService.service.implementations.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UserAccountController {

    private UserRepository userRepository;
    private ConfirmationTokenRepository confirmationTokenRepository;
    private EmailService emailService;

    public UserAccountController(UserRepository userRepository, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }



    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user)
    {

        User currentUser = userRepository.findUserByEmail(user.getEmail());
        if(currentUser != null)
        {
            modelAndView.addObject("message","This email already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("siavosh.arminrad@gmail.com");
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailService.sendEmail(mailMessage);

            modelAndView.addObject("emailId", user.getEmail());

            modelAndView.setViewName("successfulRegisteration");
        }

        return modelAndView;
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            User user = userRepository.findUserByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
