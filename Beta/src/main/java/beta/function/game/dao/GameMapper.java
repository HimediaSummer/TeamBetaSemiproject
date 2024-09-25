package beta.function.game.dao;

import beta.function.game.dto.GameDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameMapper {

    List<GameDTO> findAllGame();

    GameDTO findGameByCode(int gameCode);

    void registNewGame(GameDTO newGame);

    void updateGame(GameDTO game);

    void deleteGame(int gameCode);
}
