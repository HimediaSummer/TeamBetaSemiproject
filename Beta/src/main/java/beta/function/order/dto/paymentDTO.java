package beta.function.order.dto;

import java.sql.Date;

public class paymentDTO {

    private int paymentCode;
    private Date paymentDate;

    public paymentDTO() {
    }

    public paymentDTO(int paymentCode, Date paymentDate) {
        this.paymentCode = paymentCode;
        this.paymentDate = paymentDate;
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

    @Override
    public String toString() {
        return "paymentDTO{" +
                "paymentCode=" + paymentCode +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
