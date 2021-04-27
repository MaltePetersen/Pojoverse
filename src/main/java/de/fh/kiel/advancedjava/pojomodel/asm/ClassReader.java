package de.fh.kiel.advancedjava.pojomodel.asm;


public class ClassReader extends org.objectweb.asm.ClassReader {

    public ClassReader(byte[] classFile) {
        super(classFile);
    }

    @Override
    public String getClassName() {
        return toOnlyClassName(toJavaURI(super.getClassName()));
    }
    public String getPackageName(){
        return toOnlyPackageName(toJavaURI(super.getClassName()));
    }
    public String getCompletePath(){
        return toJavaURI(super.getClassName());
    }

    @Override
    public String getSuperName() {
        return toOnlyClassName(toJavaURI(super.getSuperName()));
    }
    public String getSuperPackageName(){
        return toOnlyPackageName(toJavaURI(super.getSuperName()));
    }
    public String getSuperCompletePath(){
        return toJavaURI(super.getSuperName());
    }

    private String toJavaURI(String path){
        return path.replaceAll("/", ".");
    }
    private String toOnlyClassName(String completePath){
        if(completePath.lastIndexOf(".") != -1)
            return completePath.substring(completePath.lastIndexOf(".") + 1);
        return completePath;
    }
    private String toOnlyPackageName(String completePath){
        if(completePath.lastIndexOf(".") != -1)
            return completePath.substring(0, completePath.lastIndexOf("."));
        return completePath;
    }


}
