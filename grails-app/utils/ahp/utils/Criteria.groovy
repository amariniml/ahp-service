package ahp.utils

class Criteria{
    private String name
    private float localPriority
    private float globalPriority

    Criteria(String name,float localPriority, float globalPriority) {
        this.name = name
        this.localPriority = localPriority
        this.globalPriority = globalPriority
    }

    float getLocalPriority() {
        return localPriority
    }

    void setLocalPriority(float localPriority) {
        this.localPriority = localPriority
    }

    float getGlobalPriority() {
        return globalPriority
    }

    void setGlobalPriority(float globalPriority) {
        this.globalPriority = globalPriority
    }
}
