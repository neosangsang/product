
package rental;

public class Ordered extends AbstractEvent {

    private Long id;
    private Long productId;
    private Integer qty;
    private String contractDate;
    private String period;
    private Integer rentalPrice;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStartYmd() {
        return contractDate;
    }

    public void setStartYmd(String contractDate) {
        this.contractDate = contractDate;
    }
    public String getEndYmd() {
        return period;
    }

    public void setEndYmd(String period) {
        this.period = period;
    }
    public Integer getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Integer rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
