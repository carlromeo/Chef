package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
public class HomeController {

    @Autowired
    ChefRepository chefRepository;

    @RequestMapping("/")
    public String listChefs(Model model){
        model.addAttribute("chefs", chefRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String chefForm(Model model){
        model.addAttribute("chef", new Chef());
        return "chefform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Chef chef,
                              BindingResult result){
        if (result.hasErrors()){
            return "chefform";
        }
        chefRepository.save(chef);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showChef(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("chef", chefRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateChef(@PathVariable("id") long id,
                             Model model){
        model.addAttribute("chef", chefRepository.findById(id).get());
        return "chefform";
    }
    @RequestMapping("/delete/{id}")
    public String delChef(@PathVariable("id") long id){
        chefRepository.deleteById(id);
        return "redirect:/";
    }
}


