package beta.function.game.dao;

import beta.function.game.dto.GameDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// MyBatis의 Mapper 인터페이스임을 나타내는 org.apache.ibatis.annotation 소소의 어노테이션.
// 이 어노테이션이 정의된 인터페이스는 MyBatis와 연동되어 SQL 매핑을 처리함.
@Mapper
public interface GameMapper {

    List<GameDTO> findAllGame();

    GameDTO findGameByCode(int gameCode);

    void registNewGame(GameDTO newGame);

    void updateGame(GameDTO game);

    void deleteGame(int gameCode);
}
