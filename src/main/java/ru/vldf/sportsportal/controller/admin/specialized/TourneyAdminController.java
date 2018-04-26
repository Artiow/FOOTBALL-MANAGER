package ru.vldf.sportsportal.controller.admin.specialized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.dto.tourney.*;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.admin.specialized.TourneyAdminService;
import ru.vldf.sportsportal.service.user.specialized.TourneyUserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TourneyAdminController {
    private AuthService authService;

    private TourneyUserService tourneyUserService;
    private TourneyAdminService tourneyAdminService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setTourneyUserService(TourneyUserService tourneyUserService) {
        this.tourneyUserService = tourneyUserService;
    }

    @Autowired
    public void setTourneyAdminService(TourneyAdminService tourneyAdminService) {
        this.tourneyAdminService = tourneyAdminService;
    }


    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }


    @GetMapping(value = {"/pp/admin/check-team"})
    public String toConfirmTeamPage(ModelMap map) {
        map.addAttribute("uTeams", tourneyAdminService.getUnconfirmedTeams());
        return "user/admin/page-check-team";
    }


    @GetMapping(value = {"/pp/admin/check-team/team{id}/confirm"})
    public String confirmTeam(@PathVariable("id") int id) {
        tourneyAdminService.confirmTeam(id);
        return "redirect:/pp/admin/check-team";
    }

    @GetMapping(value = {"/pp/admin/check-team/team{id}/reject"})
    public String rejectTeam(@PathVariable("id") int id) {
        tourneyAdminService.rejectTeam(id);
        return "redirect:/pp/admin/check-team";
    }

    @GetMapping(value = {"/pp/admin/check-team/team{id}/rename"})
    public String renameTeam(@PathVariable("id") int id, ModelMap map) {
        map.addAttribute("teamDTO", tourneyAdminService.getTeam(id));
        return "user/admin/form-rename-team";
    }

    @PostMapping(value = {"/pp/admin/check-team/team{id}/rename"})
    public String createTeam(@PathVariable("id") int id, @ModelAttribute(value = "teamDTO") TeamDTO teamDTO) {
        teamDTO.setId(id);
        tourneyAdminService.renameTeam(teamDTO);
        tourneyAdminService.confirmTeam(teamDTO.getId());
        return "redirect:/pp/admin/check-team";
    }


    @GetMapping(value = {"/pp/admin/tourney"})
    public String toTourneyCatalogPage(ModelMap map) {
        map.addAttribute("tourneyList", tourneyAdminService.getTourneyList());
        return "user/admin/page-tourney-catalog";
    }

    @GetMapping(value = {"/pp/admin/tourney/tourney{id}"})
    public String toTourneyPage(@PathVariable("id") int id, ModelMap map) {
        TourneyDTO tourneyDTO = tourneyAdminService.getTourney(id);

        List<CompositionDTO> compositionList = tourneyAdminService.getCompositionList(tourneyDTO);
        List<GameDTO> gameList = tourneyAdminService.getCurrentGameList(tourneyDTO);

        map
                .addAttribute("tourneyDTO", tourneyDTO)
                .addAttribute("compositionList", compositionList)
                .addAttribute("gameList", gameList);


        TourneyStatusDTO statusDTO = tourneyDTO.getStatus();
        String code = statusDTO.getCode();

        if (code.equals("TOURNEY_FORMED")) return "user/admin/page-tourney-status-formed";
        else if (code.equals("TOURNEY_ALREADY")) return "user/admin/page-tourney-status-ready";
        else return "redirect:/xxx{id}"; //TODO: to tourney page!
    }

    @GetMapping(value = {"/pp/admin/tourney/tourney{id}/protocol"})
    public String toProtocolAllPage(@PathVariable("id") int id, ModelMap map) {
        TourneyDTO tourneyDTO = tourneyAdminService.getTourney(id);
        List<GameDTO> gameList = tourneyAdminService.getNextGameList(tourneyDTO);

        List<List<PlayerDTO>> redPlayerLists = new ArrayList<List<PlayerDTO>>();
        List<List<PlayerDTO>> bluePlayerLists = new ArrayList<List<PlayerDTO>>();

        List<PlayerDTO> redPlayerList;
        List<PlayerDTO> bluePlayerList;
        for (GameDTO game : gameList) {
            redPlayerList = tourneyUserService.getPlayerList(game.getRed());
            bluePlayerList = tourneyUserService.getPlayerList(game.getBlue());

            redPlayerLists.add(redPlayerList);
            bluePlayerLists.add(bluePlayerList);
        }

        map
                .addAttribute("gameList", gameList)
                .addAttribute("redPlayerLists", redPlayerLists)
                .addAttribute("bluePlayerLists", bluePlayerLists);

        return "user/admin/page-tourney-protocol-all";
    }


    @GetMapping(value = {"/pp/admin/game{id}/result"})
    public String toGameResultForm(@PathVariable("id") int id, ModelMap map) {
        GameDTO gameDTO = tourneyAdminService.getGame(id);
        map.addAttribute("gameDTO", gameDTO);
        return "user/admin/form-result-game";
    }

    @PostMapping(value = {"/pp/admin/game{id}/result"})
    public String createResultGame(
            @PathVariable("id") int id,
            @RequestParam("redGoalNum") Integer redGoalNum,
            @RequestParam("blueGoalNum") Integer blueGoalNum
    ) {
        GameDTO gameDTO = tourneyAdminService.getGame(id);
        tourneyAdminService.createResultGame(gameDTO, redGoalNum, blueGoalNum);
        return "redirect:/pp/admin/tourney/tourney" + gameDTO.getTour().getTourney().getId();
    }


    @GetMapping(value = {"/pp/admin/tourney/tourney{id}/timegrid"})
    public String toTimegridPage(@PathVariable("id") int id, ModelMap map) {
        TourneyDTO tourneyDTO = tourneyAdminService.getTourney(id);
        TourDTO tourDTO = tourneyAdminService.getNextTour(tourneyDTO);

        List<GameDTO> games = tourneyAdminService.getNextGameList(tourneyDTO);
        List<String[]> timegrid = tourneyAdminService.updateTimegrid(games); //TODO: remove

        map
                .addAttribute("tourneyDTO", tourneyDTO)
                .addAttribute("gameList", games)
                .addAttribute("timegrid", timegrid);

        String code = tourDTO.getStatus().getCode();
        if (code.equals("TOUR_TIMING")) return "user/admin/page-tourney-timegrid";
        if (code.equals("TOUR_FIXED")) return "user/admin/page-tourney-timegrid-done";
        else return "redirect:/500";
    }

    @GetMapping(value = {"/pp/admin/tourney/tourney{id}/timeup"})
    public String timeup(@PathVariable("id") int id) {
        tourneyAdminService.startTourney(tourneyAdminService.getTourney(id));
        return "redirect:/pp/admin/tourney/tourney{id}";
    }

    @GetMapping(value = {"/pp/admin/game{id}/protocol"})
    public String toProtocolPage(@PathVariable("id") int id, ModelMap map) {
        GameDTO game = tourneyAdminService.getGame(id);

        List<PlayerDTO> redPlayerList = tourneyUserService.getPlayerList(game.getRed());
        List<PlayerDTO> bluePlayerList = tourneyUserService.getPlayerList(game.getBlue());

        map
                .addAttribute("tourneyDTO", game.getTour().getTourney())
                .addAttribute("redTeamDTO", game.getRed())
                .addAttribute("blueTeamDTO", game.getBlue())
                .addAttribute("redPlayerList", redPlayerList)
                .addAttribute("bluePlayerList", bluePlayerList);

        return "user/admin/page-tourney-protocol";
    }


    @GetMapping(value = {"/pp/admin/tourney/create-tourney"})
    public String toCreateTourneyForm(ModelMap map) {
        map.addAttribute("tourneyDTO", new TourneyDTO());
        return "user/admin/form-create-tourney";
    }

    @PostMapping(value = {"/pp/admin/tourney/create-tourney"})
    public String createTourney(@ModelAttribute(value = "tourneyDTO") TourneyDTO tourneyDTO) {
        tourneyAdminService.createTourney(tourneyDTO);
        return "redirect:/pp/admin/tourney";
    }


    @GetMapping(value = {"/pp/admin/tourney/tourney{id}/invite"})
    public String toTeamInviteForm(@PathVariable("id") int id, ModelMap map) {
        map //TODO: add search by name!
                .addAttribute("tourneyDTO", tourneyAdminService.getTourney(id))
                .addAttribute("teamList", tourneyAdminService.getTeamListLike(""));

        return "user/admin/form-invite-team";
    }

    @PostMapping(value = {"/pp/admin/tourney/tourney{tourneyID}/invite"})
    public String inviteTeam(@PathVariable("tourneyID") int tourneyID, @RequestParam("teamsID") List<Integer> teamsID) {
        tourneyAdminService.inviteTeamList(tourneyID, teamsID);
        return "redirect:/pp/admin/tourney/tourney" + tourneyID;
    }
}
