package com.strongman.ssm.service.impl;

import com.strongman.ssm.model.Notes;
import com.strongman.ssm.repository.NotesRepository;
import com.strongman.ssm.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public List<Notes> findAll() {
        return notesRepository.findAll();
    }

    @Override
    public Notes findById(Long id) {
        return notesRepository.findById(id);
    }

    @Override
    public void save(Notes notes) {
        notesRepository.save(notes);
    }


}
