package com.biraj.springmysql.todo;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="todo")
public class TodoController {
  
  @Autowired
  private TodoRepository repository;
  
  @GetMapping(path="addtodo")
  public String addTodo(Model model){   
    model.addAttribute("emptytodo", new Todo());
    return "todo";
  }
  
  
  
  @PostMapping(path="addtodo")
    public String saveTodo(@ModelAttribute Todo todo){
    repository.save(todo);
    return "addnew";
  }
  
  @GetMapping(path="all")
  public String getAllTodo(Map<String , Object>  map){
    Iterable<Todo> it = repository.findAll();
    List<Todo> todolist = Lists.newArrayList(it);
    map.put("todos", todolist);
    return "alltodos";
  }
  

}
