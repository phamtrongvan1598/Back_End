package com.strongman.ssm.controller;

import com.strongman.ssm.model.Notes;
import com.strongman.ssm.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class NotesController {
    @Autowired
    private NotesService notesService;

    @GetMapping("/notess1")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Notes> notess = notesService.findAll();
        modelAndView.addObject("notess", notess);
        return modelAndView;
    }
    //-------------------Retrieve All Notes--------------------------------------------------------

    @RequestMapping(value = "/notess/", method = RequestMethod.GET)
    public ResponseEntity<List<Notes>> listAllNotess() {
        List<Notes> notess = notesService.findAll();
        if (notess.isEmpty()) {
            return new ResponseEntity<List<Notes>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Notes>>(notess, HttpStatus.OK);
    }

    //-------------------Retrieve Single Customer--------------------------------------------------------

    @RequestMapping(value = "/notess/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notes> getCustomer(@PathVariable("id") long id) {
        System.out.println("Fetching Notes with id " + id);
        Notes notes = notesService.findById(id);
        if (notes == null) {
            System.out.println("Notes with id " + id + " not found");
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Notes>(notes, HttpStatus.OK);
    }
    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/notess/", method = RequestMethod.POST)
    public ResponseEntity<Void> createNotes(@RequestBody Notes notes, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Notes " + notes.getTitle());
        notesService.save(notes);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/notess/{id}").buildAndExpand(notes.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Customer --------------------------------------------------------

    @RequestMapping(value = "/notesss/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Notes> updateCustomer(@PathVariable("id") long id, @RequestBody Notes notes) {
        System.out.println("Updating Notes " + id);

        Notes currentNotes = notesService.findById(id);

        if (currentNotes == null) {
            System.out.println("Notes with id " + id + " not found");
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }

        currentNotes.setTitle(notes.getTitle());
        currentNotes.setTitle(notes.getContent());
        currentNotes.setId(notes.getId());

        notesService.save(currentNotes);
        return new ResponseEntity<Notes>(currentNotes, HttpStatus.OK);
    }

}
