package com.amela.service;

import com.amela.model.Note;
import com.amela.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NoteService implements INoteService{
@Autowired
private INoteRepository repository;

    @Override
    public Page<Note> findByTitle(String title ,Pageable pageable) {



        return this.repository.findByTitle("%"+ title +"%",pageable);
    }

    @Override
    public Page<Note> findByNoteType(Integer typeID, Pageable pageable) {
        return repository.findByNoteType(typeID,pageable);
    }

    @Override
    public  Iterable<Note> findAll() {
        return null;
    }

    @Override
    public Optional<Note> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void save(Note note) {
        repository.save(note);
    }


    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Note> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }





}
