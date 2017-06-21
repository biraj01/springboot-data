package com.biraj.springmysql;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biraj.springmysql.todo.Todo;
import com.biraj.springmysql.todo.TodoRepository;


@Controller
public class MainController {
  
  private static final Logger log = LoggerFactory.getLogger(MainController.class);

  @Autowired
  private UserRepository userRepository;
  
  @Autowired
  private TodoRepository todorepository;


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
  
  @GetMapping(path="/todos")
  public String listTodos(Model model){
    model.addAttribute("todo", new Todo());
    return "test";
  }
  
  
  @PostMapping(path="/todos")
  public String saveTodos(@ModelAttribute Todo todo){
    todorepository.save(todo);
    //Iterable<Todo> todos = todorepository.findAll();
    return "test";
  }
 
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
   // List<User> userlist =  new ArrayList(user);
    //model.put("liste", userlist);
    log.info("all users list");
    return "listofuser";
  }
  
  
  @GetMapping(path="/login")
  public String login(Model model){
	  model.addAttribute("user", new User());
   return "login"; 
  }
  
  @PostMapping(path="/login")
  public String checklogin(@ModelAttribute User user){
	  log.info(user.toString());
	  //if(userRepository.exists(new Long(231321321))){
		//  return "redirect:/all";
	 // }
	  //check login details with the saved user, if user is in databank show o
	  return "redirect:/all";
  }
  
  @PostMapping(path="/all")
  public String listalluser(){ 
   return "listofuser"; 
  }
  
  @GetMapping(path="/register")
  public String registeruser(Model model){
	  model.addAttribute("user", new User());
	  return "register";
	  
  }
  
  @PostMapping(path="register")
  public String saveuserdata(@ModelAttribute User user ,  @RequestParam("file") MultipartFile file){
	  
	  //validate user data, send error if data is not correct then to register success page
	  log.info(user.toString());
	  log.info(file.getOriginalFilename());
	  try {
      file.transferTo(new File("C:\\Users\\bdhungel\\eclipseinstallation-Master2\\ws\\spring-mysql\\src\\main\\resources\\static\\images\\bewertung.png"));
    } catch (IllegalStateException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
	  userRepository.save(user);
	  //User userfromRepo = userRepository.findOneByName(user.getName());
	  return "redirect:/welcome";
	  
  }
  
  @GetMapping(path="/welcome")
  public String welcome(Model model){ 
    model.addAttribute("user", userRepository.findOneByName("biraj"));
    return "welcome";
  }
  

 @RequestMapping(path="/logout")
 public String logout(){
   return "redirect:/login?logout";
 }
 
 @GetMapping(path="/uploadfile")
 public String uplodefile(){
	 
	 return "uploadfile";
 }
 
 @PostMapping(path="/uploadfile")
 public String uploadSucess(@RequestParam("file") MultipartFile file, RedirectAttributes redirectattributes){
	if(file.isEmpty()){
		redirectattributes.addFlashAttribute("message", "please select a file to upload");
	}else{
		redirectattributes.addFlashAttribute("message", "file sucessfullt uploded");
	}
	 return "redirect:/uploadsuccess";
 }
 
 @GetMapping("/uploadsuccess")
 public String uploadStatus() {
     return "uploadsuccess";
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
     //SecurityContextHolder.getContext().setAuthentication(new GithubAuthentication());
   }
   
   return "redirect:/all";
 }
 
  
  
  

  
}
