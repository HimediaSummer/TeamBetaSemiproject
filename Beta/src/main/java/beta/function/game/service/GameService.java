package beta.function.game.service;

import beta.function.game.dao.GameMapper;
import beta.function.game.dto.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    private final GameMapper gameMapper;

    @Autowired
    public GameService(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    public List<GameDTO> findAllGame() {
        return gameMapper.findAllGame();
    }

    public GameDTO findGameByCode(int gameCode) {
        return gameMapper.findGameByCode(gameCode);
    }

    @Transactional
    public void registNewGame(GameDTO newGame) {
        gameMapper.registNewGame(newGame);  // Save everything using MyBatis
    }

    @Transactional
    public void updateGame(GameDTO game) {
        gameMapper.updateGame(game);
    }

    @Transactional
    public void deleteGame(int gameCode) {
        gameMapper.deleteGame(gameCode);
    }
}
