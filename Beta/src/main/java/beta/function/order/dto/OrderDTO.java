package beta.function.order.dto;

import beta.function.game.dto.GameDTO;
import beta.function.order.dto.PaymentDTO;

public class OrderDTO {
    private int orderCode;
    private int gameCode;
    private int cartCode;
    private int userCode;
    private int paymentCode;
    private GameDTO gameDTO;
    private PaymentDTO paymentDTO;
    private CartDTO cartDTO;

    public OrderDTO() {
    }

    public OrderDTO(int orderCode, int gameCode, int cartCode, int userCode, int paymentCode, GameDTO gameDTO, PaymentDTO paymentDTO, CartDTO cartDTO) {
        this.orderCode = orderCode;
        this.gameCode = gameCode;
        this.cartCode = cartCode;
        this.userCode = userCode;
        this.paymentCode = paymentCode;
        this.gameDTO = gameDTO;
        this.paymentDTO = paymentDTO;
        this.cartDTO = cartDTO;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public int getGameCode() {
        return gameCode;
    }

    public void setGameCode(int gameCode) {
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

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public GameDTO getGameDTO() {
        return gameDTO;
    }

    public void setGameDTO(GameDTO gameDTO) {
        this.gameDTO = gameDTO;
    }

    public PaymentDTO getPaymentDTO() {
        return paymentDTO;
    }

    public void setPaymentDTO(PaymentDTO paymentDTO) {
        this.paymentDTO = paymentDTO;
    }

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderCode=" + orderCode +
                ", gameCode=" + gameCode +
                ", cartCode=" + cartCode +
                ", userCode=" + userCode +
                ", paymentCode=" + paymentCode +
                ", gameDTO=" + gameDTO +
                ", paymentDTO=" + paymentDTO +
                ", cartDTO=" + cartDTO +
                '}';
    }
}
