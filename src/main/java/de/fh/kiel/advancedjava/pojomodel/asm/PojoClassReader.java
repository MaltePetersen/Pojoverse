package de.fh.kiel.advancedjava.pojomodel.asm;


import java.util.Arrays;


public class PojoClassReader extends org.objectweb.asm.ClassReader {

    public PojoClassReader(byte[] classFile) {
        super(classFile);
    }

    @Override
    public String getClassName() {
        return toOnlyClassName(toJavaURI(super.getClassName()));
    }

    public String getPackageName() {
        return toOnlyPackageName(toJavaURI(super.getClassName()));
    }

    public String getCompletePath() {
        return toJavaURI(super.getClassName());
    }

    @Override
    public String getSuperName() {
        return toOnlyClassName(toJavaURI(super.getSuperName()));
    }

    public String getSuperPackageName() {
        return toOnlyPackageName(toJavaURI(super.getSuperName()));
    }

    public String getSuperCompletePath() {
        return toJavaURI(super.getSuperName());
    }

    @Override
    public String[] getInterfaces() {
        return Arrays.stream(super.getInterfaces()).map(this::toJavaURI).map(this::toOnlyClassName).toArray(String[]::new);
    }

    private String toJavaURI(String path) {
        return path.replace("/", ".");
    }

    private String toOnlyClassName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(completePath.lastIndexOf(".") + 1);
        return completePath;
    }

    private String toOnlyPackageName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(0, completePath.lastIndexOf("."));
        return completePath;
    }


}
