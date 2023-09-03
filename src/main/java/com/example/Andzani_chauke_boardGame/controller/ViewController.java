package com.example.Andzani_chauke_boardGame.controller;

import com.example.Andzani_chauke_boardGame.form.NameForm;
import com.example.Andzani_chauke_boardGame.model.Game;
import com.example.Andzani_chauke_boardGame.service.GameService;
import com.example.Andzani_chauke_boardGame.util.Constants;
import com.example.Andzani_chauke_boardGame.util.GreetingUtil;
import com.example.Andzani_chauke_boardGame.util.ModelAttributes;
import com.example.Andzani_chauke_boardGame.util.RequestRouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.UUID;

@Controller
public class ViewController {


    @GetMapping(RequestRouting.HOME)
    public String getGame(@Valid @ModelAttribute(ModelAttributes.NAME_FORM) NameForm name, BindingResult result) {
        if (result.hasErrors()) {
            return Constants.HOME;
        }

        return Constants.GAME_TEMPLATE;
    }

    /**
     * Action we execute after filling in Form
     *
     * @param name Form with player's names
     * @param result Any possible errors
     * @param model
     *
     * @return
     */
    @PostMapping(RequestRouting.GAME)
    public String getStart(@Valid @ModelAttribute(ModelAttributes.NAME_FORM) NameForm name, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return Constants.HOME;
        }


        model.addAttribute(ModelAttributes.GREETING, GreetingUtil.getGreeting());
        model.addAttribute(ModelAttributes.PLAYER_NAMES, name);

        return Constants.GAME_TEMPLATE;

    }

    /**
     * Returns index screen
     *
     * @param name Form with player's names
     * @param model
     * @return
     */
    @GetMapping(RequestRouting.INDEX)
    public String main( NameForm name,Model model) {

        model.addAttribute(ModelAttributes.NAME_FORM,name);
        return Constants.HOME;
    }


}
