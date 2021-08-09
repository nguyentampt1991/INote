package com.amela.formatter;

import com.amela.model.NoteType;
import com.amela.service.INoteTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class NoteTypeFormatter implements Formatter<NoteType> {

    private INoteTypeService noteTypeService;
    @Autowired
    public NoteTypeFormatter(INoteTypeService noteTypeService){
        this.noteTypeService=noteTypeService;
    }

    @Override
    public NoteType parse(String text, Locale locale) throws ParseException {
        Optional<NoteType> noteTypeOptional = noteTypeService.findById(Integer.parseInt(text));
        return noteTypeOptional.orElse(null);
    }

    @Override
    public String print(NoteType object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
