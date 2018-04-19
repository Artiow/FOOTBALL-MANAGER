package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.tourney.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.service.AdminService;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

//    TODO: tmp solution! move to 'TOURNEY'!
    private UserService.UserTourneyService userTourneyService;

//    TODO: tmp solution! move to 'TOURNEY'!
    @Autowired
    public void setUserTourneyService(UserService.UserTourneyService userTourneyService) {
        this.userTourneyService = userTourneyService;
    }


    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }

    @GetMapping(value = {"/personalpage"})
    public String toPersonalPage(ModelMap map) {
        map.addAttribute("teamList", userTourneyService.getTeamList()); //TODO: tmp solution! remove this!
        return "user/personalpage";
    }


    @Controller //ADMIN
    public class UserAdminController {

        private AdminService.AdminUserService userService;
        private AdminService.AdminTourneyService tourneyService;

        @Autowired
        public void setUserService(AdminService.AdminUserService userService) {
            this.userService = userService;
        }

        @Autowired
        public void setTourneyService(AdminService.AdminTourneyService tourneyService) {
            this.tourneyService = tourneyService;
        }


        @ModelAttribute("authUser")
        public UserDTO getAuthUser() {
            return UserController.this.getAuthUser();
        }

        @GetMapping(value = {"/pp/admin"})
        public String toAdminMenu(ModelMap map) {
            map
                    .addAttribute("uUsersNum", userService.getUnconfirmedUsersNum())
                    .addAttribute("uTeamsNum", tourneyService.getUnconfirmedTeamsNum());

            return "user/admin/menu-admin";
        }

//    ----------------------------------------------------------------------------------
//    --- USER

        @GetMapping(value = {"/pp/admin/check-user"})
        public String toCheckUserPage(ModelMap map) {
            UserDTO user = userService.getFirstUnconfirmedUser(); //TODO: lol wtf dude? add pagination!
            if (user == null) return "redirect:/pp/admin";

            List<PlayerDTO> duplicates
                    = userService.getDuplicates(user);
            map
                    .addAttribute("uUser", user)
                    .addAttribute("duplicates", duplicates);

            return "user/admin/page-check-user";
        }

        @GetMapping(value = {"/pp/admin/check-user/user{id}"})
        public String toCheckUserPage(@PathVariable("id") int id, ModelMap map) {
            UserDTO user = userService.getUser(id);
            if (user == null) return "redirect:/404";

            List<PlayerDTO> duplicates
                    = userService.getDuplicates(user);
            map
                    .addAttribute("uUser", user)
                    .addAttribute("duplicates", duplicates);

            return "user/admin/page-check-user";
        }


        @GetMapping(value = {"/pp/admin/check-user/user{id}/confirm"})
        public String confirmUser(@PathVariable("id") int id) {
            userService.confirmUser(id);
            return "redirect:/pp/admin/check-user";
        }

        @GetMapping(value = {"/pp/admin/check-user/user{id}/reject"})
        public String rejectUser(@PathVariable("id") int id) {
            userService.rejectUser(id);
            return "redirect:/pp/admin/check-user";
        }

        @GetMapping(value = {"/pp/admin/check-user/duplicate{id}/delete"})
        public String deleteDuplicate(@PathVariable("id") int id) {
            userService.deleteDuplicate(id);
            return "redirect:/pp/admin/check-user";
        }

        @GetMapping(value = "/pp/admin/check-user/user{userID}/bind/duplicate{duplicateID}")
        public String bindDuplicate(@PathVariable("userID") int userID, @PathVariable("duplicateID") int duplicateID) {
            userService.bindUser(userID, duplicateID);
            return "redirect:/pp/admin/check-user";
        }

//    ----------------------------------------------------------------------------------
//    --- TOURNEY

        @GetMapping(value = {"/pp/admin/check-team"})
        public String toConfirmTeamPage(ModelMap map) {
            map.addAttribute("uTeams", tourneyService.getUnconfirmedTeams());
            return "user/admin/page-check-team";
        }


        @GetMapping(value = {"/pp/admin/check-team/team{id}/confirm"})
        public String confirmTeam(@PathVariable("id") int id) {
            tourneyService.confirmTeam(id);
            return "redirect:/pp/admin/check-team";
        }

        @GetMapping(value = {"/pp/admin/check-team/team{id}/reject"})
        public String rejectTeam(@PathVariable("id") int id) {
            tourneyService.rejectTeam(id);
            return "redirect:/pp/admin/check-team";
        }

        @GetMapping(value = {"/pp/admin/check-team/team{id}/rename"})
        public String renameTeam(@PathVariable("id") int id, ModelMap map) {
            map.addAttribute("teamDTO", tourneyService.getTeam(id));
            return "user/admin/form-rename-team";
        }

        @PostMapping(value = {"/pp/admin/check-team/team{id}/rename"})
        public String createTeam(@PathVariable("id") int id, @ModelAttribute(value="teamDTO") TeamDTO teamDTO) {
            teamDTO.setId(id);
            tourneyService.renameTeam(teamDTO);
            tourneyService.confirmTeam(teamDTO.getId());
            return "redirect:/pp/admin/check-team";
        }


        @GetMapping(value = {"/pp/admin/tourney"})
        public String toTourneyCatalogPage(ModelMap map) {
            map.addAttribute("tourneyList", tourneyService.getTourneyList());
            return "user/admin/page-tourney-catalog";
        }

        @GetMapping(value = {"/pp/admin/tourney/tourney{id}"})
        public String toTourneyPage(@PathVariable("id") int id, ModelMap map) {
            TourneyDTO tourneyDTO = tourneyService.getTourney(id);

            map
                    .addAttribute("tourneyDTO", tourneyDTO)
                    .addAttribute("compositionList", tourneyService.getTeamCompositions(tourneyDTO));

            int status = tourneyDTO.getStatus().getId();
            switch (status) {
                case 1: return "user/admin/page-tourney-status-formed"; //TOURNEY_FORMED
                case 2: return "user/admin/page-tourney-status-ready"; //TOURNEY_READY

                default: return "redirect:/xxx{id}"; //TODO: to tourney page!
            }
        }


        @GetMapping(value = {"/pp/admin/tourney/create-tourney"})
        public String toCreateTourneyForm(ModelMap map) {
            map.addAttribute("tourneyDTO", new TourneyDTO());
            return "user/admin/form-create-tourney";
        }

        @PostMapping(value = {"/pp/admin/tourney/create-tourney"})
        public String createTourney(@ModelAttribute(value="tourneyDTO") TourneyDTO tourneyDTO) {
            tourneyService.createTourney(tourneyDTO);
            return "redirect:/pp/admin/tourney";
        }


        @GetMapping(value = {"/pp/admin/tourney/tourney{id}/invite"})
        public String toTeamInviteForm(@PathVariable("id") int id, ModelMap map) {
            map //TODO: add search by name!
                    .addAttribute("tourneyDTO", tourneyService.getTourney(id))
                    .addAttribute("teamList", tourneyService.getTeamsLike(""));

            return "user/admin/form-invite-team";
        }

        @PostMapping(value = {"/pp/admin/tourney/tourney{tourneyID}/invite"})
        public String inviteTeam(@PathVariable("tourneyID") int tourneyID, @RequestParam("teamsID") List<Integer> teamsID) {
            tourneyService.inviteTeams(tourneyID, teamsID);
            return "redirect:/pp/admin/tourney/tourney" + tourneyID;
        }

    }


    @Controller //TOURNEY
    public class UserTourneyController {

//
//        TODO: put UserTourneyService here!
//


        @ModelAttribute("authUser")
        public UserDTO getAuthUser() {
            return UserController.this.getAuthUser();
        }

        @GetMapping(value = {"/pp/tourney"})
        public String toTourneyMenu(ModelMap map) {
//            TODO: add current composition (participating in the tournament)!
            map.addAttribute("teamList", userTourneyService.getTeamList());
            return "user/tourney/menu-tourney";
        }


        @GetMapping(value = {"/pp/tourney/team{id}"})
        public String toTeamPage(@PathVariable("id") int id, ModelMap map) {
            TeamDTO teamDTO = userTourneyService.getTeam(id);
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

        @GetMapping(value = {"/pp/tourney/composition{id}"})
        public String toRecruitingCompositionPage(@PathVariable("id") int id, ModelMap map) {
            CompositionDTO compositionDTO = userTourneyService.getComposition(id);
            if (compositionDTO == null) return "redirect:/xxx" + id; //not user's composition

            if (!compositionDTO.getStatus().getCode().equals("COMPOSITION_RECRUITING")) return "redirect:/500"; //TODO: lol wut?

            Integer maxSize = 18;
            Integer currentSize;

            List<PlayerDTO> playerDTOList = userTourneyService.getPlayers(compositionDTO);
            if (playerDTOList != null) currentSize = playerDTOList.size(); else currentSize = 0;

            char[] chars = compositionDTO.getTimegrid().toCharArray();
            String[] timegrid = new String[10]; for (int i = 0; i < 10; i++) timegrid[i] = ("" + chars[i]);

            map
                    .addAttribute("maxSize", maxSize)
                    .addAttribute("currentSize", currentSize)

                    .addAttribute("teamDTO", compositionDTO.getTeam())
                    .addAttribute("compositionDTO", compositionDTO)
                    .addAttribute("timegrid", timegrid)

                    .addAttribute("playerDTO", new PlayerDTO())
                    .addAttribute("currentPlayerDTOList", playerDTOList)

                    .addAttribute("isFull", !(currentSize < maxSize));

            return "user/tourney/page-composition-status-recruiting";
        }

        @GetMapping(value = {"/pp/tourney/composition{id}/confirm"})
        public String confirmComposition(@PathVariable("id") int id) {
//            TODO: optimize
            CompositionDTO compositionDTO = userTourneyService.getComposition(id);
            if (compositionDTO == null) return "redirect:/500"; //not user's composition

//            TODO: that's right, huh?
            userTourneyService.confirmComposition(compositionDTO.getId());

            Integer teamID = compositionDTO.getTeam().getId();
            return "redirect:/pp/tourney/team" + teamID;
        }


        @GetMapping(value = {"/pp/tourney/composition{compositionID}/time{time}/{choice}"})
        public String updateTimeGrid(
                @PathVariable("compositionID") int compositionID,
                @PathVariable("time") Integer time, @PathVariable("choice") Character choice
        ) {
//            TODO: optimize
            CompositionDTO compositionDTO = userTourneyService.getComposition(compositionID);
            if (compositionDTO == null) return "redirect:/500"; //not user's composition

            userTourneyService.timeChoice(compositionDTO, time, choice);
            return "redirect:/pp/tourney/composition{compositionID}";
        }


        @PostMapping(value = {"/pp/tourney/composition{id}/player-search"})
        public String searchPlayers(
                @PathVariable("id") int id, ModelMap map,
                @ModelAttribute(value = "playerDTO") PlayerDTO playerDTO
        ) {
//            TODO: add partial search!
            map.addAttribute("foundedPlayerDTOList", userTourneyService.getPlayers(
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
            CompositionDTO compositionDTO = userTourneyService.getComposition(compositionID);
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
            CompositionDTO compositionDTO = userTourneyService.getComposition(compositionID);
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
}