package ru.vldf.sportsportal.controller.user.specialized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.dto.lease.PlaygroundDTO;
import ru.vldf.sportsportal.dto.tourney.*;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.LeaseService;
import ru.vldf.sportsportal.service.user.specialized.TourneyUserService;

import java.util.List;

@Controller
public class TourneyUserController {

    private AuthService authService;
    private TourneyUserService userTourneyService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserTourneyService(TourneyUserService userTourneyService) {
        this.userTourneyService = userTourneyService;
    }


    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }


    @GetMapping(value = {"/pp/tourney"})
    public String toTourneyMenu(ModelMap map) {
//            TODO: add current composition (participating in the tournament)!
        map.addAttribute("teamList", userTourneyService.getTeamList());
        return "user/tourney/menu-tourney";
    }


    @GetMapping(value = {"/pp/tourney/team{id}"})
    public String toTeamPage(@PathVariable("id") int id, ModelMap map) {
        TeamDTO teamDTO = userTourneyService.getTeamSafely(id);
        if (teamDTO == null) return "redirect:/xxx" + id; //not user's team

        TeamStatusDTO status = teamDTO.getStatus();
        String code = status.getCode();

        map.addAttribute("teamDTO", teamDTO);
        if (code.equals("TEAM_UNCONFIRMED")) return "user/tourney/page-team-status-unconfirmed";
        if (code.equals("TEAM_REJECTED")) return "user/tourney/page-team-status-rejected";

        map.addAttribute("compositionList", userTourneyService.getCompositionList(teamDTO));
        if (code.equals("TEAM_CONFIRMED")) return "user/tourney/page-team-status-confirmed";

        return "redirect:/xxx{id}";
    }

    private LeaseService leaseService;

    @Autowired
    public void setLeaseService(LeaseService leaseService) {
        this.leaseService = leaseService;
    }

    @GetMapping(value = {"/pp/tourney/composition{id}"})
    public String toRecruitingCompositionPage(@PathVariable("id") int id, ModelMap map) {
        CompositionDTO compositionDTO = userTourneyService.getCompositionSafely(id);
        if (compositionDTO == null) return "redirect:/xxx" + id; //not user's composition

        Integer maxSize = 18;
        Integer currentSize;

        List<PlayerDTO> playerDTOList = userTourneyService.getPlayerList(compositionDTO);
        if (playerDTOList != null) currentSize = playerDTOList.size();
        else currentSize = 0;

        char[] chars;

        Integer maxImp = 4;
        Integer currentImp = 0;

        chars = compositionDTO.getTimegrid().toCharArray();
        String[] timegrid = new String[10];

        char tmp;
        for (int i = 0; i < 10; i++) {
            tmp = chars[i];

            if (tmp == 'N') currentImp++;
            timegrid[i] = ("" + chars[i]);
        }

        TourDTO tourDTO = userTourneyService.getNextTour(compositionDTO.getTourney());
        GameDTO gameDTO = userTourneyService.getRival(id, tourDTO.getId());
        CompositionDTO rivalDTO = null;
        String[] rivalgrid = null;
        if (gameDTO != null) {
            if (gameDTO.getRed().getId() == id) rivalDTO = gameDTO.getBlue();
            if (gameDTO.getBlue().getId() == id) rivalDTO = gameDTO.getRed();

            chars = rivalDTO.getTimegrid().toCharArray(); //TODO: ?
            rivalgrid = new String[10];
            for (int i = 0; i < 10; i++) rivalgrid[i] = ("" + chars[i]);
        }

        List<PlaygroundDTO> playgroundList = leaseService.getPlaygroundList();

        map
                .addAttribute("maxSize", maxSize)
                .addAttribute("currentSize", currentSize)

                .addAttribute("teamDTO", compositionDTO.getTeam())

                .addAttribute("tourDTO", tourDTO)
                .addAttribute("compositionDTO", compositionDTO)
                .addAttribute("rivalDTO", rivalDTO)

                .addAttribute("playgroundList", playgroundList)

                .addAttribute("timegrid", timegrid)
                .addAttribute("rivalgrid", rivalgrid)

                .addAttribute("impLimit", !(currentImp < maxImp))

                .addAttribute("playerDTO", new PlayerDTO())
                .addAttribute("currentPlayerDTOList", playerDTOList)

                .addAttribute("isFull", !(currentSize < maxSize));

        return "user/tourney/page-composition";
    }

    @PostMapping(value = {"/pp/tourney/composition{id}/pgconfirm"})
    public String confirmPlayground(@PathVariable("id") int id, @RequestParam(value = "pgID", required = false) Integer pgID) {
        userTourneyService.confirmPlayground(id, pgID);
        return "redirect:/pp/tourney/composition{id}";
    }


    @GetMapping(value = {"/pp/tourney/composition{compositionID}/time{time}/{choice}"})
    public String updateTimeGrid(
            @PathVariable("compositionID") int compositionID,
            @PathVariable("time") Integer time, @PathVariable("choice") Character choice
    ) {
//            TODO: optimize
        CompositionDTO compositionDTO = userTourneyService.getCompositionSafely(compositionID);
        if (compositionDTO == null) return "redirect:/500"; //not user's composition

        userTourneyService.timeChoice(compositionDTO, time, choice);
        return "redirect:/pp/tourney/composition{compositionID}";
    }


    @PostMapping(value = {"/pp/tourney/composition{id}/player-search"})
    public String searchPlayers(
            @PathVariable("id") int id, ModelMap map,
            @ModelAttribute(value = "playerDTO") PlayerDTO playerDTO
    ) {
        map.addAttribute("foundedPlayerDTOList", userTourneyService.getPlayerList(
                playerDTO.getName(),
                playerDTO.getSurname(),
                playerDTO.getPatronymic()
        ));

        return toRecruitingCompositionPage(id, map);
    }

    @GetMapping(value = {"/pp/tourney/composition{compositionID}/player{playerID}/add"})
    public String addPlayer(
            @PathVariable("compositionID") int compositionID,
            @PathVariable("playerID") int playerID
    ) {
//            TODO: optimize
        CompositionDTO compositionDTO = userTourneyService.getCompositionSafely(compositionID);
        if (compositionDTO == null) return "redirect:/500"; //not user's composition

        userTourneyService.addPlayerToComposition(compositionDTO.getId(), playerID);
        return "redirect:/pp/tourney/composition{compositionID}";
    }

    @GetMapping(value = {"/pp/tourney/composition{compositionID}/player{playerID}/delete"})
    public String deletePlayer(
            @PathVariable("compositionID") int compositionID,
            @PathVariable("playerID") int playerID
    ) {
//            TODO: optimize
        CompositionDTO compositionDTO = userTourneyService.getCompositionSafely(compositionID);
        if (compositionDTO == null) return "redirect:/500"; //not user's composition

        userTourneyService.deletePlayerFromComposition(compositionDTO.getId(), playerID);
        return "redirect:/pp/tourney/composition{compositionID}";
    }


    @GetMapping(value = {"/pp/tourney/create-team"})
    public String toCreateTeamForm(ModelMap map) {
        map.addAttribute("teamDTO", new TeamDTO());
        return "user/tourney/form-create-team";
    }

    @PostMapping(value = {"/pp/tourney/create-team"})
    public String createTeam(@ModelAttribute(value="teamDTO") TeamDTO teamDTO) {
        userTourneyService.createTeam(teamDTO);
        return "redirect:/pp/tourney";
    }

}
