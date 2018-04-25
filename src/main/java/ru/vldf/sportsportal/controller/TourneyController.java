package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.dto.tourney.CompositionStatisticDTO;
import ru.vldf.sportsportal.dto.tourney.GameDTO;
import ru.vldf.sportsportal.dto.tourney.TourDTO;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.TourneyService;

import java.util.List;

@Controller
public class TourneyController {
    private AuthService authService;
    private TourneyService tourneyService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setTourneyService(TourneyService tourneyService) {
        this.tourneyService = tourneyService;
    }


    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }


    @GetMapping(value = {"/tourney"})
    public String toCoverPage() {
        Integer tourneyID = tourneyService.getFirstTourneyID();
        return "redirect:/tourney" + tourneyID;
    }


    @GetMapping(value = {"/tourney{tourneyID}"})
    public String toTourneyPage(@PathVariable("tourneyID") int tourneyID) {
        Integer tourID = tourneyService.getFirstTourID(tourneyID);
        return "redirect:/tourney{tourneyID}/tour" + tourID;
    }

    @GetMapping(value = {"/tourney{tourneyID}/tour{tourID}"})
    public String toTourneyPage(@PathVariable("tourneyID") int tourneyID, @PathVariable("tourID") int tourID, ModelMap map) {
        TourneyDTO tourneyCur = tourneyService.getTourney(tourneyID);
        map.addAttribute("tourneyCur", tourneyCur);

        List<TourneyDTO> tourneyList = tourneyService.getTourneyList();
        map.addAttribute("tourneyList", tourneyList);

        List<TourDTO> tourList = tourneyService.getTourList(tourneyID);
        map.addAttribute("tourList", tourList);

        List<GameDTO> gameList = tourneyService.getGameList(tourID);
        map.addAttribute("gameList", gameList);

        List<Integer[]> matches = tourneyService.getResults(gameList);
        map.addAttribute("matchList", matches);

        return "tourney/cover-tourney-score";
    }

    @GetMapping(value = {"/tourney{tourneyID}/table"})
    public String toTourneyTablePage(@PathVariable("tourneyID") int tourneyID, ModelMap map) {
        TourneyDTO tourneyCur = tourneyService.getTourney(tourneyID);
        map.addAttribute("tourneyCur", tourneyCur);

        List<TourneyDTO> tourneyList = tourneyService.getTourneyList();
        map.addAttribute("tourneyList", tourneyList);

        List<CompositionStatisticDTO> statList = tourneyService.getStatistics(tourneyCur);
        map.addAttribute("statList", statList);

        return "tourney/cover-tourney-table";
    }
}