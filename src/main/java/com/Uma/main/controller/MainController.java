package com.Uma.main.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Uma.main.model.QuestionForm;
import com.Uma.main.model.Result;
import com.Uma.main.service.QuizService;

@Controller
public class MainController {

	@Autowired
	QuizService qService;

	Result result;
	Boolean submitted;

	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}

	@GetMapping("/")
	public String home() {
		return "index.html";
	}

	@PostMapping("/quiz")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
		if (username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}

		submitted = false;
		result = new Result();
		result.setUsername(username);

		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);

		return "quiz.html";
	}

	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
		if (!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}

		return "result.html";
	}

	@GetMapping("/score")
	public String score(Model m) {
		List<Result> sList = qService.getTopScore();
		m.addAttribute("sList", sList);

		return "scoreboard.html";
	}

}
