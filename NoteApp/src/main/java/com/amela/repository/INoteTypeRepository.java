package com.amela.repository;

import com.amela.model.NoteType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteTypeRepository extends PagingAndSortingRepository<NoteType,Integer> {

}
