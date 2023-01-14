package tugas.pmobile.banksampah.Model;

public class StoringTrashRequest {
    private Integer accountId;
    private Integer trashId;
    private Integer locationId;
    private Integer weight;

    public StoringTrashRequest(Integer accountId, Integer trashId, Integer locationId, Integer weight) {
        this.accountId = accountId;
        this.trashId = trashId;
        this.locationId = locationId;
        this.weight = weight;
    }
}
