package beta.function.order.dto;

import beta.function.account.dto.AccountDTO;
import beta.function.game.dto.GameDTO;

import java.sql.Date;
import java.util.List;

public class PaymentDTO {

    private int paymentCode;
    private Date paymentDate;
    private int amount;
    private int userCode;
    private List<CartDTO> cartDTO;

    public PaymentDTO() {
    }

    public PaymentDTO(int paymentCode, Date paymentDate, int amount, int userCode, List<CartDTO> cartDTO) {
        this.paymentCode = paymentCode;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.userCode = userCode;
        this.cartDTO = cartDTO;
    }

    public int getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(int paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public List<CartDTO> getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(List<CartDTO> cartDTO) {
        this.cartDTO = cartDTO;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "paymentCode=" + paymentCode +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", userCode=" + userCode +
                ", cartDTO=" + cartDTO +
                '}';
    }
}


