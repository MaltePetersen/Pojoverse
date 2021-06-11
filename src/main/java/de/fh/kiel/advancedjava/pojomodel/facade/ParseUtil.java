package de.fh.kiel.advancedjava.pojomodel.facade;

/**
 * Ich hab hier bewusst die Entscheidung getroffen diese Klasse nicht in PojoFacadeService zu packen, trotzdass sie nur dort genutzt wird.
 * Der Code in PojoFacadeService hat eine hohe Komplexität, ein Entwickler soll sich nur mit diesem beschäftigen, deshalb wurden alle util
 * Methoden der Facade in diese Klasse ausgelagert.
 */
public class ParseUtil {

    private ParseUtil(){}

    protected static String parsePackageName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(0, completePath.lastIndexOf("."));
        return completePath;
    }

    protected static String parseClassName(String completePath) {
        if (completePath.lastIndexOf(".") != -1)
            return completePath.substring(completePath.lastIndexOf(".") + 1);
        return completePath;
    }


}

