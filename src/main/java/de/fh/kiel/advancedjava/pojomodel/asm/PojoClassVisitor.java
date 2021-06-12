package de.fh.kiel.advancedjava.pojomodel.asm;

import de.fh.kiel.advancedjava.pojomodel.model.AttributeInfo;
import lombok.Getter;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.Opcodes;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public class PojoClassVisitor extends ClassVisitor {

    private final Set<AttributeInfo> attributes;

    public PojoClassVisitor() {
        super(Opcodes.ASM9);
        attributes = new HashSet<>();
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        //This needs to be tested because the vistor will read a field with static two times once without acces modifiers and once more with
        Optional<AttributeInfo> attributeAlreadyExists = this.attributes.stream().filter(attribute -> attribute.getName().equals(name)).map(attribute -> {
            attribute.setAccessModifier(attribute.getAccessModifier() + " " + Modifier.toString(access));
            return attribute;
        }).findAny();
        if (attributeAlreadyExists.isEmpty()) {
            desc = toJavaURI(Primitiv.getWrapperByPrimitive(desc));
            this.attributes.add(new AttributeInfo(name, desc, Modifier.toString(access), parseClassName(desc), parsePackageName(desc)));
        }
        return super.visitField(access, name, desc, signature, value);
    }

    private String toJavaURI(String path) {
        path = path.substring(0, path.lastIndexOf(";"));
        path = path.replace("/", ".");
        return path.substring(1);
    }

    private String parseClassName(String completePath) {
        return completePath.substring(completePath.lastIndexOf('.') + 1);
    }

    private String parsePackageName(String completePath) {
        return completePath.substring(0, completePath.lastIndexOf('.'));
    }
}
