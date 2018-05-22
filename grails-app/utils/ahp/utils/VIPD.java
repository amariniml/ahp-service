package ahp.utils;

public class VIPD {
    private int id;
    private String description;
    private float localPriority;
    private float globalPriority;

    public VIPD(int id, String description) {
        this.id = id;
        this.description = description;
        this.localPriority = 0;
        this.globalPriority = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLocalPriority() {
        return localPriority;
    }

    public void setLocalPriority(float localPriority) {
        this.localPriority = localPriority;
    }

    public float getGlobalPriority() {
        return globalPriority;
    }

    public void setGlobalPriority(float globalPriority) {
        this.globalPriority = globalPriority;
    }
}
