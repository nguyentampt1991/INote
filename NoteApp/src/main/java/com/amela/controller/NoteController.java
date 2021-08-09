package com.amela.controller;

import com.amela.model.Note;
import com.amela.model.NoteType;
import com.amela.service.INoteService;
import com.amela.service.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
public class NoteController {

    @Autowired
    private INoteService noteService;
    @Autowired
    private INoteTypeService noteTypeService;
    private int id;

    @ModelAttribute("types")
    public Iterable<NoteType> noteTypes() {
        return noteTypeService.findAll();
    }

    @RequestMapping("/list")
    public ModelAndView showList(@PageableDefault(value = 7) Pageable pageable,
                                 @RequestParam("search") Optional<String> search
                                 ) {

        Page<Note> notes;
         if (search.isPresent()) {
            notes = noteService.findByTitle(search.get(), pageable);
  } else {
            notes = noteService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes", notes);


        return modelAndView;

    }
    @PostMapping("/notes-by-type/{id}")
    public ModelAndView searchNoteTtype(@PageableDefault(value = 7) Pageable pageable,
                                 @RequestParam("id") Optional<Integer> noteType
    ) {

        Page<Note> notes;
        if (noteType.isPresent()) {
            notes = noteService.findByNoteType(noteType.get(), pageable);
        } else {
            notes = noteService.findAll(pageable);
        }

        ModelAndView modelAndView = new ModelAndView("/note/list");
        modelAndView.addObject("notes", notes);


        return modelAndView;

    }


    @RequestMapping("index")
    public String show() {
        return "/note/index";
    }

    @GetMapping("/create-note")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/note/create");
        modelAndView.addObject("notes", new Note());
        return modelAndView;
    }

    @PostMapping("/create-note")
    public ModelAndView saveNote(@Valid @ModelAttribute("notes") Note note, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/note/create");
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("/note/create");
            return modelAndView;
        }
        noteService.save(note);
        modelAndView.addObject("notes", new Note());
        modelAndView.addObject("message", "Thêm mới thành công");
        return modelAndView;
    }

    @GetMapping("/edit-note/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/note/edit");
            modelAndView.addObject("notes", note.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-note")
    public ModelAndView updateNote(@ModelAttribute("notes") Note note) {
        noteService.save(note);
        ModelAndView modelAndView = new ModelAndView("/note/edit");
        modelAndView.addObject("notes", note);
        modelAndView.addObject("message", "Note updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-note/{id}")
    public ModelAndView showDeleteForm(@PathVariable Integer id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/note/delete");
            modelAndView.addObject("notes", note.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }
    @GetMapping("/view-note/{id}")
    public ModelAndView showViewForm(@PathVariable Integer id) {
        Optional<Note> note = noteService.findById(id);
        if (note.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/note/view");
            modelAndView.addObject("note", note.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error-404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-note")
    public String deleteProvince(@ModelAttribute("notes") Note note) {
        noteService.remove(note.getId());
        return "redirect:list";
    }
}

