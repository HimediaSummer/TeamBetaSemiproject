package beta.function.order.dto;

import beta.function.account.dto.AccountDTO;
import beta.function.game.dto.GameDTO;

public class CartDTO {

    private int cartCode;
    private int userCode;
    private int gameCode;
    private GameDTO gameDTO;
    private AccountDTO accountDTO;

    public CartDTO() {
    }

    public CartDTO(int cartCode, int userCode, int gameCode, GameDTO gameDTO, AccountDTO accountDTO) {
        this.cartCode = cartCode;
        this.userCode = userCode;
        this.gameCode = gameCode;
        this.gameDTO = gameDTO;
        this.accountDTO = accountDTO;
    }

    public CartDTO(AccountDTO member, GameDTO game, int gamePrice) {
    }

    public int getCartCode() {
        return cartCode;
    }

    public void setCartCode(int cartCode) {
        this.cartCode = cartCode;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public int getGameCode() {
        return gameCode;
    }

    public void setGameCode(int gameCode) {
        this.gameCode = gameCode;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartCode=" + cartCode +
                ", userCode=" + userCode +
                ", gameCode=" + gameCode +
                ", gameDTO=" + gameDTO +
                ", accountDTO=" + accountDTO +
                '}';
    }
}
