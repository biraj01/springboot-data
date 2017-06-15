package com.biraj.springmysql;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import security.GithubAuthentication;

@Controller
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
    return "redirect:/all";
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
   return "redirect:/login?logout";
 }
 
 @Value("${client_id}")
 private String clientId;
 
 @Value("${github.authorize.url}")
 private String githubAuthorizeUrl;
 
 @Value("${github.token.url}")
 private String githubTokenUrl;

 @RequestMapping(value = "/github/authorize", method = RequestMethod.GET)
 public String redirectGithubLogin() {
   log.info(githubAuthorizeUrl);
   return "redirect:" + githubAuthorizeUrl;
 }
 
 @RequestMapping(value = "/github/token", method = RequestMethod.GET)
 public String loginWithToken(String code) {
   log.info("in token");

   if (code == null){
     return "redirect:/login";
   }
   RestTemplate restTemplate = new RestTemplate();
   String url = githubTokenUrl + "&code="+code;
   ResponseEntity<String> postForEntity = restTemplate.postForEntity(url, null, String.class);
   String responseText = postForEntity.getBody();
   
   if (responseText.matches("access_token=.+")){
     SecurityContextHolder.getContext().setAuthentication(new GithubAuthentication());
   }
   
   return "redirect:/all";
 }
 
  
  
  

  
}
