package com.biraj.springmysql;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {
  
  private static final Logger log = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private UserRepository userRepository;

//  @GetMapping(path="/add") // Map ONLY GET Requests
//  public @ResponseBody String addNewUser (@RequestParam String name
//      , @RequestParam String email) {
//    // @ResponseBody means the returned String is the response, not a view name
//    // @RequestParam means it is a parameter from the GET or POST request
//
//    User n = new User();
//    n.setName(name);
//    n.setEmail(email);
//    userRepository.save(n);
//    return "Saved";
//  }
  
  @GetMapping(path="/adduser") // Map ONLY GET Requests
  public String addNewUser (Model model) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    model.addAttribute("user",new User());
    log.info("add new user page");
    return "adduser";
  }
  
 
  
  @PostMapping(path="/adduser") // Map ONLY Post Requests
  public String saveNewUser (@ModelAttribute User user) {
    userRepository.save(user);  
    log.info("new user added successfully");
    return "redirect:/demo/all";
  }
  
  @GetMapping(path="/all")
  public  String getAllUsers(Map<String, Object> model) {
    Iterable<User> user = userRepository.findAll();
    List<User> userlist =  Lists.newArrayList(user);
    model.put("liste", userlist);
    log.info("all users list");
    return "listofuser";
  }
  
  
  @GetMapping(path="/login")
  public String login(){ 
   return "login"; 
  }
  
  @PostMapping(path="/all")
  public String loginredirect(){ 
   return "listofuser"; 
  }
  

 @RequestMapping(path="/logout")
 public String logout(){
   return "redirect:/demo/login?logout";
 }
  
  
  

  
}
