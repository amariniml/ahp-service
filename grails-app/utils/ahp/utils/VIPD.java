package ahp.utils;

public class VIPD {
    private int id;
    private String description;
    private double localPriority;
    private double globalPriority;

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

    public double getLocalPriority() {
        return localPriority;
    }

    public void setLocalPriority(double localPriority) {
        this.localPriority = localPriority;
    }

    public double getGlobalPriority() {
        return globalPriority;
    }

    public void setGlobalPriority(double globalPriority) {
        this.globalPriority = globalPriority;
    }
}
