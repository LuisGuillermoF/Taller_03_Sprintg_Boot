package com.riwi.Taller_01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riwi.Taller_01.entity.Tarea;
import com.riwi.Taller_01.service.TareaService;

@Controller
@RequestMapping("/")
public class TareaController {

    @Autowired
    private TareaService objTareaService;

    @GetMapping
    public String mostrarTarea(Model objModel,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Tarea> listTareas = this.objTareaService.finAllPaginate(page - 1, size);

        objModel.addAttribute("listTareas", listTareas);
        objModel.addAttribute("currentPage", page);
        objModel.addAttribute("totalPage", listTareas.getTotalPages());

        return "viewTarea";
    }

    @GetMapping("/from")
    public String showFormTarea(Model model){
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("action", "/create-tarea");

        return "viewForm";
    }

    @PostMapping("create-tarea")
    public String createTarea(@ModelAttribute Tarea objTarea){
        this.objTareaService.create(objTarea);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTarea(@PathVariable Long id){
        this.objTareaService.delete(id);

        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateTarea (@PathVariable Long id,Model model){
        Tarea objTarea = this.objTareaService.findById(id);
        model.addAttribute("tarea", objTarea);
        model.addAttribute("action", "/edit/" + id);

        return "viewForm";
    }

    @PostMapping("/edit/{id}")
    public String updateTarea(@PathVariable Long id,@ModelAttribute Tarea obTarea){
        this.objTareaService.update(id, obTarea);

        return "redirect:/";
    }
}