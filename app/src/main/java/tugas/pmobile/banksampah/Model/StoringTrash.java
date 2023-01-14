package tugas.pmobile.banksampah.Model;

import java.sql.Timestamp;

public class StoringTrash {
    private Integer id;
    private Account accountId;
    private Trash trashId;
    private Location locationId;
    private Integer weight;
    private Long total;
    private Timestamp createdAt;
    private StoringTrashStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public Trash getTrashId() {
        return trashId;
    }

    public void setTrashId(Trash trashId) {
        this.trashId = trashId;
    }

    public Location getLocationId() {
        return locationId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(StoringTrashStatus status) {
        this.status = status;
    }
}

