package beta.function.order.dto;

import beta.function.game.dto.GameDTO;

public class CartDTO {

    private int cartCode;
    private int userCode;
    private int gameCode;
    private char addCart;
    private GameDTO gameDTO;

    public CartDTO() {
    }

    public CartDTO(int cartCode, int userCode, int gameCode, char addCart, GameDTO gameDTO) {
        this.cartCode = cartCode;
        this.userCode = userCode;
        this.gameCode = gameCode;
        this.addCart = addCart;
        this.gameDTO = gameDTO;
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

    public char getAddCart() {
        return addCart;
    }

    public void setAddCart(char addCart) {
        this.addCart = addCart;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartCode=" + cartCode +
                ", userCode=" + userCode +
                ", gameCode=" + gameCode +
                ", addCart=" + addCart +
                ", gameDTO=" + gameDTO +
                '}';
    }
}
