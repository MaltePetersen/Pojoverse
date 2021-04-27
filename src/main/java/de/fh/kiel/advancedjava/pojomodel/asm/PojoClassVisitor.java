package de.fh.kiel.advancedjava.pojomodel.asm;

import de.fh.kiel.advancedjava.pojomodel.model.AttributeInfo;
import lombok.Getter;
import org.objectweb.asm.*;
import java.lang.reflect.Modifier;
import java.util.*;

@Getter
public class PojoClassVisitor extends ClassVisitor {

    private Set<AttributeInfo> attributes;

    public PojoClassVisitor() {
        super(Opcodes.ASM9);
        attributes = new HashSet<>();
    }

    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value){
        //This needs to be tested because the vistor will read a field with static two times once without acces modifiers and once more with
        Optional<AttributeInfo> attributeAlreadyExists = this.attributes.stream().filter(attribute -> attribute.getName().equals(name)).peek(attribute -> attribute.setAccessModifier(attribute.getAccessModifier() + " " + Modifier.toString(access))).findAny();
        if(attributeAlreadyExists.isEmpty()){
            desc = toJavaURI(transformPrimtives(desc));
            this.attributes.add(new AttributeInfo(name, desc, Modifier.toString(access), parseClassName(desc), parsePackageName(desc)));
        }
        return super.visitField(access,name,desc,signature,value);
    }
    private String toJavaURI(String path){
        path = path.substring(0,path.lastIndexOf(";"));
        path = path.replaceAll("/", ".");
        return path.substring(1);
    }
    private String transformPrimtives(String desc){
        return switch (desc) {
            case "Z" -> "Ljava.lang.Boolean;";
            case "B" -> "Ljava.lang.Byte;";
            case "C" -> "Ljava.lang.Character;";
            case "D" -> "Ljava.lang.Double;";
            case "F" -> "Ljava.lang.Float;";
            case "I" -> "Ljava.lang.Integer;";
            case "J" -> "Ljava.lang.Long;";
            case "S" -> "Ljava.lang.Short;";
            default -> desc;
        };
    }
    private String parseClassName(String completePath){
        return completePath.substring(completePath.lastIndexOf('.') + 1);
    }
    private String parsePackageName(String completePath){
        return completePath.substring(0,completePath.lastIndexOf('.'));
    }
}
