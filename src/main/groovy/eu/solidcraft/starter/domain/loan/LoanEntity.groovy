package eu.solidcraft.starter.domain.loan

import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.SequenceGenerator
import javax.validation.constraints.NotNull

@Entity
class LoanEntity {
    @Id
    @SequenceGenerator(name = "LoanSequence", sequenceName = "SEQ_LOAN_PK", initialValue=10000)
    @GeneratedValue(generator = "LoanSequence")
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal interest;

    @NotNull
    private Date startDate;

    @NotNull
    private Integer periodInWeeks;

    private Integer extensions;

    protected LoanEntity() {
    }

    LoanEntity(String username, BigDecimal amount, BigDecimal interest, Date startDate, Integer periodInWeeks) {
        this.username = username
        this.amount = amount
        this.interest = interest
        this.startDate = startDate
        this.periodInWeeks = periodInWeeks
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    BigDecimal getAmount() {
        return amount
    }

    void setAmount(BigDecimal amount) {
        this.amount = amount
    }

    BigDecimal getInterest() {
        return interest
    }

    void setInterest(BigDecimal interest) {
        this.interest = interest
    }

    Date getStartDate() {
        return startDate
    }

    void setStartDate(Date startDate) {
        this.startDate = startDate
    }

    Integer getPeriodInWeeks() {
        return periodInWeeks
    }

    void setPeriodInWeeks(Integer periodInWeeks) {
        this.periodInWeeks = periodInWeeks
    }

    Integer getExtensions() {
        return extensions
    }

    void setExtensions(Integer extensions) {
        this.extensions = extensions
    }
}
