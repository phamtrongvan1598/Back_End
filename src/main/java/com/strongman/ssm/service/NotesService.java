package com.strongman.ssm.service;

import com.strongman.ssm.model.Notes;

import java.util.List;

public interface NotesService {
    List<Notes> findAll();
    Notes findById(Long id);

    void save(Notes notes);
}
