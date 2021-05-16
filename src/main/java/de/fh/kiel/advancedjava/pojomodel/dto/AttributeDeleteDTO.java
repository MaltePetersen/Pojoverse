package de.fh.kiel.advancedjava.pojomodel.dto;

public class AttributeDeleteDTO {
    private String className;
    private String packageName;
    private String attributeName;

    public AttributeDeleteDTO(String className, String packageName, String attributeName) {
        this.className = className;
        this.packageName = packageName;
        this.attributeName = attributeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
}
