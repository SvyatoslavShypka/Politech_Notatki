package eu.dar3.notatki.controller;

import eu.dar3.notatki.dto.NoteDto;
import eu.dar3.notatki.entity.Note;
import eu.dar3.notatki.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/note")
@Controller
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @Secured({"ADMIN", "SUPER_ADMIN"})
    @GetMapping("/edit")
    public String showAddNoteForm(Note note) {
        return "add-note";
    }

    @Secured({"ADMIN", "SUPER_ADMIN"})
    @PostMapping("/edit")
    public String addNote(Note note, BindingResult result) {
        if (result.hasErrors()) {
            return "add-note";
        }
        noteService.add(note);
        return "redirect:/";
    }

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/list")
    public String showAllNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "index";
    }

    @Secured({"USER", "ADMIN", "SUPER_ADMIN"})
    @GetMapping("/list-ro")
    public String showAllNotesRO(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "index_user";
    }

    @Secured({"ADMIN", "SUPER_ADMIN"})
    @GetMapping("/list-rw")
    public String showAllNotesRW(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "index_rw";
    }

    @Secured({"ADMIN", "SUPER_ADMIN"})
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        NoteDto note = noteService.getById(id);

        model.addAttribute("note", note);
        return "update-note";
    }

    @Secured({"ADMIN", "SUPER_ADMIN"})
    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable("id") long id, Note note, BindingResult result) {
        if (result.hasErrors()) {
            note.setId(id);
            return "update-note";
        }
        noteService.update(note);
        return "redirect:/";
    }

    @Secured({"SUPER_ADMIN"})
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
}
