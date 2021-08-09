package com.amela.controller;

import com.amela.model.NoteType;
import com.amela.service.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class NoteTypeController {
    @Autowired
    private INoteTypeService noteTypeService;

    @GetMapping("/types")
    public ModelAndView listProvinces() {
        Iterable<NoteType> noteTypes = noteTypeService.findAll();
        ModelAndView modelAndView = new ModelAndView("/notetype/list");
        modelAndView.addObject("types", noteTypes);
        return modelAndView;
    }

    @GetMapping("/create-type")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/notetype/create");
        modelAndView.addObject("type", new NoteType());
        return modelAndView;
    }

    @PostMapping("/create-type")
    public ModelAndView saveProvince(@ModelAttribute("type") NoteType noteType) {
        noteTypeService.save(noteType);

        ModelAndView modelAndView = new ModelAndView("/notetype/create");
        modelAndView.addObject("type", new NoteType());
        modelAndView.addObject("message", "New notetype created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-type/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<NoteType> noteType = noteTypeService.findById(id);
        if (noteType.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/notetype/edit");
            modelAndView.addObject("type", noteType.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-type")
    public ModelAndView updateProvince(@ModelAttribute("type") NoteType noteType) {
        noteTypeService.save(noteType);
        ModelAndView modelAndView = new ModelAndView("/notetype/edit");
        modelAndView.addObject("type", noteType);
        modelAndView.addObject("message", "Province updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-type/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<NoteType> noteType = noteTypeService.findById(id);
        if (noteType.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/notetype/delete");
            modelAndView.addObject("type", noteType.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-type")
    public String deleteProvince(@ModelAttribute("type") NoteType noteType) {
        noteTypeService.remove(noteType.getId());
        return "redirect:types";
    }
}
