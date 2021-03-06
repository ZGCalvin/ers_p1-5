package com.revature.models;

import com.revature.util.StatusHelper;
import com.revature.util.TypeHelper;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * The base unit of the ERS system. ready to include images
 */

@Entity
@Table(name = "ers_reimbursements")
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "amount")
    private Double amount;

    @Generated(value = GenerationTime.INSERT)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    @Column(name = "submitted", nullable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Timestamp submitted;
    @Column(name = "resolved")
    private Timestamp resolved;
    @Column(name = "description")
    private String description;

    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "receipt", columnDefinition = "bytea")
    private byte[] receipt;
    @Column(name = "author_id")//, insertable = false, updatable=false)
    private int authorId;

//    @Column(name = "resolver_id", columnDefinition = "int4 NULL", insertable = false, updatable=false)
//    @ColumnDefault(value = "NULL")
    @Column(name = "resolver_id", columnDefinition = "int4 NULL",insertable = false, updatable=false)
    private Integer resolverId;
    @Column(name = "reimbursement_status_id")
    //@Convert(converter = ReimbursementTypeConverter.class)
    @Convert(converter = StatusHelper.class)
    private ReimbursementStatus reimbursementStatus;
    @Column(name = "reimbursement_type_id")
    @Convert(converter = TypeHelper.class)
    private ReimbursementType reimbursementType;


    @ManyToOne
    @JoinColumn(name = "resolver_id", referencedColumnName = "id")
    private User user;
//    @ManyToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "id")
//    private User user;


//    @OneToOne
//    @JoinColumn(name = "resolver_id", referencedColumnName = "id")
//    private User user;
//
//    @OneToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "id")
//    private User user2;

//
//    @OneToOne
//    @JoinColumn(name = "resolver_id")
//    private User user;
//
//    @OneToOne
//    @JoinColumn(name = "resolver_id")
//    private User user;

    public Reimbursement() {
        super();
    }

    public Reimbursement(Double amount, String description, int authorId,
                         ReimbursementStatus reimbursementStatus, ReimbursementType reimbursementType) {
        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementType = reimbursementType;
    }

    public Reimbursement(Integer id, Double amount, String description, int authorId,
                         ReimbursementStatus reimbursementStatus, ReimbursementType reimbursementType) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.authorId = authorId;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementType = reimbursementType;
    }

    public Reimbursement(Integer id, Double amount, Timestamp submitted,
                         Timestamp resolved, String description, int authorId, int resolverId,
                         ReimbursementStatus reimbursementStatus, ReimbursementType reimbursementType) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.reimbursementStatus = reimbursementStatus;
        this.reimbursementType = reimbursementType;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }

    public void setResolver(User resolver){
        this.user = resolver;
    }
    public ReimbursementStatus getReimbursementStatus() {
        return reimbursementStatus;
    }

    public void setReimbursementStatus(ReimbursementStatus reimbursementStatus) {
        this.reimbursementStatus = reimbursementStatus;
    }

    public ReimbursementType getReimbursementType() {
        return reimbursementType;
    }

    public void setReimbursementType(ReimbursementType reimbursementType) {
        this.reimbursementType = reimbursementType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reimbursement)) return false;
        Reimbursement that = (Reimbursement) o;
        return authorId == that.authorId &&
                resolverId == that.resolverId &&
                Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(submitted, that.submitted) &&
                Objects.equals(resolved, that.resolved) &&
                Objects.equals(description, that.description) &&
                reimbursementStatus == that.reimbursementStatus &&
                reimbursementType == that.reimbursementType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submitted, resolved, description, authorId, resolverId, reimbursementStatus, reimbursementType);
    }

    @Override
    public String toString() {
        return "id= " + id + " " +' ' + " " +
                " amount= " + amount +' ' +" " +
                " submitted= " + submitted +' ' +" " +
                " resolved= " + resolved +' ' +" " +
                " description= " + description + ' ' +" " +
                " authorId= " + authorId +' ' +" " +
                " resolverId= " + resolverId +' ' +" " +
                " reimbursementStatus= " + reimbursementStatus +' ' +" " +
                " reimbursementType= " + reimbursementType ;
    }
}
