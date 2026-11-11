package jp.ac.ccmc.festival_entry.controller;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.ac.ccmc.festival_entry.model.Performer;
import jp.ac.ccmc.festival_entry.repository.PerformerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/performer")
public class PerformerController {

    private final PerformerRepository repository;

    @GetMapping({"", "/", "/list"})
    public String showList(Model model) {
        model.addAttribute("performers", repository.findAll());
        return "performer/list";
    }

    @GetMapping("/add")
    public String addPerformer(@ModelAttribute Performer performer) {
        return "performer/form";
    }

    @PostMapping("/process")
    public String process(
            @Validated @ModelAttribute Performer performer,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "performer/form";
        }
        boolean isNew = performer.getId() == null;
        repository.save(performer);
        redirectAttributes.addFlashAttribute("message",
                isNew ? "参加者を登録しました。" : "参加者情報を更新しました。");
        return "redirect:/performer/list";
    }

    @GetMapping("/edit")
    public String editPerformer(@RequestParam("id") Long id, Model model) {
        Performer performer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("参加者が見つかりません: " + id));
        model.addAttribute("performer", performer);
        return "performer/form";
    }

    @GetMapping("/delete")
    public String confirmDelete(@RequestParam("id") Long id, Model model) {
        Performer performer = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("参加者が見つかりません: " + id));
        model.addAttribute("performer", performer);
        return "performer/delete";
    }

    @PostMapping("/delete")
    public String deletePerformer(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("参加者が見つかりません: " + id);
        }
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "参加者を削除しました。");
        return "redirect:/performer/list";
    }

    @ExceptionHandler(Exception.class)
    public String error(Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "performer/error";
    }
}
