package com.IO2.Gradebook.controllers;

import com.IO2.Gradebook.repositories.MiscRepository;
import com.IO2.Gradebook.models.Dictionary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class MiscController {

    private final MiscRepository miscRepository;

    public MiscController(MiscRepository miscRepository) {
        this.miscRepository = miscRepository;
    }

    @GetMapping("/subjects")
    public List<Dictionary> getAllSubjects() {
        return miscRepository.getAllSubjects();
    }

    @GetMapping("/classes")
    public List<Dictionary> getSubjectGradesInClass() {
        return miscRepository.getAllClasses();
    }
}