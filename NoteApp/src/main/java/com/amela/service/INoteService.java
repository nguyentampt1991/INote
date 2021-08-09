package com.amela.service;

import com.amela.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface INoteService extends IGeneralService<Note> {
    Page<Note> findAll(Pageable pageable);

    Page<Note> findByTitle(String title, Pageable pageable);

    Page<Note> findByNoteType(Integer typeID,Pageable pageable);

   // Page<Note> findAll(int pageNo,int pageSize );

}
