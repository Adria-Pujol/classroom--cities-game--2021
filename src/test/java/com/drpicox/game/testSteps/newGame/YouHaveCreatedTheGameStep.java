package com.drpicox.game.testSteps.newGame;

import com.drpicox.game.games.api.GamesApi;
import com.drpicox.game.games.api.NewGameForm;
import com.drpicox.game.players.api.LoginForm;
import com.drpicox.game.players.api.PlayersApi;
import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class YouHaveCreatedTheGameStep extends AbstractPostLineStep {

    private final PlayersApi playersApi;
    private final GamesApi gamesApi;

    public YouHaveCreatedTheGameStep(PlayersApi playersApi, GamesApi gamesApi) {
        this.playersApi = playersApi;
        this.gamesApi = gamesApi;
    }

    @Override
    protected String getRegex() {
        return "you have created the game \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = "leonard";
        var gameName = match[1];

        var login = new LoginForm(playerName, "tbbt12");
        var token = playersApi.login(login).getToken();

        var newGame = new NewGameForm(gameName, token);
        gamesApi.newGame(newGame);
    }
}
