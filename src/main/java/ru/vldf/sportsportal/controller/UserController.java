package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.tourney.CompositionDTO;
import ru.vldf.sportsportal.dto.tourney.TeamDTO;
import ru.vldf.sportsportal.dto.tourney.PlayerDTO;
import ru.vldf.sportsportal.dto.tourney.TourneyDTO;
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
            if (user == null) return "redirect:/404";

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


        @GetMapping(value = {"/pp/admin/tourney"})
        public String toTourneyCatalogPage(ModelMap map) {
            map.addAttribute("tourneyList", tourneyService.getTourneysList());
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
            map.addAttribute("teamList", userTourneyService.getTeamList());
            return "user/tourney/menu-tourney";
        }


        @GetMapping(value = {"/pp/tourney/team{id}"})
        public String toTeamPage(@PathVariable("id") int id, ModelMap map) {
            TeamDTO teamDTO = userTourneyService.getTeam(id);
            if (teamDTO == null) return "redirect:/xxx" + id; //not user's team

            map.addAttribute("teamDTO", teamDTO);

            int status = teamDTO.getStatus().getId();
            switch (status) {
                case 1: return "user/tourney/page-team-status-unconfirmed"; //TEAM_UNCONFIRMED
                case 3: return "user/tourney/page-team-status-rejected";    //TEAM_REJECTED

//                TODO: composition choice add!
                case 4: return "redirect:/pp/tourney/team{id}/composition"; //TEAM_INVITE

                default: return "redirect:/xxx{id}";
            }
        }

        @GetMapping(value = {"/pp/tourney/team{id}/composition"})
        public String toInvitedTeamPage(@PathVariable("id") int id, ModelMap map) {
            TeamDTO teamDTO = userTourneyService.getTeam(id);
            if (teamDTO == null) return "redirect:/xxx" + id; //not user's team

            List<CompositionDTO> list = userTourneyService.getRecruitingCompositions(id);
            if (list == null) return "redirect:/xxx" + id;  //TODO: workaround! composition choice add!
            CompositionDTO compositionDTO = list.get(0);    //TODO: remove .get(0)! composition choice add!

            Integer maxSize = 18;
            Integer currentSize;

            List<PlayerDTO> playerDTOList = userTourneyService.getPlayers(compositionDTO);
            if (playerDTOList != null) currentSize = playerDTOList.size(); else currentSize = 0;

            map
                    .addAttribute("maxSize", maxSize)
                    .addAttribute("currentSize", currentSize)

                    .addAttribute("teamDTO", teamDTO)
                    .addAttribute("compositionDTO", compositionDTO)

                    .addAttribute("playerDTO", new PlayerDTO())
                    .addAttribute("currentPlayerDTOList", playerDTOList)

                    .addAttribute("isFull", !(currentSize < maxSize));

            return "user/tourney/page-team-status-invited";
        }

        @PostMapping(value = {"/pp/tourney/team{teamID}/composition{compositionID}/player-search"})
        public String searchPlayers(
                @PathVariable("teamID") int teamID, @PathVariable("compositionID") int compositionID,
                ModelMap map, @ModelAttribute(value = "playerDTO") PlayerDTO playerDTO
        ) {
//            TODO: add partial search!
            map.addAttribute("foundedPlayerDTOList", userTourneyService.getPlayers(
                    playerDTO.getName(),
                    playerDTO.getSurname(),
                    playerDTO.getPatronymic()
            ));

            return toInvitedTeamPage(teamID, map); //TODO: compositionID?
        }


        @GetMapping(value = {"/pp/tourney/team{teamID}/composition{compositionID}/player{playerID}/add"})
        public String addPlayer(
                @PathVariable("teamID") int teamID,
                @PathVariable("compositionID") int compositionID,
                @PathVariable("playerID") int playerID
        ) {
            userTourneyService.addPlayerToComposition(compositionID, playerID);
            return "redirect:/pp/tourney/team" + teamID + "/composition"; //TODO: compositionID?
        }

        @GetMapping(value = {"/pp/tourney/team{teamID}/composition{compositionID}/player{playerID}/delete"})
        public String deletePlayer(
                @PathVariable("teamID") int teamID,
                @PathVariable("compositionID") int compositionID,
                @PathVariable("playerID") int playerID
        ) {
            userTourneyService.deletePlayerFromComposition(compositionID, playerID);
            return "redirect:/pp/tourney/team" + teamID + "/composition"; //TODO: compositionID?
        }


        @GetMapping(value = {"/pp/tourney/composition{compositionID}/confirm"})
        public String confirmComposition(@PathVariable("compositionID") int compositionID) {
            userTourneyService.confirmComposition(compositionID);
            return "redirect:/pp/tourney"; //TODO: compositionID?
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