package ru.vldf.sportsportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.vldf.sportsportal.dto.lease.PlaygroundDTO;
import ru.vldf.sportsportal.dto.tourney.*;
import ru.vldf.sportsportal.dto.common.UserDTO;
import ru.vldf.sportsportal.service.AuthService;
import ru.vldf.sportsportal.service.LeaseService;
import ru.vldf.sportsportal.service.user.specialized.TourneyUserService;
import ru.vldf.sportsportal.service.admin.specialized.TourneyAdminService;
import ru.vldf.sportsportal.service.admin.AdminService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

//    TODO: tmp solution! move to 'TOURNEY'!
    private TourneyUserService userTourneyService;

//    TODO: tmp solution! move to 'TOURNEY'!
    @Autowired
    public void setUserTourneyService(TourneyUserService userTourneyService) {
        this.userTourneyService = userTourneyService;
    }


    @ModelAttribute("authUser")
    public UserDTO getAuthUser() {
        return authService.getAuthUser();
    }

    @GetMapping(value = {"/personalpage", "/pp"})
    public String toPersonalPage(ModelMap map) {
        map.addAttribute("teamList", userTourneyService.getTeamList()); //TODO: tmp solution! remove this!
        return "user/personalpage";
    }


    @Controller //ADMIN
    public class UserAdminController {

        private TourneyUserService tourneyUserService;
        private AdminService adminService;
        private TourneyAdminService tourneyAdminService;

        @Autowired
        public void setTourneyUserService(TourneyUserService tourneyUserService) {
            this.tourneyUserService = tourneyUserService;
        }

        @Autowired
        public void setAdminService(AdminService adminService) {
            this.adminService = adminService;
        }

        @Autowired
        public void setTourneyAdminService(TourneyAdminService tourneyAdminService) {
            this.tourneyAdminService = tourneyAdminService;
        }


        @ModelAttribute("authUser")
        public UserDTO getAuthUser() {
            return UserController.this.getAuthUser();
        }

        @GetMapping(value = {"/pp/admin"})
        public String toAdminMenu(ModelMap map) {
            map
                    .addAttribute("uUsersNum", adminService.getUnconfirmedUsersNum())
                    .addAttribute("uTeamsNum", tourneyAdminService.getUnconfirmedTeamsNum());

            return "user/admin/menu-admin";
        }

//    ----------------------------------------------------------------------------------
//    --- USER

        @GetMapping(value = {"/pp/admin/check-user"})
        public String toCheckUserPage(ModelMap map) {
            UserDTO user = adminService.getFirstUnconfirmedUser(); //TODO: lol wtf dude? add pagination!
            if (user == null) return "redirect:/pp/admin";

            List<PlayerDTO> duplicates
                    = adminService.getDuplicates(user);
            map
                    .addAttribute("uUser", user)
                    .addAttribute("duplicates", duplicates);

            return "user/admin/page-check-user";
        }

        @GetMapping(value = {"/pp/admin/check-user/user{id}"})
        public String toCheckUserPage(@PathVariable("id") int id, ModelMap map) {
            UserDTO user = adminService.getUser(id);
            if (user == null) return "redirect:/404";

            List<PlayerDTO> duplicates
                    = adminService.getDuplicates(user);
            map
                    .addAttribute("uUser", user)
                    .addAttribute("duplicates", duplicates);

            return "user/admin/page-check-user";
        }


        @GetMapping(value = {"/pp/admin/check-user/user{id}/confirm"})
        public String confirmUser(@PathVariable("id") int id) {
            adminService.confirmUser(id);
            return "redirect:/pp/admin/check-user";
        }

        @GetMapping(value = {"/pp/admin/check-user/user{id}/reject"})
        public String rejectUser(@PathVariable("id") int id) {
            adminService.rejectUser(id);
            return "redirect:/pp/admin/check-user";
        }

        @GetMapping(value = {"/pp/admin/check-user/duplicate{id}/delete"})
        public String deleteDuplicate(@PathVariable("id") int id) {
            adminService.deleteDuplicate(id);
            return "redirect:/pp/admin/check-user";
        }

        @GetMapping(value = "/pp/admin/check-user/user{userID}/bind/duplicate{duplicateID}")
        public String bindDuplicate(@PathVariable("userID") int userID, @PathVariable("duplicateID") int duplicateID) {
            adminService.bindUser(userID, duplicateID);
            return "redirect:/pp/admin/check-user";
        }

//    ----------------------------------------------------------------------------------
//    --- TOURNEY

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
        public String createTeam(@PathVariable("id") int id, @ModelAttribute(value="teamDTO") TeamDTO teamDTO) {
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
            List<GameDTO> gameList = tourneyAdminService.getNextGames(tourneyDTO);

            map
                    .addAttribute("tourneyDTO", tourneyDTO)
                    .addAttribute("compositionList", compositionList)
                    .addAttribute("gameList", gameList);


            int status = tourneyDTO.getStatus().getId();
            switch (status) {
                case 1: return "user/admin/page-tourney-status-formed"; //TOURNEY_FORMED
                case 2: return "user/admin/page-tourney-status-timeup"; //TOURNEY_TIMEUP

                default: return "redirect:/xxx{id}"; //TODO: to tourney page!
            }
        }

        @GetMapping(value = {"/pp/admin/tourney/tourney{id}/protocol"})
        public String toProtocolAllPage(@PathVariable("id") int id, ModelMap map) {
            TourneyDTO tourneyDTO = tourneyAdminService.getTourney(id);
            List<GameDTO> gameList = tourneyAdminService.getNextGames(tourneyDTO);

            List<List<PlayerDTO>> redPlayerLists = new ArrayList<List<PlayerDTO>>();
            List<List<PlayerDTO>> bluePlayerLists = new ArrayList<List<PlayerDTO>>();

            List<PlayerDTO> redPlayerList;
            List<PlayerDTO> bluePlayerList;
            for(GameDTO game: gameList) {
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
            map.addAttribute("gameDTO",gameDTO);
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

            List<GameDTO> games = tourneyAdminService.getNextGames(tourneyDTO);
            List<String[]> timegrid = tourneyAdminService.updateTimegrid(games); //TODO: remove

            map
                    .addAttribute("tourneyDTO", tourneyDTO)
                    .addAttribute("gameList", games)
                    .addAttribute("timegrid", timegrid);

            if (tourneyDTO.getStatus().getCode().equals("TOURNEY_FORMED")) return "user/admin/page-tourney-timegrid";
            if (tourneyDTO.getStatus().getCode().equals("TOURNEY_TIMEUP")) return "user/admin/page-tourney-timegrid-done";
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
        public String createTourney(@ModelAttribute(value="tourneyDTO") TourneyDTO tourneyDTO) {
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

            Integer tourNum = compositionDTO.getTourney().getNextTour();
//            TourDTO tourDTO = userTourneyService.getNextTour(compositionDTO.getTourney(), tourNum);

            GameDTO gameDTO = userTourneyService.getRival(id, tourNum);
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
//                    .addAttribute("tourDTO", tourDTO)

                    .addAttribute("teamDTO", compositionDTO.getTeam())
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
}