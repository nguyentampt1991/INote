package com.amela.service;

import com.amela.model.NoteType;
import com.amela.repository.INoteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteTypeService implements INoteTypeService{
    @Autowired
    private INoteTypeRepository noteTypeRepository;
    @Override
    public Iterable<NoteType> findAll() {
        return noteTypeRepository.findAll();
    }

    @Override
    public Optional<NoteType> findById(Integer id) {
        return noteTypeRepository.findById(id);
    }

    @Override
    public void save(NoteType noteType) {
        noteTypeRepository.save(noteType);
    }

    @Override
    public void remove(Integer id) {
        noteTypeRepository.deleteById(id);
    }

}
