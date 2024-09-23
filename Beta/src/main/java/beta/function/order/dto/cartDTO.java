package beta.function.order.dto;

public class cartDTO {

    private int cartCode;
    private int userCode;
    private int gameCode;

    public cartDTO() {
    }

    public cartDTO(int cartCode, int userCode, int gameCode) {
        this.cartCode = cartCode;
        this.userCode = userCode;
        this.gameCode = gameCode;
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

    @Override
    public String toString() {
        return "cartDTO{" +
                "cartCode=" + cartCode +
                ", userCode=" + userCode +
                ", gameCode=" + gameCode +
                '}';
    }
}
