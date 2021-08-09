package com.amela.repository;

import com.amela.model.Note;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

;

@Repository
public interface INoteRepository extends PagingAndSortingRepository<Note,Integer> {
    @Query (value = "select * from note  where title like :title",nativeQuery = true)
    Page<Note> findByTitle(@Param("title") String title, Pageable pageable);

    Page<Note> findAll(  Pageable pageable);


    // Đây là Native SQL
    @Query("SELECT c FROM Note c WHERE c.noteType.id = :typeID")
  Page<Note> findByNoteType(@Param("typeID") Integer typeID,Pageable pageable);


}
